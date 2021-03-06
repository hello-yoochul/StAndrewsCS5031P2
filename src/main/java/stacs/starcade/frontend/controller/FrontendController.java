package stacs.starcade.frontend.controller;

import stacs.starcade.frontend.model.ICard;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.model.IFrontendModel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * This CanvasController controls a model to be specified in constructor.
 * Mainly, it commands model to draw shapes and has the function of save and import
 * vectors from specified file.
 *
 * @author 200012756
 */
public class FrontendController implements IFrontendController {
    private FrontendModel model;
    private HttpClient client;

//    String serverBasicAddress = "https://localhost:8080/";
//    final static String startGame = "startGame";
//    final static String getLeaderboard = "getLeaderboard";
//    final static String getCards = "getCards";


    /**
     * FrontendController constructor.
     */
    public FrontendController(FrontendModel model) {
        this.model = model;
        client = HttpClient.newBuilder().build();
    }

    /**
     * Start a game.
     */
    @Override
    public void startGame() {
        // TODO: check if the game is already running.

        model.setGameStatus(IFrontendModel.GameStatus.RUNNING);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://localhost:8080/startGame")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("error", e);
        }

        // TODO: get the id of current player from the request and store it by using model.setPlayerId("the id")

        setUpCards();

        System.out.println();
    }

    /**
     * Check if the three cards are set.
     *
     * @return true if it is set
     */
    @Override
    public boolean isSet() {
        // TODO: 1 check if three cards are choosen
        // TODO: 2 send http://localhost:8080/game/isSet wih the three cards properties, e.g., "2143"

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://localhost:8080/isSet")).build();

        try {
            // TODO: 3 From the request above, check if the server sends the info on it is set
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("error", e);
        }

        // TODO: 4 if it is set remove the card by repainting, else show dialog message "not set"

        return false;
    }

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
     * Set up the 12 cards.
     */
    @Override
    public void setUpCards() {
        List<ICard> cards = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://localhost:8080/getCards")).build();

        try {
            // TODO: Get the 12 cards from server and Paint it.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("error", e);
        }

        model.setUpCard(cards);
    }

    /**
     * Select a card. Player will invoke this method 3 times to choose three cards.
     *
     * @param card a card to check if chosen cards are set
     */
    @Override
    public void chooseCard(ICard card) {
        model.selectCard(card);
    }
}
