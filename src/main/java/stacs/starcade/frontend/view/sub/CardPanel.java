package stacs.starcade.frontend.view.sub;

import stacs.starcade.frontend.controller.FrontendController;
import stacs.starcade.frontend.model.Card;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.model.ICard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class CardPanel extends JPanel {
    private final FrontendController controller;
    private final FrontendModel model;

    ArrayList<Card> cardsOnBoard;


    private BufferedImage testImage;

    public CardPanel(FrontendController controller, FrontendModel model) {
        this.controller = controller;
        this.model = model;
        cardsOnBoard = new ArrayList<>();

        try {
            testImage = ImageIO.read(new File("resources/cardImages/BLUE-ONE-CIRCLE-OPEN.png"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    /**
     * When model has a change, it will be invoked to redraw
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(testImage, 100, 100, this);
        /*Graphics2D graphics2D = (Graphics2D) g;
        Stack<AbstractShape> shapeList = model.getShapeList();

        if (shapeList != null && shapeList.size() != 0) {
            for (AbstractShape shape : model.getShapeList()) {
                if (shape.isFillNeeded()) {
                    graphics2D.setColor(shape.getColor());
                    graphics2D.fill(shape.getShape());
                } else {
                    graphics2D.setColor(shape.getColor());
                    graphics2D.draw(shape.getShape());
                }
            }
        }*/
    }
}
