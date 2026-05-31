module hr.tvz.game.heatgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens hr.tvz.game.heatgame to javafx.fxml;
    exports hr.tvz.game.heatgame;
    exports hr.tvz.game.heatgame.ui;
    opens hr.tvz.game.heatgame.ui to javafx.fxml;
    exports hr.tvz.game.heatgame.ui.controllers;
    opens hr.tvz.game.heatgame.ui.controllers to javafx.fxml;

}