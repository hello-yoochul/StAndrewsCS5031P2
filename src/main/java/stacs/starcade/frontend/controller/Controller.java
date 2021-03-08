package stacs.starcade.frontend.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.model.IFrontendModel;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

import static javax.swing.SwingUtilities.getRoot;
import static stacs.starcade.frontend.model.IFrontendModel.*;


/**
 *
 */
public class Controller implements IController {

    private IFrontendModel model;
    private HttpClient client;

    HttpPost postRequest;
    HttpResponse response;

    final static String basicServerAddress = "http://localhost:8080/";
    final static String registerPlayerParam = "/registerPlayer";
    final static String nextRoundParam = "/nextRound";
    final static String endRoundParam = "/endRound";
    final static String getLeaderboardParam = "/getLeaderboard";

    /**
     * FrontendController constructor.
     */
    public Controller(IFrontendModel model) {
        this.model = model;
        client = HttpClientBuilder.create().build();
//        register();
        //TODO: a leader board should be loaded at the beginning
//        getLeaderBoard();
    }

    private void getLeaderBoard() {
        // Ignore the button event if the game has already started
        model.setGameStatus(GameStatus.RUNNING);

        postRequest = new HttpPost(basicServerAddress + getLeaderboardParam);
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Connection", "keep-alive");
        postRequest.setHeader("Content-Type", "application/json");

        try {
            response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                //TODO: get leaderboard from server and paint it on the right panel (infoPane)
                System.out.println(response);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("error", e);
        }
    }

//    private void register() {
//        int playerID = 0;
//        HttpGet clientID = new HttpGet(basicServerAddress + registerPlayerParam);
//        // Read playerID from request
//        this.model.setPlayerId(playerID);
//    }

