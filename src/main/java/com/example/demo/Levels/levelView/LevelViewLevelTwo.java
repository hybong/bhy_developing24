package com.example.demo.Levels.levelView;

import com.example.demo.view.BossHealth;
import com.example.demo.view.ShieldImage;
import com.example.demo.models.Boss;
import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private final Group root;
	private final ShieldImage shieldImage;
	private final BossHealth bossHealth;
	
	public LevelViewLevelTwo(Group root, int heartsToDisplay, int bossHealth) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage();
		this.bossHealth = new BossHealth(bossHealth);
	}
	
	public void displayShield() {
		root.getChildren().add(shieldImage);
	}

	public void displayBossHealth() {
		root.getChildren().add(bossHealth);
	}

	public void updateShieldPosition(Boss boss) {
		double bossX = boss.getLayoutX() + boss.getTranslateX();
		double bossY = boss.getLayoutY() + boss.getTranslateY();
		shieldImage.updateShieldPosition(bossX, bossY);
	}
	
	public void showShield() {
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

	public void updateHealthPosition(Boss boss) {
		double bossX = boss.getLayoutX() + boss.getTranslateX();
		double bossY = boss.getLayoutY() + boss.getTranslateY();
		bossHealth.updateHealthPosition(bossX, bossY);
	}
	public void updateBossHealth(int presentHealth){
		bossHealth.updateHealth(presentHealth);
	}

	public void hideBossHealth() {
		bossHealth.hideHealth();
	}

}