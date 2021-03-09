package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IClientModel;
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
    private IController controller;
    private IClientModel model;

    private Toolkit toolkit;
    private ArrayList<CardImageButton> cardImageButtons;

    public CardPane(IClientModel model, IController controller) {
        this.controller = controller;
        this.model = model;

        ((Observable) this.model).addObserver(this);

        toolkit = Toolkit.getDefaultToolkit(); // to get image from resource folder
        cardImageButtons = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            cardImageButtons.add(new CardImageButton(this.model));
            add(cardImageButtons.get(i));
        }

        setLayout(new GridLayout(3, 4));

        setCardsOnButtons();

        setBackground(new Color(146, 146, 146));
    }

    private void setCardsOnButtons() {
        List<ICard> cards = model.getCards();
        if (cards != null && cards.size() != 0) {
            for (int i = 0; i < cards.size(); i++) {
                cardImageButtons.get(i).setCard(cards.get(i));
            }
        } else {
            for (int i = 0; i < cardImageButtons.size(); i++) {
                cardImageButtons.get(i).setCard(null);
            }
        }
    }

    /**
     * Get all the 12 cards from frontend Model and draw it on the Card Panel:
     * when model has a change, it will be invoked to redraw.
     */
    @Override
    public void update(Observable o, Object arg) {
        setCardsOnButtons();
        repaint();
    }
}
