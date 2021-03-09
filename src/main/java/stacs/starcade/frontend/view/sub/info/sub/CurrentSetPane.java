package stacs.starcade.frontend.view.sub.info.sub;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IClientModel;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
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

    public CurrentSetPane(IClientModel model, IController controller) {
        this.model = model;
        this.controller = controller;

        ((Observable) this.model).addObserver(this);

        setGrid();
    }

    private void setGrid() {
        this.setLayout(new GridLayout(5, 3));

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
        int numSets = loggedSets.size();
        int counter = 0;
        if (numSets > 0) {
            for (int i = 0; i < numSets; i++) {
                for (int j = 0; j < CARDS_PER_SET; j++) {
                    ICard card = loggedSets.get(i)[j];
                    Image img = getImage(card);
                    this.labels.get(counter).setIcon(new ImageIcon(img));
                    counter++;
                }
            }
        }
        repaint();
    }
}
