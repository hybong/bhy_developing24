package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.Levels.LevelParent;

/**
 * The Controller class handles the logic for launching the game and managing different levels.
 * It implements the Observer interface to respond to updates from observable game elements.
 */
public class Controller implements Observer {

	/** The class name of the first level to load. */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.Levels.LevelOne";

	/** The primary stage for the application. */
	private final Stage stage;

	/**
	 * Constructs a new Controller instance with the specified stage.
	 *
	 * @param stage the primary stage for this application
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by showing the primary stage and navigating to the first level.
	 *
	 * @throws ClassNotFoundException if the specified class cannot be found
	 * @throws NoSuchMethodException if the constructor of the class cannot be found
	 * @throws SecurityException if a security violation occurs
	 * @throws InstantiationException if an instance of the class cannot be created
	 * @throws IllegalAccessException if access to the constructor is restricted
	 * @throws IllegalArgumentException if invalid arguments are passed to the constructor
	 * @throws InvocationTargetException if the constructor or method invoked throws an exception
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Navigates to the specified level class by using reflection to dynamically load and instantiate it.
	 *
	 * @param className the name of the level class to load
	 * @throws ClassNotFoundException if the specified class cannot be found
	 * @throws NoSuchMethodException if the constructor of the class cannot be found
	 * @throws SecurityException if a security violation occurs
	 * @throws InstantiationException if an instance of the class cannot be created
	 * @throws IllegalAccessException if access to the constructor is restricted
	 * @throws IllegalArgumentException if invalid arguments are passed to the constructor
	 * @throws InvocationTargetException if the constructor or method invoked throws an exception
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
		myLevel.addObserver(this);
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();
	}

	/**
	 * Responds to updates from the observed game elements.
	 * If a level change is requested, it transitions to the new level based on the provided class name.
	 *
	 * @param arg0 the observable object (game element)
	 * @param arg1 the new level class name to load
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}
}