package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {

	private static final int HEIGHT = 500;
	private static final String GAME_OVER_IMAGE = "/com/example/demo/images/gameover.png";

	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(GAME_OVER_IMAGE).toExternalForm()) );
//		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
		setFitHeight(HEIGHT);
		setPreserveRatio(true);
		toFront();
	}

}
