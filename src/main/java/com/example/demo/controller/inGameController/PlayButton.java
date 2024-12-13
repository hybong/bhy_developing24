package com.example.demo.controller.inGameController;

import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The PlayButton class represents a play button displayed in the game UI.
 * It allows the user to resume the game by triggering a given action when clicked.
 */
public class PlayButton extends ImageView {

    /** The size of the play button. */
    private static final double PLAY_BUTTON_SIZE = 60;

    /** The Y position of the play button on the screen. */
    private static final double PLAY_BUTTON_Y_POSITION = 10;

    /** The X position of the play button on the screen. */
    private static final double PLAY_BUTTON_X_POSITION = Main.SCREEN_WIDTH - PLAY_BUTTON_SIZE
            - PLAY_BUTTON_Y_POSITION;

    /** The file path for the play button image. */
    private static final String PLAY_BUTTON_IMAGE = "/com/example/demo/images/playbutton.png";

    /** The file path for the button click sound effect. */
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";

    /** The sound effect for button clicks. */
    private final SoundEffect buttonSound;

    /** The volume level for the button sound effects. */
    private final double BUTTON_SOUND_VOLUME = 1;

    /**
     * Constructs the PlayButton with the given action to be triggered when clicked.
     * Sets up the button's image, size, position, and event handler for mouse clicks.
     *
     * @param resume the action to be executed when the play button is clicked
     */
    public PlayButton(Runnable resume) {
        buttonSound = new SoundEffect(BUTTON_SOUND);

        // Set the image of the play button
        this.setImage(new Image(getClass().getResource(PLAY_BUTTON_IMAGE).toExternalForm()));
        this.setFitHeight(PLAY_BUTTON_SIZE);
        this.setPreserveRatio(true);
        this.setLayoutX(PLAY_BUTTON_X_POSITION);
        this.setLayoutY(PLAY_BUTTON_Y_POSITION);
        this.toFront();

        // Add mouse click event handler
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            resume.run();
        });

        // Initially, the play button is hidden
        this.setVisible(false);
    }

    /**
     * Makes the play button visible on the screen.
     */
    public void showPlayButton() {
        this.setVisible(true);
    }

    /**
     * Hides the play button from the screen.
     */
    public void hidePlayButton() {
        this.setVisible(false);
    }

}