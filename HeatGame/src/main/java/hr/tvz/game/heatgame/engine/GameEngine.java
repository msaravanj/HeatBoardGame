package hr.tvz.game.heatgame.engine;

import hr.tvz.game.heatgame.enums.CarColor;
import hr.tvz.game.heatgame.model.Car;
import hr.tvz.game.heatgame.model.GameData;
import hr.tvz.game.heatgame.model.TrackPoint;
import hr.tvz.game.heatgame.util.GameEngineUtils;
import hr.tvz.game.heatgame.util.RankingTableUtils;
import hr.tvz.game.heatgame.util.TrackUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import lombok.Data;
import java.util.*;

@Data
public class GameEngine {

    private GameData gameData;
    private List<TrackPoint> fields;
    private GraphicsContext gc;
    private Map<CarColor, Image> carImagesMap;

    public GameEngine(GraphicsContext gc, Map<CarColor, Image> carImagesMap) {
        this.gameData = GameData.getInstance();
        this.fields = TrackUtils.drawTrack(gc);
        this.gc = gc;
        this.carImagesMap = carImagesMap;

    }

    public void startGame(){
        List<Car> cars = gameData.getCars();
        for (int i = 0; i < cars.size(); i++) {
            int fieldIndex = 46 - (i / 2);
            int positionInField = i % 2;
            cars.get(i).setTrackPosition(fieldIndex);
            cars.get(i).setPositionInTrack(positionInField);
        }
        gameData.setCurrentCarIndex(0);
        redrawBoard();
    }


    public void moveCar(Car car, int speed){
        car.setSpeed(speed);
        checkFieldAvailability(car);
        redrawBoard();
        nextPlayer();

    }

    private void nextPlayer(){
        if (gameData.getCurrentCarIndex() >= gameData.getCars().size() - 1) {
            gameData.setCurrentCarIndex(0);
        } else {
            gameData.setCurrentCarIndex(gameData.getCurrentCarIndex() + 1);
        }
    }

    private void checkFieldAvailability(Car car) {
        if (car.getTrackPosition() + car.getSpeed() >= fields.size()) {
            car.setLoop(car.getLoop() + 1);
        }
        int newFieldIndex = (car.getTrackPosition() + car.getSpeed()) % fields.size();
        checkInnerOuter(newFieldIndex, car);
    }

    private void checkInnerOuter(int fieldIndex, Car car) {

        boolean track0Taken = false;
        boolean track1Taken = false;

        for (Car c : gameData.getCars()) {
            if (c == car) {
                continue;
            }

            if (c.getTrackPosition() == fieldIndex) {
                if (c.getPositionInTrack() == 0) {
                    track0Taken = true;
                } else {
                    track1Taken = true;
                }
            }
        }

        if (!track0Taken) {
            car.setTrackPosition(fieldIndex);
            car.setPositionInTrack(0);
            return;
        }

        if (!track1Taken) {
            car.setTrackPosition(fieldIndex);
            car.setPositionInTrack(1);
            return;
        }

        // oba zauzeta, probaj prethodno polje
        checkInnerOuter((fieldIndex - 1 + fields.size()) % fields.size(), car);
    }

    private void redrawBoard() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(javafx.scene.paint.Color.GREEN);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        fields = TrackUtils.drawTrack(gc);

        for (Car car : gameData.getCars()) {
            TrackPoint basePoint = fields.get(car.getTrackPosition());
            double x = basePoint.getX();
            double y = basePoint.getY();

            GameEngineUtils.fixCarOrientation(gc, car, x, y, carImagesMap);
        }
    }

}
