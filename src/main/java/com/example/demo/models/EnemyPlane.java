package com.example.demo.models;

import com.example.demo.media.SoundEffect;
import com.example.demo.projectiles.EnemyProjectile;

public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final String SHOOTING_SOUND = "/com/example/demo/media/soundEffects/firing/enemyPlaneShooting.mp3";
	private static final int IMAGE_HEIGHT = 50;
	public static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -40.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 26.0;
	public static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .02;
	private SoundEffect shootingSound;
	private final double SHOOTING_SOUND_VOLUME = 0.2;

	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		shootingSound = new SoundEffect(SHOOTING_SOUND);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			shootingSound.playSoundEffect(SHOOTING_SOUND_VOLUME);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}
