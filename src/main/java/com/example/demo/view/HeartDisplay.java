package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This class represents a heart display UI component that shows a series of hearts
 * to represent the player's remaining health or lives. The hearts are displayed
 * horizontally within an HBox container.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private HBox container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;

	/**
	 * Constructor that initializes the heart display with the specified number of hearts.
	 *
	 * @param xPosition The x-coordinate of the heart display on the screen.
	 * @param yPosition The y-coordinate of the heart display on the screen.
	 * @param heartsToDisplay The number of hearts to be displayed.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) that will hold the hearts.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the hearts and adds them to the container.
	 * Creates the specified number of heart images and sets their size and ratio.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes the first heart from the display (used when the player loses a life).
	 * If there are no hearts left to remove, nothing happens.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Gets the container (HBox) that holds the heart images.
	 *
	 * @return The HBox container holding the hearts.
	 */
	public HBox getContainer() {
		return container;
	}

	/**
	 * Hides the heart display, making the hearts invisible.
	 */
	public void hideHearts() {
		container.setVisible(false);
	}
}