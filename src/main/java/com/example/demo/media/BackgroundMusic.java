package com.example.demo.media;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusic {

    private MediaPlayer mediaPlayer;  // The MediaPlayer to play the music
    private Media music;
    private final String musicFilePath;

    // Constructor: Initialize the mediaPlayer with a default track
    public BackgroundMusic(String musicFilePath) {
        mediaPlayer = null;  // Initially, no music is loaded
        this.musicFilePath = musicFilePath;
    }

    // Method to play music for a specific level (track)
    public void playMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();  // Stop any music currently playing
        }

        // Load the new music file
        music = new Media(getClass().getResource(musicFilePath).toExternalForm());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music indefinitely
        mediaPlayer.setVolume(1); // Set a default volume (0.0 to 1.0)
        mediaPlayer.play(); // Start playing the music
    }

    // Method to stop the music (can be used when exiting or switching levels)
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    // Method to change the volume
    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);  // Set the volume (0.0 is silent, 1.0 is max)
        }
    }

    // Method to check if music is playing
    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
}
