package hr.tvz.game.heatgame.ui.controllers;

import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.model.GameData;
import hr.tvz.game.heatgame.ui.ScreenManager;
import hr.tvz.game.heatgame.util.GameSaveManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.Optional;
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


    @FXML
    private void onLoadGameButtonClick(){
        Optional<GameData> loadedGameData = GameSaveManager.loadGame();
        if (loadedGameData.isPresent()) {
            GameData gameData = loadedGameData.get();
            ScreenManager.switchScreen(ScreenType.GAME, gameData);
        } else {
            System.out.println("No saved game found.");
        }
    }

}
