package hr.tvz.game.heatgame.util;

import hr.tvz.game.heatgame.model.TrackPoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TrackUtils {

    public static void drawTrack(GraphicsContext gc, List<TrackPoint> trackPoints) {
        // VANJSKA TRAKA
        gc.setLineWidth(30);
        gc.beginPath();

        gc.moveTo(220, 100);
        gc.lineTo(1090, 100);
        // gornji desni kut
        gc.arcTo(1200, 100, 1200, 200, 110);
        gc.lineTo(1200,350);
        // donji desni kut
        gc.arcTo(1200, 450, 1100, 450, 110);
        gc.lineTo(200, 450);
        // donji lijevi kut
        gc.arcTo(100, 450, 100, 350, 110);
        gc.lineTo(100, 200);
        // gornji lijevi kut
        gc.arcTo(100, 100, 200, 100, 110);
        gc.stroke();

        // UNUTARNJA TRAKA
        gc.beginPath();
        gc.moveTo(230, 130);
        gc.lineTo(1060, 130);

        // gornji desni kut
        gc.arcTo(1170, 130, 1170, 230, 80);
        gc.lineTo(1170, 320);

        // donji desni kut
        gc.arcTo(1170, 420, 1070, 420, 80);
        gc.lineTo(230, 420);

        // donji lijevi kut
        gc.arcTo(130, 420, 130, 320, 80);
        gc.lineTo(130, 230);

        // gornji lijevi kut
        gc.arcTo(130, 130, 230, 130, 80);
        gc.stroke();

        // BIJELA ISPREKIDANA LINIJA
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        gc.setLineDashes(20, 15); // 20 px crta, 15 px razmak

        gc.beginPath();

        gc.moveTo(235, 115);
        gc.lineTo(1075, 115);

        // gornji desni kut
        gc.arcTo(1185, 115, 1185, 215, 95);

        gc.lineTo(1185, 344);

        // donji desni kut
        gc.arcTo(1185, 435, 1085, 435, 95);

        gc.lineTo(215, 435);

        // donji lijevi kut
        gc.arcTo(115, 435, 115, 335, 95);

        gc.lineTo(115, 215);

        // gornji lijevi kut
        gc.arcTo(115, 115, 215, 115, 95);
        gc.stroke();

        // pune linije za ostalo crtanje
        gc.setLineDashes();

        // SEGMENTI
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);

        double trackWidth = 29; // polovica širine između unutarnjeg i vanjskog ruba
        double midpointTolerance = 3;
        double[][] curveMidpoints = new double[][] {
                {1157, 143},
                {1157, 407},
                {143, 407},
                {143, 143}
        };
        double straightTolerance = 3;
        double topY = 115;
        double bottomY = 435;
        double leftX = 115;
        double rightX = 1185;

        for (int i = 0; i < trackPoints.size(); i++) {

            TrackPoint current = trackPoints.get(i);
            TrackPoint previous = trackPoints.get((i - 1 + trackPoints.size()) % trackPoints.size());
            TrackPoint next = trackPoints.get((i + 1) % trackPoints.size());

            boolean isCurveMidpoint = false;
            double toleranceSquared = midpointTolerance * midpointTolerance;
            for (double[] midpoint : curveMidpoints) {
                double mx = midpoint[0];
                double my = midpoint[1];
                double dxMid = current.getX() - mx;
                double dyMid = current.getY() - my;
                if (dxMid * dxMid + dyMid * dyMid <= toleranceSquared) {
                    isCurveMidpoint = true;
                    break;
                }
            }

            double nx;
            double ny;

            if (isCurveMidpoint) {
                // Dijagonala na zavoju (jugozapad-sjeveroistok ili jugoistok-sjeverozapad).
                if ((current.getX() > 1000 && current.getY() > 200) || (current.getX() < 1000 && current.getY() < 200)) {
                    nx = 1;
                    ny = 1;
                } else {
                    nx = -1;
                    ny = 1;
                }
            } else if (Math.abs(current.getY() - topY) <= straightTolerance
                    || Math.abs(current.getY() - bottomY) <= straightTolerance) {
                // gornja/donja ravnina: crte gore-dolje
                nx = 0;
                ny = 1;
            } else if (Math.abs(current.getX() - leftX) <= straightTolerance
                    || Math.abs(current.getX() - rightX) <= straightTolerance) {
                // lijeva/desna ravnina: crte lijevo-desno
                nx = 1;
                ny = 0;
            } else {
                // fallback: koristi smjer između susjednih točaka
                double dx = next.getX() - previous.getX();
                double dy = next.getY() - previous.getY();
                double len = Math.sqrt(dx * dx + dy * dy);
                if (len == 0) {
                    dx = next.getX() - current.getX();
                    dy = next.getY() - current.getY();
                    len = Math.sqrt(dx * dx + dy * dy);
                }
                if (len == 0) {
                    continue;
                }
                nx = -dy / len;
                ny = dx / len;
            }

            double nLen = Math.sqrt(nx * nx + ny * ny);
            if (nLen == 0) {
                continue;
            }
            nx /= nLen;
            ny /= nLen;

            gc.strokeLine(
                    current.getX() - nx * trackWidth,
                    current.getY() - ny * trackWidth,
                    current.getX() + nx * trackWidth,
                    current.getY() + ny * trackWidth
            );
        }
    }

    public static List<TrackPoint> createTrackPoints() {
        List<TrackPoint> trackPoints = new ArrayList<>();

        // gornja ravnina
        trackPoints.add(new TrackPoint(540,115));
        trackPoints.add(new TrackPoint(600,115));
        trackPoints.add(new TrackPoint(660,115));
        trackPoints.add(new TrackPoint(720,115));
        trackPoints.add(new TrackPoint(780,115));
        trackPoints.add(new TrackPoint(840,115));
        trackPoints.add(new TrackPoint(900,115));
        trackPoints.add(new TrackPoint(960,115));
        trackPoints.add(new TrackPoint(1020,115));
        trackPoints.add(new TrackPoint(1080,115));

        // gornji desni zavoj - jedna točka u sredini zavoja
        trackPoints.add(new TrackPoint(1157,143));

        // desna ravnina
        trackPoints.add(new TrackPoint(1185,215));
        trackPoints.add(new TrackPoint(1185,275));
        trackPoints.add(new TrackPoint(1185,335));

        // donji desni zavoj - jedna točka u sredini zavoja
        trackPoints.add(new TrackPoint(1157,407));

        // donja ravnina
        trackPoints.add(new TrackPoint(1080,435));
        trackPoints.add(new TrackPoint(1020,435));
        trackPoints.add(new TrackPoint(960,435));
        trackPoints.add(new TrackPoint(900,435));
        trackPoints.add(new TrackPoint(840,435));
        trackPoints.add(new TrackPoint(780,435));
        trackPoints.add(new TrackPoint(720,435));
        trackPoints.add(new TrackPoint(660,435));
        trackPoints.add(new TrackPoint(600,435));
        trackPoints.add(new TrackPoint(540,435));
        trackPoints.add(new TrackPoint(480,435));
        trackPoints.add(new TrackPoint(420,435));
        trackPoints.add(new TrackPoint(355,435));
        trackPoints.add(new TrackPoint(290,435));
        trackPoints.add(new TrackPoint(225,435));

        // donji lijevi zavoj - jedna točka u sredini zavoja
        trackPoints.add(new TrackPoint(143,407));

        // lijeva ravnina
        trackPoints.add(new TrackPoint(115,335));
        trackPoints.add(new TrackPoint(115,275));
        trackPoints.add(new TrackPoint(115,215));

        // gornji lijevi zavoj - jedna točka u sredini zavoja
        trackPoints.add(new TrackPoint(143,143));

        // završetak kruga
        trackPoints.add(new TrackPoint(225,115));
        trackPoints.add(new TrackPoint(290,115));
        trackPoints.add(new TrackPoint(355,115));
        trackPoints.add(new TrackPoint(420,115));
        trackPoints.add(new TrackPoint(480,115));

        return trackPoints;
    }
}
