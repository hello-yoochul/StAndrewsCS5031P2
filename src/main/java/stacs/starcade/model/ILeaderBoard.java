package stacs.starcade.model;

import java.time.Duration;

public interface ILeaderBoard {

    Integer getPlayerId();
    String getPlayerName();
    Duration getPlayerTime();


}
