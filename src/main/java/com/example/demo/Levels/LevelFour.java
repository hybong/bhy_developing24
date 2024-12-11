package com.example.demo.Levels;

import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.Levels.levelView.LevelViewLevelFour;
import com.example.demo.media.BackgroundMusic;
import com.example.demo.models.Boss;
import com.example.demo.models.secondBoss;
import javafx.scene.Scene;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jpg";
    private static final String BACKGROUND_MUSIC = "/com/example/demo/media/backgroundMusic/LevelFourMusic.mp3";
    private static final int PLAYER_INITIAL_HEALTH = 8;
    private final Boss bossOne;
    private final secondBoss bossTwo;
    private LevelViewLevelFour levelView;
    private BackgroundMusic backgroundMusic;
    private final int FRAMES_TO_REVIVE = 100;
    private static int BOSS_DEAD_FRAME;

    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        addBackgroundMusic(BACKGROUND_MUSIC);
        bossOne = new Boss();
        bossTwo = new secondBoss();
        BOSS_DEAD_FRAME = 0;
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

        if (bossOne.isDestroyed()) levelView.hideHealthOne();
        if (bossTwo.isDestroyed()) levelView.hideHealthTwo();

        levelView.updateHealthPosition(bossOne, bossTwo);
        levelView.updateBossHealth(bossOne.getHealth(), bossTwo.getHealth());

        updateRevive();

    }

    private void updateRevive() {
        if (bossOne.isDestroyed() || bossTwo.isDestroyed()){
            BOSS_DEAD_FRAME++;
            if (BOSS_DEAD_FRAME == FRAMES_TO_REVIVE) {
                if (bossOne.isDestroyed() && !bossTwo.isDestroyed()) {
                    bossOne.revive();
                    addEnemyUnit(bossOne);
                    levelView.showHealthOne();
                }
                if (bossTwo.isDestroyed() && !bossOne.isDestroyed()) {
                    bossTwo.revive();
                    addEnemyUnit(bossTwo);
                    levelView.showHealthTwo();
                }
                BOSS_DEAD_FRAME = 0;
            }
        }
    }

}