package com.example.demo.models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserPlaneTest {

    private UserPlane userPlane;

    @BeforeEach
    public void setUp() {
        new JFXPanel(); // Initialize JavaFX
        userPlane = new UserPlane(10); // Create UserPlane instance with initial health of 10
    }

    @Test
    public void testProjectileFiring() {
        userPlane.fireProjectile();
        // Verify that a projectile is created (we assume the UserProjectile class exists and has some checkable property)
        assertNotNull(userPlane.fireProjectile(), "Projectile should be created when fireProjectile is called.");
    }

    @Test
    public void testKillCount() {
        int initialKills = userPlane.getNumberOfKills();
        userPlane.incrementKillCount();
        assertEquals(initialKills + 1, userPlane.getNumberOfKills(), "Kill count should increment by 1.");
    }

}
