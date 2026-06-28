package hr.tvz.game.heatgame.ui.controllers;

import hr.tvz.game.heatgame.enums.CarColor;
import hr.tvz.game.heatgame.model.Car;
import hr.tvz.game.heatgame.model.TrackPoint;
import hr.tvz.game.heatgame.model.GameData;
import hr.tvz.game.heatgame.util.TrackUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private static final double DEFAULT_WIDTH = 1300.0;
    private static final double DEFAULT_HEIGHT = 500.0;

    @FXML
    private Canvas canvas;

    private GameData gameData;

    private List<Car> cars;

    private List<TrackPoint> trackPoints;

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

        TrackUtils.drawTrack(gc);

        gameData = GameData.getInstance();
        cars = gameData.getCars() != null ? gameData.getCars() : new ArrayList<>();

        Map<CarColor, Image> carImages = new HashMap<>();
        for (CarColor color : CarColor.values()) {
            carImages.put(color, new Image(getClass().getResourceAsStream(color.path)));
        }

        List<TrackPoint> points = TrackUtils.getFields();

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            Image carImage = carImages.get(car.getColor());
            int fieldIndex = 46 - (i / 2);
            int positionInField = i % 2;

            TrackPoint basePoint = points.get(fieldIndex);
            double x = basePoint.getX();
            double y = basePoint.getY();

            if (positionInField == 0) {
                y -= 45;
            } else {
                y -= 5;
            }

            gc.save();
            gc.translate(x, y);
            gc.rotate(90);
            gc.drawImage(carImage, 0, 0, 50, 50);
            gc.restore();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
}