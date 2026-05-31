package hr.tvz.game.heatgame.model;

import lombok.Data;

@Data
public class Gearbox {

    private int currentGear;
    private int gearChangeLastRound;
    private final int maxGear = 4;
    private final int minGear = 1;
}
