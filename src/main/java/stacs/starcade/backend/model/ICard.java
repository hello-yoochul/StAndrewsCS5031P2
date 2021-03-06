package stacs.starcade.backend.model;

/**
 * Interface for the representation of a single card.
 */
public interface ICard {
    enum Colour {BLUE, GREEN, RED}
    enum Number {NULL, ONE, TWO}
    enum Shape {TRIANGLE, SQUARE, CIRCLE}
    enum LineStyle {DOTTED, DASHED, SOLID}

    int TRIANGLE = 0;
    int SQUARE = 1;
    int CIRCLE = 2;

    int BLUE = 0;
    int GREEN = 1;
    int RED = 2;

    int DOTTED = 0;
    int DASHED = 1;
    int SOLID = 2;

    int NULL = 0;
    int ONE = 1;
    int TWO = 2;


    void setOwner(IPlayer owner);

    void setColour(Colour colour);

    void setShape(Shape shape);

    void setNumber(Number number);

    void setLineStyle(LineStyle lineStyle);

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
    Shape getShape();

    /**
     * Gets the shading of the card.
     * @return enum representing a DOTTED, DASHED or SOLID shading
     */
    LineStyle getLineStyle();

    /**
     * Gets the colour of the card.
     * @return enum representing a BLUE, GREEN or WHITE colour
     */
    Colour getColour();
}
