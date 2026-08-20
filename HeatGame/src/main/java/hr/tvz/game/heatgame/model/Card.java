package hr.tvz.game.heatgame.model;

import hr.tvz.game.heatgame.enums.CardType;

public record Card(CardType type, int value, String imagePath){}
