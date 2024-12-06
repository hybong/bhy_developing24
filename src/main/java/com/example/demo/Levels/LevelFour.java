package com.example.demo.Levels;

import com.example.demo.models.Boss;
import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.Levels.levelView.LevelViewLevelFour;
import javafx.scene.Scene;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;
    private LevelViewLevelFour levelView;

    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss();
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
        else if (boss.isDestroyed()) {
            winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelFour(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    @Override
    public Scene initializeScene() {
        Scene scene = super.initializeScene();
        levelView.displayShield();
        return scene;
    }

    public void updateLevelView() {
        super.updateLevelView();
        // update shield position
        levelView.updateShieldPosition(boss);
//		boolean bossWasShielded = isBossShielded;

        if (boss.isShielded()) {
            levelView.showShield();
        } else {
            levelView.hideShield();
        }

    }

}