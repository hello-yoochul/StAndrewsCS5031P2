package stacs.starcade.backend.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import stacs.starcade.backend.impl.LeaderBoard;
import stacs.starcade.shared.Card;

/**
 * Tests for the {@link SetGameAPI} class.
 */

@SpringBootTest
public class SetGameAPITests {

    private WebTestClient client;

    @BeforeEach
    void setup() {
        client = WebTestClient.bindToController(new SetGameAPI()).build();
    }

    @Test
    void registerPlayer() {
        // success
        client.post().uri("/registerPlayer")
          .accept(MediaType.APPLICATION_JSON)
          .exchange()
          .expectStatus().isOk()
          .expectBody().json("1");
    }

    @Test
    void mustReturnNotFoundForNonExistingGame() {
        client.post().uri("/game/1000")
          .exchange()
          .expectStatus().isNotFound();
    }

    @Test
    void mustGetLeaderBoard() {
        client.get().uri("/getLeaderboard")
          .exchange().expectStatus().isOk().expectBodyList(LeaderBoard.class);
    }

    @Test
    void mustReturnCards() {
        client.post().uri("/registerPlayer/John").accept(MediaType.APPLICATION_JSON)
          .exchange();

        client.post().uri("/nextRound/1").accept(MediaType.APPLICATION_JSON)
          .exchange()
          .expectStatus().isOk().expectBodyList(Card.class);
    }

}
