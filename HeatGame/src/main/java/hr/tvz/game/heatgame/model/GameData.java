package hr.tvz.game.heatgame.model;

import hr.tvz.game.heatgame.enums.CarColor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class GameData {

    private static final GameData instance = new GameData();

    private int numberOfCars;
    private CarColor selectedColor;
    private String playerName;
    private List<Car> cars;

    private GameData() {}

    public static GameData getInstance() {
        return instance;
    }

}
