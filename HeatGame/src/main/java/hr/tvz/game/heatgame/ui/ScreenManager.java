package hr.tvz.game.heatgame.ui;

import hr.tvz.game.heatgame.enums.ScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class ScreenManager {

    private static Stage stage;

    private static final Map<ScreenType, Scene> scenes =
            new EnumMap<>(ScreenType.class);

    private ScreenManager() {
    }

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void loadScreens() throws IOException {

        for (ScreenType screenType : ScreenType.values()) {

            FXMLLoader loader =
                    new FXMLLoader(ScreenManager.class.getResource(screenType.getFxmlPath()));

            Parent root = loader.load();

            scenes.put(screenType, new Scene(root));
        }
    }

    public static void switchScreen(ScreenType screenType) {

        Scene scene = scenes.get(screenType);

        if (scene == null) {
            throw new IllegalArgumentException("Screen nije učitan: " + screenType);
        }

        stage.setScene(scene);
        stage.show();
    }

}
