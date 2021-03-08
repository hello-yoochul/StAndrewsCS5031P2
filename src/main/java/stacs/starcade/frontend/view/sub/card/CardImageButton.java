package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * In card view panel, it will have 12 buttons.
 * Each button should be object so this class is needed.
 */
// TODO: I am not sure it is needed, but we should figure out how we can store client choice to the Model.
public class CardImageButton extends JButton {
    private ICard card;
    private String imagePathStr;

    /**
     * Construct CardImageButton with addition of the mouse listener:
     * if client click the card image button, the button colour will
     * be changed to show their choice.
     */
    public CardImageButton() {
        this.addMouseListener(new MyMouseListener());
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
        imagePathStr = card.getColour() + "-" + card.getNumber() + "-" + card.getShape() + "-" + card.getLineStyle() + ".png";
        this.card = card;
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
            clickedButton.setBackground(Color.BLACK);
        }
    }
}
