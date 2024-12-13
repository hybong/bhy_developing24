package com.example.demo.models;

import com.example.demo.media.SoundEffect;

/**
 * The `FighterPlane` class represents a fighter aircraft in the game. It extends `ActiveActorDestructible`
 * and provides functionality for health management, damage handling, and firing projectiles.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	public int health; // Health of the fighter plane
	private static final String DESTROY_SOUND_PATH = "/com/example/demo/media/soundEffects/destroyed/fighterPlaneDestroy.mp3"; // Path to the destroy sound effect
	private final SoundEffect destroySound; // Sound effect played when the plane is destroyed
	private final double DESTROY_SOUND_VOLUME = 0.5; // Volume for the destroy sound effect

	/**
	 * Constructor for the `FighterPlane` class.
	 *
	 * @param imageName The name of the image for the fighter plane.
	 * @param imageHeight The height of the fighter plane image.
	 * @param initialXPos The initial X position of the fighter plane.
	 * @param initialYPos The initial Y position of the fighter plane.
	 * @param health The initial health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos); // Call to the parent constructor to initialize image and position
		this.health = health;
		destroySound = new SoundEffect(DESTROY_SOUND_PATH); // Initialize the destroy sound effect
	}

	/**
	 * Abstract method that must be implemented by subclasses to fire a projectile.
	 *
	 * @return A new `ActiveActorDestructible` representing the fired projectile.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Method that is called when the fighter plane takes damage. Decreases health and plays the destruction sound
	 * when health reaches zero.
	 */
	@Override
	public void takeDamage() {
		health--; // Decrease health by 1
		if (healthAtZero()) { // Check if the health is zero
			destroySound.playSoundEffect(DESTROY_SOUND_VOLUME); // Play the destroy sound effect
			this.destroy(); // Destroy the plane
		}
	}

	/**
	 * Helper method to calculate the X position of the projectile based on the plane's current position and an offset.
	 *
	 * @param xPositionOffset The offset to apply to the X position of the projectile.
	 * @return The calculated X position of the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset; // Return the X position adjusted by the offset
	}

	/**
	 * Helper method to calculate the Y position of the projectile based on the plane's current position and an offset.
	 *
	 * @param yPositionOffset The offset to apply to the Y position of the projectile.
	 * @return The calculated Y position of the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset; // Return the Y position adjusted by the offset
	}

	/**
	 * Helper method to check if the health of the fighter plane has reached zero.
	 *
	 * @return `true` if the health is zero, `false` otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0; // Return true if health is zero
	}

	/**
	 * Gets the current health of the fighter plane.
	 *
	 * @return The current health of the fighter plane.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the health of the fighter plane to the specified value.
	 *
	 * @param health The new health value.
	 */
	public void setHealth(int health) {
		this.health = health;
	}

}