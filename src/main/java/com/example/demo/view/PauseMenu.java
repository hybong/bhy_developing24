package com.example.demo.view;

import com.example.demo.Levels.LevelParent;
import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

import static com.example.demo.Levels.LevelParent.isMute;

public class PauseMenu extends StackPane {

    private LevelParent levelParent;
    private final double MENU_WIDTH = 350;
    private final double MENU_HEIGHT = 300;
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";
    private final SoundEffect buttonSound;
    private final double BUTTON_SOUND_VOLUME = 1;

    // Constructor
    public PauseMenu(LevelParent levelParent) {
        this.levelParent = levelParent;
        this.setVisible(false);
        buttonSound = new SoundEffect(BUTTON_SOUND);

        // Create Buttons
        Button resumeButton = createResumeButton();
        Button musicButton = createMusicButton();
        Button backButton = createBackToMenuButton();

        // Create VBox for the layout
        VBox menuLayout = new VBox(15);
        menuLayout.getChildren().addAll(resumeButton, musicButton, backButton);
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the background and size for the Pause Menu
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        this.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        this.setLayoutX((double) Main.SCREEN_WIDTH /2 - MENU_WIDTH /2);
        this.setLayoutY((double) Main.SCREEN_HEIGHT /2 - MENU_HEIGHT /2);

        // Add the VBox to the StackPane
        this.getChildren().add(menuLayout);
    }

    // Method to create "Resume" button
    private Button createResumeButton() {
        Button button = new Button("Resume");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            resumeGame();
        });
        return button;
    }

    // Method to create "Music: On/Off" button
    private Button createMusicButton() {
        Button button = new Button("Music: On");
        if (isMute) button.setText("Music: Off");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #2196F3; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            toggleMusic(button);
        });
        return button;
    }

    // Method to create "Back to Main Menu" button
    private Button createBackToMenuButton() {
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
    private void resumeGame() {
        levelParent.resumeGame();
        this.setVisible(false);
    }

    // Handle toggling the music on/off
    private void toggleMusic(Button button) {
        levelParent.toggleBackgroundMusic();
        if(levelParent.backgroundMusicPlaying()) button.setText("Music: Off");
        else button.setText("Music: On");
    }

    // Go back to the main menu
    private void goToMainMenu() {
        levelParent.goToMainMenu();
    }

}