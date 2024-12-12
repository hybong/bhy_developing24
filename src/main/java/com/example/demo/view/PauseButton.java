package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PauseButton extends ImageView {

    private static final double PAUSE_BUTTON_SIZE = 60;
    private static final double PAUSE_BUTTON_Y_POSITION = 10;
    private static final double PAUSE_BUTTON_X_POSITION = Main.SCREEN_WIDTH - PAUSE_BUTTON_SIZE
                                                            - PAUSE_BUTTON_Y_POSITION;
    private static final String PAUSE_BUTTON_IMAGE = "/com/example/demo/images/pausebutton.png";

    public PauseButton(Runnable pause) {
        this.setImage(new Image(getClass().getResource(PAUSE_BUTTON_IMAGE).toExternalForm()));
        this.setFitHeight(PAUSE_BUTTON_SIZE);
        this.setPreserveRatio(true);
        this.setLayoutX(PAUSE_BUTTON_X_POSITION);
        this.setLayoutY(PAUSE_BUTTON_Y_POSITION);
        this.toFront();
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> pause.run());
    }

    public void showPauseButton() {
        this.setVisible(true);
    }

    public void hidePauseButton() {
        this.setVisible(false);
    }

}