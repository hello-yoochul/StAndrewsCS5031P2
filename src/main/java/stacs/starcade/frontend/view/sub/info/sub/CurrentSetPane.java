package stacs.starcade.frontend.view.sub.info.sub;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IClientModel;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static stacs.starcade.frontend.view.sub.card.CardImageButton.getImage;

/**
 * Panel for Set cards which are selected by the player.
 */
public class CurrentSetPane extends JPanel implements Observer {

    private static final int GRID_FIELDS = 15;
    private static final int CARDS_PER_SET = 3;
    private IClientModel model;
    private IController controller;
    private ArrayList<JLabel> labels;
    private int numberSets = 0;
    private int counter = 0;

    public CurrentSetPane(IClientModel model, IController controller) {
        this.model = model;
        this.controller = controller;

        ((Observable) this.model).addObserver(this);

        setGrid();
    }

    /**
     * Sets the grid for the current_pane.
     */
    private void setGrid() {
        setLayout(new GridLayout(5, 3));

        // Generate labels and put them into grid
        this.labels = new ArrayList<>();
        for (int i = 0; i < GRID_FIELDS; i++) {
            JLabel imgLabel = new JLabel();
            labels.add(imgLabel);
            add(imgLabel);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ICard[]> loggedSets = model.getSetsLog();

        if (loggedSets != null) {

            int numSets = loggedSets.size();
            if (numSets > this.numberSets) {
                int newSetIndex = numSets - 1;
                for (int i = 0; i < CARDS_PER_SET; i++) {
                    ICard card = loggedSets.get(newSetIndex)[i];
                    Image img = getImage(card);
                    ImageIcon icon = new ImageIcon(img);
                    this.labels.get(counter).setIcon(icon);
                    counter++;
                }
                this.numberSets++;
                repaint();
            }

        } else {

            for (int i = 0; i < this.labels.size(); i++) {
                this.labels.get(i).setIcon(null);
            }
            counter = 0;
            this.numberSets = 0;
            repaint();

        }
    }
}
