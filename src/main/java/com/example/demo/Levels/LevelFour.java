package com.example.demo.Levels;

import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.Levels.levelView.LevelViewLevelFour;
import com.example.demo.media.BackgroundMusic;
import com.example.demo.models.Boss;
import com.example.demo.models.SecondBoss;
import com.example.demo.view.ReviveTimer;
import javafx.scene.Scene;

/**
 * The LevelFour class represents the fourth level in the game.
 * It involves two bosses (a primary and a secondary boss) and handles the gameplay,
 * including spawning the bosses, checking win/loss conditions, and managing the revive mechanic for destroyed bosses.
 */
public class LevelFour extends LevelParent {

    /** The background image for this level. */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jpg";

    /** The background music for this level. */
    private static final String BACKGROUND_MUSIC = "/com/example/demo/media/backgroundMusic/LevelFourMusic.mp3";

    /** The volume of the background music. */
    private final double BACKGROUND_MUSIC_VOLUME = 0.5;

    /** The initial health of the player in this level. */
    private static final int PLAYER_INITIAL_HEALTH = 8;

    /** The first boss in the level. */
    private final Boss bossOne;

    /** The second boss in the level. */
    private final SecondBoss bossTwo;

    /** The level view that handles the graphical representation of the level. */
    private LevelViewLevelFour levelView;

    /** The background music player. */
    private BackgroundMusic backgroundMusic;

    /** The number of frames to wait before a boss can revive. */
    public static final int FRAMES_TO_REVIVE = 10;

    /** The frame counter for a boss being destroyed. */
    private static int BOSS_DEAD_FRAME;

    /** The timer for reviving destroyed bosses. */
    private ReviveTimer timer;

    /**
     * Constructs a LevelFour instance with the specified screen dimensions.
     * It also adds the background music and initializes the two bosses.
     *
     * @param screenHeight the height of the screen for the level
     * @param screenWidth the width of the screen for the level
     */
    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        addBackgroundMusic(BACKGROUND_MUSIC, BACKGROUND_MUSIC_VOLUME);
        bossOne = new Boss();
        bossTwo = new SecondBoss();
        this.timer = levelView.accessTimer();
    }

    /**
     * Initializes the friendly units (the player) for the level.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks whether the game is over by evaluating the player's status or if both bosses are defeated.
     * The game ends if the player is destroyed, or if both bosses are defeated, the player wins.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            removeNotActors();
            loseGame();
        }
        else if (bossOne.isDestroyed() && bossTwo.isDestroyed()) {
            removeNotActors();
            winGame();
        }
    }

    /**
     * Spawns the enemy units for this level. If no enemies exist, both bosses are added to the level.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(bossOne);
            addEnemyUnit(bossTwo);
        }
    }

    /**
     * Instantiates and returns the LevelView for this level.
     * This view handles the graphical representation, health management, and shield displays for the level.
     *
     * @return the LevelView for this level
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelFour(getRoot(), PLAYER_INITIAL_HEALTH, Boss.HEALTH, SecondBoss.HEALTH);
        return levelView;
    }

    /**
     * Initializes the Scene for this level and adds the shield, boss health, and revive timer displays.
     *
     * @return the Scene for this level
     */
    @Override
    public Scene initializeScene() {
        Scene scene = super.initializeScene();
        levelView.displayShield();
        levelView.displayBossHealth();
        levelView.displayTimer();
        return scene;
    }

    /**
     * Updates the level view based on the current state of the bosses and their shields.
     * It also updates the revive timer and the health of the bosses.
     */
    public void updateLevelView() {
        super.updateLevelView();

        // Update shield positions for both bosses
        levelView.updateShieldPosition(bossOne, bossTwo);

        if (bossOne.isShielded()) levelView.showShieldOne();
        else levelView.hideShieldOne();

        if (bossTwo.isShielded()) levelView.showShieldTwo();
        else levelView.hideShieldTwo();

        // Hide health if a boss is destroyed
        if (bossOne.isDestroyed()) levelView.hideHealthOne();
        if (bossTwo.isDestroyed()) levelView.hideHealthTwo();

        // Update the health of both bosses
        levelView.updateHealthPosition(bossOne, bossTwo);
        levelView.updateBossHealth(bossOne.getHealth(), bossTwo.getHealth());

        // Handle revive mechanic for destroyed bosses
        updateRevive();
    }

    /**
     * Handles the revive mechanic for a destroyed boss. If a boss is destroyed,
     * the revive timer is shown and starts counting down. Once the timer reaches zero,
     * the boss is revived and added back to the game.
     */
    private void updateRevive() {
        if (bossOne.isDestroyed() || bossTwo.isDestroyed()) {
            timer.showTimer();
            timer.startTimer();

            if (timer.timeFinished()) {
                // Revive boss one if destroyed and boss two is still alive
                if (bossOne.isDestroyed() && !bossTwo.isDestroyed()) {
                    bossOne.revive();
                    addEnemyUnit(bossOne);
                    levelView.showHealthOne();
                }

                // Revive boss two if destroyed and boss one is still alive
                if (bossTwo.isDestroyed() && !bossOne.isDestroyed()) {
                    bossTwo.revive();
                    addEnemyUnit(bossTwo);
                    levelView.showHealthTwo();
                }

                timer.hideTimer();
                timer.reset(FRAMES_TO_REVIVE);
            }
        }
    }

    /**
     * Removes non-actor elements (such as shields, health, and timers) from the level view.
     */
    private void removeNotActors() {
        levelView.hideShieldOne();
        levelView.hideShieldTwo();
        levelView.hideHealthOne();
        levelView.hideHealthTwo();
        timer.hideTimer();
    }
}