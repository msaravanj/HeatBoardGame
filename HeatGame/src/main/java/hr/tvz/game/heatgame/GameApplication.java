package hr.tvz.game.heatgame;

import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.ui.ScreenManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        ScreenManager.setStage(stage);
        ScreenManager.loadScreens();
        ScreenManager.switchScreen(ScreenType.MAIN_MENU);
    }
}
