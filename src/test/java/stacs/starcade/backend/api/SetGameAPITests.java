package stacs.starcade.backend.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import stacs.starcade.backend.impl.LeaderBoard;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.reactive.server.WebTestClient.*;
import static reactor.core.publisher.Mono.when;

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
        SetGameAPI setGameAPI = new SetGameAPI();
        int returnedPlayerId = setGameAPI.registerPlayer("anyName");

        ArrayList<ICard> givenTwelveCards = setGameAPI.startNextRound(returnedPlayerId);
        List<List<Card>> sets = new ArrayList<>();

        // Find 5 set in the twelve cards and send it to server in order to end the Game
        // Then the server will verify if the 5 sets are selectable for the certain player
        int setListSizeCount = 1;
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

//        System.out.println("sets list size: " + sets.size());
        setGameAPI.endRound(returnedPlayerId, sets);
    }
}
