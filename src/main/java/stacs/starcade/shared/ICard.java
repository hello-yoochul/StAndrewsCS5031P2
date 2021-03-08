package stacs.starcade.shared;

/**
 * Interface for the representation of a single card.
 */
public interface ICard {
    enum Colour {BLUE, GREEN, RED}
    enum Number {ONE, TWO, THREE}
    enum Shape {TRIANGLE, SQUARE, CIRCLE}
    enum LineStyle {DOTTED, DASHED, SOLID}

    /**
     * Sets the colour on the cards.
     * @param colour set card colour
     */
    void setColour(Colour colour);

    /**
     * Sets the shape on the cards.
     * @param shape the set card shape
     */
    void setShape(Shape shape);

    /**
     * Sets the number on the cards.
     * @param number the set card number
     */
    void setNumber(Number number);

    /**
     * Sets the line_style on the cards.
     * @param lineStyle the set card line_style
     */
    void setLineStyle(LineStyle lineStyle);

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
