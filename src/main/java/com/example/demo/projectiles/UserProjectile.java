package com.example.demo.projectiles;

/**
 * The `UserProjectile` class represents a projectile fired by the user (player).
 * It extends from the `Projectile` class and defines the behavior specific to user-fired projectiles.
 */
public class UserProjectile extends Projectile {

	// Constants for the user projectile
	private static final String IMAGE_NAME = "userfire.png"; // The image used to represent the user projectile
	private static final int IMAGE_HEIGHT = 15; // The height of the projectile image
	private static final int HORIZONTAL_VELOCITY = 30; // The speed at which the projectile moves horizontally

	/**
	 * Constructor for the `UserProjectile` class.
	 * Initializes the user projectile with the specified initial X and Y positions.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos); // Call the parent constructor with image, size, and position
	}

	/**
	 * Updates the position of the user projectile.
	 * The projectile moves horizontally at a constant velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Move the projectile horizontally by the specified velocity
	}

	/**
	 * Updates the behavior of the user projectile.
	 * This method is called each frame to update the position of the projectile.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Update the position of the projectile
	}
}