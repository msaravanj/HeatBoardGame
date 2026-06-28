package hr.tvz.game.heatgame.ui;

import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.ui.controllers.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class ScreenManager {

    private static final ScreenManager INSTANCE = new ScreenManager();

    @Setter
    private static Stage stage;

    private static final Map<ScreenType, Scene> scenes =
            new EnumMap<>(ScreenType.class);

    private static final Map<ScreenType, Object> controllers =
            new EnumMap<>(ScreenType.class);

    private ScreenManager() {
    }

    public static ScreenManager getInstance() {
        return INSTANCE;
    }

    public void loadScreens() throws IOException {

        for (ScreenType screenType : ScreenType.values()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screenType.getFxmlPath()));
            Parent root = loader.load();
            scenes.put(screenType, new Scene(root));
            controllers.put(screenType, loader.getController());
        }
    }

    public static void switchScreen(ScreenType screenType) {

        Scene scene = scenes.get(screenType);

        if (scene == null) {
            throw new IllegalArgumentException("Screen not loaded: " + screenType);
        }

        stage.setScene(scene);
        stage.setResizable(false);

        if (screenType == ScreenType.GAME) {
            Object controller = controllers.get(ScreenType.GAME);
            if (controller instanceof GameController gameController) {
                gameController.refresh();
            }
        }

        stage.show();
    }

}
