package com.example.demo.Levels;

import com.example.demo.Levels.levelView.LevelView;
import com.example.demo.Levels.levelView.LevelViewLevelFour;
import com.example.demo.media.BackgroundMusic;
import com.example.demo.models.Boss;
import com.example.demo.models.SecondBoss;
import com.example.demo.view.ReviveTimer;
import javafx.scene.Scene;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jpg";
    private static final String BACKGROUND_MUSIC = "/com/example/demo/media/backgroundMusic/LevelFourMusic.mp3";
    private final double BACKGROUND_MUSIC_VOLUME = 0.5;
    private static final int PLAYER_INITIAL_HEALTH = 8;
    private final Boss bossOne;
    private final SecondBoss bossTwo;
    private LevelViewLevelFour levelView;
    private BackgroundMusic backgroundMusic;
    public static final int FRAMES_TO_REVIVE = 10;
    private static int BOSS_DEAD_FRAME;
    private ReviveTimer timer;

    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        addBackgroundMusic(BACKGROUND_MUSIC, BACKGROUND_MUSIC_VOLUME);
        bossOne = new Boss();
        bossTwo = new SecondBoss();
        this.timer = levelView.accessTimer();
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            removeNotActors();
            loseGame();
        }
        else if (bossOne.isDestroyed() && bossTwo.isDestroyed()) {
            removeNotActors();
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
        levelView = new LevelViewLevelFour(getRoot(), PLAYER_INITIAL_HEALTH, Boss.HEALTH, SecondBoss.HEALTH);
        return levelView;
    }

    @Override
    public Scene initializeScene() {
        Scene scene = super.initializeScene();
        levelView.displayShield();
        levelView.displayBossHealth();
        levelView.displayTimer();
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
            timer.showTimer();
            timer.startTimer();
            if (timer.timeFinished()) {
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
                timer.hideTimer();
                timer.reset(FRAMES_TO_REVIVE);
            }
        }
    }

    private void removeNotActors() {
        levelView.hideShieldOne();
        levelView.hideShieldTwo();
        levelView.hideHealthOne();
        levelView.hideHealthTwo();
        timer.hideTimer();
    }

}