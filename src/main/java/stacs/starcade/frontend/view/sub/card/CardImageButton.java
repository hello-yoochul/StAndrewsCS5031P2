package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static stacs.starcade.shared.ICard.*;


/**
 * In card view panel, it will have 12 buttons.
 * Each button should be object so this class is needed.
 */
// TODO: I am not sure it is needed, but we should figure out how we can store client choice to the Model.
public class CardImageButton extends JButton {
    private ICard card;
    private String imagePathStr;

    public CardImageButton() {
        this.addMouseListener(new MyMouseListener());
    }

    public String getImagePathStr() {
        return imagePathStr;
    }

    public void setCard(ICard card) {
        imagePathStr = card.getColour() + "-" + card.getNumber() + "-" + card.getShape() + "-" + card.getLineStyle() + ".png";
        this.card = card;
    }

    public ICard getCard() {
        return this.card;
    }

    class MyMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            clickedButton.setBackground(Color.BLACK);
        }
    }
}
