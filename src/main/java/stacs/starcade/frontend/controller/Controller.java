package stacs.starcade.frontend.controller;

import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import stacs.starcade.frontend.model.IClientModel;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static stacs.starcade.frontend.model.IClientModel.*;


/**
 *
 */
public class Controller implements IController {
    /**
     * Maximum number of sets.
     */
    private static final int MAX_NUM_SETS = 5;
    /**
     * Number of column for leaderboard.
     */
    public static final int NUM_COLS = 3;

    /**
     * Basic server address.
     */
    final static String basicServerAddress = "http://localhost:8080/";
    /**
     * Param for registering the player.
     */
    final static String registerPlayerParam = "/registerPlayer";
    /**
     * Param for next round.
     */
    final static String nextRoundParam = "/nextRound";
    /**
     * Param for end round.
     */
    final static String endRoundParam = "/endRound";
    /**
     * Param for getting leaderboard.
     */
    final static String getLeaderboardParam = "/getLeaderboard";
    /**
     * Param for disconnecting the play.
     */
    final static String disconnectParam = "/disconnect";

    private IClientModel model;
    private HttpClient client;

    /**
     * FrontendController constructor.
     */
    public Controller(IClientModel model) {
        this.model = model;
        client = HttpClientBuilder.create().build();
    }

