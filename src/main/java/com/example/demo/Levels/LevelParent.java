package com.example.demo.Levels;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.controller.Main;
import com.example.demo.controller.inGameController.*;
import com.example.demo.media.BackgroundMusic;
import com.example.demo.media.SoundEffect;
import com.example.demo.models.ActiveActorDestructible;
import com.example.demo.models.FighterPlane;
import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.models.UserPlane;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The LevelParent class serves as the base class for game levels, containing common functionality such as managing the game loop,
 * player and enemy units, projectiles, collision detection, level progression, and background music.
 * Subclasses should implement level-specific features such as spawning enemies and managing the level view.
 */
public abstract class LevelParent extends Observable {

	/** Constant to adjust the screen height for UI elements. */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/** Millisecond delay between each frame in the game loop. */
	private static final int MILLISECOND_DELAY = 50;

	/** The screen height for the level. */
	private final double screenHeight;

	/** The screen width for the level. */
	private final double screenWidth;

	/** The maximum y-position for enemies to spawn. */
	private final double enemyMaximumYPosition;

	/** The root node for all visual elements of the scene. */
	private final Group root;

	/** The game loop timer. */
	private final Timeline timeline;

	/** The user's plane. */
	private final UserPlane user;

	/** The scene that represents the game screen. */
	private final Scene scene;

	/** The background image for the level. */
	private final ImageView background;

	/** Button to pause the game. */
	private final PauseButton pauseButton;

	/** Button to resume the game. */
	private final PlayButton playButton;

	/** Menu displayed when the game is paused. */
	private final PauseMenu pauseMenu;

	/** Main controller for transitioning between scenes. */
	private final Main main = new Main();

	/** The name of the current level. */
	private static String currentLevelName = "com.example.demo.Levels.LevelOne";

	/** The name of the next level. */
	public static String nextLevelName = "com.example.demo.Levels.LevelTwo";

	/** The winning song to play when the player wins. */
	private final String WINNING_SONG = "/com/example/demo/media/soundEffects/winningSong.mp3";

	/** List of friendly units in the level. */
	private final List<ActiveActorDestructible> friendlyUnits;

	/** List of enemy units in the level. */
	private final List<ActiveActorDestructible> enemyUnits;

	/** List of projectiles fired by the user. */
	private final List<ActiveActorDestructible> userProjectiles;

	/** List of projectiles fired by enemies. */
	private final List<ActiveActorDestructible> enemyProjectiles;

	/** The current number of enemies in the level. */
	private int currentNumberOfEnemies;

	/** Whether the game is paused. */
	private boolean isPaused;

	/** The level view for rendering UI elements like health bars and scores. */
	private LevelView levelView;

	/** Background music player. */
	private BackgroundMusic backgroundMusic;

	/** Whether the background music is muted. */
	public static boolean isMute = false;

	/** Menu displayed when the player loses the level. */
	private LoseLevelMenu loseLevelMenu;

	/** Menu displayed when the player wins the level. */
	private WinLevelMenu winLevelMenu;

	/** Menu displayed when the player wins the game. */
	private WinGameMenu winGameMenu;

	/** Sound effect for the winning song. */
	private SoundEffect winningSong;

	/** Volume level for the winning song. */
	private final double WINNING_SONG_VOLUME = 1;

	/**
	 * Constructs a new LevelParent object.
	 * Initializes the level with the specified background image, screen dimensions, and player's initial health.
	 * Sets up the root, scene, and game loop.
	 *
	 * @param backgroundImageName the background image for the level
	 * @param screenHeight the screen height
	 * @param screenWidth the screen width
	 * @param playerInitialHealth the initial health of the player
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		this.pauseButton = new PauseButton(this::pauseGame);
		this.playButton = new PlayButton(this::resumeGame);
		this.isPaused = false;
		this.pauseMenu = new PauseMenu(this);
		this.loseLevelMenu = new LoseLevelMenu(this);

		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Pauses the game by stopping the game loop and showing the pause menu.
	 */
	private void pauseGame() {
		timeline.pause();
		isPaused = true;
		pauseButton.hidePauseButton();
		playButton.showPlayButton();
		pauseMenu.setVisible(true);
	}

