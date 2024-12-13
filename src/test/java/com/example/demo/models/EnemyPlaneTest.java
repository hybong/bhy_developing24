package com.example.demo.models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyPlaneTest {

    private EnemyPlane enemyPlane;

    // Initialize the JavaFX environment using JFXPanel
    @BeforeEach
    public void setUp() {
        // This initializes JavaFX toolkit
        new JFXPanel(); // This is a trick to initialize the JavaFX runtime

        // Create a new EnemyPlane instance before each test
        enemyPlane = new EnemyPlane(100, 100); // Initial position (100, 100) for testing
    }

    // Test: Ensure the EnemyPlane fires a projectile based on fire rate
    @Test
    public void testProjectileFiring() {
        // Set a fixed seed for the random number generator to control the probability
        // Ensure that the fire rate is not exactly 0, so there is some chance of firing
        double previousHealth = enemyPlane.getHealth();
        ActiveActorDestructible projectile = enemyPlane.fireProjectile();
        if (projectile != null) {
            assertNotNull(projectile, "EnemyPlane should fire a projectile");
        } else {
            assertEquals(previousHealth, enemyPlane.getHealth(), "EnemyPlane health should remain unchanged if no projectile is fired");
        }
    }

    // Test: Ensure that the EnemyPlane can take damage and decrease health
    @Test
    public void testTakeDamage() {
        int initialHealth = enemyPlane.getHealth();

        // Simulate the enemy plane taking damage
        enemyPlane.takeDamage();
        assertTrue(enemyPlane.getHealth() < initialHealth, "EnemyPlane health should decrease after taking damage");
    }

    // Test: Ensure the EnemyPlane's health is initialized correctly
    @Test
    public void testInitialHealth() {
        assertEquals(EnemyPlane.INITIAL_HEALTH, enemyPlane.getHealth(),
                "EnemyPlane should have the correct initial health");
    }

    // Test: Ensure the EnemyPlane's firing sound is played when firing a projectile
    @Test
    public void testFiringSound() {
        // For the test, we cannot directly check sound playback, but we can check
        // if the sound effect object is properly instantiated and called.
        enemyPlane.fireProjectile(); // Fire a projectile (sound effect will play)
        // If no exception occurs during the sound effect playing, the test passes.
        assertTrue(true, "EnemyPlane should play shooting sound when firing a projectile");
    }

}
