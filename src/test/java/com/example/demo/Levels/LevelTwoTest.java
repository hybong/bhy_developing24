package com.example.demo.Levels;

import com.example.demo.models.Boss;
import com.example.demo.models.UserPlane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTwoTest {

    private LevelTwo levelTwo;
    private UserPlane userPlane;
    private Boss boss;

    @BeforeEach
    void setUp() {
        // Initialize the JavaFX environment for testing
        new JFXPanel(); // Initializes the JavaFX toolkit for testing

        // Initialize LevelTwo with arbitrary screen dimensions
        levelTwo = new LevelTwo(600, 800); // Example screen dimensions
        userPlane = levelTwo.getUser();  // Access the user plane instance
        boss = new Boss(); // Create a new Boss instance
    }

    @Test
    void testUserPlaneHealthInitiallyCorrect() {
        // Check if the user plane's initial health is correct
        assertEquals(5, userPlane.getHealth(), "The initial health of the user plane should be 5.");
    }

    @Test
    void testBossHealthInitiallyCorrect() {
        // Check if the boss's initial health is correct
        assertEquals(Boss.HEALTH, boss.getHealth(), "The initial health of the boss should be the constant value.");
    }

    @Test
    void testBossSpawning() {
        // Check if the boss is spawned when there are no enemies
        levelTwo.spawnEnemyUnits();

        // Check if the boss has been added as an enemy
        assertEquals(1, levelTwo.getCurrentNumberOfEnemies(), "The boss should be spawned when there are no enemies.");
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
        Platform.runLater(levelTwo::checkIfGameOver);

        // Since the user is destroyed, the game over condition should trigger
        assertTrue(levelTwo.userIsDestroyed(), "The game should be over when the user plane is destroyed.");
    }

}
