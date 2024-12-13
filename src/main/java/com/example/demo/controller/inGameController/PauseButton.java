package com.example.demo.controller.inGameController;

import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The PauseButton class represents a button that is used to pause the game.
 * It is displayed in the top-right corner of the screen and plays a sound effect when clicked.
 */
public class PauseButton extends ImageView {

    /** The size of the pause button. */
    private static final double PAUSE_BUTTON_SIZE = 60;

    /** The Y position of the pause button on the screen. */
    private static final double PAUSE_BUTTON_Y_POSITION = 10;

    /** The X position of the pause button on the screen. */
    private static final double PAUSE_BUTTON_X_POSITION = Main.SCREEN_WIDTH - PAUSE_BUTTON_SIZE
            - PAUSE_BUTTON_Y_POSITION;

    /** The file path to the pause button image. */
    private static final String PAUSE_BUTTON_IMAGE = "/com/example/demo/images/pausebutton.png";

    /** The file path for the button click sound effect. */
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";

    /** The sound effect for button clicks. */
    private final SoundEffect buttonSound;

    /** The volume level for the button sound effects. */
    private final double BUTTON_SOUND_VOLUME = 1;

    /**
     * Constructs a PauseButton instance and sets the button's image, position, and click behavior.
     *
     * @param pause The Runnable that will be executed when the pause button is clicked.
     */
    public PauseButton(Runnable pause) {
        this.setImage(new Image(getClass().getResource(PAUSE_BUTTON_IMAGE).toExternalForm()));
        this.setFitHeight(PAUSE_BUTTON_SIZE);
        this.setPreserveRatio(true);
        this.setLayoutX(PAUSE_BUTTON_X_POSITION);
        this.setLayoutY(PAUSE_BUTTON_Y_POSITION);
        this.toFront();
        buttonSound = new SoundEffect(BUTTON_SOUND);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            pause.run();
        });
    }

    /**
     * Makes the pause button visible on the screen.
     */
    public void showPauseButton() {
        this.setVisible(true);
    }

    /**
     * Hides the pause button from the screen.
     */
    public void hidePauseButton() {
        this.setVisible(false);
    }

}