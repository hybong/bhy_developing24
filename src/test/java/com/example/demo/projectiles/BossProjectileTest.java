package com.example.demo.projectiles;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossProjectileTest {

    private BossProjectile bossProjectile;

    // This setup ensures that the JavaFX application thread is initialized for testing
    @BeforeEach
    void setUp() {
        new JFXPanel(); // Initialize JavaFX environment
        bossProjectile = new BossProjectile(200); // Create a BossProjectile with initial Y position of 200
    }

    @Test
    void testInitialPosition() {
        // Verify that the initial position of the projectile is correct
        assertEquals(950, bossProjectile.getLayoutX(), "Initial X position should be 950");
        assertEquals(200, bossProjectile.getLayoutY(), "Initial Y position should be 200");
    }

    @Test
    void testProjectileMovement() {
        // Call updateActor to move the projectile horizontally
        bossProjectile.updateActor();

        // The projectile should move horizontally by HORIZONTAL_VELOCITY (-15 units)
        assertEquals(-15, bossProjectile.getTranslateX(), "Projectile should move 15 units to the left.");
    }

    @Test
    void testProjectileDestruction() {
        // Ensure the projectile is not destroyed initially
        assertFalse(bossProjectile.isDestroyed(), "Projectile should not be destroyed initially.");

        // Call takeDamage to destroy the projectile
        bossProjectile.takeDamage();

        // The projectile should now be destroyed
        assertTrue(bossProjectile.isDestroyed(), "Projectile should be destroyed after taking damage.");
    }

    @Test
    void testProjectileImage() {
        // Verify that the image of the projectile is loaded correctly
        Image expectedImage = new Image(getClass().getResource("/com/example/demo/images/fireball.png").toExternalForm());
        assertEquals(expectedImage.getUrl(), bossProjectile.getImage().getUrl(), "The image of the projectile should match.");
    }
}
