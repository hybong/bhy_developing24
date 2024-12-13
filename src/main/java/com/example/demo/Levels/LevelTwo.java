package com.example.demo.Levels;

import com.example.demo.models.Boss;
import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.Levels.levelView.LevelViewLevelTwo;
import javafx.scene.Scene;

/**
 * The LevelTwo class represents the second level in the game.
 * It handles the initialization of the level, spawning of enemies (including a boss),
 * checking for game over conditions, and managing the level view.
 */
public class LevelTwo extends LevelParent {

	/** The background image for this level. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

	/** The background music for this level. */
	private static final String BACKGROUND_MUSIC = "/com/example/demo/media/backgroundMusic/LevelTwoMusic.mp3";

	/** The next level to transition to. */
	private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelThree";

	/** The volume of the background music. */
	private final double BACKGROUND_MUSIC_VOLUME = 1;

	/** The initial health of the player in this level. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/** The boss for this level. */
	private final Boss boss;

	/** The LevelView object that handles the graphical representation of the level. */
	private LevelViewLevelTwo levelView;

	/**
	 * Constructs a LevelTwo instance with the specified screen dimensions.
	 * It also adds the background music and initializes the boss.
	 *
	 * @param screenHeight the height of the screen for the level
	 * @param screenWidth the width of the screen for the level
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
		addBackgroundMusic(BACKGROUND_MUSIC, BACKGROUND_MUSIC_VOLUME);
	}

	/**
	 * Initializes the friendly units (the player) for the level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks whether the game is over by evaluating the player's status or if the boss is defeated.
	 * The game ends if the player is destroyed, or if the boss is defeated, the next level is triggered.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			removeNotActors();
			loseGame();
		}
		else if (boss.isDestroyed()) {
			removeNotActors();
			checkToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Spawns enemy units for the level. If no enemies exist, the boss is added as the first enemy.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates and returns the LevelView for this level.
	 * This view handles the graphical representation, health management, and shield display for the level.
	 *
	 * @return the LevelView for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH, Boss.HEALTH);
		return levelView;
	}

	/**
	 * Initializes the Scene for this level and adds the shield and boss health displays.
	 *
	 * @return the Scene for this level
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.displayShield();
		levelView.displayBossHealth();
		return scene;
	}

	/**
	 * Updates the level view with the current status of the boss and the player's shield.
	 * Displays or hides the shield depending on whether the boss is shielded.
	 * Updates the boss's health in the view.
	 */
	public void updateLevelView() {
		super.updateLevelView();
		levelView.updateShieldPosition(boss);

		if (boss.isShielded()) {
			levelView.showShield();
		} else {
			levelView.hideShield();
		}

		levelView.updateHealthPosition(boss);
		levelView.updateBossHealth(boss.getHealth());
	}

	/**
	 * Removes non-actor elements (such as shield and boss health) from the level view.
	 */
	private void removeNotActors() {
		levelView.hideShield();
		levelView.hideBossHealth();
	}

}
