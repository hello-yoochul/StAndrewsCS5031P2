package stacs.starcade.model;

import javafx.scene.shape.Line;

public interface ICard {
//    int number;
//    int symbol;
//    int shading;
//    int colour;

    static final int TRIANGLE = 1;
    static final int SQUARE = 2;
    static final int CIRCLE = 3;

    static final int BLUE = 1;
    static final int GREEN = 2;
    static final int RED = 3;

    static final int DOTTED = 1;
    static final int DASHED = 2;
    static final int SOLID = 3;

    int getNumber();
    int getSymbol();
    int getShading();
    int getColour();
}
