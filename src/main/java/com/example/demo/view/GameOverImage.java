package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents the "Game Over" image displayed when the game ends.
 * It is positioned at the center of the screen with a width of 300 pixels.
 * The image is loaded from the resources folder and is displayed on top of other elements.
 */
public class GameOverImage extends ImageView {

	/** The width of the "Game Over" image. */
	private static final double WIDTH = 300;

	/** The X position of the "Game Over" image, centered horizontally on the screen. */
	private static final double XPosition = (double) Main.SCREEN_WIDTH / 2 - WIDTH / 2;

	/** The Y position of the "Game Over" image, positioned at 20% of the screen height from the top. */
	private static final double YPosition = (double) Main.SCREEN_HEIGHT * 0.2;

	/** The path to the image file for the "Game Over" screen. */
	private static final String GAME_OVER_IMAGE = "/com/example/demo/images/gameover.png";

	/**
	 * Constructor that initializes the "Game Over" image and sets its position and size.
	 * The image is loaded from the resources and displayed at the center of the screen.
	 */
	public GameOverImage() {
		setImage(new Image(getClass().getResource(GAME_OVER_IMAGE).toExternalForm()));
		setLayoutX(XPosition);
		setLayoutY(YPosition);
		setFitWidth(WIDTH);
		setPreserveRatio(true);
		toFront(); // Ensures the image is displayed above other elements
	}
}