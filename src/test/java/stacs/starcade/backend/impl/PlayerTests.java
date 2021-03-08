package stacs.starcade.backend.impl;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PlayerTests {

    private IPlayer player;
    private IPlayer mockPlayer;

    @BeforeEach
    public void setup() {
        player = new Player("Paul", 1);
        mockPlayer = mock(Player.class);
    }

    @Test
    void testGetPlayerId() {
        assertEquals(1, player.getPlayerId());
    }

    @Test
    void testGetPlayerName() {
        assertEquals("Paul", player.getPlayerName());
    }

    @Test
    void testGetDurationOfCurrentRound() {

    }

    @Test
    void testStartingARound() {

    }

    @Test
    void testEndingARound() {

    }

}
