package com.example.demo.models;

import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import com.example.demo.projectiles.BossProjectile;

import java.util.*;

/**
 * The `Boss` class represents a boss in the game, extending the `FighterPlane` class.
 * It handles the behavior of the boss, including movement patterns, shield activation,
 * firing projectiles, taking damage, and reviving.
 */
public class Boss extends FighterPlane {

	private static final String BOSS_IMAGE = "bossplane.png";
	private static final String SHOOTING_SOUND = "/com/example/demo/media/soundEffects/firing/bossShooting.mp3";
	private static final String SHIELD_ON_SOUND = "/com/example/demo/media/soundEffects/bossShield.mp3";
	private static final String SHIELD_OFF_SOUND = "/com/example/demo/media/soundEffects/bossShieldDown.mp3";
	private static final String REVIVE_SOUND = "/com/example/demo/media/soundEffects/bossRevive.mp3";

	private static final int IMAGE_HEIGHT = 80;
	private static final double INITIAL_X_POSITION = Main.SCREEN_WIDTH - 5 * IMAGE_HEIGHT;
	private static final double INITIAL_Y_POSITION = (double) Main.SCREEN_HEIGHT / 2 - (double) IMAGE_HEIGHT / 2;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	protected double BOSS_FIRE_RATE = .001;
	private static final double BOSS_SHIELD_PROBABILITY = .002;
	protected static int VERTICAL_VELOCITY = 8;
	public static final int HEALTH = 20;
	protected static int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	protected static int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = 10;
	private static final int Y_POSITION_LOWER_BOUND = Main.SCREEN_HEIGHT - IMAGE_HEIGHT - 60;
	private static final int MAX_FRAMES_WITH_SHIELD = 50;
	protected List<Integer> movePattern;
	private boolean isShielded;
	protected int consecutiveMovesInSameDirection;
	protected int indexOfCurrentMove;
	private int framesWithShieldActivated;
	public int ShieldCount = 0;
	private final SoundEffect shootingSound;
	private final double SHOOTING_SOUND_VOLUME = 0.3;
	private final SoundEffect shieldOnSound;
	private final double SHIELD_ON_VOLUME = 1;
	private final SoundEffect shieldOffSound;
	private final double SHIELD_OFF_VOLUME = 1;
	private final SoundEffect reviveSound;
	private final double REVIVE_SOUND_VOLUME = 1;

	/**
	 * Constructor to initialize the boss with default settings and initialize sounds and move pattern.
	 */
	public Boss() {
		super(BOSS_IMAGE, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		shootingSound = new SoundEffect(SHOOTING_SOUND);
		shieldOnSound = new SoundEffect(SHIELD_ON_SOUND);
		shieldOffSound = new SoundEffect(SHIELD_OFF_SOUND);
		reviveSound = new SoundEffect(REVIVE_SOUND);
		initializeMovePattern();
	}

	/**
	 * Updates the position of the boss plane, ensuring it stays within vertical bounds.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);  // Prevents moving out of bounds
		}
	}

	/**
	 * Updates the actor's state (position and shield status).
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile if the boss's fire rate condition is met.
	 * Plays a sound effect when firing.
	 *
	 * @return A new `BossProjectile` if the boss fires, otherwise `null`.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (bossFiresInCurrentFrame()) {
			this.shootingSound.playSoundEffect(SHOOTING_SOUND_VOLUME); // Play the sound effect
			return new BossProjectile(getProjectileInitialPosition());
		}
		return null; // If the boss doesn't fire, return null
	}

	/**
	 * The boss takes damage only if it's not shielded.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the move pattern for the boss, alternating between moving up and down.
	 */
	protected void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield status of the boss.
	 * If the shield is activated, it tracks the number of frames it's active.
	 */
	protected void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
		} else if (shieldShouldBeActivated()) {
			activateShield();
		}
		if (shieldExhausted()) {
			deactivateShield();
		}
	}

	/**
	 * Gets the next move direction from the move pattern, updating the move counter.
	 *
	 * @return The next move direction (up, down, or none).
	 */
	protected int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);  // Reshuffle the move pattern after a certain number of moves
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines if the boss should fire a projectile in the current frame based on its fire rate.
	 *
	 * @return `true` if the boss fires, `false` otherwise.
	 */
	protected boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Calculates the initial Y position for the projectile to be fired.
	 *
	 * @return The initial Y position for the projectile.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines whether the boss's shield should be activated based on a random chance
	 * or if the boss's health is below half.
	 *
	 * @return `true` if the shield should be activated, `false` otherwise.
	 */
	protected boolean shieldShouldBeActivated() {
		if (ShieldCount != 0) {
			return false; // No activation if already shielded
		}
		return Math.random() < BOSS_SHIELD_PROBABILITY || this.health <= HEALTH / 2;
	}

	/**
	 * Determines if the shield has been active for too long and needs to be deactivated.
	 *
	 * @return `true` if the shield should be deactivated, `false` otherwise.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the boss's shield and plays the corresponding sound effect.
	 */
	protected void activateShield() {
		isShielded = true;
		shieldOnSound.playSoundEffect(SHIELD_ON_VOLUME);
		this.ShieldCount++;
	}

	/**
	 * Deactivates the boss's shield and plays the corresponding sound effect.
	 */
	public void deactivateShield() {
		isShielded = false;
		shieldOffSound.playSoundEffect(SHIELD_OFF_VOLUME);
		framesWithShieldActivated = 0;
	}

	/**
	 * Checks if the boss is currently shielded.
	 *
	 * @return `true` if the boss is shielded, `false` otherwise.
	 */
	public boolean isShielded() {
		return isShielded;
	}

	/**
	 * Revives the boss if it has been destroyed, resetting health and shield count.
	 * Plays a sound effect upon revival.
	 */
	public void revive() {
		if (this.isDestroyed()) {
			reviveSound.playSoundEffect(REVIVE_SOUND_VOLUME);
			this.setDestroyed(false);
			this.setHealth(HEALTH);
			this.ShieldCount = 0;
		}
	}
}