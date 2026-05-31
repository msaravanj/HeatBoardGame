package hr.tvz.game.heatgame.model;

import hr.tvz.game.heatgame.enums.SegmentType;
import lombok.Data;

@Data
public class TrackSegment {

    private SegmentType type;
    private int maxSpeed;
    private int segmentNumber;
}
