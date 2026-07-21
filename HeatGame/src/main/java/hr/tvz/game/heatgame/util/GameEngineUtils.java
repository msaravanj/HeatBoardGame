package hr.tvz.game.heatgame.util;

import hr.tvz.game.heatgame.enums.CarColor;
import hr.tvz.game.heatgame.model.Car;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Map;

public class GameEngineUtils {


    public static void fixCarOrientation(GraphicsContext gc, Car car, double x, double y, Map<CarColor, Image> carImagesMap) {
        double rotation = 0;

        // gornja dionica
        if (car.getTrackPosition() > 38 || car.getTrackPosition() < 8) {
            rotation = 90;
            if (car.getPositionInTrack() == 0) {
                y -= 45;
            } else {
                y -= 5;
            }
            // donja dionica
        }  else if (car.getTrackPosition() > 15 && car.getTrackPosition() < 32) {
            rotation = 270;
            if (car.getPositionInTrack() == 0) {
                y += 45;
            } else {
                y += 5;
            }
            // desna dionica
        } else if (car.getTrackPosition() > 9 &&  car.getTrackPosition() <= 13) {
            rotation = 180;
            if (car.getPositionInTrack() == 0) {
                x += 45;
            } else {
                x += 5;
            }
            // lijeva dionica
        } else if (car.getTrackPosition() > 33 && car.getTrackPosition() < 37) {
            rotation = 0;
            if (car.getPositionInTrack() == 0) {
                x -= 45;
            } else {
                x -= 5;
            }
            // KRIVINE
        } else if (car.getTrackPosition() == 9) {
            rotation = 135;
            if (car.getPositionInTrack() == 0) {
                x += 35;
                y -= 35;
            } else {
                y -= 5;
            }
        } else if (car.getTrackPosition() == 14) {
            rotation = 205;
            if (car.getPositionInTrack() == 0) {
                x += 40;
                y += 30;
            }
            else {
                x += 15;
            }
        } else if (car.getTrackPosition() == 15) {
            rotation = 245;
            if (car.getPositionInTrack() == 0) {
                x += 25;
                y += 45;
            } else {
                x += 5;
                y += 5;
            }
        } else if (car.getTrackPosition() == 33) {
            rotation = 315;
            if (car.getPositionInTrack() == 0) {
                x -= 35;
                y += 40;
            } else {
                x -= 5;
                y += 5;
            }
        } else if (car.getTrackPosition() == 8) {
            rotation = 105;
            if (car.getPositionInTrack() == 0) {
                y -= 45;
                x += 5;
            } else {
                y -= 7;
            }
        } else if (car.getTrackPosition() == 32) {
            rotation = 290;
            if (car.getPositionInTrack() == 0) {
                y += 45;
                x -= 5;
            } else {
                y += 5;
            }
        } else if (car.getTrackPosition() == 37) {
            rotation = 20;
            if (car.getPositionInTrack() == 0) {
                x -= 42;
                y -= 10;
            }
        } else if (car.getTrackPosition() == 38) {
            rotation = 45;
            if (car.getPositionInTrack() == 0) {
                x -= 35;
                y -= 30;
            } else {
                y -= 7;
                x -= 8;
            }
        }

        gc.save();
        gc.translate(x, y);
        gc.rotate(rotation);
        gc.drawImage(carImagesMap.get(car.getColor()), 0, 0, 50, 50);
        gc.restore();

    }
}
