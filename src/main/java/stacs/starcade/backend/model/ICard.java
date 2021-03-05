package stacs.starcade.backend.model;

/**
 * Interface for the representation of a single card.
 */
public interface ICard {
    enum Colour {BLUE, GREEN, RED}
    enum Number {ONE, TWO, THREE}
    enum Shape {TRIANGLE, SQUARE, CIRCLE}
    enum LineType {DOTTED, DASHED, SOLID}

    int TRIANGLE = 0;
    int SQUARE = 1;
    int CIRCLE = 2;

    int BLUE = 0;
    int GREEN = 1;
    int RED = 2;

    int DOTTED = 0;
    int DASHED = 1;
    int SOLID = 2;

    /**
     * Getter for the card number of the current card.
     *
     * @return integer between 1 and 3
     */
    int getNumber();

    /**
     * Gets the symbol on the card.
     *
     * @return integer representing a TRIANGLE, SQUARE or CIRCLE shape
     */
    int getSymbol();

    /**
     * Gets the shading of the card.
     *
     * @return integer representing a DOTTED, DASHED or SOLID shading
     */
    int getShading();

    /**
     * Gets the colour of the card.
     *
     * @return integer representing a BLUE, GREEN or WHITE colour
     */
    int getColour();
}
