package hr.tvz.game.heatgame.ui.controllers;

import hr.tvz.game.heatgame.enums.CarColor;
import hr.tvz.game.heatgame.enums.ScreenType;
import hr.tvz.game.heatgame.ui.ScreenManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import hr.tvz.game.heatgame.model.GameData;
import hr.tvz.game.heatgame.model.Car;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class GameSetupController implements Initializable {

    @FXML
    private Button startGameButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField playerNameField;

    @FXML
    private ChoiceBox<CarColor> choiceColorBox;

    @FXML
    private ChoiceBox<Integer> choiceNumberOfPlayersBox;

    @FXML
    private void onStartGameButtonClick() {
        // Gather setup data
        String playerName = playerNameField != null ? playerNameField.getText() : "Player 1";
        CarColor selectedColor = choiceColorBox != null && choiceColorBox.getValue() != null
                ? choiceColorBox.getValue() : CarColor.RED;

        // Read number of players from UI control (range 2-6)
        int numberOfPlayers = choiceNumberOfPlayersBox != null && choiceNumberOfPlayersBox.getValue() != null
                ? choiceNumberOfPlayersBox.getValue() : 4;

        // Build list of cars: first car uses chosen name/color; remaining are auto-generated
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(playerName, selectedColor));
        CarColor[] colors = CarColor.values();
        int colorIdx = 0;
        for (int i = 1; i < numberOfPlayers; i++) {
            // pick next color that's not the selected color
            while (colors[colorIdx] == selectedColor) {
                colorIdx = (colorIdx + 1) % colors.length;
            }
            cars.add(new Car("Player " + (i + 1), colors[colorIdx]));
            colorIdx = (colorIdx + 1) % colors.length;
        }

        // Store into shared GameData so the game screen can read it
        GameData.getInstance().setNumberOfCars(numberOfPlayers);
        GameData.getInstance().setCars(cars);
        GameData.getInstance().setPlayerName(playerName);
        GameData.getInstance().setSelectedColor(selectedColor);
        GameData.getInstance().setCurrentCar(cars.get(0));

        ScreenManager.switchScreen(ScreenType.GAME);
    }

    @FXML
    private void onCancelButtonClick() {
        ScreenManager.switchScreen(ScreenType.MAIN_MENU);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (playerNameField != null) {
            playerNameField.setText("John Doe");
        }

        if (choiceColorBox != null) {
            ObservableList<CarColor> colors = FXCollections.observableArrayList(CarColor.values());
            choiceColorBox.setItems(colors);
            if (!colors.isEmpty()) {
                choiceColorBox.getSelectionModel().selectFirst();
            }
        }


        if (choiceNumberOfPlayersBox != null) {
            ObservableList<Integer> numbers = FXCollections.observableArrayList(2, 3, 4, 5, 6);
            choiceNumberOfPlayersBox.setItems(numbers);
            if (!numbers.isEmpty()) {
                choiceNumberOfPlayersBox.getSelectionModel().selectFirst();
            }
        }
    }

}
