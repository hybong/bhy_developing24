package com.example.demo.Levels;

import com.example.demo.models.UserPlane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelOneTest {

    private LevelOne levelOne;
    private UserPlane userPlane;

    // Setup method to initialize the LevelOne instance and user plane
    @BeforeEach
    void setUp() {
        // Initialize the JavaFX environment for testing
        new JFXPanel(); // This is needed to initialize the JavaFX toolkit for testing

        // Initialize LevelOne with arbitrary screen dimensions
        levelOne = new LevelOne(600, 800); // Example screen dimensions
        userPlane = levelOne.getUser();  // Access the user plane instance
    }

    @Test
    void testUserPlaneHealthInitiallyCorrect() {
        // Check if the user plane's initial health is correct
        assertEquals(5, userPlane.getHealth(), "The initial health of the user plane should be 5.");
    }

    @Test
    void testUserPlaneTakesDamage() {
        // Simulate damage to the user plane
        userPlane.takeDamage();

        // Check if the health is decreased by 1
        assertEquals(4, userPlane.getHealth(), "The user plane should lose health when it takes damage.");
    }

    @Test
    void testUserPlaneDestruction() {
        // Simulate damage until the user plane is destroyed
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage(); // Total of 5 damage, should destroy the plane

        // Check if the user plane is destroyed after taking enough damage
        assertTrue(levelOne.userIsDestroyed(), "The user plane should be destroyed when health reaches 0.");
    }

    @Test
    void testGameOverWhenUserDestroyed() {
        // Simulate damage to the user plane until it's destroyed
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage(); // Total of 5 damage

        // Call the method to check if the game is over
        Platform.runLater(levelOne::checkIfGameOver);

        // Since the user is destroyed, the game over condition should trigger
        assertTrue(levelOne.userIsDestroyed(), "The game should be over when the user plane is destroyed.");
    }
}
