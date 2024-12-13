package com.example.demo.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossHealthTest {

    private BossHealth bossHealth;

    // This setup ensures that the JavaFX application thread is initialized for testing
    @BeforeEach
    void setUp() {
        new JFXPanel(); // Initialize JavaFX environment
        bossHealth = new BossHealth(100); // Create BossHealth with 100 as initial health
    }

    @Test
    void testInitialHealth() {
        // Verify that the progress bar is initially full (100%) for health = 100
        ProgressBar progressBar = (ProgressBar) bossHealth.getChildren().get(0); // Get the progress bar
        Label healthLabel = (Label) bossHealth.getChildren().get(1); // Get the health label

        // Check the initial progress (should be 1.0 for full health)
        assertEquals(1.0, progressBar.getProgress(), "Progress bar should be full initially.");

        // Check the initial health label
        assertEquals("100/100", healthLabel.getText(), "Health label should show '100/100' initially.");
    }

    @Test
    void testUpdateHealth() {
        // Update the health to 50
        bossHealth.updateHealth(50);

        ProgressBar progressBar = (ProgressBar) bossHealth.getChildren().get(0); // Get the progress bar
        Label healthLabel = (Label) bossHealth.getChildren().get(1); // Get the health label

        // The progress bar should be at 50% for health = 50
        assertEquals(0.5, progressBar.getProgress(), "Progress bar should show 50% health.");

        // The health label should show '50/100'
        assertEquals("50/100", healthLabel.getText(), "Health label should show '50/100'.");
    }

    @Test
    void testHideHealth() {
        // Initially, the health bar should be visible
        assertTrue(bossHealth.isVisible(), "Health bar should be visible initially.");

        // Update health to 0 and check if it's hidden
        bossHealth.updateHealth(0);
        assertFalse(bossHealth.isVisible(), "Health bar should be hidden when health is 0.");

        // Show health again and check visibility
        bossHealth.showHealth();
        assertTrue(bossHealth.isVisible(), "Health bar should be visible after calling showHealth.");
    }

    @Test
    void testUpdateHealthPosition() {
        // Set the boss position to (150, 250)
        bossHealth.updateHealthPosition(150, 250);

        // Check the updated position of the health bar
        assertEquals(150, bossHealth.getLayoutX(), "Health bar's X position should be 150.");
        assertEquals(330, bossHealth.getLayoutY(), "Health bar's Y position should be 250 + 80 = 330.");
    }
}
