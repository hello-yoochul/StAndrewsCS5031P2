package stacs.starcade.backend.impl;

import java.util.HashMap;
import stacs.starcade.shared.ICard;

import java.util.ArrayList;

public class Player implements IPlayer {
    private int playerId;
    private int playerName;

    private ArrayList<ICard[]> setsLog;

    public Player(int playerId) {
        setsLog = new ArrayList<>();
        this.playerId = playerId;
    }

    @Override
    public Integer getPlayerId() {
        return playerId;
    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public void setSetsLog(ICard[] threeCards) { setsLog.add(threeCards); }

    @Override
    public ArrayList<ICard[]> getSetsLog() { return this.setsLog; }
}
