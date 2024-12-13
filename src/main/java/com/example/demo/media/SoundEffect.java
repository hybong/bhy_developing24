package com.example.demo.media;

import javafx.scene.media.AudioClip;

/**
 * The `SoundEffect` class is responsible for playing sound effects in the game.
 * It allows you to load sound files and play them with customizable volume.
 */
public class SoundEffect {

    private final AudioClip soundEffect;  // The AudioClip for sound effects

    /**
     * Constructor to initialize a sound effect with a specific file.
     *
     * @param soundFilePath The path to the sound file to be played (relative to the resources folder).
     */
    public SoundEffect(String soundFilePath) {
        // Initialize your sound effects here (paths can be to resources)
        soundEffect = new AudioClip(getClass().getResource(soundFilePath).toExternalForm());
    }

    /**
     * Plays the sound effect with a specific volume.
     *
     * @param volume The volume level, where 0.0 is silent and 1.0 is the maximum volume.
     */
    public void playSoundEffect(double volume) {
        soundEffect.setVolume(volume);  // Set the volume for the sound effect
        soundEffect.play();  // Play the sound effect
    }

}