package hr.tvz.game.heatgame.enums;

public enum CarColor {
    BLUE("/hr/tvz/game/heatgame/images/blue_car.png"),
    YELLOW("/hr/tvz/game/heatgame/images/yellow_car.png"),
    GREEN("/hr/tvz/game/heatgame/images/green_car.png"),
    RED("/hr/tvz/game/heatgame/images/red_car.png"),
    PURPLE("/hr/tvz/game/heatgame/images/purple_car.png"),
    ORANGE("/hr/tvz/game/heatgame/images/orange_car.png");

    public final String path;

    CarColor(String path) {
        this.path = path;
    }
}


