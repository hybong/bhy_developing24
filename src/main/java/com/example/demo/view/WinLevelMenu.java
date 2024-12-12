package com.example.demo.view;

import com.example.demo.Levels.LevelParent;
import com.example.demo.controller.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

import static com.example.demo.Levels.LevelParent.nextLevelName;

public class WinLevelMenu extends StackPane {

    private LevelParent levelParent;
    private final double MENU_WIDTH = 550;
    private final double MENU_HEIGHT = 300;
    private final double XPosition = (double) Main.SCREEN_WIDTH /2 - MENU_WIDTH /2;
    private final double YPosition = (double) Main.SCREEN_HEIGHT * 0.25;

    // Constructor
    public WinLevelMenu(LevelParent levelParent) {
        this.levelParent = levelParent;
        this.setVisible(false);

        // Create Buttons
        Button nextButton = createNextButton();
        Button backtoMainButton = createBackToMainButton();

        // Create VBox for the layout
        HBox buttonLayout = new HBox(15);
        buttonLayout.getChildren().addAll(backtoMainButton, nextButton);
        buttonLayout.setAlignment(javafx.geometry.Pos.CENTER);

        Label label1 = new Label("All enemies defeated!");
        label1.setFont(Font.font("Arial", 40));
        label1.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        Label label2 = new Label("Proceed to next level?");
        label2.setFont(Font.font("Arial", 30));
        label2.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        VBox menuLayout = new VBox(30);
        menuLayout.getChildren().addAll(label1, label2, buttonLayout);
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the background and size for the Pause Menu
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        this.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        this.setLayoutX(XPosition);
        this.setLayoutY(YPosition);

        // Add the HBox to the StackPane
        this.getChildren().add(menuLayout);
    }

    // Method to create "Replay" button
    private Button createNextButton() {
        Button button = new Button("Next Level");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> nextLevel());
        return button;
    }

    // Method to create "Back to Main Menu" button
    private Button createBackToMainButton() {
        Button button = new Button("Back to Main Menu");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #F44336; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> goToMainMenu());
        return button;
    }

    // Handle the Resume action
    private void nextLevel() {
        levelParent.goToNextLevel(nextLevelName);
    }

    // Go back to the main menu
    private void goToMainMenu() {
        levelParent.goToMainMenu();
    }

}