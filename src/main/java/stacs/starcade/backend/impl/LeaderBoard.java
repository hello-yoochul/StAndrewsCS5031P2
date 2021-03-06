package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ILeaderBoard;

import java.time.Duration;

public class LeaderBoard implements ILeaderBoard {
    @Override
    public int getPlayerId() {
        return 0;
    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public Duration getPlayerTime() {
        return null;
    }
}
