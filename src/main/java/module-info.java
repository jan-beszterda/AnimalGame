module group6.animalgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens group6.animalgame to javafx.fxml;
    opens group6.animalgame.controllers to javafx.fxml;
    exports group6.animalgame;
    exports group6.animalgame.controllers;
}