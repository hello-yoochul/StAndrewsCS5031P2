package stacs.starcade.backend.impl;

import stacs.starcade.shared.ICard;
import stacs.starcade.shared.ITimer;
import stacs.starcade.shared.Timer;

import java.time.Duration;
import java.util.ArrayList;

public class Player implements IPlayer {

    private String name;
    private Integer id;
    private Integer round;

    private ITimer timer;
    private Duration totalTime;
    private Duration averageTime;
    private ArrayList<ICard> twelveCards;

    public Player(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.totalTime = Duration.ZERO;
        this.averageTime = Duration.ZERO;
        this.twelveCards = new ArrayList<>();
        this.timer = new Timer();
    }

    /**
     * Getter for the playerID.
     *
     * @return a unique ID representing a player
     */
    @Override
    public Integer getPlayerId() {
        return this.id;
    }

    /**
     * Getter for the player name.
     *
     * @return a String representing the player name
     */
    @Override
    public String getPlayerName() {
        return this.name;
    }

    private void setTotalTime(Duration currentTime) {
        this.totalTime.plus(currentTime);
    }

    /**
     * Gets duration player has needed for previously played rounds on avergade.
     *
     * @return average round duration
     */
    private void setAvgTime() { this.averageTime = this.totalTime.dividedBy(this.round); }

    /**
     * Gets duration player has needed for previously played rounds on avergade.
     *
     * @return average round duration
     */
    @Override
    public Duration getAvgTime() {
        return this.averageTime;
    }

    /**
     * Starts a new round and the timer for this round.
     *
     * @param twelveCards the cards the round shall be played with
     */
    @Override
    public void startRound(ArrayList<ICard> twelveCards) {

        // Increment round
        // Set cards for this round
        // start Timer


        if (this.round > 0) {

        }
        this.round++;
        this.totalTime.plus(totalTime);
        this.twelveCards = twelveCards;
    }

    /**
     * End the current round, stopping the timer.
     */
    @Override
    public void endRound() {
        setTotalTime(timer.getTime());
        timer.reset();
        setAvgTime();

        // TODO: Notify leaderboard to update list
    }
}
