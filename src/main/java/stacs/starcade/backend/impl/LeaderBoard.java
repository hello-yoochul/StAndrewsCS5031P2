package stacs.starcade.backend.impl;

import java.util.ArrayList;
import java.util.Comparator;

public class LeaderBoard implements ILeaderBoard {

    ArrayList<IPlayer> players;

    public LeaderBoard() {
        players = new ArrayList<>();
    }

    /**
     * Gets list of players that are currently playing.
     * Player objects contain information on individual performance: num of rounds played and average time per round.
     *
     * @return
     */
    @Override
    public ArrayList<IPlayer> getPlayersList() {
        return this.players;
    }

    /**
     * Adds player to leaderboard.
     *
     * @param player player object that shall be added
     */
    @Override
    public void addPlayer(IPlayer player) throws IllegalArgumentException {
        if (!players.contains(player)) {
            players.add(player);
        } else {
            throw new IllegalArgumentException("This player has already been registered.");
        }
        sortList();
    }

    /**
     * Removes player from leaderboard once player has disconnected.
     *
     * @param removedPlayer player that shall be removed from leaderboard
     */
    @Override
    public void removePlayer(IPlayer removedPlayer) {
        // get player object with given playerID
        players.remove(removedPlayer);
        sortList();
    }

    /**
     * Sorts the players list based on their average times needed to complete a round.
     * Players with lowest average time first.
     */
    public void sortList() {
        players.sort(new Comparator<IPlayer>() {
            @Override
            public int compare(IPlayer p1, IPlayer p2) {
                if (p1.getAvgTime().compareTo(p2.getAvgTime()) == 0) {
                    return 0;
                } else if (p1.getAvgTime().compareTo(p2.getAvgTime()) < 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
}
