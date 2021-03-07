package stacs.starcade.backend.impl;

import java.time.Duration;

public class Player implements IPlayer {

    private String name;
    private Integer id;
    private Integer round;
    private Duration totalTime;
    private Duration averageTime;

    public Player(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.totalTime = Duration.ZERO;
        this.averageTime = Duration.ZERO;
    }


    /**
     * Gets playerID.
     *
     * @return a unique ID representing a player
     */
    @Override
    public Integer getPlayerId() {
        return this.id;
    }

    /**
     * Gets player name.
     *
     * @return a String representing the player name
     */
    @Override
    public String getPlayerName() {
        return this.name;
    }

    /**
     * Increments round when called.
     *
     * @param timeLastRound
     */
    @Override
    public void setRound(Duration timeLastRound) {
        this.round++;
        this.totalTime.plus(timeLastRound);
        setAvgTime();
    }

    /**
     * Gets number of rounds player has played so far.
     *
     * @return num of rounds as integer
     */
    @Override
    public Integer getRound() {
        return this.round;
    }


    /**
     * Updates average time a player has needed to play x rounds.
     */
    private void setAvgTime() { averageTime = totalTime.dividedBy(this.round); }

    /**
     * Gets duration player has needed for previously played rounds on avergade.
     *
     * @return average round duration
     */
    @Override
    public Duration getAvgTime() {
        return this.averageTime;
    }
}
