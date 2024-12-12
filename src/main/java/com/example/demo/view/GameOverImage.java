package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {

	private static final double WIDTH = 300;
	private static final double XPosition = (double) Main.SCREEN_WIDTH /2 - WIDTH /2;
	private static final double YPosition = (double) Main.SCREEN_HEIGHT * 0.2;
	private static final String GAME_OVER_IMAGE = "/com/example/demo/images/gameover.png";

	public GameOverImage() {
		setImage(new Image(getClass().getResource(GAME_OVER_IMAGE).toExternalForm()) );
		setLayoutX(XPosition);
		setLayoutY(YPosition);
		setFitWidth(WIDTH);
		setPreserveRatio(true);
		toFront();
	}

}
