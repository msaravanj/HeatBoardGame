package hr.tvz.game.heatgame.ui.controllers;

import hr.tvz.game.heatgame.engine.GameEngine;
import hr.tvz.game.heatgame.enums.CarColor;
import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.model.Car;
import hr.tvz.game.heatgame.model.GameData;
import hr.tvz.game.heatgame.ui.ScreenManager;
import hr.tvz.game.heatgame.util.GameSaveManager;
import hr.tvz.game.heatgame.util.RankingTableUtils;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Data;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Data
public class GameController implements Initializable {

    private static final double DEFAULT_WIDTH = 1300.0;
    private static final double DEFAULT_HEIGHT = 500.0;

    GameEngine gameEngine;
    ObservableList<Car> observableList;

    @FXML
    private Canvas canvas;

    @FXML
    private HBox rankingHBox;

    @FXML
    private HBox orderNumberHBox;

    @FXML
    private Button moveCarButton;

    @FXML
    private ChoiceBox<Integer> choiceBox;

    @FXML
    private MenuItem saveGameMenuItem;

    @FXML
    private MenuItem loadGameMenuItem;

    @FXML
    private MenuItem exitGameMenuItem;


    public void onMoveCarClicked(){
        gameEngine.moveCar(gameEngine.getGameData().getCars().get(gameEngine.getGameData().getCurrentCarIndex()), (Integer) choiceBox.getValue());
        RankingTableUtils.rerankTable(observableList);
        RankingTableUtils.checkIsGameOver(gameEngine.getGameData());
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

    private void refreshRanking() {
        rankingHBox.getChildren().clear();

        for (Car car : observableList) {
            Label label = new Label(car.getName());
            label.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 16));
            label.setStyle("-fx-background-color: " + car.getColor());
            label.setPrefSize(130, 30);
            label.setAlignment(Pos.CENTER);
            label.setBorder(Border.stroke(Color.BLACK));

            rankingHBox.getChildren().add(label);
        }
    }

    @FXML
    private void onExitGameClicked() {
        ScreenManager.switchScreen(ScreenType.MAIN_MENU);
    }

    @FXML
    private void onSaveGameClicked() throws IOException {
        GameSaveManager.saveGame(gameEngine.getGameData());
    }

    @FXML
    private void onLoadGameClicked() throws IOException {
        Optional<GameData> loadedGameData = GameSaveManager.loadGame();
        if (loadedGameData.isPresent()) {
            gameEngine.setGameData(loadedGameData.get());
            observableList = FXCollections.observableList(new ArrayList<>(loadedGameData.get().getCars()));
            RankingTableUtils.rerankTable(observableList);
            gameEngine.redrawBoard();
            updateRankingsUI();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveCarButton = new Button("Move Car");
        choiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        choiceBox.setValue(1);
        refresh();
        updateRankingsUI();
    }

    public void updateRankingsUI() {
        List<Car> carList = new ArrayList(gameEngine.getGameData().getCars());
        observableList = FXCollections.observableList(carList);
        observableList.addListener((ListChangeListener<Car>) change -> refreshRanking());

        List<String> numberList = new ArrayList<>(Arrays.asList("1st place", "2nd place", "3rd place", "4th place", "5th place", "6th place"));
        ObservableList<String> obsList = FXCollections.observableList(numberList);
        orderNumberHBox.getChildren().clear();
        refreshRanking();
        for (int i = 0; i < carList.size(); i++) {
            Label label = new Label(obsList.get(i));
            label.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 16));
            label.setPrefSize(130, 30);
            label.setAlignment(Pos.CENTER);
            label.setBorder(Border.stroke(Color.BLACK));
            label.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            orderNumberHBox.getChildren().add(label);
        }
        RankingTableUtils.rerankTable(observableList);
    }
}