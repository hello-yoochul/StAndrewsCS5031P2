package stacs.starcade.backend.impl;

import java.time.Duration;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LeaderBoardTests {

    private LeaderBoard leaderBoard;
    private ArrayList<IPlayer> lbList;
    private IPlayer p1;
    private IPlayer p2;
    private IPlayer p3;

    @BeforeEach
    public void setup() {
        leaderBoard = new LeaderBoard();
        lbList = leaderBoard.getPlayersList();
        p1 = mock(Player.class);
        p2 = mock(Player.class);
        p3 = mock(Player.class);
    }

    @Test
    void testCorrectSetup() {
        int sizeLeaderBoard = leaderBoard.getPlayersList().size();
        assertEquals(0, sizeLeaderBoard);
    }

    @Test
    void testAddPlayerToLeaderBoard() {
        // Add a player to list
        leaderBoard.addPlayer(p1);
        // Get size of player list
        int sizeLB = lbList.size();

        assertTrue(sizeLB == 1 && lbList.contains(p1));
    }

    @Test
    void testRemovePlayerFromLeaderBoard() {
        // Add two players
        leaderBoard.addPlayer(p1);
        leaderBoard.addPlayer(p2);
        // Remove p1 again
        leaderBoard.removePlayer(p1);
        // Get size of current player list in leaderboard
        int sizeLB = lbList.size();

        assertTrue(sizeLB == 1 && lbList.contains(p2));
    }

    @Test
    void testSuccessfulDenialOfTryingToAddTheSamePlayerAgain() {
        String expectedMessage = "This player has already been registered.";
        leaderBoard.addPlayer(p1);

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
            // Try to add p1 again, which should cause an exception
            leaderBoard.addPlayer(p1);
        });
        String actualMessage = iae.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testSortingPlayersList() {
        // Add two players
        leaderBoard.addPlayer(p1);
        leaderBoard.addPlayer(p2);
        leaderBoard.addPlayer(p3);

        when(p1.getAvgTime()).thenReturn((long) 90);
        when(p2.getAvgTime()).thenReturn((long) 60);
        when(p3.getAvgTime()).thenReturn((long) 120);

        leaderBoard.sortList();

        // sortList() should reorder the list so that p2 is item 0 and p1 item 1
        assertTrue(lbList.get(0).equals(p2) && lbList.get(1).equals(p1) && lbList.get(2).equals(p3));
    }
}
