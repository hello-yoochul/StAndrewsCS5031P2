package stacs.starcade.frontend.model;

public class Card implements ICard {

    /**
     * Getter for the card number of the current card.
     * @return enum representing ONE, TWO or THREE
     */
    @Override
    public Number getNumber() {
        return null;
    }

    /**
     * Gets the shape on the card.
     * @return enum representing a TRIANGLE, SQUARE or CIRCLE shape
     */
    @Override
    public Shape getShape() {
        return null;
    }

    /**
     * Gets the shading of the card.
     * @return enum representing a DOTTED, DASHED or SOLID shading
     */
    @Override
    public LineType getLineType() {
        return null;
    }

    /**
     * Gets the colour of the card.
     * @return enum representing a BLUE, GREEN or WHITE colour
     */
    @Override
    public Colour getColour() {
        return null;
    }

    /**
     * Sets the colour of the card.
     * @param colour enum of either BLUE, GREEN or WHITE
     */
    @Override
    public void setColour(Colour colour) {

    }

    /**
     * Sets the shape on the card.
     * @param shape enum of either TRIANGLE, SQUARE or CIRCLE
     */
    @Override
    public void setShape(Shape shape) {

    }

    /**
     * Sets the card number of the current card.
     * @param number enum of either ONE, TWO or THREE
     */
    @Override
    public void setNumber(Number number) {

    }

    /**
     * Sets the Line Type of the card.
     * @param lineType enum of either DOTTED, DASHED or SOLID
     */
    @Override
    public void setLineType(LineType lineType) {

    }
}
