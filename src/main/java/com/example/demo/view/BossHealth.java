package com.example.demo.view;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * This class represents the health bar of a boss character in the game.
 * It uses a {@link ProgressBar} to visually represent the boss's health,
 * and a {@link Label} to display the current health value.
 */
public class BossHealth extends StackPane {

    /** The ProgressBar used to visually represent the health of the boss. */
    private final ProgressBar healthBar;

    /** The Label displaying the numeric health of the boss. */
    private final Label healthLabel;

    /** The maximum health value of the boss, used to calculate health percentage. */
    private final int Health;

    /** The height of the health bar. */
    private static final int BAR_HEIGHT = 20;

    /** The width of the health bar. */
    private static final int BAR_WIDTH = 370;

    /**
     * Constructor to initialize the BossHealth display.
     *
     * @param bossHealth The total health of the boss.
     */
    public BossHealth(int bossHealth) {
        this.Health = bossHealth;
        this.healthBar = new ProgressBar(1.0);
        this.healthBar.setPrefHeight(BAR_HEIGHT);
        this.healthBar.setPrefWidth(BAR_WIDTH);
        this.healthBar.setStyle("-fx-accent: red;");
        this.healthLabel = new Label(bossHealth + "/" + bossHealth);
        this.healthLabel.setStyle("-fx-font-size: 15; -fx-text-fill: black;");
        this.getChildren().addAll(healthBar, healthLabel);
    }

    /**
     * Updates the health bar and label based on the current health.
     *
     * @param presentHealth The current health of the boss.
     */
    public void updateHealth(int presentHealth) {
        double healthPercentage = (double) presentHealth / Health; // Calculate health percentage
        if (presentHealth == 0) hideHealth(); // Hide health bar if health is zero
        healthBar.setProgress(healthPercentage);
        healthLabel.setText(presentHealth + "/" + Health);
    }

    /**
     * Updates the position of the health bar relative to the boss's position.
     *
     * @param bossX The current X position of the boss.
     * @param bossY The current Y position of the boss.
     */
    public void updateHealthPosition(double bossX, double bossY) {
        this.setLayoutX(bossX);
        this.setLayoutY(bossY + 80); // Position the health bar slightly below the boss
    }

    /**
     * Hides the health bar from view.
     */
    public void hideHealth() {
        this.setVisible(false);
    }

    /**
     * Shows the health bar after it has been hidden.
     */
    public void showHealth() {
        this.setVisible(true);
    }
}