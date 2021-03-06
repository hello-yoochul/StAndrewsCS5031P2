package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.IPlayer;

public class Card implements ICard {

    private IPlayer owner;

    @Override
    public void setOwner(IPlayer owner) {
        this.owner = owner;
    }

    @Override
    public void setColour(int i) {

    }

    @Override
    public void setShape(int j) {

    }

    @Override
    public void setNumber(int k) {

    }

    @Override
    public void setLineStyle(int l) {

    }

    @Override
    public IPlayer getOwner() {
        return this.owner;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int getShape() {
        return 0;
    }

    @Override
    public int getLineStyle() {
        return 0;
    }

    @Override
    public int getColour() {
        return 0;
    }
}
