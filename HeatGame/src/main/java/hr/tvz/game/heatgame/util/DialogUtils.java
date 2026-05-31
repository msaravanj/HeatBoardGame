package hr.tvz.game.heatgame.util;

import javafx.scene.control.Alert;

public class DialogUtils {

    public static void showDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
