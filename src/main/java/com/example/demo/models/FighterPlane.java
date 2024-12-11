package com.example.demo.models;

import com.example.demo.media.SoundEffect;

public abstract class FighterPlane extends ActiveActorDestructible {

	public int health;
	private static final String DESTROY_SOUND_PATH = "/com/example/demo/media/soundEffects/destroyed/fighterPlaneDestroy.mp3";
	private final SoundEffect destroySound;
	private final double DESTROY_SOUND_VOLUME = 0.5;

	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
		destroySound = new SoundEffect(DESTROY_SOUND_PATH);
	}

	public abstract ActiveActorDestructible fireProjectile();
	
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			destroySound.playSoundEffect(DESTROY_SOUND_VOLUME);
			this.destroy();
		}
	}

	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	private boolean healthAtZero() {
		return health == 0;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
		
}
