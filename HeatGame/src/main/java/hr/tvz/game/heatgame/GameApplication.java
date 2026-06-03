package hr.tvz.game.heatgame;

import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.ui.ScreenManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        ScreenManager screenManager =
                ScreenManager.getInstance();

        screenManager.setStage(stage);
        screenManager.loadScreens();
        screenManager.switchScreen(ScreenType.MAIN_MENU);

        stage.setTitle("Heat: Pedal to the Metal");
    }
}
