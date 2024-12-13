package com.example.demo.models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossTest {

    private Boss boss;

    // Initialize the JavaFX environment using JFXPanel
    @BeforeEach
    public void setUp() {
        // This initializes JavaFX toolkit
        new JFXPanel(); // This is a trick to initialize the JavaFX runtime

        // Create a new Boss instance before each test
        boss = new Boss();
    }

    // Test: Ensure that the Boss is not shielded initially
    @Test
    public void testInitialShieldState() {
        assertFalse(boss.isShielded(), "Boss should not be shielded initially");
    }

    // Test: Ensure that the shield can be activated and deactivated
    @Test
    public void testShieldActivation() {
        // Activate shield
        boss.activateShield();
        assertTrue(boss.isShielded(), "Boss should be shielded after activation");

        // Deactivate shield
        boss.deactivateShield();
        assertFalse(boss.isShielded(), "Boss should not be shielded after deactivation");
    }

    // Test: Ensure the Boss takes damage if not shielded
    @Test
    public void testTakeDamage() {
        int initialHealth = boss.getHealth();

        // Simulate the boss taking damage
        boss.takeDamage();
        assertTrue(boss.getHealth() < initialHealth, "Boss health should decrease after taking damage");
    }

    // Test: Ensure that the Boss can revive after being destroyed
    @Test
    public void testRevival() {
        // Simulate the boss being destroyed
        boss.setDestroyed(true);
        assertTrue(boss.isDestroyed(), "Boss should be destroyed after taking damage");

        // Revive the boss
        boss.revive();
        assertFalse(boss.isDestroyed(), "Boss should be revived and not destroyed anymore");
        assertEquals(Boss.HEALTH, boss.getHealth(), "Boss health should be reset to full after revival");
    }

    // Test: Ensure the Boss's movement pattern is being utilized
    @Test
    public void testMovementPattern() {
        // Check if the initial move pattern is set correctly
        assertNotNull(boss.movePattern, "Boss move pattern should not be null");
        assertFalse(boss.movePattern.isEmpty(), "Boss move pattern should not be empty");

        // Ensure that the boss moves according to the pattern
        int initialMove = boss.getNextMove();
        assertTrue(initialMove == 8 || initialMove == -8 || initialMove == 0, "Boss move should be either 8, -8, or 0");
    }

    // Test: Ensure that the Boss's shield activation is probabilistic based on health
    @Test
    public void testShieldActivationBasedOnHealth() {
        // Initially, the boss should not have a shield
        assertFalse(boss.isShielded(), "Boss should not be shielded initially");

        // Simulate health going below half
        boss.setHealth(Boss.HEALTH / 2);
        // Check if the shield should be activated (there's a probability but we test the condition)
        assertTrue(boss.isShielded() || boss.shieldShouldBeActivated(), "Boss shield should activate when health is low");

        // Simulate a random probability for shield activation
        boss.ShieldCount = 0;  // Make sure the shield hasn't been activated yet
        assertTrue(boss.shieldShouldBeActivated(), "Shield should be activated due to probability");
    }
}
