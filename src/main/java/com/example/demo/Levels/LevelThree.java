package com.example.demo.Levels;

import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.Levels.levelView.LevelViewLevelThree;
import com.example.demo.media.BackgroundMusic;
import com.example.demo.models.secondBoss;
import javafx.scene.Scene;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    private static final String BACKGROUND_MUSIC = "/com/example/demo/media/backgroundMusic/LevelThreeMusic.mp3";
    private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelFour";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final secondBoss boss;
    private LevelViewLevelThree levelView;

    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new secondBoss();
        addBackgroundMusic(BACKGROUND_MUSIC);
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
            goToNextLevel(NEXT_LEVEL);
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
        levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH, secondBoss.HEALTH);
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
        levelView.updateShieldPosition(boss);
//		boolean bossWasShielded = isBossShielded;

        if (boss.isShielded()) {
            levelView.showShield();
        } else {
            levelView.hideShield();
        }

        levelView.updateHealthPosition(boss);
        levelView.updateBossHealth(boss.getHealth());

    }

}