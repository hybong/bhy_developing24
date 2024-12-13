package com.example.demo.models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.demo.models.SecondBoss.DANGER_FIRE_RATE;
import static org.junit.jupiter.api.Assertions.*;

public class SecondBossTest {

    private SecondBoss secondBoss;

    @BeforeEach
    public void setUp() {
        new JFXPanel(); // Initialize JavaFX
        secondBoss = new SecondBoss(); // Create secondBoss instance
    }

    @Test
    public void testInDangerState() {
        secondBoss.setHealth(31); // Set health above danger level
        assertFalse(secondBoss.isInDanger(), "Boss should not be in danger when health is above threshold");

        secondBoss.setHealth(30); // Set health to danger level
        assertTrue(secondBoss.isInDanger(), "Boss should be in danger when health is below threshold");
    }

    @Test
    public void testFireRateInDanger() {
        secondBoss.setHealth(30); // Set health to danger level
        double fireRate = secondBoss.bossFiresInCurrentFrame() ? 0.1 : 0.02; // Check fire rate
        assertEquals(DANGER_FIRE_RATE, fireRate, "Boss fire rate should increase when in danger");
    }
}
