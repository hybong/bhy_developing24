package com.example.demo.Levels.levelView;

import com.example.demo.view.ShieldImage;
import com.example.demo.models.Boss;
import javafx.scene.Group;

public class LevelViewLevelThree extends LevelView {

    private final Group root;
    private final ShieldImage shieldImage;

    public LevelViewLevelThree(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage();
    }

    public void displayShield() {
        root.getChildren().add(shieldImage);
    }

    public void updateShieldPosition(Boss boss) {
        double bossPositionX = boss.getLayoutX() + boss.getTranslateX() - 20;
        double bossPositionY = boss.getLayoutY() + boss.getTranslateY() + 20;
        shieldImage.updateShieldPosition(bossPositionX, bossPositionY);
    }

    public void showShield() {
        shieldImage.showShield();
    }

    public void hideShield() {
        shieldImage.hideShield();
    }

}