package hr.tvz.game.heatgame.ui.controllers;

import hr.tvz.game.heatgame.util.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameController {

    @FXML
    private Button button;

    @FXML
    private void onButtonClick() {
        DialogUtils.showDialog("Button Clicked", "You clicked the button!");
    }
}
