package com.example.demo.Levels.levelView;

import com.example.demo.view.KillCount;
import javafx.scene.Group;

/**
 * The `LevelViewLevelOne` class is a subclass of `LevelView` that specifically manages the visual
 * elements for level one, including the player's health (from `LevelView`) and the kill count display.
 */
public class LevelViewLevelOne extends LevelView {

    private final Group root;
    private final KillCount killCount;

    /**
     * Constructor for the `LevelViewLevelOne` class.
     * Initializes the heart display from the superclass (`LevelView`) and sets up the kill count display.
     *
     * @param root The root group of the scene that will contain all visual elements.
     * @param heartsToDisplay The number of hearts to display initially for the player's health.
     * @param killsToAdvance The number of kills required to advance to the next level.
     */
    public LevelViewLevelOne(Group root, int heartsToDisplay, int killsToAdvance) {
        super(root, heartsToDisplay);
        this.root = root;
        this.killCount = new KillCount(killsToAdvance);
    }

    /**
     * Displays the kill count on the screen.
     */
    public void displayKills() {
        root.getChildren().add(killCount);
    }

    /**
     * Shows the kill count (visible on the screen).
     */
    public void showKills() {
        killCount.showKill();
    }

    /**
     * Hides the kill count from the screen.
     */
    public void hideKills() {
        killCount.hideKill();
    }

    /**
     * Updates the displayed kill count.
     *
     * @param killCount The current number of kills to update the display.
     */
    public void updateKillCount(int killCount) {
        this.killCount.updateKill(killCount);
    }
}