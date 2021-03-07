package stacs.starcade.backend.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeaderBoard implements ILeaderBoard {

    List<IPlayer> players;

    public LeaderBoard() {
        players = new ArrayList<>();
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

    /**
     * Removes player from leaderboard once player has disconnected.
     *
     * @param playerID id of player that shall be removed
     */
    @Override
    public void removePlayer(int playerID) {
        // get player object with given playerID
        for (int i = 0; i < players.size(); i++) {
            int ID = players.get(i).getPlayerId();
            if (ID == playerID) {
                players.remove(i);
                break;
            }
        }
        sortList();
    }

    /**
     * Gets list of players that are currently playing.
     * Player objects contain information on individual performance: num of rounds played and average time per round.
     *
     * @return
     */
    @Override
    public List<IPlayer> getPlayersList() {
        return this.players;
    }
}
