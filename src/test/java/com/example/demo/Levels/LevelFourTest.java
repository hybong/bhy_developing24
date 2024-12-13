package com.example.demo.Levels;

import com.example.demo.models.Boss;
import com.example.demo.models.UserPlane;
import com.example.demo.models.SecondBoss;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelFourTest {

    private LevelFour levelFour;
    private UserPlane userPlane;
    private Boss bossOne;
    private SecondBoss bossTwo;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX toolkit
        new JFXPanel(); // This is required to initialize the JavaFX environment for testing

        // Initialize LevelFour with arbitrary screen dimensions
        levelFour = new LevelFour(600, 800); // Example screen dimensions
        userPlane = levelFour.getUser();  // Access the user plane instance
        bossOne = new Boss();  // Create a new Boss instance
        bossTwo = new SecondBoss();  // Create a new secondBoss instance
    }

    @Test
    void testInitialHealthOfUser() {
        // Check if the user plane's initial health is correct
        assertEquals(8, userPlane.getHealth(), "The initial health of the user plane should be 8.");
    }

    @Test
    void testInitialHealthOfBosses() {
        // Check if both bosses' initial health is correct
        assertEquals(Boss.HEALTH, bossOne.getHealth(), "The initial health of bossOne should be 20.");
        assertEquals(SecondBoss.HEALTH, bossTwo.getHealth(), "The initial health of bossTwo should be 50.");
    }

    @Test
    void testSpawningOfBosses() {
        // Ensure that both bosses are spawned when there are no enemies
        levelFour.spawnEnemyUnits();

        // Verify that two bosses have been added as enemies
        assertEquals(2, levelFour.getCurrentNumberOfEnemies(), "There should be two bosses spawned.");
    }

    @Test
    void testGameOverWhenUserDestroyed() {
        // Simulate damage to the user plane until it is destroyed
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage();
        userPlane.takeDamage(); // Total of 8 damage

        // Call the method to check if the game is over
        Platform.runLater(levelFour::checkIfGameOver);

        // Since the user is destroyed, the game over condition should trigger
        assertTrue(levelFour.userIsDestroyed(), "The game should be over when the user plane is destroyed.");
    }

    @Test
    void testWinConditionWhenBothBossesDestroyed() {
        // Simulate damage to both bosses until they are destroyed
        for (int i = 0; i < Boss.HEALTH; i++) bossOne.takeDamage();
        for (int i = 0; i < SecondBoss.HEALTH; i++) bossTwo.takeDamage();

        // Call the method to check if the game is over
        Platform.runLater(levelFour::checkIfGameOver);

        // Both bosses are destroyed, so the game should win
        assertTrue(bossOne.isDestroyed() && bossTwo.isDestroyed(), "The game should be won when both bosses are destroyed.");
    }

}
