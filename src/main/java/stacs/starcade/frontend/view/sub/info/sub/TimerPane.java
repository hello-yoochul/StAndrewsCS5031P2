package stacs.starcade.frontend.view.sub.info.sub;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.model.IClientModel;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.Observable;
import java.util.Observer;

import stacs.starcade.shared.Timer;

import static stacs.starcade.frontend.model.IClientModel.*;

/**
 * Panel for Timer.
 */
public class TimerPane extends JPanel implements Observer {
    private IClientModel model;
    private JLabel timerLabel;
    private TimerRunner timerRunner;
    private Thread thread;
    private boolean isGameRunning;

    /**
     * Inner class for timer running in another Thread.
     */
    public TimerPane(IClientModel model, IController controller) {
        this.model = model;

        ((Observable) this.model).addObserver(this);

        timerLabel = new JLabel("0:00:00");
        timerLabel.setBounds(0, 0, 100, 20);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 25));
        timerRunner = new TimerRunner();
        thread = new Thread(timerRunner);
        isGameRunning = false;

        add(timerLabel);
    }

    /**
     * Inner class for timer running in another Thread.
     */
    class TimerRunner extends JPanel implements Runnable {
        @Override
        public void run() {
            while (isGameRunning) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timerLabel.setText(Timer.formatDuration(model.getTime()));
            }
        }
    }

    /**
     * If the {@link ClientModel} is updated, it will be invoked (observer notification)).
     *
     * @param arg0 the observable object.
     * @param arg1 an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        if (model.getStatus() == GameStatus.RUNNING) {
            if (!isGameRunning) {
                if (thread.getState() == Thread.State.NEW) {
                    thread.start();
                }
            }
            isGameRunning = true;
        } else {
            isGameRunning = false;
            thread.stop();
            thread = new Thread(timerRunner);
            timerLabel.setText("0:00:00");
        }
    }
}
