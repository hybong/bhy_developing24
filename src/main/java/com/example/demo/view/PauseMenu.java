package com.example.demo.view;

import com.example.demo.Levels.LevelParent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

public class PauseMenu extends StackPane {

    private LevelParent levelParent;

    // Constructor
    public PauseMenu(LevelParent levelParent) {
        this.levelParent = levelParent;
        this.setVisible(false);

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
        this.setPrefSize(400, 300);
        this.setLayoutX(475);
        this.setLayoutY(400);

        // Add the VBox to the StackPane
        this.getChildren().add(menuLayout);
    }

    // Method to create "Resume" button
    private Button createResumeButton() {
        Button button = new Button("Resume");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;");
        button.setOnAction(e -> resumeGame());
        return button;
    }

    // Method to create "Music: On/Off" button
    private Button createMusicButton() {
        Button button = new Button("Music: On");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #2196F3; -fx-font-weight: bold;");

        button.setOnAction(e -> toggleMusic(button));
        return button;
    }

    // Method to create "Back to Main Menu" button
    private Button createBackToMenuButton() {
        Button button = new Button("Back to Main Menu");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #F44336; -fx-font-weight: bold;");
        button.setOnAction(e -> goToMainMenu());
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