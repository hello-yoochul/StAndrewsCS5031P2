package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IFrontendModel;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The panel for 12 cards.
 */
public class CardPane extends JPanel implements Observer {
    private final IController controller;
    private final IFrontendModel model;

    private Toolkit toolkit;

    private ArrayList<CardImageButton> cardImageButtons;

    public CardPane(IFrontendModel model, IController controller) {
        this.controller = controller;
        this.model = model;

        ((Observable) this.model).addObserver(this);

        toolkit = Toolkit.getDefaultToolkit(); // to get image from resource folder
        cardImageButtons = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            cardImageButtons.add(new CardImageButton(this.model));
            add(cardImageButtons.get(i));
        }

        this.setLayout(new GridLayout(3, 4));

        setCardsOnButtons();
    }

    private void setCardsOnButtons() {
        List<ICard> cards = model.getCards();
        if (cards.size() != 0) {
            for (int i = 0; i < cards.size(); i++) {
                System.out.println("setCards");
                cardImageButtons.get(i).setCard(cards.get(i));
            }
        }
    }

    /**
     * Get all the 12 cards from frontend Model and draw it on the Card Panel:
     * when model has a change, it will be invoked to redraw.
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("REPAINT CARD PANE");
        setCardsOnButtons();
        repaint();
    }
}
