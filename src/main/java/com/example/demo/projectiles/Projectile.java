package com.example.demo.projectiles;

import com.example.demo.models.ActiveActorDestructible;

/**
 * The `Projectile` class represents a general projectile in the game.
 * It is an abstract class that extends `ActiveActorDestructible` and serves as a base class for specific types of projectiles.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructor for the `Projectile` class.
	 *
	 * @param imageName The image name representing the projectile.
	 * @param imageHeight The height of the projectile image.
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos); // Initialize the projectile as an active actor
	}

	/**
	 * Handles the damage taken by the projectile.
	 * Since projectiles are typically destroyed upon colliding with objects, this method destroys the projectile.
	 */
	@Override
	public void takeDamage() {
		this.destroy(); // Destroy the projectile when it takes damage
	}

	/**
	 * Abstract method to update the position of the projectile.
	 * This method should be implemented by subclasses to define specific movement behavior.
	 */
	@Override
	public abstract void updatePosition();
}