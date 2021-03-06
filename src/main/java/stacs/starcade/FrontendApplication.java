package stacs.starcade;

import stacs.starcade.frontend.controller.FrontendController;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.view.FrontendView;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Entry method for frontend
 */
public class FrontendApplication {
    public static void main(String[] args) {
        FrontendModel setGameModel = new FrontendModel();
        FrontendController controller = new FrontendController(setGameModel);
        new FrontendView(setGameModel, controller);
    }
}
