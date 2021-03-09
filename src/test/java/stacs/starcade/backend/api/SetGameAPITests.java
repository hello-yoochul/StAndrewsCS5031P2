package stacs.starcade.backend.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import stacs.starcade.backend.impl.LeaderBoard;
import stacs.starcade.shared.Card;

import static org.springframework.test.web.reactive.server.WebTestClient.*;

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
    void mustEndGame() {
        client.get().uri(getLeaderboardParam)
                .exchange().expectStatus().isOk().expectBodyList(LeaderBoard.class);
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
}
