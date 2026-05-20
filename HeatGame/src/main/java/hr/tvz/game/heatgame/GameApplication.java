package hr.tvz.game.heatgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("fxml/gameBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 540);
        stage.setTitle("Heat: Pedal to the Metal");
        stage.setScene(scene);
        stage.show();
    }
}
