package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;

public class CurrentSetPane extends JPanel {

    FrontendModel model;
    Controller controller;

    public CurrentSetPane(FrontendModel model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }
}
