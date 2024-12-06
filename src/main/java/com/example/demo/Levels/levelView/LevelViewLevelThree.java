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

}