package com.example.demo.Levels;

import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.Levels.levelView.LevelViewLevelFour;
import com.example.demo.models.Boss;
import com.example.demo.models.secondBoss;
import javafx.scene.Scene;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 8;
    private final Boss bossOne;
    private final secondBoss bossTwo;
    private LevelViewLevelFour levelView;

    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        bossOne = new Boss();
        bossTwo = new secondBoss();
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (bossOne.isDestroyed() && bossTwo.isDestroyed()) {
            winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(bossOne);
            addEnemyUnit(bossTwo);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelFour(getRoot(), PLAYER_INITIAL_HEALTH, Boss.HEALTH, secondBoss.HEALTH);
        return levelView;
    }

    @Override
    public Scene initializeScene() {
        Scene scene = super.initializeScene();
        levelView.displayShield();
        levelView.displayBossHealth();
        return scene;
    }

    public void updateLevelView() {
        super.updateLevelView();
        // update shield position
        levelView.updateShieldPosition(bossOne, bossTwo);
//		boolean bossWasShielded = isBossShielded;

        if (bossOne.isShielded()) levelView.showShieldOne();
        else levelView.hideShieldOne();
        if (bossTwo.isShielded()) levelView.showShieldTwo();
        else levelView.hideShieldTwo();

        levelView.updateHealthPosition(bossOne, bossTwo);
        levelView.updateBossHealth(bossOne.getHealth(), bossTwo.getHealth());

    }

}