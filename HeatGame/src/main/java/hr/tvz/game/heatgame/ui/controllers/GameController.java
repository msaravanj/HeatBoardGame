package hr.tvz.game.heatgame.ui.controllers;

import hr.tvz.game.heatgame.engine.GameEngine;
import hr.tvz.game.heatgame.enums.CarColor;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private static final double DEFAULT_WIDTH = 1300.0;
    private static final double DEFAULT_HEIGHT = 500.0;

    GameEngine gameEngine;

    @FXML
    private Canvas canvas;

    @FXML
    private Button moveCarButton;

    @FXML
    private ChoiceBox<Integer> choiceBox;

    public void onMoveCarClicked(){
            gameEngine.moveCar(gameEngine.getGameData().getCars().get(gameEngine.getGameData().getCurrentCarIndex()), (Integer) choiceBox.getValue());
    }

    public void refresh() {
        if (canvas == null) {
            return;
        }

        if (canvas.getWidth() <= 0) {
            canvas.setWidth(DEFAULT_WIDTH);
        }

        if (canvas.getHeight() <= 0) {
            canvas.setHeight(DEFAULT_HEIGHT);
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Map<CarColor, Image> carImages = new EnumMap<>(CarColor.class);
        for (CarColor color : CarColor.values()) {
            var stream = getClass().getResourceAsStream(color.path);
            if (stream != null) {
                carImages.put(color, new Image(stream));
            }
        }

        gameEngine = new GameEngine(gc, carImages);
        gameEngine.startGame();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveCarButton = new Button("Move Car");
        choiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        choiceBox.setValue(1);
        refresh();
    }
}