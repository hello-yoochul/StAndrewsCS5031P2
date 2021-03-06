package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.IView;

public class View implements IView {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

}
