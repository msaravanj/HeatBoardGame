package hr.tvz.game.heatgame.engine;

import hr.tvz.game.heatgame.enums.CarColor;
import hr.tvz.game.heatgame.model.Car;
import hr.tvz.game.heatgame.model.GameData;
import hr.tvz.game.heatgame.model.TrackPoint;
import hr.tvz.game.heatgame.util.TrackUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
public class GameEngine {

    private GameData gameData;
    private List<TrackPoint> fields;
    private GraphicsContext gc;
    private Map<CarColor, Image> carImagesMap;

    public GameEngine(GraphicsContext gc, Map<CarColor, Image> carImagesMap) {
        this.gameData = GameData.getInstance();
        this.fields = TrackUtils.getFields();
        this.gc = gc;
        this.carImagesMap = carImagesMap;

    }

    public void startGame(){
        List<Car> cars = gameData.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            Image carImage = carImagesMap.get(car.getColor());
            int fieldIndex = 46 - (i / 2);
            int positionInField = i % 2;

            TrackPoint basePoint = fields.get(fieldIndex);
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

    public void moveCar(Car car, int speed){

    }

    public boolean isGameOver() {
        return false;
    }

    public void nextPlayer(){}

    public void setCarsToStartingPositions() {

    }
}
