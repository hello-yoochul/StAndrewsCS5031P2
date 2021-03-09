package stacs.starcade.backend.impl;

import java.time.Duration;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.shared.ICard;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTests {

    private IPlayer player;
    private IPlayer mockPlayer;
    private ArrayList<ICard> mockCardList;

    @BeforeEach
    public void setup() {
        player = new Player("Paul", 1);
        mockPlayer = mock(Player.class);
        mockCardList = mock(ArrayList.class);
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
    void testGetAverageDuration() {
        when(mockPlayer.getAvgTime()).thenReturn((long) 60);
        assertEquals(60, mockPlayer.getAvgTime());
    }

    @Test
    void testStartingARoundByCheckingWhetherCardsHaveBeenStoredSuccessfully() {
        player.startRound(mockCardList);
        assertTrue(player.getStoredCards().equals(mockCardList));
    }

    @Test
    void testEndingARound() throws InterruptedException {
        long avgTime1 = player.getAvgTime(); // Get average time before starting round (this should be 0)
        player.startRound(mockCardList);// Start round

        // this will allow the timer inside of player to run for a bit
        Thread.sleep(2000);

        // End round
        // This should set average time for round that just ended.
        player.endRound();
        long avgTime2 = player.getAvgTime(); // Get updated average time

        assertTrue(avgTime1 < avgTime2);
    }

    @Test
    void testPlayingTwoRoundsWithEqualSpeed() throws InterruptedException {
        player.startRound(mockCardList); // Start round
        Thread.sleep(2000);
        player.endRound();
        long avgTime1 = player.getAvgTime(); // Get average time before starting round (this should be 0)

        player.startRound(mockCardList); // Start second round
        Thread.sleep(2000);
        player.endRound();
        long avgTime2 = player.getAvgTime(); // Get updated average time

        assertTrue(avgTime1 == avgTime2);
    }

    @Test
    void testPlayingTwoRoundsWithUnequalSpeed() throws InterruptedException {
        player.startRound(mockCardList); // Start round
        Thread.sleep(2000);
        player.endRound();
        long avgTime1 = player.getAvgTime(); // Get average time before starting round (this should be 0)

        player.startRound(mockCardList); // Start second round
        Thread.sleep(1000);
        player.endRound();
        long avgTime2 = player.getAvgTime(); // Get updated average time

        assertTrue(avgTime1 > avgTime2);
    }

}
