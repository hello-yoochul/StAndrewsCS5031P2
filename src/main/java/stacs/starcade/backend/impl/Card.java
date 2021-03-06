package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.IPlayer;

public class Card implements ICard {

    private IPlayer owner;
    private Colour colour;
    private Shape shape;
    private LineStyle lineStyle;
    private Number number;

    @Override
    public void setOwner(IPlayer owner) {
        this.owner = owner;
    }

    @Override
    public void setColour(Colour colour) {
        this.colour = colour;
    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void setNumber(Number number) {
        this.number = number;
    }

    @Override
    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    @Override
    public IPlayer getOwner() {
        return this.owner;
    }

    @Override
    public Number getNumber() {
        return this.number;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public LineStyle getLineStyle() {
        return this.lineStyle;
    }

    @Override
    public Colour getColour() {
        return this.colour;
    }
}
