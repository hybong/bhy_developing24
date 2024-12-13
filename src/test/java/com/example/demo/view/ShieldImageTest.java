package com.example.demo.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShieldImageTest {

    private ShieldImage shieldImage;

    // This setup ensures that the JavaFX application thread is initialized for testing
    @BeforeEach
    void setUp() {
        new JFXPanel(); // Initialize JavaFX environment
        shieldImage = new ShieldImage(); // Create ShieldImage instance
    }

    @Test
    void testInitialization() {
        // Check that the shield is initially invisible
        assertFalse(shieldImage.isVisible(), "Shield should be hidden initially.");

        // Check the size of the shield image
        assertEquals(100, shieldImage.getFitHeight(), "Shield height should be 100.");
        assertEquals(100, shieldImage.getFitWidth(), "Shield width should be 100.");

        // Check that the image is properly set
        Image image = shieldImage.getImage();
        assertNotNull(image, "Shield image should be initialized.");
    }

    @Test
    void testShowShield() {
        // Show the shield
        shieldImage.showShield();

        // Verify that the shield is visible
        assertTrue(shieldImage.isVisible(), "Shield should be visible after calling showShield.");
    }

    @Test
    void testHideShield() {
        // First, show the shield
        shieldImage.showShield();

        // Now hide the shield
        shieldImage.hideShield();

        // Verify that the shield is hidden
        assertFalse(shieldImage.isVisible(), "Shield should be hidden after calling hideShield.");
    }

    @Test
    void testUpdateShieldPosition() {
        // Update the shield's position to (150, 250)
        shieldImage.updateShieldPosition(150, 250);

        // Check the updated position
        assertEquals(110, shieldImage.getLayoutX(), "Shield's X position should be 150 - 40 = 110.");
        assertEquals(250, shieldImage.getLayoutY(), "Shield's Y position should be 250.");
    }
}
