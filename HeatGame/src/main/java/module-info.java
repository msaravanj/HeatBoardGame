module hr.tvz.game.heatgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.tvz.game.heatgame to javafx.fxml;
    exports hr.tvz.game.heatgame;
    exports hr.tvz.game.heatgame.ui.controllers;
    opens hr.tvz.game.heatgame.ui.controllers to javafx.fxml;
}