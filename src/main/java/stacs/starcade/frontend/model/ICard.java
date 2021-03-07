package stacs.starcade.frontend.model;

public interface ICard {
    enum Colour {BLUE, GREEN, RED}
    enum Number {ONE, TWO, THREE}
    enum Shape {TRIANGLE, SQUARE, CIRCLE}
    enum LineType {DOTTED, DASHED, SOLID}

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
    LineType getLineType();

    /**
     * Gets the colour of the card.
     * @return enum representing a BLUE, GREEN or WHITE colour
     */
    Colour getColour();

    /**
     * Sets the colour of the card.
     * @param colour enum of either BLUE, GREEN or WHITE
     */
    void setColour(Colour colour);

    /**
     * Sets the shape on the card.
     * @param shape enum of either TRIANGLE, SQUARE or CIRCLE
     */
    void setShape(Shape shape);

    /**
     * Sets the card number of the current card.
     * @param number enum of either ONE, TWO or THREE
     */
    void setNumber(Number number);

    /**
     * Sets the Line Type of the card.
     * @param lineType enum of either DOTTED, DASHED or SOLID
     */
    void setLineType(LineType lineType);
}