    /**
     * Start a game.
     */
    @Override
    public void startGame() {
        // Ignore the button event if the game has already started
        if (model.getStatus() != GameStatus.RUNNING) {
            model.setGameStatus(GameStatus.RUNNING);

            System.out.println("name: " + model.getPlayerName());

            // TODO: remove the "anyname" and get the input of client name
            postRequest = new HttpPost(basicServerAddress + registerPlayerParam + "/" + model.getPlayerName());
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json");

            try {
                response = client.execute(postRequest);
                if (response.getStatusLine().getStatusCode() == 200) {
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    String body = handler.handleResponse(response);
                    model.setPlayerId(Integer.parseInt(body));
                    setUpCards();
                } else {
                    System.out.println("response is error : " + response.getStatusLine().getStatusCode());
                }
            } catch (IOException e) {
                throw new RuntimeException("error", e);
            }
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller(new FrontendModel());
        controller.setUpCards();
    }

    /**
     * Set up the 12 cards.
     */
    @Override
    public void setUpCards() {
        List<ICard> cards = new ArrayList<>();

        postRequest = new HttpPost(basicServerAddress + nextRoundParam + "/" + model.getPlayerId());
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Connection", "keep-alive");
        postRequest.setHeader("Content-Type", "application/json");

        HttpResponse response = null;
        try {
            response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                // obtained from  https://stackoverflow.com/questions/39764621/parse-json-without-key/39764907
                try {
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    String body = handler.handleResponse(response);
                    JSONArray jsonArray = new JSONArray(body);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        ICard card = new Card();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Iterator<?> keys = jsonObject.keys();

                        card.setNumber(ICard.Number.valueOf(jsonObject.getString("number")));
                        card.setColour(ICard.Colour.valueOf(jsonObject.getString("colour")));
                        card.setShape(ICard.Shape.valueOf(jsonObject.getString("shape")));
                        card.setLineStyle(ICard.LineStyle.valueOf(jsonObject.getString("lineStyle")));

                        cards.add(card);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("(response error) status code : " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException /*| URISyntaxException*/ e) {
            e.printStackTrace();
        }

        model.setUpCard(cards);
        model.setGameStatus(GameStatus.RUNNING);
    }

//    /**
//     * Check if the three cards are set.
//     *
//     * @return true if it is set
//     */
//    @Override
//    public boolean isSet() {
//        // TODO: 1 check if three cards are selected
//        // TODO: 2 get the selected card from model
//        // TODO: 3 and send them to server, e.g., http://localhost:8080/game/isSet?firstCard=1111&secondCard=1221&thirdCard=3113
//
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://localhost:8080/isSet")).build();
//
////        try {
////            // TODO: 4 From the request above, check if the server sends the info on it is set
////            java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
////            System.out.println(response.body());
////        } catch (IOException | InterruptedException e) {
////            throw new RuntimeException("error", e);
////        }
//
//        // TODO: 5 if it is set remove the cards on the board and repaint, if not, show dialog message "not set"
//
//        return false;
//    }

    /**
     * Select a card. Player will invoke this method 3 times to choose three cards.
     *
     * @param card a card to check if selected cards are set
     */
    @Override
    public void selectCard(ICard card) {
        model.selectCard(card);
    }

    /**
     * Set the current unique player id.
     */
    @Override
    public void setPlayerId() {
        // TODO: Get player ID from Server and store it the Model, FrontendModel
    }

    /**
     * Validate the three cards if it is set.
     *
     * @param threeCards the three cards to be validated
     */
    @Override
    public void validateCards(ICard[] threeCards) throws IllegalArgumentException {
        int logSize = model.getSetsLog().size(); // Get size of current setsLog

        // Validate input
        if (threeCards.length != 3) {
            throw new IllegalArgumentException("A set can only consist of exactly three cards!");
        } else if (logSize > 0 && alreadyLogged(threeCards)) {
            throw new IllegalArgumentException("Selected set has already been logged.");
        }

        (new JLabel("Set!!")).setHorizontalAlignment(SwingConstants.CENTER);

        if (Checks.isSet(threeCards)) {
            JOptionPane.showMessageDialog(null, "Set!!", "VALIDATION RESULT", JOptionPane.PLAIN_MESSAGE);
            // Check whether owner (player) of card objects has already logged this set of cards
            model.setSetsLog(threeCards); // Trigger model to log valid set of cards
        } else {
            JOptionPane.showMessageDialog(null, "No Set...", "VALIDATION RESULT", JOptionPane.ERROR_MESSAGE);
            // TODO: remove the selected cards
        }
    }

    /**
     * Checks whether a set that has been selected in the UI has already been logged.
     *
     * @param threeCards in the UI selected set,
     * @return true if given set has already been logged.
     */
    private boolean alreadyLogged(ICard[] threeCards) {
        boolean alreadyLogged = false;
        ArrayList<ICard[]> sL = model.getSetsLog();

        // Change alreadyLogged to true, if same combination of cards can be found in setsLog of Model
        for (int i = 0; i < sL.size(); i++) {
            ICard[] currentSet = sL.get(i);
            if (setsEqual(threeCards, currentSet)) {
                alreadyLogged = true;
                break;
            }
        }

        return alreadyLogged;
    }

    /**
     * Checks whether two given sets of three cards equal.
     *
     * @param set1 first given set of three cards
     * @param set2 second given set of three cards
     * @return true if sets contain the same card objects
     */
    private boolean setsEqual(ICard[] set1, ICard[] set2) {
        boolean setsEqual = false;
        int counter = 0;
        int successCondition = 3;
        // For each ICard in set1 check, if it can be found in set2
        for (int i = 0; i < set1.length; i++) {
            for (int j = 0; j < set2.length; j++) {
                if (set1[i].equals(set2[j])) {
                    counter++; // Increment if ICard from set1 could be found in set2
                    break;
                }
            }
        }
        // If each ICard from set1 could also be found in set2, counter equals 3
        if (counter == successCondition) {
            setsEqual = true;
        }
        return setsEqual;
    }

    /**
     * End the round.
     */
    @Override
    public void endRound() {
        if (model.getStatus() != GameStatus.RUNNING) {
            model.setGameStatus(GameStatus.RUNNING);

            // TODO: remove the "anyname" and get the input of client name
            postRequest = new HttpPost(basicServerAddress + endRoundParam + "/" + model.getPlayerId());
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json");

            try {
                response = client.execute(postRequest);
                if (response.getStatusLine().getStatusCode() == 200) {
                    //
                } else {
                    System.out.println("response is error : " + response.getStatusLine().getStatusCode());
                }
            } catch (IOException e) {
                throw new RuntimeException("error", e);
            }
        }
    }
}

