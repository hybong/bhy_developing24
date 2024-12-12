package com.example.demo.models;

import com.example.demo.media.SoundEffect;

import java.util.ArrayList;
import java.util.Collections;

public class secondBoss extends Boss {

    private static final String REVIVE_SOUND = "/com/example/demo/media/soundEffects/bossRevive.mp3";
    private final SoundEffect reviveSound;
    private final double REVIVE_SOUND_VOLUME = 1;

    private static final double BOSS_SHIELD_PROBABILITY = .005;
    private static final int HEALTH_DANGER = 30;
    public static final int HEALTH = 50;
    private static final int MAX_SHIELD = 5;
    private static final double DANGER_FIRE_RATE = .1;
    private static final int DANGER_VELOCITY = 12;
    private boolean MOVE_UPDATED;
    private boolean isInDanger;

    public secondBoss() {
        super();
        this.setHealth(HEALTH);
        ShieldCount = 0;
        isInDanger = false;
        MOVE_UPDATED = false;
        reviveSound = new SoundEffect(REVIVE_SOUND);
        this.initializeMovePattern();
    }

    @Override
    protected boolean shieldShouldBeActivated() {
        if(ShieldCount >= MAX_SHIELD) return false;
        return Math.random() < BOSS_SHIELD_PROBABILITY || isInDanger();
    }

    public boolean isInDanger() {
        if (this.health <= HEALTH_DANGER) {
            isInDanger = true;
        }
        return isInDanger;
    }

    @Override
    protected void initializeMovePattern() {
        movePattern = new ArrayList<>();
        indexOfCurrentMove = 0;
        if(this.isInDanger()){
            for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
                movePattern.add(DANGER_VELOCITY);
                movePattern.add(-DANGER_VELOCITY);
                movePattern.add(0);
            }
        }
        else {
            for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
                movePattern.add(VERTICAL_VELOCITY);
                movePattern.add(-VERTICAL_VELOCITY);
                movePattern.add(0);
            }
        }
        Collections.shuffle(movePattern);
    }

    private void updateMovePattern() {
        if(this.isInDanger()){
            this.initializeMovePattern();
            MOVE_UPDATED = true;
        }
    }

    @Override
    public void revive() {
        if(this.isDestroyed()){
            reviveSound.playSoundEffect(REVIVE_SOUND_VOLUME);
            this.setDestroyed(false);
            this.setHealth(HEALTH);
            this.ShieldCount = 0;
            isInDanger = false;
            MOVE_UPDATED = false;
            this.initializeMovePattern();
        }
    }

    @Override
    protected boolean bossFiresInCurrentFrame() {
        if(isInDanger()){
            return Math.random() < DANGER_FIRE_RATE;
        }
        return Math.random() < BOSS_FIRE_RATE;
    }

    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
        if (!MOVE_UPDATED){
            updateMovePattern();
        }
    }

}