package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;
import java.time.Duration;
import java.util.Observable;
import java.util.Observer;

import static stacs.starcade.frontend.model.IFrontendModel.*;

/**
 * Panel for Timer.
 */
public class TimerPane extends JPanel implements Observer {
    private FrontendModel model;
    private JLabel timerLabel;
    private TimerRunner timerRunner;

    public TimerPane(FrontendModel model, Controller controller) {
        this.model = model;
        ((Observable) model).addObserver(this);

        timerLabel = new JLabel("0:00:00");
        timerLabel.setBounds(0, 0, 100, 20);
//        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerRunner = new TimerRunner();
        add(timerLabel);
    }

    /**
     * Once the game start, the timer thread will be started.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (model.getStatus() == GameStatus.RUNNING) {
            new Thread(timerRunner).start();
        }
    }

    /**
     * Format changer for Duration.
     */
    // obtained from https://stackoverflow.com/questions/266825/how-to-format-a-duration-in-java-e-g-format-hmmss
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

    /**
     * Inner class for timer running in another Thread.
     */
    class TimerRunner extends JPanel implements Runnable {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timerLabel.setText(formatDuration(model.getTime()));
                System.out.println("keeeppp");
            } while (true);
        }
    }
}
