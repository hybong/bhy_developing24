package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents the "You Win" image that is displayed when the player wins the game.
 * The image is initially hidden and can be made visible when the player wins.
 * It is positioned at the center of the screen during display.
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final double WIDTH = 300;
	private static final double XPosition = (double) Main.SCREEN_WIDTH / 2 - WIDTH / 2;
	private static final double YPosition = (double) Main.SCREEN_HEIGHT * 0.2;

	/**
	 * Constructor for the WinImage class.
	 * Initializes the "You Win" image, sets its size, and positions it at the center of the screen.
	 * The image is initially set to be invisible.
	 */
	public WinImage() {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false); // Initially set the image to be invisible
		this.setFitWidth(WIDTH);
		this.setPreserveRatio(true);
		this.setLayoutX(XPosition);
		this.setLayoutY(YPosition);
		this.toFront(); // Ensure the image appears above other elements
	}

	/**
	 * Makes the "You Win" image visible.
	 * This method should be called when the player wins the game.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}