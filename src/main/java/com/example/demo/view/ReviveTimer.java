package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ReviveTimer extends StackPane {

    private int timeRemaining; // The time left in the countdown
    private Timeline countdownTimeline; // The Timeline to manage the countdown
    private Label timerLabel; // The label that will display the countdown
    private static final int XPosition = Main.SCREEN_WIDTH /2 - 100;
    private static final int YPosition = 10;
    private boolean timeFinished;

    // Constructor for the Timer class
    public ReviveTimer(int startTime) {
        this.timeRemaining = startTime; // Set the starting time
        this.timerLabel = new Label("Time to Revive: " + timeRemaining); // Initialize the label
        timerLabel.setStyle("-fx-font-size: 30; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(XPosition);
        this.setLayoutY(YPosition);
        this.setVisible(false);
        this.timeFinished = false;

        // Add buttons and label to the StackPane
        this.getChildren().add(timerLabel);

        // Create the Timeline for the countdown, to update every second
        countdownTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateTimer())
        );

        // Set the cycle count to INDEFINITE so it keeps running until stopped
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);

    }

    // Method to start the countdown
    public void startTimer() {
        countdownTimeline.play();
    }

    // Method to stop the countdown
    public void stopTimer() {
        countdownTimeline.stop();
    }

    // Method to reset the countdown
    public void reset(int startTime) {
        timeFinished = false;
        this.timeRemaining = startTime;
        timerLabel.setText("Time to Revive: " + timeRemaining); // Reset the label display
        stopTimer(); // Stop the countdown if it's running
    }

    // Method to update the timer every second
    public void updateTimer() {
        // Decrease the time
        timeRemaining--;

        // Update the label with the new time
        timerLabel.setText("Time to Revive: " + timeRemaining);

        // When time reaches 0, stop the countdown
        if (timeRemaining <= 0) {
            timeFinished = true;
            stopTimer(); // Stop the countdown when it reaches 0
        }
    }

    public boolean timeFinished() {
        return timeFinished;
    }

    public void showTimer() {
        this.setVisible(true);
    }

    public void hideTimer() {
        this.setVisible(false);
    }
}