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

    private ArrayList<CardImageButton> cardImageButtonList;

    private ArrayList<CardImageButton> cardImageButtons;

    public CardPane(FrontendModel model, Controller controller) {
        this.controller = controller;
        this.model = model;
        ((Observable) model).addObserver(this);
        toolkit = Toolkit.getDefaultToolkit(); // to get image from resource folder
        cardImageButtonList = new ArrayList<>();
        cardImageButtons = new ArrayList<>();
        this.setLayout(new GridLayout(3, 4));
    }

    // TEST for 12 cards view
    public static void main(String[] args) {
        ControlPane controlPanel;
        CardPane testCardPane;
        InfoPane infoPanel;
        FrontendModel testModel = new FrontendModel();
        Controller testController = new Controller(testModel);

        JFrame frame = new JFrame();
        // obtained from https://stackoverflow.com/questions/33576358/how-to-use-java-swing-layout-manager-to-make-this-gui
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        frame.add((controlPanel = new ControlPane(testModel, testController)), gbc);
        gbc.weighty = 1;
        gbc.gridy++;
        frame.add((testCardPane = new CardPane(testModel, testController)), gbc);

        gbc.gridy = 0;
        gbc.gridx++;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        gbc.weightx = 0.5;
        frame.add((infoPanel = new InfoPane(testModel, testController)), gbc);

        ArrayList<ICard> twelveCards = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            ICard card = new Card();
            card.setLineStyle(ICard.LineStyle.DOTTED);
            card.setColour(ICard.Colour.RED);
            card.setShape(ICard.Shape.TRIANGLE);
            card.setNumber(ICard.Number.ONE);
            twelveCards.add(card);
        }

        testModel.setUpCard(twelveCards);

        frame.setSize(1400, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        testCardPane.repaint();
    }

    /**
     * Get all the 12 cards from frontend Model and draw it on the Card Panel:
     * when model has a change, it will be invoked to redraw.
     */
    @Override
    protected void paintComponent(Graphics g) {
        List<ICard> cards = model.getCards();
        int cardsSize = cards.size();
        if (cardsSize != 0) {
            for (int i = 0; i < cardsSize; i++) {
                CardImageButton cardImageButton = new CardImageButton(this.model);
                cardImageButton.setCard(cards.get(i));
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
