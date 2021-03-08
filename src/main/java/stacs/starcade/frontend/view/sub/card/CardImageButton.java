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
 * In card view panel, it will have 12 buttons.
 * Each button should be object so this class is needed.
 */
// TODO: I am not sure it is needed, but we should figure out how we can store client choice to the Model.
public class CardImageButton extends JButton {
    static final String BASIC_IMAGE_PATH = "src/main/resources/cardImages/";
    static final Dimension BUTTON_SIZE = new Dimension(110, 160);
    static final int IMAGE_WIDTH = 100;
    static final int IMAGE_HEIGHT = 150;

    private FrontendModel model;
    private ICard card;
    private String imagePathStr;
    private Toolkit toolkit;

    public CardImageButton() {
        this.addMouseListener(new MyMouseListener());
        setSize(BUTTON_SIZE);
        toolkit = Toolkit.getDefaultToolkit();
    }

    /**
     * Construct CardImageButton with addition of the mouse listener:
     * if client click the card image button, the button colour will
     * be changed to show their choice.
     *
     * @param model the front end model
     */
    public CardImageButton(FrontendModel model) {
        this.addMouseListener(new MyMouseListener());
        this.model = model;

        setSize(BUTTON_SIZE);

        toolkit = Toolkit.getDefaultToolkit(); // to get image from resource folder
//        testImg = toolkit.getImage(BASIC_IMAGE_PATH + "/BLUE-ONE-CIRCLE-OPEN.png").getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
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
            clickedButton.setBackground(Color.BLACK);
        }
    }
}
