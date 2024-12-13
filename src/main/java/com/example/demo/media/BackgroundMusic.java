package com.example.demo.media;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The `BackgroundMusic` class handles the playback of background music for different game levels.
 * It allows playing, pausing, resuming, stopping, and adjusting the volume of music tracks.
 */
public class BackgroundMusic {

    /**
     * The `mediaPlayer` is used to play the background music.
     * It provides methods for controlling playback such as play, pause, and stop.
     */
    private MediaPlayer mediaPlayer;

    /**
     * The `music` represents the media file for the background music that will be played.
     * It is loaded from the given `musicFilePath`.
     */
    private Media music;

    /**
     * The file path of the background music file, relative to the resources folder.
     * This path is used to load the music file into the `Media` object.
     */
    private final String musicFilePath;

    /**
     * Constructor to initialize the `BackgroundMusic` object with a file path for the music.
     * Initially, no music is loaded.
     *
     * @param musicFilePath The path to the music file to be played (relative to the resources folder).
     */
    public BackgroundMusic(String musicFilePath) {
        mediaPlayer = null;  // Initially, no music is loaded
        this.musicFilePath = musicFilePath;
    }

    /**
     * Starts playing the music for a specific level or track.
     * If there is already music playing, it stops the current music and starts the new track.
     */
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

    /**
     * Stops the music, which can be used when exiting or switching levels.
     * Stops the current track if it's playing.
     */
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    /**
     * Pauses the music if it's currently playing.
     * This can be used to pause the track without stopping it completely.
     */
    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    /**
     * Resumes the music if it was previously paused.
     * This method will continue the track from where it was paused.
     */
    public void resumeMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    /**
     * Sets the volume of the music.
     *
     * @param volume The volume level, where 0.0 is silent and 1.0 is the maximum volume.
     */
    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);  // Set the volume (0.0 is silent, 1.0 is max)
        }
    }

    /**
     * Checks if the music is currently playing.
     *
     * @return `true` if the music is playing, `false` otherwise.
     */
    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
}