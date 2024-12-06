package com.example.demo.view;

import com.example.demo.models.Boss;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class BossHealth extends StackPane {
    private final ProgressBar healthBar;
    private final Label healthLabel;
    private final int Health;

    public BossHealth(int bossHealth) {
        this.Health = bossHealth;
        this.healthBar = new ProgressBar(1.0);
        this.healthLabel = new Label(bossHealth + "/" + bossHealth);
        this.getChildren().addAll(healthBar,healthLabel);
    }

    public void updateHealth(int presentHealth) {
        double healthPercentage = (double) presentHealth / Health; // Assuming HEALTH is a constant in Boss
        healthBar.setProgress(healthPercentage);
        healthLabel.setText(presentHealth + "/" + Health);
    }

    public void updateHealthPosition(double bossX, double bossY){
        this.setLayoutX(bossX);
        this.setLayoutY(bossY);
    }
}