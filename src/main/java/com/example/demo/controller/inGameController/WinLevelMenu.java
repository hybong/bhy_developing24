package com.example.demo.controller.inGameController;

import com.example.demo.Levels.LevelParent;
import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

import static com.example.demo.Levels.LevelParent.nextLevelName;

/**
 * The WinLevelMenu class represents a menu displayed when the player completes a level.
 * It offers options to proceed to the next level or return to the main menu.
 */
public class WinLevelMenu extends StackPane {

    /** The level parent that controls the game's current level. */
    private LevelParent levelParent;

    /** The width of the win level menu. */
    private final double MENU_WIDTH = 550;

    /** The height of the win level menu. */
    private final double MENU_HEIGHT = 300;

    /** The X position of the menu on the screen. */
    private final double XPosition = (double) Main.SCREEN_WIDTH /2 - MENU_WIDTH /2;

    /** The Y position of the menu on the screen. */
    private final double YPosition = (double) Main.SCREEN_HEIGHT * 0.25;

    /** The file path for the button click sound effect. */
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";

    /** The sound effect for button clicks. */
    private final SoundEffect buttonSound;

    /** The volume level for the button sound effects. */
    private final double BUTTON_SOUND_VOLUME = 1;

    /**
     * Constructs the WinLevelMenu with buttons and messages.
     * Sets up the layout, buttons, and sound effects for the win level menu.
     *
     * @param levelParent the parent level that controls the game's current state
     */
    public WinLevelMenu(LevelParent levelParent) {
        buttonSound = new SoundEffect(BUTTON_SOUND);

        this.levelParent = levelParent;
        this.setVisible(false);

        // Create Buttons
        Button nextButton = createNextButton();
        Button backtoMainButton = createBackToMainButton();

        // Layout the buttons horizontally
        HBox buttonLayout = new HBox(15);
        buttonLayout.getChildren().addAll(backtoMainButton, nextButton);
        buttonLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Create labels with messages for level completion
        Label label1 = new Label("All enemies defeated!");
        label1.setFont(Font.font("Arial", 40));
        label1.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        Label label2 = new Label("Proceed to next level?");
        label2.setFont(Font.font("Arial", 30));
        label2.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        // Create VBox for the overall layout
        VBox menuLayout = new VBox(30);
        menuLayout.getChildren().addAll(label1, label2, buttonLayout);
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the background and size for the win level menu
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        this.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        this.setLayoutX(XPosition);
        this.setLayoutY(YPosition);

        // Add the VBox to the StackPane
        this.getChildren().add(menuLayout);
    }

    /**
     * Creates the "Next Level" button with style and event handler.
     *
     * @return the "Next Level" button
     */
    private Button createNextButton() {
        Button button = new Button("Next Level");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            nextLevel();
        });
        return button;
    }

    /**
     * Creates the "Back to Main Menu" button with style and event handler.
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
     * Handles the action of proceeding to the next level.
     */
    private void nextLevel() {
        levelParent.goToNextLevel(nextLevelName);
    }

    /**
     * Navigates the player back to the main menu.
     */
    private void goToMainMenu() {
        levelParent.goToMainMenu();
    }

}