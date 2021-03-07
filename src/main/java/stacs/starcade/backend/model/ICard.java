package stacs.starcade.backend.model;

/**
 * Interface for the representation of a single card.
 */
public interface ICard {
    enum Colour {BLUE, GREEN, RED}
    enum Number {NULL, ONE, TWO}
    enum Shape {TRIANGLE, SQUARE, CIRCLE}
    enum LineStyle {DOTTED, DASHED, SOLID}

    /**
     * Sets the owner of a card.
     * @param owner player object
     */
    void setOwner(IPlayer owner);

    /**
     * Sets the colour on the cards.
     * @param colour
     */
    void setColour(Colour colour);

    /**
     * Sets the shape on the cards.
     * @param shape
     */
    void setShape(Shape shape);

    /**
     * Sets the number on the cards.
     * @param number
     */
    void setNumber(Number number);

    /**
     * Sets the line_style on the cards.
     * @param lineStyle
     */
    void setLineStyle(LineStyle lineStyle);

    /**
     * Gets the player who is owning the card.
     * @return the player object
     */
    IPlayer getOwner();

    /**
     * Gets the number on the card.
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
