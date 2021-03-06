package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.IPlayer;

public class Card implements ICard {

    private IPlayer owner;

    @Override
    public void setOwner(IPlayer owner) {
        this.owner = owner;
    }

    /**
     * Gets the shape on the card.
     * @return enum representing a TRIANGLE, SQUARE or CIRCLE shape
     */
    @Override
    public void setColour(int i) {

    }

    /**
     * Gets the shading of the card.
     * @return enum representing a DOTTED, DASHED or SOLID shading
     */
    @Override
    public void setShape(int j) {

    }

    /**
     * Gets the colour of the card.
     * @return enum representing a BLUE, GREEN or WHITE colour
     */
    @Override
    public void setNumber(int k) {

    }

    /**
     * Sets the colour of the card.
     * @param colour enum of either BLUE, GREEN or WHITE
     */
    @Override
    public void setLineStyle(int l) {

    }

    /**
     * Sets the shape on the card.
     * @param shape enum of either TRIANGLE, SQUARE or CIRCLE
     */
    @Override
    public IPlayer getOwner() {
        return this.owner;
    }

    /**
     * Sets the card number of the current card.
     * @param number enum of either ONE, TWO or THREE
     */
    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int getShape() {
        return 0;
    }

    /**
     * Sets the Line Type of the card.
     * @param lineType enum of either DOTTED, DASHED or SOLID
     */
    @Override
    public int getLineStyle() {
        return 0;
    }

    @Override
    public int getColour() {
        return 0;
    }
}
