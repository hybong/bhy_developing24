package com.example.demo.view;

import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

public class KillCount extends StackPane {
    private final Label killLabel;
    private final int killToWin;
    private int kills;
    private final int xPosition = 1200;
    private final int yPosition = 10;

    public KillCount(int killToWin) {
        this.killToWin = killToWin;
        this.killLabel = new Label(kills + "/" + killToWin);
        this.killLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;");
        setPosition();
        this.getChildren().add(killLabel);
    }

    private void setPosition() {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    public void updateKill(int kills) {
        this.kills = kills;
        this.killLabel.setText(kills + "/" + killToWin);
    }

    public void showKill() {
        this.setVisible(true);
    }

    public void hideKill() {
        this.setVisible(false);
    }
}