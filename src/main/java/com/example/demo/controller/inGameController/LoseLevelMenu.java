package com.example.demo.controller.inGameController;

import com.example.demo.Levels.LevelParent;
import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

/**
 * The LoseLevelMenu class represents the menu displayed when the player loses a level.
 * It provides options to retry the level or go back to the main menu.
 */
public class LoseLevelMenu extends StackPane {

    /** The level parent that controls the current game level. */
    private LevelParent levelParent;

    /** The width of the menu. */
    private final double MENU_WIDTH = 550;

    /** The height of the menu. */
    private final double MENU_HEIGHT = 150;

    /** The file path for the button click sound effect. */
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";

    /** The sound effect for button clicks. */
    private final SoundEffect buttonSound;

    /** The volume level for the button sound effects. */
    private final double BUTTON_SOUND_VOLUME = 1;

    /**
     * Constructs the LoseLevelMenu with the given LevelParent.
     * Initializes the layout and buttons for retrying the level or going back to the main menu.
     *
     * @param levelParent the parent level that controls the game's logic and transitions
     */
    public LoseLevelMenu(LevelParent levelParent) {
        this.levelParent = levelParent;
        buttonSound = new SoundEffect(BUTTON_SOUND);
        this.setVisible(false);

        // Create Buttons
        Button replayButton = createReplayButton();
        Button backtoMainButton = createBackToMainButton();

        // Create HBox for the layout
        HBox menuLayout = new HBox(15);
        menuLayout.getChildren().addAll(backtoMainButton, replayButton);
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the background and size for the menu
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        this.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        this.setLayoutX((double) Main.SCREEN_WIDTH / 2 - MENU_WIDTH / 2);
        this.setLayoutY((double) Main.SCREEN_HEIGHT * 0.6);

        // Add the HBox to the StackPane
        this.getChildren().add(menuLayout);
    }

    /**
     * Creates and returns the "Retry" button that allows the player to restart the level.
     *
     * @return the "Retry" button
     */
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

    /**
     * Creates and returns the "Back to Main Menu" button that allows the player to return to the main menu.
     *
     * @return the "Back to Main Menu" button
     */
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

    /**
     * Handles the action of retrying the level.
     * Calls the replayLevel method from the LevelParent class.
     */
    private void replayLevel() {
        levelParent.replayLevel();
    }

    /**
     * Handles the action of going back to the main menu.
     * Calls the goToMainMenu method from the LevelParent class.
     */
    private void goToMainMenu() {
        levelParent.goToMainMenu();
    }
}