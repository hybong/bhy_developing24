package com.example.demo.models;

import javafx.scene.image.*;

/**
 * The `ActiveActor` class represents an actor (e.g., a character, enemy, or object) that is active in the game.
 * It extends the `ImageView` class to display an image, and provides methods to move the actor on the screen.
 * This class is abstract and should be extended to define specific behaviors for different types of active actors.
 */
public abstract class ActiveActor extends ImageView {

	/**
	 * The folder location where images are stored.
	 * This constant defines the base directory for loading all image files used in the game.
	 * It is prepended to the relative paths of individual images to form the full resource path.
	 * Images are expected to be located under this directory within the project's resources.
	 */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructor to create an active actor with an image, initial position, and size.
	 *
	 * @param imageName    The name of the image file to be used for the actor.
	 * @param imageHeight  The height of the image for the actor.
	 * @param initialXPos  The initial X position of the actor on the screen.
	 * @param initialYPos  The initial Y position of the actor on the screen.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		// Set the image for the actor
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));

		// Set the initial position
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);

		// Set the image height and preserve the aspect ratio
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * This method must be implemented by subclasses to define how the actor's position is updated.
	 * Typically, this will be used in each game loop to move the actor based on user input or game logic.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by a specified amount.
	 * This will update the actor's current X position on the screen.
	 *
	 * @param horizontalMove The distance to move the actor horizontally.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by a specified amount.
	 * This will update the actor's current Y position on the screen.
	 *
	 * @param verticalMove The distance to move the actor vertically.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}