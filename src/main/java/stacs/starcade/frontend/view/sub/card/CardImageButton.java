package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.frontend.model.IFrontendModel;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import static stacs.starcade.frontend.model.FrontendModel.MAXIMUM_NUMBER_OF_SELECTED_CARDS;

/**
 * Needed to know which card object is clicked.
 */
public class CardImageButton extends JButton implements Observer {
    /**
     * Image resource path.
     */
    private static final String BASIC_IMAGE_PATH = "src/main/resources/cardImages/";
    /**
     * Image width of the card image in each button.
     */
    private static final int IMAGE_WIDTH = 130;
    /**
     * Image height of the card image in each button.
     */
    private static final int IMAGE_HEIGHT = 180;

    /**
     * Button should be toggled when clicked.
     */
    private boolean isClicked = false;

    /**
     * Card to be correspondent to one of Model card, to show on the button.
     */
    private ICard card;
    /**
     * The card image path.
     */
    private String imagePathStr;
    /**
     * To get images from the directory.
     */
    private Toolkit toolkit;
    /**
     * Needed to send message to Model to remove or add the selected card image button.
     */
    private IFrontendModel model;

    /**
     * Construct CardImageButton with addition of the mouse listener:
     * if client clicks the card image button, the button colour will
     * be changed to show their choice.
     */
    public CardImageButton(IFrontendModel model) {
        this.model = model;

        ((Observable) this.model).addObserver(this);

        this.addMouseListener(new MyMouseListener());
        toolkit = Toolkit.getDefaultToolkit();

        setContentAreaFilled(false);
        setOpaque(true);
        setBorderPainted(false);
        setBackground(Color.WHITE);
    }

    /**
     * Get Image path string of the card.
     */
    public String getImagePathStr() {
        return this.imagePathStr;
    }

    /**
     * Set the card and Store the path of the corresponding card image.
     */
    public void setCard(ICard card) {
        imagePathStr = "/" + card.getColour() + "-" + card.getNumber() + "-" + card.getShape() + "-" +
                card.getLineStyle() + ".png";
        this.card = card;
        Image cardImage = toolkit.getImage(BASIC_IMAGE_PATH + imagePathStr)
                .getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(cardImage));
    }

    /**
     * Get the card in the button.
     */
    public ICard getCard() {
        return this.card;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {
        // If the card associated with this button is not in selected list
        // the background color has to be set to white
        if (!model.getSelectedCards().contains(getCard())) {
            setBackground(Color.WHITE);
        }
    }

    /**
     * Inner class for Mouse Listener. If client click the card image button,
     * the button colour will be changed to show their chocie.
     */
    class MyMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (model.getStatus() == IFrontendModel.GameStatus.RUNNING) {
                JButton clickedButton = (JButton) e.getSource();
                if (!isClicked) {
                    if (model.getSelectedCards().size() < MAXIMUM_NUMBER_OF_SELECTED_CARDS) {
                        setBackground(Color.GRAY);
                        isClicked = true;
                        model.selectCard(getCard());
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select less than three cards");
                    }
                } else {
                    setBackground(Color.WHITE);
                    isClicked = false;
                    model.removeSelectedCard(getCard());
                }
            }

        }
    }
}
