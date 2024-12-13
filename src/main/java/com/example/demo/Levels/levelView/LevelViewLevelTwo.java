package com.example.demo.Levels.levelView;

import com.example.demo.view.BossHealth;
import com.example.demo.view.ShieldImage;
import com.example.demo.models.Boss;
import javafx.scene.Group;

/**
 * The `LevelViewLevelTwo` class is a subclass of `LevelView` that specifically manages the visual
 * elements for level two, including the shield and health of the boss, as well as the player's health.
 */
public class LevelViewLevelTwo extends LevelView {

	/** The root group that contains all the UI elements and game components. */
	private final Group root;

	/** The image representing the shield of the boss, which is displayed when the boss has a shield. */
	private final ShieldImage shieldImage;

	/** The health display for the boss, showing the current health of the boss. */
	private final BossHealth bossHealth;

	/**
	 * Constructor for the `LevelViewLevelTwo` class.
	 * Initializes the heart display from the superclass (`LevelView`) and sets up the boss health and shield displays.
	 *
	 * @param root The root group of the scene that will contain all visual elements.
	 * @param heartsToDisplay The number of hearts to display initially for the player's health.
	 * @param bossHealth The initial health of the boss to be displayed.
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay, int bossHealth) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage();
		this.bossHealth = new BossHealth(bossHealth);
	}

	/**
	 * Displays the shield image on the screen.
	 */
	public void displayShield() {
		root.getChildren().add(shieldImage);
	}

	/**
	 * Displays the boss health display on the screen.
	 */
	public void displayBossHealth() {
		root.getChildren().add(bossHealth);
	}

	/**
	 * Updates the shield's position based on the boss's current position.
	 *
	 * @param boss The boss whose position is used to update the shield's position.
	 */
	public void updateShieldPosition(Boss boss) {
		double bossX = boss.getLayoutX() + boss.getTranslateX();
		double bossY = boss.getLayoutY() + boss.getTranslateY();
		shieldImage.updateShieldPosition(bossX, bossY);
	}

	/**
	 * Makes the shield visible on the screen.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the shield from the screen.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}

	/**
	 * Updates the position of the boss health display based on the boss's current position.
	 *
	 * @param boss The boss whose position is used to update the health display position.
	 */
	public void updateHealthPosition(Boss boss) {
		double bossX = boss.getLayoutX() + boss.getTranslateX();
		double bossY = boss.getLayoutY() + boss.getTranslateY();
		bossHealth.updateHealthPosition(bossX, bossY);
	}

	/**
	 * Updates the displayed health of the boss.
	 *
	 * @param presentHealth The current health of the boss.
	 */
	public void updateBossHealth(int presentHealth) {
		bossHealth.updateHealth(presentHealth);
	}

	/**
	 * Hides the boss health display from the screen.
	 */
	public void hideBossHealth() {
		bossHealth.hideHealth();
	}
}