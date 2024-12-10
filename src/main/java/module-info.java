module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


//    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.Levels to javafx.fxml;
    opens com.example.demo.view to javafx.fxml;
    opens com.example.demo.Levels.levelView to javafx.fxml;
    opens com.example.demo.projectiles to javafx.fxml;
    opens com.example.demo.models to javafx.fxml;
}