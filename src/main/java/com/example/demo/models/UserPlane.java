package com.example.demo.models;

import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import com.example.demo.projectiles.UserProjectile;

/**
 * The `UserPlane` class represents the player's fighter plane in the game.
 * It handles movement, firing projectiles, and tracking the number of kills made by the user.
 */
public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png"; // Image for the user plane
	private static final int IMAGE_HEIGHT = 40; // Height of the user plane image
	private static final String SHOOTING_SOUND = "/com/example/demo/media/soundEffects/firing/userShooting.mp3"; // Path to the shooting sound effect
	private static final double Y_UPPER_BOUND = 10; // Upper vertical boundary for movement
	private static final double Y_LOWER_BOUND = Main.SCREEN_HEIGHT - IMAGE_HEIGHT - 60; // Lower vertical boundary for movement
	private static final double X_UPPER_BOUND = 0; // Left boundary for movement
	private static final double X_LOWER_BOUND = Main.SCREEN_WIDTH - 550; // Right boundary for movement
	private static final double INITIAL_X_POSITION = 5.0; // Initial X position of the user plane
	private static final double INITIAL_Y_POSITION = 300.0; // Initial Y position of the user plane
	private static int VERTICAL_VELOCITY = 8; // Default vertical velocity of the plane
	private static int HORIZONTAL_VELOCITY = 8; // Default horizontal velocity of the plane
	private static final int PROJECTILE_X_POSITION = 140; // X offset for projectile launch
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20; // Y offset for projectile launch
	private int verticalVelocityMultiplier; // Multiplier for vertical movement
	private int horizontalVelocityMultiplier; // Multiplier for horizontal movement
	private int numberOfKills; // Number of kills made by the user
	private SoundEffect shootingSound; // Sound effect for shooting
	private final double SHOOTING_SOUND_VOLUME = 0.1; // Volume for the shooting sound effect

	/**
	 * Constructor for the `UserPlane` class.
	 * Initializes the user's plane with health, initial position, and the shooting sound.
	 *
	 * @param initialHealth The initial health of the user plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
		shootingSound = new SoundEffect(SHOOTING_SOUND);
	}

	/**
	 * Updates the position of the user plane based on the movement velocity and boundary constraints.
	 * The plane can move vertically and horizontally based on user input.
	 */
	@Override
	public void updatePosition() {
		// Vertical movement
		if (isMovingVertically()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			double newYPosition = getLayoutY() + getTranslateY();
			if (newYPosition < Y_UPPER_BOUND || newYPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
		// Horizontal movement
		if (isMovingHorizontally()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newXPosition = getLayoutX() + getTranslateX();
			if (newXPosition < X_UPPER_BOUND || newXPosition > X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Updates the user plane's state (e.g., position).
	 * This method is called every game loop iteration.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Update the position of the plane
	}

	/**
	 * Fires a projectile from the user plane.
	 * A shooting sound is played when the user fires.
	 *
	 * @return A new `UserProjectile` object that represents the fired projectile.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		shootingSound.playSoundEffect(SHOOTING_SOUND_VOLUME); // Play the shooting sound
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the user plane is currently moving vertically.
	 *
	 * @return `true` if the user plane is moving vertically, `false` otherwise.
	 */
	private boolean isMovingVertically() {
		return verticalVelocityMultiplier != 0;
	}

	/**
	 * Checks if the user plane is currently moving horizontally.
	 *
	 * @return `true` if the user plane is moving horizontally, `false` otherwise.
	 */
	private boolean isMovingHorizontally() {
		return horizontalVelocityMultiplier != 0;
	}

	/**
	 * Moves the user plane up by setting the vertical velocity multiplier to -1.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user plane down by setting the vertical velocity multiplier to 1.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the user plane left by setting the horizontal velocity multiplier to -1.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user plane right by setting the horizontal velocity multiplier to 1.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stops the vertical movement of the user plane by setting the vertical velocity multiplier to 0.
	 */
	public void stopVertically() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops the horizontal movement of the user plane by setting the horizontal velocity multiplier to 0.
	 */
	public void stopHorizontally() {
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Increases the movement speed of the user plane.
	 * Both vertical and horizontal velocities are increased by 5.
	 */
	public void moveFaster() {
		HORIZONTAL_VELOCITY += 5;
		VERTICAL_VELOCITY += 5;
	}

	/**
	 * Decreases the movement speed of the user plane.
	 * Both vertical and horizontal velocities are decreased by 5.
	 */
	public void moveSlower() {
		HORIZONTAL_VELOCITY -= 5;
		VERTICAL_VELOCITY -= 5;
	}

	/**
	 * Gets the number of kills made by the user plane.
	 *
	 * @return The number of kills made by the user.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the number of kills made by the user plane.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
}