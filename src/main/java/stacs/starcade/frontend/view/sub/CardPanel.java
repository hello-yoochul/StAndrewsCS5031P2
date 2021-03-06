package stacs.starcade.frontend.view.sub;

import stacs.starcade.frontend.controller.FrontendController;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private final FrontendController controller;
    private final FrontendModel model;

    public CardPanel(FrontendController controller, FrontendModel model) {
        this.controller = controller;
        this.model = model;
    }

    /**
     * When model has a change, it will be invoked to redraw
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
