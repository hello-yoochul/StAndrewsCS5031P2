package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.model.IFrontendModel;
import stacs.starcade.frontend.view.sub.control.ControlPane;
import stacs.starcade.frontend.view.sub.info.InfoPane;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static stacs.starcade.frontend.model.IFrontendModel.*;

/**
 * The panel for 12 cards.
 */
public class CardPane extends JPanel implements Observer {
    private final Controller controller;
    private final FrontendModel model;

    private Toolkit toolkit;

    private ArrayList<CardImageButton> cardImageButtons;

    public CardPane(FrontendModel model, Controller controller) {
        this.controller = controller;
        this.model = model;
        ((Observable) model).addObserver(this);
        toolkit = Toolkit.getDefaultToolkit(); // to get image from resource folder
        cardImageButtons = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            cardImageButtons.add(new CardImageButton(this.model));
            add(cardImageButtons.get(i));
        }

        this.setLayout(new GridLayout(3, 4));
    }

    /**
     * Get all the 12 cards from frontend Model and draw it on the Card Panel:
     * when model has a change, it will be invoked to redraw.
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("REPAINT CARD PANE");
        List<ICard> cards = model.getCards();
        int cardsSize = cards.size();
        if (cardsSize != 0) {
            for (int i = 0; i < cardsSize; i++) {
                System.out.println("setCards");
                cardImageButtons.get(i).setCard(cards.get(i));
            }
        }
        repaint();
    }
}
