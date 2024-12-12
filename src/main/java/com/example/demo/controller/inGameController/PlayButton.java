package com.example.demo.controller.inGameController;

import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlayButton extends ImageView {

    private static final double PLAY_BUTTON_SIZE = 60;
    private static final double PLAY_BUTTON_Y_POSITION = 10;
    private static final double PLAY_BUTTON_X_POSITION = Main.SCREEN_WIDTH - PLAY_BUTTON_SIZE
                                                            - PLAY_BUTTON_Y_POSITION;
    private static final String PLAY_BUTTON_IMAGE = "/com/example/demo/images/playbutton.png";
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";
    private final SoundEffect buttonSound;
    private final double BUTTON_SOUND_VOLUME = 1;

    public PlayButton(Runnable resume) {
        buttonSound = new SoundEffect(BUTTON_SOUND);

        this.setImage(new Image(getClass().getResource(PLAY_BUTTON_IMAGE).toExternalForm()));
        this.setFitHeight(PLAY_BUTTON_SIZE);
        this.setPreserveRatio(true);
        this.setLayoutX(PLAY_BUTTON_X_POSITION);
        this.setLayoutY(PLAY_BUTTON_Y_POSITION);
        this.toFront();
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            resume.run();
        });
        this.setVisible(false);
    }

    public void showPlayButton() {
        this.setVisible(true);
    }

    public void hidePlayButton() {
        this.setVisible(false);
    }

}