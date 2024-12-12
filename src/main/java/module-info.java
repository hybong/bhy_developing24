module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;
    requires org.junit.platform.launcher;

    opens com.example.demo.Levels to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.media to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.view to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.Levels.levelView to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.projectiles to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.models to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.controller.inGameController to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.controller to org.junit.platform.commons, org.junit.platform.launcher;

    exports com.example.demo.Levels;
    exports com.example.demo.media;
    exports com.example.demo.view;
    exports com.example.demo.Levels.levelView;
    exports com.example.demo.projectiles;
    exports com.example.demo.models;
    exports com.example.demo.controller.inGameController;
    exports com.example.demo.controller;
}