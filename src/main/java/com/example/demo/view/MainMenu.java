package com.example.demo.view;

import com.example.demo.controller.Main;
import com.example.demo.controller.Controller;
import javafx.scene.control.Alert;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MainMenu {

    private final Stage stage;
    private final String BACKGROUND_IMAGE = "/com/example/demo/images/mainmenuBackground.jpg";
    private final String BACKGROUND_MUSIC = "/com/example/demo/media/backgroundMusic/MainMenuMusic.mp3";
    private Controller myController;
    private MediaPlayer mediaPlayer;

    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    public void display() {
        Button startButton = new Button("Start Game");
        Button exitButton = new Button("Exit");
        Label title = new Label();

        title.setText(Main.TITLE);
        title.setFont(Font.font("Verdana", 72)); // Set the font to Arial and size to 36
        title.setTextFill(Color.web("#FFFFFF")); // Set the text color to white using hex code
        title.setStyle("-fx-font-weight: bold;");

        // Style the start button
        startButton.setFont(Font.font("Arial", 18)); // Set font and size for start button
        startButton.setTextFill(Color.web("#FFFFFF")); // White text color for the button
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;"); // Green background and bold font
        startButton.setMinWidth(200); // Set a minimum width
        startButton.setMinHeight(50); // Set a minimum height
        startButton.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK)); // Optional: Add a shadow effect

        // Style the exit button
        exitButton.setFont(Font.font("Arial", 18)); // Set font and size for exit button
        exitButton.setTextFill(Color.web("#FFFFFF")); // White text color for the button
        exitButton.setStyle("-fx-background-color: #F44336; -fx-font-weight: bold;"); // Red background and bold font
        exitButton.setMinWidth(200); // Set a minimum width
        exitButton.setMinHeight(50); // Set a minimum height
        exitButton.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK)); // Optional: Add a shadow effect

        startButton.setOnAction(event -> {
            try {
                startPlaying();
            } catch (ClassNotFoundException | InvocationTargetException |
                     NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getClass().toString());
                alert.show();
            }
        });
        exitButton.setOnAction(event -> {
            stage.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, startButton, exitButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Image backgroundImage = new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm());
        BackgroundImage backGroundImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        layout.setBackground(new Background(backGroundImage));

        Scene scene = new Scene(layout, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(Main.TITLE);
        stage.show();

        playBackgroundMusic();
    }

    private void startPlaying() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myController = new Controller(stage);
        myController.launchGame();
    }

    private void playBackgroundMusic() {
        // Make sure the music file is in the correct path, here it's located in the "resources" folder
        String musicPath = getClass().getResource(BACKGROUND_MUSIC).toExternalForm();
        Media music = new Media(musicPath);
        mediaPlayer = new MediaPlayer(music);

        // Loop the music
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(1);  // Adjust volume (0.0 is silent, 1.0 is full volume)
        mediaPlayer.play();
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

}