package hr.tvz.game.heatgame.ui.controllers;

import hr.tvz.game.heatgame.model.TrackPoint;
import hr.tvz.game.heatgame.util.TrackUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private static final double DEFAULT_WIDTH = 1300.0;
    private static final double DEFAULT_HEIGHT = 500.0;

    @FXML
    private Canvas canvas;

    private List<TrackPoint> trackPoints;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (canvas == null) {
            return;
        }

        if (canvas.getWidth() <= 0) {
            canvas.setWidth(DEFAULT_WIDTH);
        }

        if (canvas.getHeight() <= 0) {
            canvas.setHeight(DEFAULT_HEIGHT);
        }


        trackPoints = TrackUtils.createTrackPoints();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        TrackUtils.drawTrack(gc, trackPoints);
    }

}


