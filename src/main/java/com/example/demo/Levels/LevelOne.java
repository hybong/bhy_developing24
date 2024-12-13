package com.example.demo.Levels;

import com.example.demo.models.ActiveActorDestructible;
import com.example.demo.models.EnemyPlane;
import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.Levels.levelView.LevelViewLevelOne;
import javafx.scene.Scene;

/**
 * The LevelOne class represents the first level in the game.
 * It handles the initialization of the level, spawning of enemies,
 * checking for game over conditions, and managing the level view.
 */
public class LevelOne extends LevelParent {

	/** The background image for this level. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	/** The background music for this level. */
	private static final String BACKGROUND_MUSIC = "/com/example/demo/media/backgroundMusic/LevelOneMusic.mp3";

	/** The next level to transition to. */
	private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelTwo";

	/** The volume of the background music. */
	private final double BACKGROUND_MUSIC_VOLUME = 1;

	/** The total number of enemies for this level. */
	private static final int TOTAL_ENEMIES = 5;

	/** The number of kills required to advance to the next level. */
	private static final int KILLS_TO_ADVANCE = 10;

	/** The probability of spawning a new enemy. */
	private static final double ENEMY_SPAWN_PROBABILITY = .20;

	/** The initial health of the player in this level. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/** The LevelView object that handles the graphical representation of the level. */
	private LevelViewLevelOne levelView;

	/**
	 * Constructs a LevelOne instance with the specified screen dimensions.
	 * It also adds the background music and sets the player's initial health.
	 *
	 * @param screenHeight the height of the screen for the level
	 * @param screenWidth the width of the screen for the level
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		addBackgroundMusic(BACKGROUND_MUSIC, BACKGROUND_MUSIC_VOLUME);
	}

	/**
	 * Checks whether the game is over by evaluating the player's status.
	 * The game ends if the player is destroyed or if they have reached the kill target.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
			levelView.hideKills();
		}
		else if (userHasReachedKillTarget()) {
			checkToNextLevel(NEXT_LEVEL);
			levelView.hideKills();
		}
	}

	/**
	 * Initializes the friendly units (the player) for the level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units for the level based on a random probability.
	 * The number of enemies spawned depends on the total enemies and current enemy count.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiates and returns the LevelView for this level.
	 * This view handles the graphical representation and health management for the level.
	 *
	 * @return the LevelView for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelOne(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
		return levelView;
	}

	/**
	 * Initializes the Scene for this level and adds the kill count display.
	 *
	 * @return the Scene for this level
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.displayKills();
		return scene;
	}

	/**
	 * Updates the level view with the current kill count.
	 * This is called when the user kills an enemy.
	 */
	public void updateLevelView() {
		super.updateLevelView();
		levelView.showKills();
		levelView.updateKillCount(getUser().getNumberOfKills());
	}

	/**
	 * Checks if the user has reached the kill target to advance to the next level.
	 *
	 * @return true if the user has killed enough enemies to advance, false otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}