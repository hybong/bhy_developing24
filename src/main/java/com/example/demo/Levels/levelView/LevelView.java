package com.example.demo.Levels.levelView;

import com.example.demo.view.GameOverImage;
import com.example.demo.view.HeartDisplay;
import com.example.demo.view.WinImage;
import javafx.scene.Group;

/**
 * The `LevelView` class is responsible for managing and updating the visual elements specific to the level.
 * It includes the player's health (in the form of hearts), win image, and game over image.
 */
public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;

	/**
	 * Constructor to initialize the level view with the provided root group and the initial number of hearts.
	 *
	 * @param root The root group of the scene that will contain all visual elements.
	 * @param heartsToDisplay The number of hearts to display initially for the player's health.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage();
		this.gameOverImage = new GameOverImage();
	}

	/**
	 * Displays the heart display (the player's health) on the screen.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image when the player wins the level.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game over image when the player loses the level.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Removes hearts from the heart display based on the player's remaining health.
	 *
	 * @param heartsRemaining The number of hearts the player has remaining.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Hides the heart display (removes it from the screen).
	 */
	public void hideHearts() {
		heartDisplay.hideHearts();
	}
}