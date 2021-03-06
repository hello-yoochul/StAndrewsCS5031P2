package stacs.starcade.backend.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Tests for the {@link SetGameAPI} class.
 */
public class SetGameAPITests {

    WebTestClient client;

    @BeforeEach
    void setup() {
        client = WebTestClient.bindToController(new SetGameAPI()).build();
    }

    @Test
    void createGame() {
        // success
        client.post().uri("/startGame")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("1");
    }

    @Test
    public void mustReturnNotFoundForNonExistingGame() {
        client.get().uri("/game/1000").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void mustGetLeaderBoard() {
        client.get().uri("/getLeaderboard").accept(MediaType.APPLICATION_JSON).exchange();
    }

    @Test
    void mustReturnThePlayersCards() {
        client.post().uri("/getLeaderboard").accept(MediaType.APPLICATION_JSON).exchange();
    }
}
