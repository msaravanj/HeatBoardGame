package hr.tvz.game.heatgame.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label gameText;

    @FXML
    protected void onHelloButtonClick() {
        gameText.setText("GAME STARTED!");
    }
}
