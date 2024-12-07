package com.example.demo.Levels.levelView;

import com.example.demo.models.secondBoss;
import com.example.demo.view.BossHealth;
import com.example.demo.view.ShieldImage;
import com.example.demo.models.Boss;
import javafx.scene.Group;

public class LevelViewLevelFour extends LevelView {

    private final Group root;
    private final ShieldImage shieldImageOne;
    private final ShieldImage shieldImageTwo;
    private final BossHealth bossOneHealth;
    private final BossHealth bossTwoHealth;

    public LevelViewLevelFour(Group root, int heartsToDisplay, int bossOneHealth, int bossTwoHealth) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImageOne = new ShieldImage();
        this.shieldImageTwo = new ShieldImage();
        this.bossOneHealth = new BossHealth(bossOneHealth);
        this.bossTwoHealth = new BossHealth(bossTwoHealth);
    }

    public void displayShield() {
        root.getChildren().addAll(shieldImageOne, shieldImageTwo);
    }

    public void updateShieldPosition(Boss bossOne, secondBoss bossTwo) {
        double bossOneX = bossOne.getLayoutX() + bossOne.getTranslateX();
        double bossOneY = bossOne.getLayoutY() + bossOne.getTranslateY();
        shieldImageOne.updateShieldPosition(bossOneX, bossOneY);
        double bossTwoX = bossTwo.getLayoutX() + bossTwo.getTranslateX();
        double bossTwoY = bossTwo.getLayoutY() + bossTwo.getTranslateY();
        shieldImageTwo.updateShieldPosition(bossTwoX, bossTwoY);
    }

    public void showShieldOne() {
        shieldImageOne.showShield();
    }

    public void hideShieldOne() {
        shieldImageOne.hideShield();
    }

    public void showShieldTwo() {
        shieldImageTwo.showShield();
    }

    public void hideShieldTwo() {
        shieldImageTwo.hideShield();
    }

    public void updateHealthPosition(Boss bossOne, secondBoss bossTwo) {
        double bossOneX = bossOne.getLayoutX() + bossOne.getTranslateX();
        double bossOneY = bossOne.getLayoutY() + bossOne.getTranslateY();
        bossOneHealth.updateHealthPosition(bossOneX, bossOneY);
        double bossTwoX = bossTwo.getLayoutX() + bossTwo.getTranslateX();
        double bossTwoY = bossTwo.getLayoutY() + bossTwo.getTranslateY();
        bossTwoHealth.updateHealthPosition(bossTwoX, bossTwoY);
    }

    public void updateBossHealth(int presentHealthOne, int presentHealthTwo) {
        bossOneHealth.updateHealth(presentHealthOne);
        bossTwoHealth.updateHealth(presentHealthTwo);
    }

    public void displayBossHealth() {
        root.getChildren().addAll(bossOneHealth, bossTwoHealth);
    }

}