package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

/**
 * This class represents a UI component that displays the player's kill count.
 * It shows how many kills the player has achieved and how many are needed to win.
 * The kill count is displayed in the top part of the screen.
 */
public class KillCount extends StackPane {

    private final Label killLabel;
    private final int killToWin;
    private int kills;
    private double xPosition = (double) Main.SCREEN_WIDTH /2 - 40;
    private final int yPosition = 10;

    /**
     * Constructor that initializes the kill count display with the number of kills needed to win.
     *
     * @param killToWin The total number of kills required to win the game.
     */
    public KillCount(int killToWin) {
        this.killToWin = killToWin;
        this.killLabel = new Label("Kills: " + kills + "/" + killToWin);
        this.killLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;");
        setPosition();
        this.getChildren().add(killLabel);
    }

    /**
     * Sets the position of the kill count display on the screen.
     * The x-position is centered horizontally, and the y-position is set to 10 pixels from the top.
     */
    public void setPosition() {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    /**
     * Updates the kill count display with the current number of kills.
     *
     * @param kills The updated number of kills.
     */
    public void updateKill(int kills) {
        this.kills = kills;
        this.killLabel.setText("Kills: " + kills + "/" + killToWin);
    }

    /**
     * Makes the kill count display visible on the screen.
     */
    public void showKill() {
        this.setVisible(true);
    }

    /**
     * Hides the kill count display from the screen.
     */
    public void hideKill() {
        this.setVisible(false);
    }
}