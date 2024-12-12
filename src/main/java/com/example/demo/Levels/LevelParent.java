package com.example.demo.Levels;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.controller.Main;
import com.example.demo.controller.inGameController.*;
import com.example.demo.media.BackgroundMusic;
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

public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	private final PauseButton pauseButton;
	private final PlayButton playButton;
	private final PauseMenu pauseMenu;
	private final Main main = new Main();
	private static String currentLevelName = "com.example.demo.Levels.LevelOne";
	public static String nextLevelName = "com.example.demo.Levels.LevelTwo";

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;
	
	private int currentNumberOfEnemies;
	private boolean isPaused;
	private LevelView levelView;
	private BackgroundMusic backgroundMusic;
	public static boolean isMute = false;
	private LoseLevelMenu loseLevelMenu;
	private WinLevelMenu winLevelMenu;
	private WinGameMenu winGameMenu;

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
		pauseMenu = new PauseMenu(this);
		loseLevelMenu = new LoseLevelMenu(this);

		initializeTimeline();
		friendlyUnits.add(user);
	}

	private void pauseGame() {
		timeline.pause();
		isPaused = true;
		pauseButton.hidePauseButton();
		playButton.showPlayButton();
		pauseMenu.setVisible(true);
	}

	public void resumeGame() {
		timeline.play();
		isPaused = false;
		playButton.hidePlayButton();
		pauseButton.showPauseButton();
		pauseMenu.setVisible(false);
	}

	protected void hidePausePlayButton() {
		playButton.hidePlayButton();
		pauseButton.hidePauseButton();
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		root.getChildren().addAll(pauseButton, playButton, pauseMenu, loseLevelMenu);
		return scene;
	}

	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	public void goToNextLevel(String levelName) {
		currentLevelName = levelName;
		timeline.stop();
		setChanged();
		backgroundMusic.stopMusic();
		notifyObservers(levelName);
	}

	public void checkToNextLevel(String levelName) {
		timeline.stop();
		removeActorsNonActors();
		nextLevelName = levelName;
		winLevelMenu = new WinLevelMenu(this);
		root.getChildren().add(winLevelMenu);
		winLevelMenu.setVisible(true);
		winLevelMenu.toFront();
	}

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

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

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

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void removeAllActors() {
		removeActors(friendlyUnits);
		removeActors(enemyUnits);
		removeActors(userProjectiles);
		removeActors(enemyProjectiles);
	}

	private void removeActors(List<ActiveActorDestructible> actors) {
		// Filter the actors based on the provided condition
		List<ActiveActorDestructible> actorsToRemove = actors.stream().collect(Collectors.toList());

		// Remove those actors from the UI (root.getChildren())
		root.getChildren().removeAll(actorsToRemove);

		// Remove those actors from the original list
		actors.removeAll(actorsToRemove);
	}


	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleProjectileCollisions() {
		handleCollisions(enemyProjectiles, userProjectiles);
	}

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

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	protected void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		removeActorsNonActors();
		timeline.stop();
		levelView.showWinImage();
		winGameMenu = new WinGameMenu(this);
		root.getChildren().add(winGameMenu);
		winGameMenu.setVisible(true);
		winGameMenu.toFront();
	}

	protected void loseGame() {
		removeActorsNonActors();
		timeline.stop();
		levelView.showGameOverImage();
		loseLevelMenu.setVisible(true);
		loseLevelMenu.toFront();
	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	protected void addBackgroundMusic(String backgroundMusic) {
		this.backgroundMusic = new BackgroundMusic(backgroundMusic);
        this.backgroundMusic.playMusic();
        if(isMute) {
            this.backgroundMusic.pauseMusic();
        }
    }

	public void toggleBackgroundMusic() {
		if(backgroundMusic.isPlaying()) {
			isMute = true;
            backgroundMusic.pauseMusic();
        } else {
			isMute = false;
            backgroundMusic.resumeMusic();
        }
	}

	public boolean backgroundMusicPlaying() {
		return backgroundMusic.isPlaying();
	}

	public void goToMainMenu() {
		try {
			timeline.stop();
			backgroundMusic.stopMusic();
			setChanged();
			Stage stage = (Stage) root.getScene().getWindow();
			main.start(stage);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException |
				IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

	public void replayLevel() {
		goToNextLevel(currentLevelName);
	}

	private void removeActorsNonActors() {
		levelView.hideHearts();
		hidePausePlayButton();
		removeAllActors();
	}

	public void exitGame() {
		timeline.stop();
		backgroundMusic.stopMusic();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
