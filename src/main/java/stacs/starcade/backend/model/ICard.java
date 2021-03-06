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

    void setOwner(IPlayer owner);

    void setColour(int i);

    void setShape(int j);

    void setNumber(int k);

    void setLineStyle(int l);

    IPlayer getOwner();

    /**
     * Getter for the card number of the current card.
     * @return enum representing ONE, TWO or THREE
     */
    Number getNumber();

    /**
     * Gets the shape on the card.
     * @return enum representing a TRIANGLE, SQUARE or CIRCLE shape
     */
    int getShape();

    /**
     * Gets the shading of the card.
     * @return enum representing a DOTTED, DASHED or SOLID shading
     */
    int getLineStyle();

    /**
     * Gets the colour of the card.
     * @return enum representing a BLUE, GREEN or WHITE colour
     */
    int getColour();
}
