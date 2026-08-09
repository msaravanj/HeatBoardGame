package hr.tvz.game.heatgame.model;

import hr.tvz.game.heatgame.enums.CarColor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Data
public class GameData {

    private static final GameData instance = new GameData();

    private int numberOfCars;
    private CarColor selectedColor;
    private String playerName;
    private List<Car> cars = new ArrayList<>();
    private int currentCarIndex;
    private int numberOfLoops;

    private GameData() {}

    public static GameData getInstance() {
        return instance;
    }

}