    /**
     * Registers the client with the server and gets a unique player id.
     */
    @Override
    public void register() {
        HttpGet postRequest = new HttpGet(basicServerAddress + registerPlayerParam + "/" + model.getPlayerName());
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Connection", "keep-alive");
        postRequest.setHeader("Content-Type", "application/json");

        try {
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                model.setPlayerId(Integer.parseInt(body));
            } else {
                System.out.println("(register) response is error : " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("error", e);
        }

        // Initial request of leaderboard so that leaderboard table can be set up in view
        requestLeaderBoard();
    }

    /**
     * Continuously polls for updates on leaderboard.
     */
    public void pollForLeaderBoard() {
        /**
         * Inner class for timer running in another Thread.
         */
        class LeaderBoardPoller implements Runnable {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    requestLeaderBoard();
                } while (true);
            }
        }
        LeaderBoardPoller lbp = new LeaderBoardPoller();
        lbp.run();
    }

    /**
     * Requests current leaderboard from server.
     */
    private void requestLeaderBoard() {
        HttpGet getRequest = new HttpGet(basicServerAddress + getLeaderboardParam);
        getRequest.setHeader("Accept", "application/json");
        getRequest.setHeader("Connection", "keep-alive");
        getRequest.setHeader("Content-Type", "application/json");

        try {
            HttpResponse response = client.execute(getRequest);
            if (response.getStatusLine().getStatusCode() == 200) {

                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                JSONArray jsonArray = new JSONArray(body);

                int rows = jsonArray.length();
                int cols = NUM_COLS;
                String[][] entries = new String[rows][cols];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    entries[i][0] = jsonObject.getString("playerName");
                    entries[i][1] = ((Integer) jsonObject.getInt("round")).toString();

                    long sec = jsonObject.getLong("avgTime");
                    BigDecimal seconds = new BigDecimal(sec);
                    entries[i][2] = parseSeconds(seconds);
                }

                model.setLeaderBoard(entries);
            } else {
                System.out.println("(requestLeaderBoard) response is error : " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("error", e);
        }
    }

    /**
     * Parses a number that represents seconds into a "hh : mm : ss" format
     *
     * @param seconds given number of seconds.
     *
     * @return the formatted number as String
     */
    private String parseSeconds(BigDecimal seconds) {
        long givenSec = seconds.longValue();
        int hrs = (int) givenSec / 3600;
        int remainder = (int) givenSec - hrs * 3600;
        int min = remainder / 60;
        remainder = remainder - min * 60;
        int sec = remainder;

        String formattedTime = format(hrs) + " : " + format(min) + " : " + format(sec);
        return formattedTime;
    }

    /**
     * Parses given number into a 00 number format.
     *
     * @param number any given int
     *
     * @return formatted number as String
     */
    private String format(int number) {
        if (number == 0) {
            return "00";
        } else if (number > 9) {
            return ((Integer) number).toString();
        } else {
            return "0" + number;
        }
    }

    /**
     * Start a game.
     */
    @Override
    public void startGame() {
        // Ignore the button event if the game has already started
        if (model.getStatus() != GameStatus.RUNNING) {
            model.setGameStatus(GameStatus.RUNNING);
            setUpCards();
        }
    }

    /**
     * Set up the 12 cards.
     */
    @Override
    public void setUpCards() {
        List<ICard> cards = new ArrayList<>();

        HttpGet postRequest = new HttpGet(basicServerAddress + nextRoundParam + "/" + model.getPlayerId());
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Connection", "keep-alive");
        postRequest.setHeader("Content-Type", "application/json");

        try {
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                // obtained from:  https://stackoverflow.com/questions/39764621/parse-json-without-key/39764907
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
                System.out.println("(setUpCards) status code : " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("(setUpCards) error", e);
        }

        model.setUpCard(cards);
        model.setGameStatus(GameStatus.RUNNING);
        model.startTimer();
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
            removeSelectedCards(threeCards);
            throw new IllegalArgumentException("Selected set has already been logged.");
        }

        (new JLabel("Set!!")).setHorizontalAlignment(SwingConstants.CENTER);

        if (Checks.isSet(threeCards)) {
            JOptionPane.showMessageDialog(null, "Set!!", "VALIDATION RESULT",
                    JOptionPane.PLAIN_MESSAGE);
            // Check whether owner (player) of card objects has already logged this set of cards
            model.setSetsLog(threeCards); // Trigger model to log valid set of cards
            if (model.getSetsLog().size() == MAX_NUM_SETS) {
                endRound(model.getSetsLog());
                JOptionPane.showMessageDialog(null, "You found all sets. The round is over." +
                        "Congratulations!! ", "VALIDATION RESULT", JOptionPane.PLAIN_MESSAGE);
                model.setSetsLog(null);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Set...", "VALIDATION RESULT",
                    JOptionPane.ERROR_MESSAGE);
            removeSelectedCards(threeCards);
        }
    }

    private void removeSelectedCards(ICard[] threeCards) {
        model.removeSelectedCard(threeCards[0]);
        model.removeSelectedCard(threeCards[1]);
        model.removeSelectedCard(threeCards[2]);
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
    public void endRound(ArrayList<ICard[]> sets) {
        if (model.getStatus() != GameStatus.RUNNING) {
            model.setGameStatus(GameStatus.RUNNING);

            StringBuilder json = new StringBuilder("[");

            for (ICard[] set : sets){
                json.append("[");
                for (ICard card : set){
                    json.append("{");
                    json.append("\"colour\": \"").append(card.getColour()).append("\",");
                    json.append("\"shape\": \"").append(card.getShape()).append("\",");
                    json.append("\"lineStyle\": \"").append(card.getLineStyle()).append("\",");
                    json.append("\"number\": \"").append(card.getNumber()).append("\",");
                    json.append("}");
                    if (card != set[set.length - 1]){
                        json.append(",");
                    }
                }

                json.append("]");

                if (set != sets.get(sets.size() - 1)){
                    json.append(",");
                }
            }
            json.append("]");


            HttpPost postRequest = new HttpPost(basicServerAddress + endRoundParam + "/" + model.getPlayerId());
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json");
            StringEntity entity = null;
            try {
                entity = new StringEntity(json.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            postRequest.setEntity(entity);

            try {
                HttpResponse response = client.execute(postRequest);
                if (response.getStatusLine().getStatusCode() == 200) {
                    model.setUpCard(null);
                } else {
                    System.out.println("(endRound) response is error : " + response.getStatusLine().getStatusCode());
                }
            } catch (IOException e) {
                throw new RuntimeException("(endRound) error", e);
            }
            model.setGameStatus(GameStatus.PAUSED);
            model.resetTimer();
        }
    }

    /**
     * Trigger disconnecting client from server.
     */
    @Override
    public void disconnect() {
        HttpPost postRequest = new HttpPost(basicServerAddress + disconnectParam + "/" + model.getPlayerId());
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Connection", "keep-alive");
        postRequest.setHeader("Content-Type", "application/json");

        try {
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                JOptionPane.showMessageDialog(null, "ROUND FINISHED", "GAME STATUS", JOptionPane.PLAIN_MESSAGE);
            } else {
                System.out.println("(disconnect) response is error : " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("(disconnect) error", e);
        }
        model.setGameStatus(GameStatus.PAUSED);
        model.resetTimer();
        model.setUpCard(null);
        model.resetTimer();
    }
}

