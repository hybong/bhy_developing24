package com.example.demo.Levels.levelView;

import com.example.demo.Levels.LevelFour;
import com.example.demo.models.SecondBoss;
import com.example.demo.view.BossHealth;
import com.example.demo.view.ShieldImage;
import com.example.demo.models.Boss;
import com.example.demo.view.ReviveTimer;
import javafx.scene.Group;

/**
 * The `LevelViewLevelFour` class extends `LevelView` to manage and display elements specific to level four,
 * including the shields, health of two bosses, and a revive timer.
 */
public class LevelViewLevelFour extends LevelView {

    /** The root group in the scene, containing all UI elements for the game. */
    private final Group root;

    /** The first shield image for the boss, indicating shield status. */
    private final ShieldImage shieldImageOne;

    /** The second shield image for the boss, indicating shield status. */
    private final ShieldImage shieldImageTwo;

    /** The health display for the first boss, showing its remaining health. */
    private final BossHealth bossOneHealth;

    /** The health display for the second boss, showing its remaining health. */
    private final BossHealth bossTwoHealth;

    /** The timer for the boss's revive countdown, tracking time left for revival. */
    private final ReviveTimer timer;

    /**
     * Constructor for the `LevelViewLevelFour` class.
     * Initializes the heart display from the superclass (`LevelView`) and sets up the shield, boss health,
     * and revive timer displays.
     *
     * @param root The root group of the scene that will contain all visual elements.
     * @param heartsToDisplay The number of hearts to display initially for the player's health.
     * @param bossOneHealth The initial health of the first boss to be displayed.
     * @param bossTwoHealth The initial health of the second boss to be displayed.
     */
    public LevelViewLevelFour(Group root, int heartsToDisplay, int bossOneHealth, int bossTwoHealth) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImageOne = new ShieldImage();
        this.shieldImageTwo = new ShieldImage();
        this.bossOneHealth = new BossHealth(bossOneHealth);
        this.bossTwoHealth = new BossHealth(bossTwoHealth);
        this.timer = new ReviveTimer(LevelFour.FRAMES_TO_REVIVE);
    }

    /**
     * Displays both shields on the screen.
     */
    public void displayShield() {
        root.getChildren().addAll(shieldImageOne, shieldImageTwo);
    }

    /**
     * Updates the positions of both shields based on the positions of the two bosses.
     *
     * @param bossOne The first boss whose position is used to update the first shield's position.
     * @param bossTwo The second boss whose position is used to update the second shield's position.
     */
    public void updateShieldPosition(Boss bossOne, SecondBoss bossTwo) {
        double bossOneX = bossOne.getLayoutX() + bossOne.getTranslateX();
        double bossOneY = bossOne.getLayoutY() + bossOne.getTranslateY();
        shieldImageOne.updateShieldPosition(bossOneX, bossOneY);
        double bossTwoX = bossTwo.getLayoutX() + bossTwo.getTranslateX();
        double bossTwoY = bossTwo.getLayoutY() + bossTwo.getTranslateY();
        shieldImageTwo.updateShieldPosition(bossTwoX, bossTwoY);
    }

    /**
     * Makes the first shield visible on the screen.
     */
    public void showShieldOne() {
        shieldImageOne.showShield();
    }

    /**
     * Hides the first shield from the screen.
     */
    public void hideShieldOne() {
        shieldImageOne.hideShield();
    }

    /**
     * Makes the second shield visible on the screen.
     */
    public void showShieldTwo() {
        shieldImageTwo.showShield();
    }

    /**
     * Hides the second shield from the screen.
     */
    public void hideShieldTwo() {
        shieldImageTwo.hideShield();
    }

    /**
     * Updates the positions of the health displays for both bosses based on their positions.
     *
     * @param bossOne The first boss whose position is used to update the first health display's position.
     * @param bossTwo The second boss whose position is used to update the second health display's position.
     */
    public void updateHealthPosition(Boss bossOne, SecondBoss bossTwo) {
        double bossOneX = bossOne.getLayoutX() + bossOne.getTranslateX();
        double bossOneY = bossOne.getLayoutY() + bossOne.getTranslateY();
        bossOneHealth.updateHealthPosition(bossOneX, bossOneY);
        double bossTwoX = bossTwo.getLayoutX() + bossTwo.getTranslateX();
        double bossTwoY = bossTwo.getLayoutY() + bossTwo.getTranslateY();
        bossTwoHealth.updateHealthPosition(bossTwoX, bossTwoY);
    }

    /**
     * Updates the displayed health of both bosses.
     *
     * @param presentHealthOne The current health of the first boss.
     * @param presentHealthTwo The current health of the second boss.
     */
    public void updateBossHealth(int presentHealthOne, int presentHealthTwo) {
        bossOneHealth.updateHealth(presentHealthOne);
        bossTwoHealth.updateHealth(presentHealthTwo);
    }

    /**
     * Displays both boss health bars on the screen.
     */
    public void displayBossHealth() {
        root.getChildren().addAll(bossOneHealth, bossTwoHealth);
    }

    /**
     * Hides the health display of the first boss.
     */
    public void hideHealthOne() {
        bossOneHealth.hideHealth();
    }

    /**
     * Hides the health display of the second boss.
     */
    public void hideHealthTwo() {
        bossTwoHealth.hideHealth();
    }

    /**
     * Makes the health display of the first boss visible on the screen.
     */
    public void showHealthOne() {
        bossOneHealth.showHealth();
    }

    /**
     * Makes the health display of the second boss visible on the screen.
     */
    public void showHealthTwo() {
        bossTwoHealth.showHealth();
    }

    /**
     * Displays the revive timer on the screen.
     */
    public void displayTimer() {
        root.getChildren().add(timer);
    }

    /**
     * Provides access to the revive timer object.
     *
     * @return The `ReviveTimer` instance used for the level's revive timer.
     */
    public ReviveTimer accessTimer() {
        return timer;
    }
}