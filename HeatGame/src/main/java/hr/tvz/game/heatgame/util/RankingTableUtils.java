package hr.tvz.game.heatgame.util;

import hr.tvz.game.heatgame.model.Car;
import javafx.collections.ObservableList;


public class RankingTableUtils {

    public static void rerankTable(ObservableList<Car> table) {
        table.sort((car1, car2) -> {
            if (car1.getLoop() != car2.getLoop()) {
                return Integer.compare(car2.getLoop(), car1.getLoop());
            } else if (car1.getTrackPosition() != car2.getTrackPosition()) {
                return Integer.compare(car2.getTrackPosition(), car1.getTrackPosition());
            } else {
                return Integer.compare(car1.getPositionInTrack(), car2.getPositionInTrack());
            }
        });
    }

}

