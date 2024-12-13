package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * This class represents a countdown timer that tracks the time remaining for a revival process.
 * The timer is displayed on the screen, updating every second and showing the remaining time until revival.
 * The timer can be started, stopped, reset, and checked for completion.
 */
public class ReviveTimer extends StackPane {

    private int timeRemaining; // The time left in the countdown
    private Timeline countdownTimeline; // The Timeline to manage the countdown
    private Label timerLabel; // The label that will display the countdown
    private static final int XPosition = Main.SCREEN_WIDTH /2 - 100;
    private static final int YPosition = 10;
    private boolean timeFinished;

    /**
     * Constructor for the ReviveTimer class.
     * Initializes the timer with a starting time and sets up the countdown display.
     *
     * @param startTime The starting time (in seconds) for the countdown.
     */
    public ReviveTimer(int startTime) {
        this.timeRemaining = startTime; // Set the starting time
        this.timerLabel = new Label("Time to Revive: " + timeRemaining); // Initialize the label
        timerLabel.setStyle("-fx-font-size: 30; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(XPosition);
        this.setLayoutY(YPosition);
        this.setVisible(false);
        this.timeFinished = false;

        // Add the label to the StackPane
        this.getChildren().add(timerLabel);

        // Create the Timeline for the countdown, updating every second
        countdownTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateTimer())
        );

        // Set the cycle count to INDEFINITE so it keeps running until stopped
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the countdown timer.
     * The countdown will update every second.
     */
    public void startTimer() {
        countdownTimeline.play();
    }

    /**
     * Stops the countdown timer.
     * The countdown will pause, and the remaining time will stay unchanged.
     */
    public void stopTimer() {
        countdownTimeline.stop();
    }

    /**
     * Resets the countdown timer to the specified starting time.
     * The time will be set back to the initial value and the label updated.
     *
     * @param startTime The new starting time (in seconds) for the countdown.
     */
    public void reset(int startTime) {
        timeFinished = false;
        this.timeRemaining = startTime;
        timerLabel.setText("Time to Revive: " + timeRemaining); // Update the label
        stopTimer(); // Stop the countdown if it's running
    }

    /**
     * Updates the timer by decreasing the time remaining by 1 second and updating the label.
     * If the time reaches 0, the countdown will stop automatically.
     */
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

    /**
     * Checks if the timer has finished counting down.
     *
     * @return True if the time has finished counting down, otherwise false.
     */
    public boolean timeFinished() {
        return timeFinished;
    }

    /**
     * Makes the timer visible on the screen.
     */
    public void showTimer() {
        this.setVisible(true);
    }

    /**
     * Hides the timer from the screen.
     */
    public void hideTimer() {
        this.setVisible(false);
    }
}