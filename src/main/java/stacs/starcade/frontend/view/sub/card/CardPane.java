package stacs.starcade.frontend.view.sub.card;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.shared.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CardPane extends JPanel {
    static final String BASIC_IMAGE_PATH = "src/main/resources/cardImages/";
    static final int INITIAL_NUMBER_OF_CARDS = 12;
    static final Dimension BUTTON_SIZE = new Dimension(100, 160);

    static final int IMAGE_WIDTH = 100;
    static final int IMAGE_HEIGHT = 150;

    private final Controller controller;
    private final FrontendModel model;

    private Toolkit toolkit;
    private Image testImg;

    private ArrayList<Card> cardsOnBoard;
    private MyMouseListener myMouseListener;
    private ArrayList<JButton> buttonList;

    private BufferedImage testImage;

    public CardPane(FrontendModel model, Controller controller) {
        this.controller = controller;
        this.model = model;

        toolkit = Toolkit.getDefaultToolkit(); // to get image from resource folder
        testImg = toolkit.getImage(BASIC_IMAGE_PATH + "/BLUE-ONE-CIRCLE-OPEN.png").getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);

        cardsOnBoard = new ArrayList<>();
        buttonList = new ArrayList<>();
        myMouseListener = new MyMouseListener();

//        add(new JButton("test"));

//        setBackground(Color.BLUE);

        this.setLayout(new GridLayout(3, 4));
        generateButtons();
    }

    private void generateButtons() {
        for (int i = 0; i < INITIAL_NUMBER_OF_CARDS; i++) {
            JButton button = new JButton();
//            button.setSize(BUTTON_SIZE);
            button.setIcon(new ImageIcon(testImg)); // test img
            buttonList.add(button);
            button.addMouseListener(myMouseListener);
            this.add(button);
        }
    }

    /**
     * When model has a change, it will be invoked to redraw
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        g.drawImage(testImage, 100, 100, this);
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

class MyMouseListener extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        clickedButton.setBackground(Color.BLACK);

//        ImageIcon test = (ImageIcon) clickedButton.getIcon();
//        System.out.println("--------------------------------------------------------");
//        System.out.println(test.getDescription().toString());
//        System.out.println(test.toString().toString());
//        System.out.println(test.getImage().getSource().toString());
//        System.out.println(test.getImage().toString());



        System.out.println("--------------------------------------------------------");
//        System.out.println(clickedButton.getIcon().getClass().getSimpleName());
//        System.out.println(clickedButton.getIcon().getClass().getName());
//        System.out.println(clickedButton.getIcon().getClass().getCanonicalName());
//        System.out.println(clickedButton.getName());
        System.out.println(clickedButton.getIcon());

    }
}
