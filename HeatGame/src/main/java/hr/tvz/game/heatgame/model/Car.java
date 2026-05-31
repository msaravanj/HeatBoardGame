package hr.tvz.game.heatgame.model;

import lombok.Data;

import java.util.List;

@Data
public class Car {

    private String name;
    private String color;
    private int speed;
    private int trackPosition;

    private List<Card> hand;
    private List<Card> playedCards;
    private List<Card> discardedCards;
    private List<HeatCard> engineCards;
    private List<StressCard> stressCards;
    private List<Card> activeDeck;

}
