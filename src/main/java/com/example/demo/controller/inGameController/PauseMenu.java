package com.example.demo.controller.inGameController;

import com.example.demo.Levels.LevelParent;
import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

import static com.example.demo.Levels.LevelParent.isMute;

/**
 * The PauseMenu class represents the menu displayed when the game is paused.
 * It provides options to resume the game, toggle music, or return to the main menu.
 */
public class PauseMenu extends StackPane {

    /** The level parent that controls the current game level. */
    private LevelParent levelParent;

    /** The width of the pause menu. */
    private final double MENU_WIDTH = 350;

    /** The height of the pause menu. */
    private final double MENU_HEIGHT = 300;

    /** The file path for the button click sound effect. */
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";

    /** The sound effect for button clicks. */
    private final SoundEffect buttonSound;

    /** The volume level for the button sound effects. */
    private final double BUTTON_SOUND_VOLUME = 1;

    /**
     * Constructs the PauseMenu with the given LevelParent.
     * Initializes the layout and buttons for resuming the game, toggling music, or returning to the main menu.
     *
     * @param levelParent the parent level that controls the game's logic and transitions
     */
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
        this.setLayoutX((double) Main.SCREEN_WIDTH / 2 - MENU_WIDTH / 2);
        this.setLayoutY((double) Main.SCREEN_HEIGHT / 2 - MENU_HEIGHT / 2);

        // Add the VBox to the StackPane
        this.getChildren().add(menuLayout);
    }

    /**
     * Creates and returns the "Resume" button that allows the player to resume the game.
     *
     * @return the "Resume" button
     */
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

    /**
     * Creates and returns the "Music: On/Off" button that allows the player to toggle the background music.
     *
     * @return the "Music: On/Off" button
     */
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

    /**
     * Creates and returns the "Back to Main Menu" button that allows the player to return to the main menu.
     *
     * @return the "Back to Main Menu" button
     */
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

    /**
     * Resumes the game by calling the appropriate method from the LevelParent class.
     * Hides the pause menu after resuming the game.
     */
    private void resumeGame() {
        levelParent.resumeGame();
        this.setVisible(false);
    }

    /**
     * Toggles the background music on or off and updates the button text accordingly.
     *
     * @param button the button whose text will be updated based on the music state
     */
    private void toggleMusic(Button button) {
        levelParent.toggleBackgroundMusic();
        if (levelParent.backgroundMusicPlaying()) button.setText("Music: Off");
        else button.setText("Music: On");
    }

    /**
     * Goes back to the main menu by calling the appropriate method from the LevelParent class.
     */
    private void goToMainMenu() {
        levelParent.goToMainMenu();
    }

}