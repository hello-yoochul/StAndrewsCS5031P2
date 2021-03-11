package stacs.starcade.backend.api;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.reactive.server.WebTestClient.bindToController;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import stacs.starcade.backend.impl.LeaderBoard;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;
import stacs.starcade.shared.ICard.Colour;
import stacs.starcade.shared.ICard.LineStyle;
import stacs.starcade.shared.ICard.Number;
import stacs.starcade.shared.ICard.Shape;

/**
 * Tests for the {@link SetGameAPI} class.
 */
@SpringBootTest
public class SetGameAPITests {
    private String registerPlayerParam = "/registerPlayer/";
    private String nextRoundParam = "/nextRound/";
    private String endRoundParam = "/endRound/";
    private String getLeaderboardParam = "/getLeaderboard/";
    private String disconnectParam = "/disconnect/";
    private String anyPlayerName = "anyName";
    private String noneExistingId = "1000";

    private WebTestClient client;

    @BeforeEach
    void setup() {
        client = bindToController(new SetGameAPI()).build();
    }

    @Test
    void registerPlayer() {
        client.get().uri(registerPlayerParam + anyPlayerName)
                .exchange().expectStatus().isOk().expectBody(Integer.class).isEqualTo(1);
    }

    @Test
    void mustIncrementThePlayerUniqueId() {
        client.get().uri(registerPlayerParam + anyPlayerName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk().expectBody(Integer.class).isEqualTo(1);

        client.get().uri(registerPlayerParam + anyPlayerName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk().expectBody(Integer.class).isEqualTo(2);

        client.get().uri(registerPlayerParam + anyPlayerName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk().expectBody(Integer.class).isEqualTo(3);

        client.get().uri(registerPlayerParam + anyPlayerName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk().expectBody(Integer.class).isEqualTo(4);
    }

    @Test
    void mustReturnClientError() {
        client.get().uri(nextRoundParam + noneExistingId)
                .exchange()
                .expectStatus().isBadRequest();

        client.get().uri(disconnectParam + noneExistingId)
                .exchange()
                .expectStatus().isBadRequest();

        client.get().uri(endRoundParam + noneExistingId)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void mustGetLeaderBoard() {
        client.get().uri(getLeaderboardParam)
                .exchange().expectStatus().isOk().expectBodyList(LeaderBoard.class);
    }

    @Test
    void mustReturnCards() {
        client.get().uri(registerPlayerParam + anyPlayerName).accept(MediaType.APPLICATION_JSON)
                .exchange();

        client.get().uri(nextRoundParam + "1").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk().expectBodyList(Card.class);
    }

    @Test
    void mustDisconnect() {
        int returnedPlayerId = 1;

        client.get().uri(registerPlayerParam + anyPlayerName)
                .exchange().expectStatus().isOk().expectBody(Integer.class).isEqualTo(returnedPlayerId);

        client.get().uri(disconnectParam + returnedPlayerId)
                .exchange().expectStatus().isOk();
    }

    @Test
    void mustRemovePlayerFromLeaderboardWhenDisconnected() {
        int returnedPlayerId = 1;
        client.get().uri(registerPlayerParam + anyPlayerName)
                .exchange().expectStatus().isOk().expectBody(Integer.class).isEqualTo(returnedPlayerId);

        client.get().uri(getLeaderboardParam).exchange().expectBodyList(LeaderBoard.class).hasSize(1);

        client.get().uri(disconnectParam + returnedPlayerId)
                .exchange().expectStatus().isOk();

        client.get().uri(getLeaderboardParam + anyPlayerName).exchange().expectBodyList(LeaderBoard.class).hasSize(0);
    }

    @Test
    void ClientMustSendSetsListWhichTheyAreGiven() {
        int returnedPlayerId = 1;
        int expectedNumberOfCardObjects = 12;
        client.get().uri(registerPlayerParam + anyPlayerName)
                .exchange().expectStatus().isOk().expectBody(Integer.class).isEqualTo(returnedPlayerId);


        client.get().uri(nextRoundParam + returnedPlayerId)
                .exchange().expectBodyList(LeaderBoard.class).hasSize(expectedNumberOfCardObjects);
    }

    @Test
    void mustEndGame() {
        FluxExchangeResult<Integer> returnedPlayerId = client.get().
          uri(registerPlayerParam + anyPlayerName).exchange().returnResult(Integer.class);

        int playerID = returnedPlayerId.getResponseBody().blockFirst();

        FluxExchangeResult<String> returnedTwelveCards = client.get().
          uri(nextRoundParam + playerID).exchange()
          .returnResult(String.class);

        List<ICard> givenTwelveCards = new ArrayList<>();

        String cards = returnedTwelveCards.getResponseBody().blockFirst();
        cards = cards.substring(1, cards.length() - 1);
        String[] cardsArray = cards.split("},\\{");
        cardsArray[0] = cardsArray[0].substring(1);
        cardsArray[cardsArray.length - 1] = cardsArray[cardsArray.length - 1]
          .substring(0, cardsArray[cardsArray.length - 1].length() - 1);
        for (String card : cardsArray){
            String[] attributes = card.split(",");
            Card c = new Card();
            for (String attribute : attributes) {
                String[] keyVal = attribute.split(":");
                switch (keyVal[1]) {
                    case "\"GREEN\"":
                        c.setColour(Colour.GREEN);
                        break;
                    case "\"RED\"":
                        c.setColour(Colour.RED);
                        break;
                    case "\"BLUE\"":
                        c.setColour(Colour.BLUE);
                        break;
                    case "\"TRIANGLE\"":
                        c.setShape(Shape.TRIANGLE);
                        break;
                    case "\"SQUARE\"":
                        c.setShape(Shape.SQUARE);
                        break;
                    case "\"CIRCLE\"":
                        c.setShape(Shape.CIRCLE);
                        break;
                    case "\"SOLID\"":
                        c.setLineStyle(LineStyle.SOLID);
                        break;
                    case "\"DASHED\"":
                        c.setLineStyle(LineStyle.DASHED);
                        break;
                    case "\"DOTTED\"":
                        c.setLineStyle(LineStyle.DOTTED);
                        break;
                    case "\"ONE\"":
                        c.setNumber(Number.ONE);
                        break;
                    case "\"TWO\"":
                        c.setNumber(Number.TWO);
                        break;
                    default:
                        c.setNumber(Number.THREE);
                        break;
                }
            }
            givenTwelveCards.add(c);
        }




        List<List<Card>> sets = new ArrayList<>();

        // Find 5 set in the twelve cards and send it to server in order to end the Game
        // Then the server will verify if the 5 sets are selectable for the certain player
        for (int i = 0; i < givenTwelveCards.size() - 2; i++) {
            for (int j = i; j < givenTwelveCards.size() - 1; j++) {
                if (j == i) {
                    continue;
                }
                List<Card> setCards = new ArrayList<>();
                for (int k = j; k < givenTwelveCards.size(); k++) {
                    if (k == j) {
                        continue;
                    }
                    if (Checks.isSet(new ICard[]{
                            givenTwelveCards.get(i),
                            givenTwelveCards.get(j),
                            givenTwelveCards.get(k)
                    })) {
                        setCards.add((Card) givenTwelveCards.get(i));
                        setCards.add((Card) givenTwelveCards.get(j));
                        setCards.add((Card) givenTwelveCards.get(k));
                        sets.add(setCards);
                    }
                }
            }
        }

        client.post().uri(endRoundParam + playerID)
          .body(BodyInserters.fromObject(sets))
          .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
          .header(ACCEPT, APPLICATION_JSON_VALUE)
          .exchange()
          .expectStatus()
          .is2xxSuccessful();
    }
}
