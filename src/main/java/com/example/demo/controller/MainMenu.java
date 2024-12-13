package com.example.demo.controller;

import com.example.demo.media.BackgroundMusic;
import com.example.demo.media.SoundEffect;

import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.lang.reflect.InvocationTargetException;

import static com.example.demo.Levels.LevelParent.isMute;

/**
 * MainMenu class is responsible for displaying the main menu, handling user interaction through buttons,
 * playing background music, and showing a background video.
 */
public class MainMenu {

    /** Path to the background video for the main menu. */
    private final String BACKGROUND_VIDEO = "/com/example/demo/video/mainMenuBackground.mp4";  // Update to video path

    /** Path to the background music for the main menu. */
    private final String BACKGROUND_MUSIC = "/com/example/demo/media/backgroundMusic/MainMenuMusic.mp3";

    /** Path to the sound effect for button clicks. */
    private final String BUTTON_SOUND = "/com/example/demo/media/soundEffects/click.mp3";

    /** Controller instance to manage the game. */
    private Controller myController;

    /** Background music handler for the main menu. */
    private BackgroundMusic backgroundMusic;

    /** Button click sound effect handler. */
    private SoundEffect buttonSound;

    /** Volume level for the button sound effects. */
    private final double BUTTON_SOUND_VOLUME = 1;

    /** Stage for the main menu scene. */
    private final Stage stage;

    /**
     * Constructs the MainMenu with the given stage.
     *
     * @param stage The stage on which the main menu will be displayed.
     */
    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    /**
     * Displays the main menu including the title, buttons, background video, and background music.
     * Handles user interactions with the buttons to start the game, toggle music, or exit.
     */
    public void display() {

        backgroundMusic = new BackgroundMusic(BACKGROUND_MUSIC);
        backgroundMusic.playMusic();
        if(isMute) backgroundMusic.pauseMusic();

        Button startButton = new Button("Start Game");
        Button musicButton = new Button("Music: On");
        Button exitButton = new Button("Exit");
        Label title = new Label();

        title.setText(Main.TITLE);
        title.setFont(Font.font("Verdana", 100)); // Set the font to Arial and size to 36
        title.setTextFill(Color.web("#FFFFFF")); // Set the text color to white using hex code
        title.setStyle("-fx-font-weight: bold;");

        // Style the start button
        if(isMute) musicButton.setText("Music: Off"); // If the App is mute, change the button
        startButton.setFont(Font.font("Arial", 18)); // Set font and size for start button
        startButton.setTextFill(Color.web("#FFFFFF")); // White text color for the button
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;"); // Green background and bold font
        startButton.setMinWidth(200); // Set a minimum width
        startButton.setMinHeight(50); // Set a minimum height
        startButton.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));

        musicButton.setFont(Font.font("Arial", 18)); // Set font and size for start button
        musicButton.setTextFill(Color.web("#FFFFFF")); // White text color for the button
        musicButton.setStyle("-fx-background-color: #2196F3; -fx-font-weight: bold;"); // Blue background and bold font
        musicButton.setMinWidth(200); // Set a minimum width
        musicButton.setMinHeight(50); // Set a minimum height
        musicButton.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));

        // Style the exit button
        exitButton.setFont(Font.font("Arial", 18)); // Set font and size for exit button
        exitButton.setTextFill(Color.web("#FFFFFF")); // White text color for the button
        exitButton.setStyle("-fx-background-color: #F44336; -fx-font-weight: bold;"); // Red background and bold font
        exitButton.setMinWidth(200); // Set a minimum width
        exitButton.setMinHeight(50); // Set a minimum height
        exitButton.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));

        buttonSound = new SoundEffect(BUTTON_SOUND);

        startButton.setOnAction(event -> {
            try {
                buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
                startPlaying();
            } catch (ClassNotFoundException | InvocationTargetException |
                     NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getClass().toString());
                alert.show();
            }
        });
        musicButton.setOnAction(event -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            if(isMute) {
                backgroundMusic.resumeMusic();
                isMute = false;
                musicButton.setText("Music: On");
            } else {
                backgroundMusic.pauseMusic();
                isMute = true;
                musicButton.setText("Music: Off");
            }
        });
        exitButton.setOnAction(event -> {
            buttonSound.playSoundEffect(BUTTON_SOUND_VOLUME);
            stage.close();
        });

        // Create a Media object for the background video
        Media media = new Media(getClass().getResource(BACKGROUND_VIDEO).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Set the MediaView as the background
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the video
        mediaPlayer.setMute(true); // Mute the video audio, since you're already playing background music
        mediaView.setFitWidth(Main.SCREEN_WIDTH);
        mediaView.setFitHeight(Main.SCREEN_HEIGHT);
        mediaView.setPreserveRatio(false);

        // Set up the layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, startButton, musicButton, exitButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the background of the layout to the video (using MediaView)
        StackPane root = new StackPane();
        root.getChildren().addAll(mediaView, layout); // Place the video behind the layout
        Scene scene = new Scene(root, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(Main.TITLE);
        stage.show();

    }

    /**
     * Starts the game by stopping the background music and launching the controller to start the game.
     *
     * @throws ClassNotFoundException If the game class is not found.
     * @throws InvocationTargetException If an exception occurs while invoking the constructor of the level.
     * @throws NoSuchMethodException If the required constructor for the level class is not found.
     * @throws InstantiationException If the level class cannot be instantiated.
     * @throws IllegalAccessException If the constructor or method cannot be accessed.
     */
    private void startPlaying() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        backgroundMusic.stopMusic();
        myController = new Controller(stage);
        myController.launchGame();
    }

}