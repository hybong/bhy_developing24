package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

public class KillCount extends StackPane {
    private final Label killLabel;
    private final int killToWin;
    private int kills;
    private double xPosition = (double) Main.SCREEN_WIDTH /2 - 40;
    private final int yPosition = 10;

    public KillCount(int killToWin) {
        this.killToWin = killToWin;
        this.killLabel = new Label("Kills: " + kills + "/" + killToWin);
        this.killLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;");
        setPosition();
        this.getChildren().add(killLabel);
    }

    public void setPosition() {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    public void updateKill(int kills) {
        this.kills = kills;
        this.killLabel.setText("Kills: " + kills + "/" + killToWin);
    }

    public void showKill() {
        this.setVisible(true);
    }

    public void hideKill() {
        this.setVisible(false);
    }
}