package com.example.demo.projectiles;

/**
 * The `BossProjectile` class represents a projectile fired by a boss in the game.
 * It is responsible for handling the projectile's behavior, including its movement
 * and updating its position.
 */
public class BossProjectile extends Projectile {

	/** The image representing the boss projectile. */
	private static final String IMAGE_NAME = "fireball.png";

	/** The height of the projectile image. */
	private static final int IMAGE_HEIGHT = 50;

	/** The horizontal velocity of the projectile, which controls its speed of movement. */
	private static final int HORIZONTAL_VELOCITY = -15;

	/** The initial X position of the projectile when it is created. */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructor for the `BossProjectile` class.
	 *
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the boss projectile.
	 * The projectile moves horizontally based on its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Move the projectile horizontally
	}

	/**
	 * Updates the boss projectile's state.
	 * This method is called every game loop iteration to update the projectile's position.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Call the updatePosition method to move the projectile
	}
}