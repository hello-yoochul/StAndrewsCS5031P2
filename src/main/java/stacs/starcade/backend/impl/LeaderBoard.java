package stacs.starcade.backend.impl;

import java.util.ArrayList;

public class LeaderBoard implements ILeaderBoard {
    private ArrayList<IPlayer> players;

    public LeaderBoard() {
        players = new ArrayList<>();
    }

    /**
     * Gets list of players that are currently playing.
     * Player objects contain information on individual performance: num of rounds played and average time per round.
     *
     * @return the player list in leader board
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
        if (players.size() > 1) {
            players.sort((p1, p2) -> {
                if (p1.getAvgTime() == p2.getAvgTime()) {
                    return 0;
                } else if (p1.getAvgTime() < p2.getAvgTime()) {
                    return -1;
                } else {
                    return 1;
                }
            });
        }
    }
}
