package com.example.demo.media;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BackgroundMusicTest {

    private static BackgroundMusic backgroundMusic;

    @BeforeAll
    public static void setupClass() {
        // Start JavaFX toolkit before running tests
        Platform.startup(() -> {});

        // Initialize your BackgroundMusic instance (you can mock or use a real file path)
        backgroundMusic = new BackgroundMusic("/com/example/demo/media/backgroundMusic/LevelOneMusic.mp3");
    }

    @Test
    void testPlayMusic() {
        // Run on the JavaFX Application Thread
        Platform.runLater(() -> {
            backgroundMusic.playMusic();
            assertTrue(backgroundMusic.isPlaying(), "The music should be playing after playMusic is called.");
        });
    }

    @Test
    void testStopMusic() {
        // Run on the JavaFX Application Thread
        Platform.runLater(() -> {
            backgroundMusic.playMusic();
            backgroundMusic.stopMusic();
            assertFalse(backgroundMusic.isPlaying(), "The music should not be playing after stopMusic is called.");
        });
    }

    @Test
    void testPauseAndResumeMusic() {
        // Run on the JavaFX Application Thread
        Platform.runLater(() -> {
            backgroundMusic.playMusic();
            backgroundMusic.pauseMusic();
            assertFalse(backgroundMusic.isPlaying(), "The music should be paused.");

            backgroundMusic.resumeMusic();
            assertTrue(backgroundMusic.isPlaying(), "The music should resume playing after resumeMusic is called.");
        });
    }

}
