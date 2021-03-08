package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Needed to know which card object is clicked.
 */
public class CardImageButton extends JButton {
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
     * Construct CardImageButton with addition of the mouse listener:
     * if client clicks the card image button, the button colour will
     * be changed to show their choice.
     */
    public CardImageButton() {
        this.addMouseListener(new MyMouseListener());
        toolkit = Toolkit.getDefaultToolkit();
        setBackground(Color.WHITE);
    }

    /**
     * Get Image path string of the card.
     */
    public String getImagePathStr() {
        return imagePathStr;
    }

    /**
     * Set the card and Store the path of the corresponding card image.
     */
    public void setCard(ICard card) {
        imagePathStr = "/" + card.getColour() + "-" + card.getNumber() + "-" + card.getShape() + "-" + card.getLineStyle() + ".png";
        this.card = card;
        Image cardImage = toolkit.getImage(BASIC_IMAGE_PATH + imagePathStr).getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(cardImage));
    }

    /**
     * Get the card in the button.
     */
    public ICard getCard() {
        return this.card;
    }

    /**
     * Inner class for Mouse Listener. If client click the card image button,
     * the button colour will be changed to show their chocie.
     */
    class MyMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            if (!isClicked) {
                clickedButton.setBackground(Color.BLACK);
                isClicked = true;
            } else {
                clickedButton.setBackground(Color.WHITE);
                isClicked = false;
            }
        }
    }
}
