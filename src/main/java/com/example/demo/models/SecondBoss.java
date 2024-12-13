package com.example.demo.models;

import com.example.demo.media.SoundEffect;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The `SecondBoss` class represents a more advanced boss in the game, with additional mechanics such as a shield,
 * unique movement patterns, and the ability to enter a "danger" state when health is low.
 */
public class SecondBoss extends Boss {

    /**
     * Path to the revive sound effect that is played when the boss is revived.
     * This sound effect plays whenever the boss regenerates its health or respawns.
     */
    private static final String REVIVE_SOUND = "/com/example/demo/media/soundEffects/bossRevive.mp3";

    /**
     * The sound effect that is played when the boss is revived.
     * This sound is triggered when the boss regains health or returns after being defeated.
     */
    private final SoundEffect reviveSound;

    /**
     * The volume level for the revive sound effect. This controls how loud the revive sound is when the boss is revived.
     */
    private final double REVIVE_SOUND_VOLUME = 1;

    /**
     * Probability of the boss activating its shield during the game.
     * This value determines the chance that the boss will use its shield to block incoming projectiles.
     */
    private static final double BOSS_SHIELD_PROBABILITY = .005;

    /**
     * The health threshold below which the boss enters a "danger" state.
     * When the boss's health is lower than this value, it will become more aggressive and change its behavior.
     */
    private static final int HEALTH_DANGER = 30;

    /**
     * The maximum health of the second boss.
     * This value represents the total health the boss starts with.
     */
    public static final int HEALTH = 80;

    /**
     * The maximum number of shields the boss can activate during the game.
     * The boss can only shield itself a limited number of times before it must recharge or use other defensive strategies.
     */
    private static final int MAX_SHIELD = 5;

    /**
     * The increased fire rate when the boss is in the danger state.
     * The boss fires projectiles more frequently when its health drops below the danger threshold.
     */
    public static final double DANGER_FIRE_RATE = .15;

    /**
     * The increased movement speed when the boss is in the danger state.
     * The boss moves faster when its health is below the danger threshold, making it more difficult for the player to avoid.
     */
    private static final int DANGER_VELOCITY = 12;

    /**
     * Flag to check if the boss's movement pattern has been updated.
     * This flag helps track whether the boss's behavior should change, for example, when switching between movement patterns.
     */
    private boolean MOVE_UPDATED;

    /**
     * Flag to indicate if the boss is currently in the "danger" state.
     * The boss enters this state when its health drops below a certain threshold and becomes more aggressive.
     */
    private boolean isInDanger;

    /**
     * Constructor for the `SecondBoss` class.
     * Initializes the boss with health, shield count, and danger state.
     */
    public SecondBoss() {
        super(); // Call the parent constructor (Boss class)
        this.setHealth(HEALTH);
        ShieldCount = 0;
        isInDanger = false;
        MOVE_UPDATED = false;
        reviveSound = new SoundEffect(REVIVE_SOUND); // Initialize revive sound effect
        this.initializeMovePattern(); // Initialize the movement pattern
    }

    /**
     * Checks whether the boss should activate its shield based on health, shield count, and random chance.
     *
     * @return `true` if the shield should be activated, `false` otherwise.
     */
    @Override
    protected boolean shieldShouldBeActivated() {
        if(ShieldCount >= MAX_SHIELD) return false; // If the boss has reached max shields, don't activate
        return Math.random() < BOSS_SHIELD_PROBABILITY || isInDanger(); // Activate shield if in danger or random chance
    }

    /**
     * Determines if the boss is in a danger state based on its health.
     * The boss is considered to be in danger if its health is below the defined threshold.
     *
     * @return `true` if the boss is in danger, `false` otherwise.
     */
    public boolean isInDanger() {
        if (this.health <= HEALTH_DANGER) {
            isInDanger = true; // Set to danger state if health is low
        }
        return isInDanger;
    }

    /**
     * Initializes or updates the boss's movement pattern.
     * The movement pattern differs when the boss is in danger (faster and more erratic movement).
     */
    @Override
    protected void initializeMovePattern() {
        movePattern = new ArrayList<>();
        indexOfCurrentMove = 0;
        if(this.isInDanger()){
            // If the boss is in danger, move faster and erratically
            for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
                movePattern.add(DANGER_VELOCITY);
                movePattern.add(-DANGER_VELOCITY);
                movePattern.add(0);
            }
        }
        else {
            // Otherwise, use normal movement
            for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
                movePattern.add(VERTICAL_VELOCITY);
                movePattern.add(-VERTICAL_VELOCITY);
                movePattern.add(0);
            }
        }
        Collections.shuffle(movePattern); // Shuffle the movement pattern to add unpredictability
    }

    /**
     * Updates the boss's movement pattern when it enters a danger state.
     * This method ensures that the boss's movement pattern is reinitialized if the danger state is triggered.
     */
    private void updateMovePattern() {
        if(this.isInDanger()){
            this.initializeMovePattern(); // Reinitialize move pattern if in danger
            MOVE_UPDATED = true; // Flag that movement has been updated
        }
    }

    /**
     * Revives the boss if it has been destroyed in Level Four. Resets health, shield count, and the danger state.
     */
    @Override
    public void revive() {
        if(this.isDestroyed()){
            reviveSound.playSoundEffect(REVIVE_SOUND_VOLUME); // Play the revive sound effect
            this.setDestroyed(false); // Mark the boss as not destroyed
            this.setHealth(HEALTH); // Reset health
            this.ShieldCount = 0; // Reset shield count
            isInDanger = false; // Reset danger state
            MOVE_UPDATED = false; // Reset move update flag
            this.initializeMovePattern(); // Reinitialize the movement pattern
        }
    }

    /**
     * Determines whether the boss fires a projectile in the current frame.
     * The fire rate increases when the boss is in danger.
     *
     * @return `true` if the boss fires a projectile, `false` otherwise.
     */
    @Override
    protected boolean bossFiresInCurrentFrame() {
        if(isInDanger()){
            return Math.random() < DANGER_FIRE_RATE; // Increased fire rate when in danger
        }
        return Math.random() < BOSS_FIRE_RATE; // Normal fire rate
    }

    /**
     * Updates the boss's position, shield state, and movement pattern.
     */
    @Override
    public void updateActor() {
        updatePosition(); // Update the boss's position
        updateShield(); // Update the shield state
        if (!MOVE_UPDATED){ // If movement hasn't been updated due to danger, update the movement pattern
            updateMovePattern();
        }
    }
}