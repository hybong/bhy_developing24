package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class is the entry point of the Sky Battle application.
 * It extends the JavaFX Application class and sets up the main menu screen
 * when the application is launched.
 */
public class Main extends Application {

	/** The width of the application window. */
	public static final int SCREEN_WIDTH = 1200;

	/** The height of the application window. */
	public static final int SCREEN_HEIGHT = 750;

	/** The title of the application window. */
	public static final String TITLE = "Sky Battle";

	/** The main menu instance for the application. */
	private MainMenu myMainMenu;

	/**
	 * Initializes the main application window and displays the main menu.
	 *
	 * @param stage the primary stage for this application
	 * @throws ClassNotFoundException if the specified class cannot be found
	 * @throws NoSuchMethodException if a specified method cannot be found
	 * @throws SecurityException if a security violation occurs
	 * @throws InstantiationException if an instance of a class cannot be created
	 * @throws IllegalAccessException if access to a class or its methods is restricted
	 * @throws IllegalArgumentException if an illegal argument is passed to a method
	 * @throws InvocationTargetException if the method being invoked throws an exception
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		myMainMenu = new MainMenu(stage);
		myMainMenu.display();
	}

	/**
	 * The main entry point for launching the application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		launch();
	}
}