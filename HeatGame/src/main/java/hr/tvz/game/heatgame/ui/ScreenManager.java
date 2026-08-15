package hr.tvz.game.heatgame.ui;

import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.model.GameData;
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
        // Load most screens at startup but avoid eagerly loading the GAME screen
        // because its start logic expects GameData to be initialized by the setup screen.
        for (ScreenType screenType : ScreenType.values()) {
            if (screenType == ScreenType.GAME) {
                //  load game lazily when actually switching to it
                continue;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screenType.getFxmlPath()));
            Parent root = loader.load();
            scenes.put(screenType, new Scene(root));
            controllers.put(screenType, loader.getController());
        }
    }

    private static void loadScreen(ScreenType screenType) throws IllegalArgumentException {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(screenType.getFxmlPath()));
            Parent root = loader.load();
            scenes.put(screenType, new Scene(root));
            controllers.put(screenType, loader.getController());
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to load screen: " + screenType, e);
        }
    }

    public static void switchScreen(ScreenType screenType) {

        Scene scene = scenes.get(screenType);

        // lazy-load the requested screen if it wasn't preloaded
        if (scene == null) {
            loadScreen(screenType);
            scene = scenes.get(screenType);
        }

        stage.setScene(scene);
        stage.setResizable(false);

        if (screenType == ScreenType.GAME) {
            Object controller = controllers.get(ScreenType.GAME);
            if (controller instanceof GameController gameController) {
                gameController.refresh();
                gameController.updateRankingsUI();
            }
        }

        stage.show();
    }

    public static void switchScreen(ScreenType screenType, GameData gameData) {

        Scene scene = scenes.get(screenType);

        // lazy-load the requested screen if it wasn't preloaded
        if (scene == null) {
            loadScreen(screenType);
            scene = scenes.get(screenType);
        }

        stage.setScene(scene);
        stage.setResizable(false);

        if (screenType == ScreenType.GAME) {
            Object controller = controllers.get(ScreenType.GAME);
            if (controller instanceof GameController gameController) {
                gameController.getGameEngine().setGameData(gameData);
                gameController.refresh();
                gameController.updateRankingsUI();
            }
        }

        stage.show();
    }

}
