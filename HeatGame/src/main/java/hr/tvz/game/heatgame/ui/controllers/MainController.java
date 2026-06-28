package hr.tvz.game.heatgame.ui.controllers;

import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.ui.ScreenManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button newGameButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private void onNewGameButtonClick(){
        ScreenManager.switchScreen(ScreenType.GAME_SETUP);
    }


}
