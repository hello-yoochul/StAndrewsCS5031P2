package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The panel for 12 cards.
 */
public class CardPane extends JPanel implements Observer {
    static final String BASIC_IMAGE_PATH = "src/main/resources/cardImages/";
    static final int INITIAL_NUMBER_OF_CARDS = 12;
    static final Dimension BUTTON_SIZE = new Dimension(100, 160);

    static final int IMAGE_WIDTH = 100;
    static final int IMAGE_HEIGHT = 150;

    private final Controller controller;
    private final FrontendModel model;

    private Toolkit toolkit;
//    private Image testImg;

    private ArrayList<CardImageButton> cardImageButtonList;

    public CardPane(FrontendModel model, Controller controller) {
        this.controller = controller;
        this.model = model;

        ((Observable) model).addObserver(this);

        toolkit = Toolkit.getDefaultToolkit(); // to get image from resource folder
//        testImg = toolkit.getImage(BASIC_IMAGE_PATH + "/BLUE-ONE-CIRCLE-OPEN.png").getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);

        cardImageButtonList = new ArrayList<>();

        this.setLayout(new GridLayout(3, 4));
    }

    /**
     * When model has a change, it will be invoked to redraw
     */
    @Override
    protected void paintComponent(Graphics g) {
        ArrayList<ICard> cards = (ArrayList) model.getCards();
        int cardsSize = cards.size();
        if (cardsSize != 0) {
            for (int i = 1; i < cardsSize; i++) {
                CardImageButton cardImageButton = new CardImageButton(/*cards.get(i)*/);
                cardImageButtonList.add(cardImageButton);
                add(cardImageButton);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
