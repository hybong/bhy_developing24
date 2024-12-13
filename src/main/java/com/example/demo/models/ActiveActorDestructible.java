package com.example.demo.models;

/**
 * The `ActiveActorDestructible` class represents an actor that can be destroyed in the game.
 * It extends the `ActiveActor` class and implements the `Destructible` interface, adding the functionality
 * to track whether the actor is destroyed and to handle destruction-related behavior.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/**
	 * Flag indicating whether the actor is destroyed.
	 * This boolean value is used to track the state of the actor (e.g., a fighter plane, boss, etc.).
	 * When set to `true`, it means the actor has been destroyed and should no longer be active in the game.
	 */
	private boolean isDestroyed;

	/**
	 * Constructor to create a destructible actor with an image, initial position, and size.
	 * The actor is initially not destroyed.
	 *
	 * @param imageName    The name of the image file to be used for the actor.
	 * @param imageHeight  The height of the image for the actor.
	 * @param initialXPos  The initial X position of the actor on the screen.
	 * @param initialYPos  The initial Y position of the actor on the screen.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;  // Initially, the actor is not destroyed
	}

	/**
	 * This method must be implemented by subclasses to define how the actor's position is updated.
	 * Typically, this will be used in each game loop to move the actor based on user input or game logic.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * This method must be implemented by subclasses to define how the actor's behavior is updated.
	 * This could include animation updates, state changes, or other actor-specific logic.
	 */
	public abstract void updateActor();

	/**
	 * This method must be implemented by subclasses to define how the actor takes damage.
	 * Typically, this method will reduce the actor's health or trigger destruction.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed.
	 * This is called when the actor's health reaches zero or when other conditions trigger destruction.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);  // Set the actor's destroyed flag to true
	}

	/**
	 * Sets the destruction status of the actor.
	 *
	 * @param isDestroyed A boolean value indicating whether the actor is destroyed.
	 */
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Returns whether the actor is destroyed.
	 *
	 * @return A boolean value indicating the destruction status of the actor.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}