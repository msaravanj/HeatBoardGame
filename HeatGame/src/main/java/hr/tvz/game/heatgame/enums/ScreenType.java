package hr.tvz.game.heatgame.enums;

public enum ScreenType {

    MAIN_MENU("/hr/tvz/game/heatgame/fxml/main-menu.fxml"),
    GAME("/hr/tvz/game/heatgame/fxml/game.fxml");

    private final String fxmlPath;

    ScreenType(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

}
