package com.example.demo.models;

import com.example.demo.media.SoundEffect;
import com.example.demo.projectiles.EnemyProjectile;

/**
 * The `EnemyPlane` class represents an enemy aircraft in the game. It extends `FighterPlane`
 * and implements behaviors for movement, projectile firing, and sound effects when firing.
 */
public class EnemyPlane extends FighterPlane {

	/**
	 * The image used to represent the enemy plane.
	 * This image is loaded from the resources to display the plane in the game.
	 */
	private static final String IMAGE_NAME = "enemyplane.png";

	/**
	 * The path to the sound effect that is played when the enemy plane shoots.
	 * This sound effect is triggered every time the enemy plane fires a projectile.
	 */
	private static final String SHOOTING_SOUND = "/com/example/demo/media/soundEffects/firing/enemyPlaneShooting.mp3";

	/**
	 * The height of the image representing the enemy plane.
	 * This value is used to set the size of the plane sprite when rendering it.
	 */
	private static final int IMAGE_HEIGHT = 50;

	/**
	 * The horizontal velocity (speed) at which the enemy plane moves.
	 * This value determines how fast the enemy plane moves across the screen.
	 */
	public static final int HORIZONTAL_VELOCITY = -6;

	/**
	 * The X-axis offset used for positioning the projectile when the enemy plane shoots.
	 * This value adjusts the position of the projectile relative to the plane's position.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -40.0;

	/**
	 * The Y-axis offset used for positioning the projectile when the enemy plane shoots.
	 * This value adjusts the vertical position of the projectile relative to the plane's position.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 26.0;

	/**
	 * The initial health of the enemy plane.
	 * This value represents how much damage the enemy plane can take before being destroyed.
	 */
	public static final int INITIAL_HEALTH = 1;

	/**
	 * The probability of the enemy plane firing a projectile each frame.
	 * This is a chance value that controls the frequency of firing. A higher value increases the likelihood of firing.
	 */
	private static final double FIRE_RATE = .02;

	/**
	 * The sound effect that is played when the enemy plane fires a projectile.
	 * The `SoundEffect` class is used to handle the audio playback when the enemy shoots.
	 */
	private SoundEffect shootingSound;

	/**
	 * The volume level for the shooting sound effect.
	 * This controls how loud the shooting sound effect is played when the enemy fires a projectile.
	 */
	private final double SHOOTING_SOUND_VOLUME = 0.2;

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
