package stacs.starcade.frontend.view.sub.info.sub;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IClientModel;

import javax.swing.*;
import java.awt.*;

public class PlayerInfoPane extends JPanel{
    private IClientModel model;
    private IController controller;

    public PlayerInfoPane(IClientModel model, IController controller) {
        this.model = model;
        this.controller = controller;

        setLayout(new GridLayout(2,2));

        JLabel nameField = new JLabel("Name: ", SwingConstants.RIGHT);
        JLabel nameInput = new JLabel(model.getPlayerName());
        JLabel idField = new JLabel("ID: ", SwingConstants.RIGHT);
        JLabel idInput = new JLabel(model.getPlayerId().toString());
        nameField.setFont(new Font("Serif", Font.BOLD, 25));
        nameInput.setFont(new Font("Serif", Font.BOLD, 25));
        idField.setFont(new Font("Serif", Font.BOLD, 25));
        idInput.setFont(new Font("Serif", Font.BOLD, 25));

        add(nameField);
        add(nameInput);
        add(idField);
        add(idInput);
    }


}
