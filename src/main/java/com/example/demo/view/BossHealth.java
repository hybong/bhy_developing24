package com.example.demo.view;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class BossHealth extends StackPane {
    private final ProgressBar healthBar;
    private final Label healthLabel;
    private final int Health;
    private static final int BAR_HEIGHT = 20;
    private static final int BAR_WIDTH = 370;

    public BossHealth(int bossHealth) {
        this.Health = bossHealth;
        this.healthBar = new ProgressBar(1.0);
        this.healthBar.setPrefHeight(BAR_HEIGHT);
        this.healthBar.setPrefWidth(BAR_WIDTH);
        this.healthBar.setStyle("-fx-accent: red; -fx-inner-background: white;");
        this.healthLabel = new Label(bossHealth + "/" + bossHealth);
        this.healthLabel.setStyle("-fx-font-size: 16; -fx-text-fill: yellow;");
        this.getChildren().addAll(healthBar,healthLabel);
    }

    public void updateHealth(int presentHealth) {
        double healthPercentage = (double) presentHealth / Health; // Assuming HEALTH is a constant in Boss
        healthBar.setProgress(healthPercentage);
        healthLabel.setText(presentHealth + "/" + Health);
    }

    public void updateHealthPosition(double bossX, double bossY){
        this.setLayoutX(bossX);
        this.setLayoutY(bossY + 80);
    }
}