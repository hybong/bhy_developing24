package com.example.demo.models;

import com.example.demo.controller.Main;
import com.example.demo.media.SoundEffect;
import com.example.demo.projectiles.BossProjectile;

import java.util.*;

public class Boss extends FighterPlane{

	private static final String BOSS_IMAGE = "bossplane.png";
	private static final String SHOOTING_SOUND = "/com/example/demo/media/soundEffects/firing/bossShooting.mp3";
	private static final int IMAGE_HEIGHT = 80;
	private static final double INITIAL_X_POSITION = Main.SCREEN_WIDTH - 5 * IMAGE_HEIGHT;
	private static final double INITIAL_Y_POSITION = (double) Main.SCREEN_HEIGHT /2 - (double) IMAGE_HEIGHT /2;
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
	private SoundEffect shootingSound;
	private final double SHOOTING_SOUND_VOLUME = 0.1;

	public Boss() {
		super(BOSS_IMAGE, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		shootingSound = new SoundEffect(SHOOTING_SOUND);
		initializeMovePattern();
	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}
	
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		// Play shooting sound when the boss fires a projectile
		if (bossFiresInCurrentFrame()) {
			this.shootingSound.playSoundEffect(SHOOTING_SOUND_VOLUME); // Play the sound effect
			return new BossProjectile(getProjectileInitialPosition());
		}
		return null; // If the boss doesn't fire, return null
	}

	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	protected void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	protected void updateShield() {
		if (isShielded) framesWithShieldActivated++;
		else if (shieldShouldBeActivated()) activateShield();
		if (shieldExhausted()) deactivateShield();
	}

	protected int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	protected boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	protected boolean shieldShouldBeActivated() {
		if(ShieldCount != 0){
			return false;
		}
		return Math.random() < BOSS_SHIELD_PROBABILITY || this.health <= HEALTH / 2;
	}

	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	protected void activateShield() {
		isShielded = true;
		this.ShieldCount++;
	}

	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}

	public boolean isShielded() {
		return isShielded;
	}

	public void revive() {
		if(this.isDestroyed()){
			this.setDestroyed(false);
			this.setHealth(HEALTH);
			this.ShieldCount = 0;
		}
	}

}
