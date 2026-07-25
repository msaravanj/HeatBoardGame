package hr.tvz.game.heatgame.util;

import hr.tvz.game.heatgame.enums.CarColor;
import hr.tvz.game.heatgame.model.Car;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.paint.Paint;

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

    public static void setItemColor(ListView<Car> listView) {
        listView.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Car car, boolean empty) {
                super.updateItem(car, empty);
                if (empty || car == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(car.getName());
                    setTextFill(Paint.valueOf("#FFFFFF"));
                    setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 20));
                    setStyle("-fx-background-color: " + car.getColor().name() + ";");
                }
            }
        });
    }
}
