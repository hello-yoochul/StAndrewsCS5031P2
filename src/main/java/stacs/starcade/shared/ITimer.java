package stacs.starcade.shared;

import java.time.Duration;

public interface ITimer {

    /**
     * Reset timer to zero and leaves it in a paused state.
     */
    void reset();

    /**
     * Start/restart timer.
     */
    void start();

    /**
     * Query the timer state.
     * @return true if the timer is currently running, false otherwise.
     */
    boolean isRunning();

    /**
     * Get the time on the timer.
     * @return a {@link Duration} value for the time on the timer.
     */
    Duration getTime();
}
