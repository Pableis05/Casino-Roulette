module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.example.roulette.ui;
    opens com.example.roulette.ui to javafx.fxml;
    exports com.example.roulette.controller;
    opens com.example.roulette.controller to javafx.fxml;
}