package stacs.starcade.shared;

import java.time.Duration;
import java.time.Instant;

/**
 * Implementation of a {@link ITimer}.
 *
 * TODO: possibly make this testable by injecting a {@link java.time.Clock}
 * that can be mocked up in a test. For now, we have inspected the code and
 * convinced ourselves that it is working.
 */
public class Timer implements ITimer {

    private Instant periodStart;
    private Duration time;
    private boolean isRunning = false;

    /**
     * Constructor produces a timer set to zero and stopped.
     */
    public Timer() {
        this.reset();
    }

    @Override
    public void reset() {
        this.time = Duration.ZERO;
        this.periodStart = null;
        this.isRunning = false;
    }

    @Override
    public void start() {
        this.periodStart = Instant.now();
        this.isRunning = true;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public Duration getTime() {
        return this.time.plus(getPeriod());
    }

    /**
     * Get the time that has passed since the timer was last started.
     * If the timer is paused, will return {@link Duration#ZERO}.
     */
    private Duration getPeriod() {
        if(this.isRunning()) {
            return Duration.between(this.periodStart, Instant.now());
        } else {
            return Duration.ZERO;
        }
    }

    /**
     * Format changer for Duration.
     */
    // obtained from: https://stackoverflow.com/questions/266825/how-to-format-a-duration-in-java-e-g-format-hmmss
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }
}
