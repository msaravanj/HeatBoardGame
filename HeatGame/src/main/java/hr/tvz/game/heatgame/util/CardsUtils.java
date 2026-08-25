package hr.tvz.game.heatgame.util;

import hr.tvz.game.heatgame.enums.CardType;
import hr.tvz.game.heatgame.model.Card;
import java.util.List;

public class CardsUtils {

    public static List<Card> shuffleCards(List<Card> cards) {
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
        return cards;
    }

    public static List<Card> drawCards(List<Card> cards, int numberOfCards) {
        List<Card> drawnCards = cards.subList(0, numberOfCards);
        cards.subList(0, numberOfCards).clear();
        return drawnCards;
    }


    public static Card selectCard(List<Card> cards, Card selectedCard) {
        cards.remove(selectedCard);
        return selectedCard;
    }

    public static List<Card> generateMainDeck() {
        List<Card> mainDeck = List.of(
                new Card(CardType.SPEED_CARD, 1, "/images/speed1.jpg"),
                new Card(CardType.SPEED_CARD, 1, "/images/speed1.jpg"),
                new Card(CardType.SPEED_CARD, 1, "/images/speed1.jpg"),
                new Card(CardType.SPEED_CARD, 2, "/images/speed2.jpg"),
                new Card(CardType.SPEED_CARD, 2, "/images/speed2.jpg"),
                new Card(CardType.SPEED_CARD, 2, "/images/speed2.jpg"),
                new Card(CardType.SPEED_CARD, 3, "/images/speed3.jpg"),
                new Card(CardType.SPEED_CARD, 3, "/images/speed3.jpg"),
                new Card(CardType.SPEED_CARD, 3, "/images/speed3.jpg"),
                new Card(CardType.SPEED_CARD, 4, "/images/speed4.jpg"),
                new Card(CardType.SPEED_CARD, 4, "/images/speed4.jpg"),
                new Card(CardType.SPEED_CARD, 4, "/images/speed4.jpg"),
                new Card(CardType.HEAT_CARD, 0, "/images/heatCard.jpg"),
                new Card(CardType.UPGRADE_CARD, 0, "/images/upgrade0.jpg"),
                new Card(CardType.UPGRADE_CARD, 5, "/images/upgrade5.jpg"),
                new Card(CardType.STRESS_CARD, 0, "/images/stressCard.jpg"),
                new Card(CardType.STRESS_CARD, 0, "/images/stressCard.jpg"),
                new Card(CardType.STRESS_CARD, 0, "/images/stressCard.jpg")
        );
        return shuffleCards(mainDeck);
    }

    public static List<Card> generateEngineDeck() {
        List<Card> engineDeck = List.of(
                new Card(CardType.HEAT_CARD, 0, "/images/heatCard.jpg"),
                new Card(CardType.HEAT_CARD, 0, "/images/heatCard.jpg"),
                new Card(CardType.HEAT_CARD, 0, "/images/heatCard.jpg"),
                new Card(CardType.HEAT_CARD, 0, "/images/heatCard.jpg"),
                new Card(CardType.HEAT_CARD, 0, "/images/heatCard.jpg"),
                new Card(CardType.HEAT_CARD, 0, "/images/heatCard.jpg")
        );
        return engineDeck;
    }
}
