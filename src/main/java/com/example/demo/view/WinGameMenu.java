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

public class WinGameMenu extends StackPane {

    private LevelParent levelParent;
    private final double MENU_WIDTH = 600;
    private final double MENU_HEIGHT = 180;
    private final double XPosition = (double) Main.SCREEN_WIDTH /2 - MENU_WIDTH /2;
    private final double YPosition = (double) Main.SCREEN_HEIGHT * 0.6;

    // Constructor
    public WinGameMenu(LevelParent levelParent) {
        this.levelParent = levelParent;
        this.setVisible(false);

        // Create Button
        Button exitButton = createExitButton();
        Button backtoMainButton = createBackToMainButton();

        HBox buttonLayout = new HBox(30);
        buttonLayout.getChildren().addAll(exitButton, backtoMainButton);
        buttonLayout.setAlignment(javafx.geometry.Pos.CENTER);

        Label label = new Label("Congratulations! You won the game!");
        label.setFont(Font.font("Arial", 30));
        label.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(label, buttonLayout);
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the background and size for the Pause Menu
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        this.setPrefSize(MENU_WIDTH, MENU_HEIGHT);
        this.setLayoutX(XPosition);
        this.setLayoutY(YPosition);

        // Add the HBox to the StackPane
        this.getChildren().add(menuLayout);
    }

    // Method to create "Back to Main Menu" button
    private Button createBackToMainButton() {
        Button button = new Button("Back to Main Menu");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #4CAF50; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> goToMainMenu());
        return button;
    }

    private Button createExitButton() {
        Button button = new Button("Exit");
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #F44336; -fx-font-weight: bold;");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setOnAction(e -> exit());
        return button;
    }

    // Go back to the main menu
    private void goToMainMenu() {
        levelParent.goToMainMenu();
    }

    private void exit() {
        levelParent.exitGame();
    }

}