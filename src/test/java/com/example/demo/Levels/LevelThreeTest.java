package com.example.demo.Levels;

import com.example.demo.models.UserPlane;
import com.example.demo.models.SecondBoss;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelThreeTest {

    private LevelThree levelThree;
    private UserPlane userPlane;
    private SecondBoss boss;

    @BeforeEach
    void setUp() {
        // Initialize the JavaFX environment for testing
        new JFXPanel(); // Initializes the JavaFX toolkit for testing

        // Initialize LevelThree with arbitrary screen dimensions
        levelThree = new LevelThree(600, 800); // Example screen dimensions
        userPlane = levelThree.getUser();  // Access the user plane instance
        boss = new SecondBoss(); // Create a new secondBoss instance
    }

    @Test
    void testUserPlaneHealthInitiallyCorrect() {
        // Check if the user plane's initial health is correct
        assertEquals(5, userPlane.getHealth(), "The initial health of the user plane should be 5.");
    }

    @Test
    void testBossHealthInitiallyCorrect() {
        // Check if the boss's initial health is correct (should be 50)
        assertEquals(SecondBoss.HEALTH, boss.getHealth(), "The initial health of the boss should be 50.");
    }

    @Test
    void testBossInDangerWhenHealthBelowThreshold() {
        // Simulate the boss's health dropping below the danger threshold
        boss.setHealth(29); // Below the danger threshold (30)

        // Check if the boss is in danger
        assertTrue(boss.isInDanger(), "The boss should be in danger when health is below 30.");
    }

    @Test
    void testBossSpawning() {
        // Check if the boss is spawned when there are no enemies
        levelThree.spawnEnemyUnits();

        // Check if the boss has been added as an enemy
        assertEquals(1, levelThree.getCurrentNumberOfEnemies(), "The boss should be spawned when there are no enemies.");
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
        Platform.runLater(levelThree::checkIfGameOver);

        // Since the user is destroyed, the game over condition should trigger
        assertTrue(levelThree.userIsDestroyed(), "The game should be over when the user plane is destroyed.");
    }

}
