package com.example.demo.models;

public class secondBoss extends Boss {

    private static final double BOSS_SHIELD_PROBABILITY = .005;
    private static final int HEALTH_DANGER = 20;
    public static final int HEALTH = 50;
    private static final int MAX_SHIELD = 5;
    private boolean IS_IN_DANGER = false;
    private static final double DANGER_FIRE_RATE = .2;

    public secondBoss() {
        super();
        this.setHealth(HEALTH);
        ShieldCount = 0;
    }

    protected boolean shieldShouldBeActivated() {
        if(ShieldCount >= MAX_SHIELD) return false;
        return Math.random() < BOSS_SHIELD_PROBABILITY || isInDanger();
    }

    private boolean isInDanger() {
        if (this.health <= HEALTH_DANGER) {
            BOSS_FIRE_RATE = DANGER_FIRE_RATE;
            IS_IN_DANGER = true;
        }
        return IS_IN_DANGER;
    }

}