package com.example.demo.models;

import java.util.Collections;

public class secondBoss extends Boss {

    private static final double BOSS_SHIELD_PROBABILITY = .005;
    private static final int HEALTH_DANGER = 20;
    public static final int HEALTH = 50;
    private static final int MAX_SHIELD = 5;
    private static final double DANGER_FIRE_RATE = .001;
    private static final int DANGER_MOVE_FREQUENCY = 10;
    private static final int DANGER_VELOCITY = 12;
    private boolean isInDanger;

    public secondBoss() {
        super();
        this.setHealth(HEALTH);
        ShieldCount = 0;
        isInDanger = false;
    }

    protected boolean shieldShouldBeActivated() {
        if(ShieldCount >= MAX_SHIELD) return false;
        return Math.random() < BOSS_SHIELD_PROBABILITY || isInDanger();
    }

    private boolean isInDanger() {
        if (this.health <= HEALTH_DANGER) {
            BOSS_FIRE_RATE = DANGER_FIRE_RATE;
            MOVE_FREQUENCY_PER_CYCLE = DANGER_MOVE_FREQUENCY;
            initializeMovePattern();
            isInDanger = true;
        }
        return isInDanger;
    }

    @Override
    protected void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(DANGER_VELOCITY);
            movePattern.add(-DANGER_VELOCITY);
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

}