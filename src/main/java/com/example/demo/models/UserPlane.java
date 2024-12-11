package com.example.demo.models;

import com.example.demo.media.SoundEffect;
import com.example.demo.projectiles.UserProjectile;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final String SHOOTING_SOUND = "/com/example/demo/media/soundEffects/firing/userShooting.mp3";
	private static final double Y_UPPER_BOUND = 10;
	private static final double Y_LOWER_BOUND = 650;
	private static final double X_UPPER_BOUND = 0;
	private static final double X_LOWER_BOUND = 700;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 40;
	private static int VERTICAL_VELOCITY = 8;
	private static int HORIZONTAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 140;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;
	private SoundEffect shootingSound;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
		shootingSound = new SoundEffect(SHOOTING_SOUND);
	}
	
	@Override
	public void updatePosition() {
		if (isMovingVertically()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			double newYPosition = getLayoutY() + getTranslateY();
			if (newYPosition < Y_UPPER_BOUND || newYPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
		if (isMovingHorizontally()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newXPosition = getLayoutX() + getTranslateX();
			if (newXPosition < X_UPPER_BOUND || newXPosition > X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		shootingSound.playSoundEffect(0.07);
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	private boolean isMovingVertically() {
		return verticalVelocityMultiplier != 0;
	}

	private boolean isMovingHorizontally() {
		return horizontalVelocityMultiplier != 0;
	}

	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	public void stopVertically() {
		verticalVelocityMultiplier = 0;
	}

	public void stopHorizontally() {
		horizontalVelocityMultiplier = 0;
	}

	public void moveFaster() {
		HORIZONTAL_VELOCITY += 5;
		VERTICAL_VELOCITY += 5;
	}

	public void moveSlower() {
		HORIZONTAL_VELOCITY -= 5;
		VERTICAL_VELOCITY -= 5;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

}
