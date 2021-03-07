package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.IPlayer;

public class Card implements ICard {

    private IPlayer owner;
    private Colour colour;
    private Shape shape;
    private LineStyle lineStyle;
    private Number number;

    /**
     * Sets the owner of a card.
     * @param owner player object
     */
    @Override
    public void setOwner(IPlayer owner) { this.owner = owner; }

    /**
     * Sets the colour on the cards.
     *
     * @param colour
     */
    @Override
    public void setColour(Colour colour) { this.colour = colour; }

    /**
     * Sets the shape on the cards.
     *
     * @param shape
     */
    @Override
    public void setShape(Shape shape) { this.shape = shape; }

    /**
     * Sets the number on the cards.
     *
     * @param number
     */
    @Override
    public void setNumber(Number number) { this.number = number; }

    /**
     * Sets the line_style on the cards.
     *
     * @param lineStyle
     */
    @Override
    public void setLineStyle(LineStyle lineStyle) { this.lineStyle = lineStyle; }

    /**
     * Gets the player who is owning the card.
     *
     * @return the player object
     */
    @Override
    public IPlayer getOwner() { return this.owner; }

    /**
     * Gets the number on the card.
     *
     * @return enum representing ONE, TWO or THREE
     */
    @Override
    public Number getNumber() { return this.number; }

    /**
     * Gets the shape on the card.
     *
     * @return enum representing a TRIANGLE, SQUARE or CIRCLE shape
     */
    @Override
    public Shape getShape() { return this.shape; }

    /**
     * Gets the shading of the card.
     *
     * @return enum representing a DOTTED, DASHED or SOLID shading
     */
    @Override
    public LineStyle getLineStyle() { return this.lineStyle; }

    /**
     * Gets the colour of the card.
     *
     * @return enum representing a BLUE, GREEN or WHITE colour
     */
    @Override
    public Colour getColour() { return this.colour; }
}