	/**
	 * Resumes the game by restarting the game loop and hiding the pause menu.
	 */
	public void resumeGame() {
		timeline.play();
		isPaused = false;
		playButton.hidePlayButton();
		pauseButton.showPauseButton();
		pauseMenu.setVisible(false);
	}

	/**
	 * Hides both the play and pause buttons from the UI.
	 * This is typically called when the game is paused and the pause menu is displayed.
	 * It ensures that the user can't accidentally resume the game from the pause screen.
	 */
	protected void hidePausePlayButton() {
		playButton.hidePlayButton();
		pauseButton.hidePauseButton();
	}

	/**
	 * Abstract method for initializing the friendly units in the level (e.g., user plane).
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Abstract method to check if the game is over (win/loss conditions).
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method for spawning enemy units.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to instantiate the level's view, which manages health bars, shields, etc.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the scene, including the background, friendly units, and UI elements (pause/play buttons, pause menu).
	 *
	 * @return the scene for this level
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		root.getChildren().addAll(pauseButton, playButton, pauseMenu, loseLevelMenu);
		pauseButton.toFront();
		return scene;
	}

	/**
	 * Starts the game by playing the game loop.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Transitions to the next level.
	 * Notifies observers of the level change and stops background music.
	 *
	 * @param levelName the name of the next level
	 */
	public void goToNextLevel(String levelName) {
		currentLevelName = levelName;
		timeline.stop();
		setChanged();
		backgroundMusic.stopMusic();
		notifyObservers(levelName);
	}

	/**
	 * Checks whether to proceed to the next level if the current level is completed.
	 *
	 * @param levelName the name of the next level
	 */
	public void checkToNextLevel(String levelName) {
		timeline.stop();
		removeActorsNonActors();
		nextLevelName = levelName;
		winLevelMenu = new WinLevelMenu(this);
		root.getChildren().add(winLevelMenu);
		winLevelMenu.setVisible(true);
		winLevelMenu.toFront();
	}

	/**
	 * Updates the game scene each frame, including spawning enemies, handling collisions, updating the level view, etc.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handleProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the game loop, which runs every frame.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background image for the level and sets up key and mouse event handlers for player movement and firing projectiles.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if(!isPaused) {
					if (kc == KeyCode.UP || kc == KeyCode.W) user.moveUp();
					if (kc == KeyCode.DOWN || kc == KeyCode.S) user.moveDown();
					if (kc == KeyCode.LEFT || kc == KeyCode.A) user.moveLeft();
					if (kc == KeyCode.RIGHT || kc == KeyCode.D) user.moveRight();
					if (kc == KeyCode.SHIFT) user.moveFaster();
					if (kc == KeyCode.SPACE) fireProjectile();
				}
			}
		});
		background.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent m) {
				if(!isPaused) {
					if (m.getButton() == MouseButton.PRIMARY || m.getButton() == MouseButton.SECONDARY)
						fireProjectile();
				}
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if(!isPaused) {
					if (kc == KeyCode.UP || kc == KeyCode.DOWN || kc == KeyCode.W || kc == KeyCode.S)
						user.stopVertically();
					if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT || kc == KeyCode.A || kc == KeyCode.D)
						user.stopHorizontally();
					if (kc == KeyCode.SHIFT) user.moveSlower();
				}
			}
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires a projectile from the player's plane.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy fire by having each enemy spawn a projectile.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns a projectile for the given enemy.
	 *
	 * @param projectile the projectile to spawn
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the actors in the game, including friendly units, enemies, and projectiles.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all actors (e.g., planes, projectiles) that are destroyed.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from the game.
	 *
	 * @param actors the list of actors to check and remove if destroyed
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Removes all actors (both friendly and enemy units, as well as projectiles)
	 * from the game scene and clears their respective lists.
	 * <p>
	 * This method is typically called when transitioning between levels or when the game is over,
	 * to clear all the actors from the scene.
	 * </p>
	 */
	private void removeAllActors() {
		removeActors(friendlyUnits);
		removeActors(enemyUnits);
		removeActors(userProjectiles);
		removeActors(enemyProjectiles);
	}

