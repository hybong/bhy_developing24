package com.example.demo.projectiles;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyProjectileTest {

    private EnemyProjectile enemyProjectile;

    // This setup ensures that the JavaFX application thread is initialized for testing
    @BeforeEach
    void setUp() {
        new JFXPanel(); // Initialize JavaFX environment
        enemyProjectile = new EnemyProjectile(300, 200); // Create an EnemyProjectile instance with initial position
    }

    @Test
    void testInitialPosition() {
        // Verify that the initial position of the projectile is correct
        assertEquals(300, enemyProjectile.getLayoutX(), "Initial X position should be 300");
        assertEquals(200, enemyProjectile.getLayoutY(), "Initial Y position should be 200");
    }

    @Test
    void testProjectileMovement() {
        // Call updateActor to move the projectile horizontally
        enemyProjectile.updateActor();

        // The projectile should move horizontally by HORIZONTAL_VELOCITY (-10 units)
        assertEquals(-10, enemyProjectile.getTranslateX(), "Projectile should move 10 units to the left.");
    }

    @Test
    void testProjectileDestruction() {
        // Ensure the projectile is not destroyed initially
        assertFalse(enemyProjectile.isDestroyed(), "Projectile should not be destroyed initially.");

        // Call takeDamage to destroy the projectile
        enemyProjectile.takeDamage();

        // The projectile should now be destroyed
        assertTrue(enemyProjectile.isDestroyed(), "Projectile should be destroyed after taking damage.");
    }

    @Test
    void testProjectileImage() {
        // Verify that the image of the projectile is loaded correctly
        Image expectedImage = new Image(getClass().getResource("/com/example/demo/images/enemyFire.png").toExternalForm());
        assertEquals(expectedImage.getUrl(), enemyProjectile.getImage().getUrl(), "The image of the projectile should match.");
    }
}
