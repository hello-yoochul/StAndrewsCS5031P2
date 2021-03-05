package stacs.starcade.model;

/**
 * Interface for the representation of a single card.
 */
public interface ICard {
//    int number;
//    int symbol;
//    int shading;
//    int colour;

    int TRIANGLE = 1;
    int SQUARE = 2;
    int CIRCLE = 3;

    int BLUE = 1;
    int GREEN = 2;
    int RED = 3;

    int DOTTED = 1;
    int DASHED = 2;
    int SOLID = 3;

    /**
     * Getter for the card number of the current card.
     * @return integer between 1 and 3
     */
    int getNumber();

    /**
     * Gets the symbol on the card.
     * @return integer representing a TRIANGLE, SQUARE or CIRCLE shape
     */
    int getSymbol();

    /**
     * Gets the shading of the card.
     * @return integer representing a DOTTED, DASHED or SOLID shading
     */
    int getShading();

    /**
     * Gets the colour of the card.
     * @return integer representing a BLUE, GREEN or WHITE colour
     */
    int getColour();
}
