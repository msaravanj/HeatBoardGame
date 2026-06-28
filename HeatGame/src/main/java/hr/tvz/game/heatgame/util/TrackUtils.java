package hr.tvz.game.heatgame.util;

import hr.tvz.game.heatgame.model.TrackPoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class TrackUtils {

    private static final double FIELD_WIDTH = 34;
    private static final double DESIRED_FIELD_LENGTH = 55;
    private static List<TrackPoint> path;
    private static List<TrackPoint> fields;
    private static int separatorCounter = 0;

    public static void drawTrack(GraphicsContext gc) {
        drawBorders(gc);
        drawCenterLine(gc);
        drawFieldSeparators(gc);
    }

    private static void drawBorders(GraphicsContext gc) {

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(40);

        gc.beginPath();
        gc.moveTo(220, 100);
        gc.lineTo(1090, 100);

        gc.bezierCurveTo(1160, 100, 1200, 140, 1200, 200);
        gc.lineTo(1200, 350);

        gc.bezierCurveTo(1200, 410, 1160, 450, 1100, 450);
        gc.lineTo(200, 450);

        gc.bezierCurveTo(140, 450, 100, 410, 100, 350);
        gc.lineTo(100, 200);

        gc.bezierCurveTo(100, 140, 140, 100, 200, 100);

        gc.stroke();

        gc.beginPath();
        gc.moveTo(230, 130);
        gc.lineTo(1060, 130);

        gc.bezierCurveTo(1130, 130, 1170, 160, 1170, 230);
        gc.lineTo(1170, 320);

        gc.bezierCurveTo(1170, 390, 1130, 420, 1070, 420);
        gc.lineTo(230, 420);

        gc.bezierCurveTo(160, 420, 130, 390, 130, 320);
        gc.lineTo(130, 230);

        gc.bezierCurveTo(130, 160, 160, 130, 230, 130);

        gc.stroke();
    }

    private static void drawCenterLine(GraphicsContext gc) {

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        gc.setLineDashes(20, 15);

        gc.beginPath();

        gc.moveTo(235, 115);
        gc.lineTo(1075, 115);

        gc.bezierCurveTo(1145, 115, 1185, 155, 1185, 215);
        gc.lineTo(1185, 345);

        gc.bezierCurveTo(1185, 405, 1145, 435, 1085, 435);
        gc.lineTo(215, 435);

        gc.bezierCurveTo(145, 435, 115, 395, 115, 335);
        gc.lineTo(115, 215);

        gc.bezierCurveTo(115, 155, 145, 115, 215, 115);

        gc.stroke();

        gc.setLineDashes();
    }

     private static void drawFieldSeparators(GraphicsContext gc) {

          List<TrackPoint> centerPath = buildCenterPath();
          path = new ArrayList<>();
          fields = new ArrayList<>();

          double totalLength = calculateLength(centerPath);

          int fieldCount = (int) (totalLength / DESIRED_FIELD_LENGTH);

          double fieldLength = totalLength / fieldCount;

          gc.setStroke(Color.WHITE);
          gc.setLineWidth(2);


          drawSeparator(gc, centerPath.get(0), centerPath.get(1));
          path.add(centerPath.get(0));
          fields.add(centerPath.get(0));

          double travelled = 0;
          double targetDistance = fieldLength;

           for (int i = 1; i < centerPath.size() && path.size() < 47; i++) {

               TrackPoint prev = centerPath.get(i - 1);
               TrackPoint curr = centerPath.get(i);

               double segmentLength = Math.hypot(
                       curr.getX() - prev.getX(),
                       curr.getY() - prev.getY());

               while (travelled + segmentLength >= targetDistance && path.size() < 47) {

                   double ratio = (targetDistance - travelled) / segmentLength;

                   double x = prev.getX() + (curr.getX() - prev.getX()) * ratio;
                   double y = prev.getY() + (curr.getY() - prev.getY()) * ratio;

                   TrackPoint point = new TrackPoint(x, y);

                    drawSeparator(gc, point, curr);
                    path.add(point);
                    fields.add(point);

                    targetDistance += fieldLength;
                }

                travelled += segmentLength;
            }

         List<TrackPoint> rotatedPoints = new ArrayList<>(fields.size());
         rotatedPoints.addAll(fields.subList(8, fields.size()));
         rotatedPoints.addAll(fields.subList(0, 8));
         fields.clear();
         fields.addAll(rotatedPoints);
      }

     private static void drawSeparator(
             GraphicsContext gc,
             TrackPoint point,
             TrackPoint next) {

        separatorCounter++;
         double dx = next.getX() - point.getX();
         double dy = next.getY() - point.getY();

         double len = Math.hypot(dx, dy);

         if (len == 0) {
             return;
         }

         dx /= len;
         dy /= len;

         double nx = -dy;
         double ny = dx;

         if (separatorCounter == 8 || separatorCounter == 55) {
             gc.setLineWidth(5);
             gc.strokeLine(point.getX() - nx * FIELD_WIDTH, point.getY() - ny * FIELD_WIDTH,
                     point.getX() + nx * FIELD_WIDTH, point.getY() + ny * FIELD_WIDTH
             );
             gc.setLineWidth(2);
         } else {
         gc.strokeLine(point.getX() - nx * FIELD_WIDTH, point.getY() - ny * FIELD_WIDTH,
                 point.getX() + nx * FIELD_WIDTH, point.getY() + ny * FIELD_WIDTH
         );}
     }

     private static List<TrackPoint> buildCenterPath() {

         List<TrackPoint> points = new ArrayList<>();

         addLine(points,
                 new TrackPoint(235, 115),
                 new TrackPoint( 1075, 115));

         addBezier(points,
                 new TrackPoint(1075, 115),
                 new TrackPoint( 1145, 115),
                 new TrackPoint( 1185, 155),
                 new TrackPoint( 1185, 215));

         addLine(points,
                 new TrackPoint( 1185, 215),
                 new TrackPoint( 1185, 345));

         addBezier(points,
                 new TrackPoint( 1185, 345),
                 new TrackPoint( 1185, 405),
                 new TrackPoint( 1145, 435),
                 new TrackPoint( 1085, 435));

         addLine(points,
                 new TrackPoint( 1085, 435),
                 new TrackPoint( 215, 435));

         addBezier(points,
                 new TrackPoint( 215, 435),
                 new TrackPoint( 145, 435),
                 new TrackPoint( 115, 395),
                 new TrackPoint( 115, 335));

         addLine(points,
                 new TrackPoint( 115, 335),
                 new TrackPoint( 115, 215));

         addBezier(points,
                 new TrackPoint( 115, 215),
                 new TrackPoint( 115, 155),
                 new TrackPoint( 145, 115),
                 new TrackPoint( 215, 115));

         return points;
     }

     private static void addLine(
             List<TrackPoint> points,
             TrackPoint start,
             TrackPoint end) {

         double distance = Math.hypot(
                 end.getX() - start.getX(),
                 end.getY() - start.getY());

         int samples = Math.max(2, (int) (distance / 3));

         for (int i = 0; i <= samples; i++) {

             double t = i / (double) samples;

             double x = start.getX() + (end.getX() - start.getX()) * t;

             double y = start.getY() + (end.getY() - start.getY()) * t;

             points.add(new TrackPoint(x, y));
         }
     }

     private static void addBezier(
             List<TrackPoint> points,
             TrackPoint p0,
             TrackPoint p1,
             TrackPoint p2,
             TrackPoint p3) {

         for (double t = 0; t <= 1; t += 0.01) {

             double x = Math.pow(1 - t, 3) * p0.getX()
                             + 3 * Math.pow(1 - t, 2) * t * p1.getX()
                             + 3 * (1 - t) * t * t * p2.getX()
                             + t * t * t * p3.getX();

             double y = Math.pow(1 - t, 3) * p0.getY()
                             + 3 * Math.pow(1 - t, 2) * t * p1.getY()
                             + 3 * (1 - t) * t * t * p2.getY()
                             + t * t * t * p3.getY();

             points.add(new TrackPoint(x, y));
         }
     }

     private static double calculateLength(List<TrackPoint> points) {

         double length = 0;

         for (int i = 1; i < points.size(); i++) {
             TrackPoint prev = points.get(i - 1);
             TrackPoint curr = points.get(i);
             length += Math.hypot(curr.getX() - prev.getX(), curr.getY() - prev.getY());
         }
         return length;
     }

     public static List<TrackPoint> getFields() {
        return fields;
     }
}