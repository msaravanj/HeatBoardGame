package hr.tvz.game.heatgame.util;

import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.model.Car;
import hr.tvz.game.heatgame.model.GameData;
import hr.tvz.game.heatgame.ui.ScreenManager;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;


public class RankingTableUtils {

    public static List<Car> rerankTable(List<Car> table) {
        table.sort((car1, car2) -> {
            if (car1.getLoop() != car2.getLoop()) {
                return Integer.compare(car2.getLoop(), car1.getLoop());
            } else if (car1.getTrackPosition() != car2.getTrackPosition()) {
                return Integer.compare(car2.getTrackPosition(), car1.getTrackPosition());
            } else {
                return Integer.compare(car1.getPositionInTrack(), car2.getPositionInTrack());
            }
        });
        return table;
    }

    public static void checkIsGameOver(GameData gameData){
        List<Car> cars = gameData.getCars();
        for (Car car : cars) {
            if (car.getLoop() > gameData.getNumberOfLoops()) {
                cars = RankingTableUtils.rerankTable(cars);
                showGameOver(cars);
                break;
            }
        }
    }

    private static void showGameOver(List<Car> cars) {
        Dialog<Void> dialog = new Dialog<>();

        dialog.setTitle("GAME OVER");
        dialog.setHeaderText("🏆 Winner: " + cars.get(0).getName());

        VBox content = new VBox(10);
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            content.getChildren().add(new Label((i + 1) + ". " + car.getName()));
        }

        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.setOnCloseRequest(event -> {
            ScreenManager.switchScreen(ScreenType.MAIN_MENU);
        });
        dialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            ScreenManager.switchScreen(ScreenType.MAIN_MENU);
        });

        dialog.showAndWait();
    }
}

