package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents the shield image that can be displayed for a boss.
 * The shield is an image that can be shown or hidden and positioned relative to the boss.
 * It is intended to visually represent the shield state of the boss during gameplay.
 */
public class ShieldImage extends ImageView {

	/** The path to the image file representing the shield. */
	private static final String SHIELD_NAME = "/com/example/demo/images/shield.png";

	/** The size (width and height) of the shield image. */
	private static final int SHIELD_SIZE = 100;


	/**
	 * Constructor for the ShieldImage class.
	 * Initializes the shield image with a specific size and sets it to be initially hidden.
	 */
	public ShieldImage() {
		this.setImage(new Image(getClass().getResource(SHIELD_NAME).toExternalForm()));
		this.setVisible(false); // Initially set the shield to be invisible
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Makes the shield visible and brings it to the front in the scene.
	 */
	public void showShield() {
		this.setVisible(true);
		this.toFront(); // Ensure the shield is drawn on top of other elements
	}

	/**
	 * Hides the shield from view.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

	/**
	 * Updates the shield's position relative to the boss.
	 * The position is adjusted based on the provided boss coordinates (X and Y).
	 *
	 * @param bossX The X coordinate of the boss.
	 * @param bossY The Y coordinate of the boss.
	 */
	public void updateShieldPosition(double bossX, double bossY) {
		this.setLayoutX(bossX - 40); // Adjust the X position as needed
		this.setLayoutY(bossY);      // Adjust the Y position as needed
	}
}