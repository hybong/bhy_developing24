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

/**
 * The WinGameMenu class represents a menu that is displayed when the player wins the game.
 * It provides options for the player to either return to the main menu or exit the game.
 */
public class WinGameMenu extends StackPane {

    /** The level parent that controls the game's current level. */
    private LevelParent levelParent;

    /** The width of the win game menu. */
    private final double MENU_WIDTH = 600;

    /** The height of the win game menu. */
    private final double MENU_HEIGHT = 180;

    /** The X position of the menu on the screen. */
    private final double XPosition = (double) Main.SCREEN_WIDTH /2 - MENU_WIDTH /2;

    /** The Y position of the menu on the screen. */
    private final double YPosition = (double) Main.SCREEN_HEIGHT * 0.6;

    /** The file path for the button click sound effect. */
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";

    /** The sound effect for button clicks. */
    private final SoundEffect buttonSound;

    /** The volume level for the button sound effects. */
    private final double BUTTON_SOUND_VOLUME = 1;

    /**
     * Constructs the WinGameMenu with buttons and a message.
     * Sets up the layout, buttons, and sound effects for the win game menu.
     *
     * @param levelParent the parent level that controls the game's current state
     */
    public WinGameMenu(LevelParent levelParent) {
        buttonSound = new SoundEffect(BUTTON_SOUND);

        this.levelParent = levelParent;
        this.setVisible(false);

        // Create Buttons
        Button exitButton = createExitButton();
        Button backtoMainButton = createBackToMainButton();

        // Layout the buttons horizontally
        HBox buttonLayout = new HBox(30);
        buttonLayout.getChildren().addAll(exitButton, backtoMainButton);
        buttonLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Create label with congratulatory message
        Label label = new Label("Congratulations! You won the game!");
        label.setFont(Font.font("Arial", 30));
        label.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        // Create VBox for the overall layout
        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(label, buttonLayout);
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the background and size for the win game menu
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        this.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        this.setLayoutX(XPosition);
        this.setLayoutY(YPosition);

        // Add the VBox to the StackPane
        this.getChildren().add(menuLayout);
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
        button.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            goToMainMenu();
        });
        return button;
    }

    /**
     * Creates the "Exit" button with style and event handler.
     *
     * @return the "Exit" button
     */
    private Button createExitButton() {
        Button button = new Button("Exit");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #F44336; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            exit();
        });
        return button;
    }

    /**
     * Navigates the player to the main menu.
     */
    private void goToMainMenu() {
        levelParent.goToMainMenu();
    }

    /**
     * Exits the game.
     */
    private void exit() {
        levelParent.exitGame();
    }

}