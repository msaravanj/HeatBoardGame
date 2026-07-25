package hr.tvz.game.heatgame.model;

import hr.tvz.game.heatgame.enums.CarColor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class Car {

    private String name;
    private CarColor color;
    private int speed;
    private int trackPosition;
    private int positionInTrack;
    private int loop;
    private int rank;

    private List<Card> hand;
    private List<Card> playedCards;
    private List<Card> discardedCards;
    private List<HeatCard> engineCards;
    private List<StressCard> stressCards;
    private List<Card> activeDeck;

    public Car(String name, CarColor color){
        this.name = name;
        this.color = color;
        speed = 0;
        trackPosition = 0;
        positionInTrack = 0;
        loop = 0;
        rank = -1;
    }

    @Override
    public String toString() {
        if (rank == -1) {
            return name;
        } else {
            return rank + ". " + name;
        }

    }
}
