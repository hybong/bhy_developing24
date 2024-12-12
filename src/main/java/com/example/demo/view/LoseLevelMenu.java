package com.example.demo.view;

import com.example.demo.Levels.LevelParent;
import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

public class LoseLevelMenu extends StackPane {

    private LevelParent levelParent;
    private final double MENU_WIDTH = 550;
    private final double MENU_HEIGHT = 150;
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";
    private final SoundEffect buttonSound;
    private final double BUTTON_SOUND_VOLUME = 1;

    // Constructor
    public LoseLevelMenu(LevelParent levelParent) {
        this.levelParent = levelParent;
        buttonSound = new SoundEffect(BUTTON_SOUND);
        this.setVisible(false);

        // Create Buttons
        Button replayButton = createReplayButton();
        Button backtoMainButton = createBackToMainButton();

        // Create VBox for the layout
        HBox menuLayout = new HBox(15);
        menuLayout.getChildren().addAll(backtoMainButton, replayButton);
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the background and size for the Pause Menu
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        this.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        this.setLayoutX((double) Main.SCREEN_WIDTH /2 - MENU_WIDTH /2);
        this.setLayoutY((double) Main.SCREEN_HEIGHT * 0.6);

        // Add the HBox to the StackPane
        this.getChildren().add(menuLayout);
    }

    // Method to create "Replay" button
    private Button createReplayButton() {
        Button button = new Button("Retry");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> {
                buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
                replayLevel();
        });
        return button;
    }

    // Method to create "Back to Main Menu" button
    private Button createBackToMainButton() {
        Button button = new Button("Back to Main Menu");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #F44336; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            goToMainMenu();
        });
        return button;
    }

    // Handle the Resume action
    private void replayLevel() {
        levelParent.replayLevel();
    }

    // Go back to the main menu
    private void goToMainMenu() {
        levelParent.goToMainMenu();
    }

}