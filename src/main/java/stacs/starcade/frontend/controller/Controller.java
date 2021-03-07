package stacs.starcade.frontend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stacs.starcade.backend.impl.IPlayer;
import stacs.starcade.backend.impl.Model;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.model.IFrontendModel;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.*;

import static stacs.starcade.shared.Checks.*;
import static stacs.starcade.frontend.model.IFrontendModel.*;
import static stacs.starcade.shared.ICard.Colour.RED;
import static stacs.starcade.shared.ICard.LineStyle.DOTTED;
import static stacs.starcade.shared.ICard.Number.ONE;
import static stacs.starcade.shared.ICard.Shape.TRIANGLE;


/**
 *
 */
public class Controller implements IController {
    String basicServerAddress = "http://localhost:8080/";

    private IFrontendModel model;
    private HttpClient client;

    HttpPost postRequest;
    HttpResponse response;

    String serverBasicAddress = "https://localhost:8080/";
    final static String startGameParam = "/startGame";
    final static String getLeaderboardParam = "/getLeaderboard";
    final static String getCardsParam = "/getCards";

    /**
     * FrontendController constructor.
     */
    public Controller(IFrontendModel model) {
        this.model = model;
        client = HttpClientBuilder.create().build();
    }

    /**
     * Start a game.
     */
    @Override
    public void startGame() {
        // Ignore the button event if the game has already started
        if (model.getStatus() != GameStatus.RUNNING) {
            model.setGameStatus(GameStatus.RUNNING);

            // get the unique player id from the server and store it to Model variable
            postRequest = new HttpPost(basicServerAddress + startGameParam);
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
    }

    /**
     * Set up the 12 cards.
     */
    @Override
    public void setUpCards() {
        List<ICard> cards = new ArrayList<>();

        postRequest = new HttpPost(basicServerAddress + "/getCards/1");
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Connection", "keep-alive");
        postRequest.setHeader("Content-Type", "application/json");

        // TODO: Get the 12 card from server and paint it on the Swing View

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
    }

//    /**
//     * Check if the three cards are set.
//     *
//     * @return true if it is set
//     */
//    @Override
//    public boolean isSet() {
//        // TODO: 1 check if three cards are chosen
//        // TODO: 2 get the chosen card from model
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
     * Pause the game.
     */
    @Override
    public void pauseGame() {
    }

    @Override
    public void resumeGame() {
    }

    /**
     * Select a card. Player will invoke this method 3 times to choose three cards.
     *
     * @param card a card to check if chosen cards are set
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

    @Override
    public void validateCards(ICard[] threeCards) throws IllegalArgumentException {
        int logSize = model.getSetsLog().size(); // Get size of current setsLog

        // Validate input
        if (threeCards.length != 3) {
            throw new IllegalArgumentException("A set can only consist of exactly three cards!");
        } else if (logSize > 0 && alreadyLogged(threeCards)) {
            throw new IllegalArgumentException("Selected set has already been logged.");
        }

        if (Checks.isSet(threeCards)) {
            // Check whether owner (player) of card objects has already logged this set of cards
            model.setSetsLog(threeCards); // Trigger model to log valid set of cards
        } else {
            // Trigger Error Message that three given cards do not make a set
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
}
