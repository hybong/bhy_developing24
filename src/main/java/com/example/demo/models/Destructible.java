package com.example.demo.models;

/**
 * The `Destructible` interface represents objects that can take damage and be destroyed.
 * Any class that implements this interface should provide implementations for taking damage
 * and destroying the object.
 */
public interface Destructible {

	/**
	 * This method is invoked when the object takes damage.
	 * The behavior of how damage is handled should be defined in the implementing class.
	 */
	void takeDamage();

	/**
	 * This method destroys the object, typically by setting it to a destroyed state.
	 * The implementation of destruction behavior should be provided by the implementing class.
	 */
	void destroy();
}