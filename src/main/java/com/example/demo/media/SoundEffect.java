package com.example.demo.media;

import javafx.scene.media.AudioClip;

public class SoundEffect {

    private final AudioClip soundEffect;  // The AudioClip for sound effects

    public SoundEffect(String soundFilePath) {
        // Initialize your sound effects here (paths can be to resources)
        soundEffect = new AudioClip(getClass().getResource(soundFilePath).toExternalForm());
    }

    // Play a sound effect with a specific volume (optional)
    public void playSoundEffect(double volume) {
        soundEffect.setVolume(volume);
        soundEffect.play();
    }

}