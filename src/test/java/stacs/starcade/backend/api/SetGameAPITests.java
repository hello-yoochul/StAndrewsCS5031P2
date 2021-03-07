package stacs.starcade.backend.api;

import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import stacs.starcade.backend.impl.LeaderBoard;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;

import java.util.List;

/**
 * Tests for the {@link SetGameAPI} class.
 */

@AutoConfigureWebTestClient(timeout = "10000")
@SpringBootTest
public class SetGameAPITests {

    private WebTestClient client;

    @BeforeEach
    void setup() {
        client = WebTestClient.bindToController(new SetGameAPI()).build();
    }

    @Test
    void createGame() {
        // success
        client.get().uri("/startGame")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("1");
    }

    @Test
    void mustReturnNotFoundForNonExistingGame() {
        client.get().uri("/game/1000")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void mustGetLeaderBoard() {
        client.get().uri("/getLeaderboard")
                .exchange().expectStatus().isOk().expectBody(LeaderBoard.class);
    }

    @Test
    void mustReturnCards() {
        client.get().uri("/startGame")
                .exchange();

        client.get().uri("/getCards/1")
                .exchange()
                .expectStatus().isOk().expectBodyList(Card.class);
    }

    @Test
    void gettingNonExistentGame() {
        client.get().uri("/getCards/1")
          .exchange()
          .expectStatus().isBadRequest();
    }

    @Test
    void getNewPlayer() {
        client.get().uri("/startGame")
          .exchange()
          .expectBody().json("1");

    }
}