	/**
	 * Removes the specified list of actors from both the game scene and their internal list.
	 * <p>
	 * This method iterates over the provided list, removes each actor from the scene's root,
	 * and also removes it from the internal list of actors.
	 * </p>
	 *
	 * @param actors The list of actors to remove from the game scene and the internal list.
	 *               This can include friendly units, enemy units, projectiles, etc.
	 */
	private void removeActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> actorsToRemove = new ArrayList<>(actors);
		root.getChildren().removeAll(actorsToRemove);
		actors.removeAll(actorsToRemove);
	}

	/**
	 * Handles collisions between planes.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and user projectiles.
	 */
	private void handleProjectileCollisions() {
		handleCollisions(enemyProjectiles, userProjectiles);
	}

	/**
	 * General collision handling method between two lists of actors.
	 *
	 * @param actors1 the first list of actors
	 * @param actors2 the second list of actors
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
								  List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Handles enemy penetration past the user plane's defenses (e.g., if an enemy reaches the user plane).
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the level view (e.g., health display) based on the user's status.
	 */
	protected void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the kill count based on the number of enemies destroyed since the last update.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Determines if the enemy has penetrated the defenses of the user.
	 *
	 * @param enemy the enemy plane
	 * @return true if the enemy has crossed into the user's area
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Handles the win condition when the player completes the level.
	 */
	protected void winGame() {
		removeActorsNonActors();
		timeline.stop();
		levelView.showWinImage();
		backgroundMusic.stopMusic();
		winningSong = new SoundEffect(WINNING_SONG);
		winningSong.playSoundEffect(WINNING_SONG_VOLUME);

		winGameMenu = new WinGameMenu(this);
		root.getChildren().add(winGameMenu);
		winGameMenu.setVisible(true);
		winGameMenu.toFront();
	}

	/**
	 * Handles the lose condition when the player fails the level.
	 */
	protected void loseGame() {
		removeActorsNonActors();
		timeline.stop();
		levelView.showGameOverImage();
		loseLevelMenu.setVisible(true);
		loseLevelMenu.toFront();
	}

	/**
	 * Returns the user's plane object.
	 *
	 * @return the UserPlane object
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Returns the root node for the level's visual elements.
	 *
	 * @return the Group root
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Returns the current number of enemies.
	 *
	 * @return the number of enemies
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the level.
	 *
	 * @param enemy the enemy unit to add
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Returns the maximum Y-position for enemy units.
	 *
	 * @return the enemy maximum Y-position
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Returns the width of the screen.
	 *
	 * @return the screen width
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user is destroyed (i.e., the player has lost).
	 *
	 * @return true if the user is destroyed
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the current number of enemies in the level.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	/**
	 * Adds background music to the level.
	 *
	 * @param backgroundMusic the music file to play
	 * @param volume the volume level of the music
	 */
	protected void addBackgroundMusic(String backgroundMusic, double volume) {
		this.backgroundMusic = new BackgroundMusic(backgroundMusic);
		this.backgroundMusic.playMusic();
		this.backgroundMusic.setVolume(volume);
		if (isMute) {
			this.backgroundMusic.pauseMusic();
		}
	}

	/**
	 * Toggles the background music between playing and paused.
	 */
	public void toggleBackgroundMusic() {
		if (backgroundMusic.isPlaying()) {
			isMute = true;
			backgroundMusic.pauseMusic();
		} else {
			isMute = false;
			backgroundMusic.resumeMusic();
		}
	}

	/**
	 * Checks if the background music is currently playing.
	 *
	 * @return true if the music is playing
	 */
	public boolean backgroundMusicPlaying() {
		return backgroundMusic.isPlaying();
	}

	/**
	 * Transitions to the main menu.
	 */
	public void goToMainMenu() {
		try {
			timeline.stop();
			backgroundMusic.stopMusic();
			setChanged();
			Stage stage = (Stage) root.getScene().getWindow();
			main.start(stage);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException |
				 IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

	/**
	 * Restarts the current level.
	 */
	public void replayLevel() {
		goToNextLevel(currentLevelName);
	}

	/**
	 * Removes all actors and non-actors from the level.
	 */
	private void removeActorsNonActors() {
		levelView.hideHearts();
		hidePausePlayButton();
		removeAllActors();
	}

	/**
	 * Exits the game.
	 */
	public void exitGame() {
		timeline.stop();
		backgroundMusic.stopMusic();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}
}
