package com.example.demo.projectiles;

/**
 * The `EnemyProjectile` class represents a projectile fired by an enemy plane.
 * It handles the movement and update of the enemy's projectile in the game.
 */
public class EnemyProjectile extends Projectile {

	private static final String IMAGE_NAME = "enemyFire.png"; // Image for the enemy projectile
	private static final int IMAGE_HEIGHT = 15; // Height of the enemy projectile
	private static final int HORIZONTAL_VELOCITY = -10; // Horizontal velocity of the enemy projectile

	/**
	 * Constructor for the `EnemyProjectile` class.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy projectile.
	 * The projectile moves horizontally based on its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Move the projectile horizontally
	}

	/**
	 * Updates the enemy projectile's state.
	 * This method is called every game loop iteration to update the projectile's position.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Call the updatePosition method to move the projectile
	}
}