package stacs.starcade.backend.impl;

import stacs.starcade.shared.ICard;
import stacs.starcade.backend.model.IPlayer;

import java.util.ArrayList;

public class Player implements IPlayer {

    private ArrayList<ICard[]> setsLog;

    public Player() {
        setsLog = new ArrayList<>();
    }

    @Override
    public Integer getPlayerId() {
        return null;
    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public void setSetsLog(ICard[] threeCards) {
        setsLog.add(threeCards);
    }

    @Override
    public ArrayList<ICard[]> getSetsLog() {
        return this.setsLog;
    }
}
