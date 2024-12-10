package com.example.demo.Levels.levelView;

import com.example.demo.view.KillCount;
import javafx.scene.Group;

public class LevelViewLevelOne extends LevelView{

    private final Group root;
    private final KillCount killCount;

    public LevelViewLevelOne(Group root, int heartsToDisplay, int killsToAdvance) {
        super(root, heartsToDisplay);
        this.root = root;
        this.killCount = new KillCount(killsToAdvance);
    }

    public void displayKills() {
        root.getChildren().add(killCount);
    }

    public void showKills() {
        killCount.showKill();
    }

    public void hideKills() {
        killCount.hideKill();
    }

    public void updateKillCount(int killCount) {
        this.killCount.updateKill(killCount);
    }

}
