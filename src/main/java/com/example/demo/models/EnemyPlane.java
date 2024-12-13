package com.example.demo.models;

import com.example.demo.media.SoundEffect;
import com.example.demo.projectiles.EnemyProjectile;

/**
 * The `EnemyPlane` class represents an enemy aircraft in the game. It extends `FighterPlane`
 * and implements behaviors for movement, projectile firing, and sound effects when firing.
 */
public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png"; // Image for the enemy plane
	private static final String SHOOTING_SOUND = "/com/example/demo/media/soundEffects/firing/enemyPlaneShooting.mp3"; // Sound effect for shooting
	private static final int IMAGE_HEIGHT = 50; // Height of the image
	public static final int HORIZONTAL_VELOCITY = -6; // Horizontal movement speed
	private static final double PROJECTILE_X_POSITION_OFFSET = -40.0; // Offset for projectile X position
	private static final double PROJECTILE_Y_POSITION_OFFSET = 26.0; // Offset for projectile Y position
	public static final int INITIAL_HEALTH = 1; // Initial health of the enemy plane
	private static final double FIRE_RATE = .02; // Probability of firing a projectile each frame
	private SoundEffect shootingSound; // Sound effect for shooting
	private final double SHOOTING_SOUND_VOLUME = 0.2; // Volume for shooting sound effect

	/**
	 * Constructor for the `EnemyPlane` class. Initializes the image, health, and sound effect.
	 *
	 * @param initialXPos The initial X position of the enemy plane.
	 * @param initialYPos The initial Y position of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		shootingSound = new SoundEffect(SHOOTING_SOUND); // Initialize the shooting sound effect
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Move the plane to the left
	}

	/**
	 * Fires a projectile from the enemy plane if the fire rate condition is met.
	 *
	 * @return A new `EnemyProjectile` if the plane fires; otherwise, returns null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			shootingSound.playSoundEffect(SHOOTING_SOUND_VOLUME); // Play the shooting sound effect
			return new EnemyProjectile(projectileXPosition, projectileYPosition); // Create and return a new projectile
		}
		return null; // No projectile fired
	}

	/**
	 * Updates the actor, including its position and any other necessary updates.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Update the position of the enemy plane
	}
}
