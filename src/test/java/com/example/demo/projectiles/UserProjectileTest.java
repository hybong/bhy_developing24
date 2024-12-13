package com.example.demo.projectiles;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProjectileTest {

    private UserProjectile userProjectile;

    // This setup ensures that the JavaFX application thread is initialized for testing
    @BeforeEach
    void setUp() {
        new JFXPanel(); // This initializes JavaFX environment needed for testing
        userProjectile = new UserProjectile(100, 200); // Create a UserProjectile instance with initial position
    }

    @Test
    void testInitialPosition() {
        // Verify that the initial position of the projectile is correct
        assertEquals(100, userProjectile.getLayoutX(), "Initial X position should be 100");
        assertEquals(200, userProjectile.getLayoutY(), "Initial Y position should be 200");
    }

    @Test
    void testProjectileMovement() {
        // Call updateActor to move the projectile horizontally
        userProjectile.updateActor();

        // The projectile should move horizontally by HORIZONTAL_VELOCITY (30 units)
        assertEquals(30, userProjectile.getTranslateX(), "Projectile should move 30 units to the right.");
    }

    @Test
    void testProjectileDestruction() {
        // Ensure the projectile is not destroyed initially
        assertFalse(userProjectile.isDestroyed(), "Projectile should not be destroyed initially.");

        // Call takeDamage to destroy the projectile
        userProjectile.takeDamage();

        // The projectile should now be destroyed
        assertTrue(userProjectile.isDestroyed(), "Projectile should be destroyed after taking damage.");
    }

    @Test
    void testProjectileImage() {
        // Verify that the image of the projectile is loaded correctly
        Image expectedImage = new Image(getClass().getResource("/com/example/demo/images/userfire.png").toExternalForm());
        assertEquals(expectedImage.getUrl(), userProjectile.getImage().getUrl(), "The image of the projectile should match.");
    }
}
