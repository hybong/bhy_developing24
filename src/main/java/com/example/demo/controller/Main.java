package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.view.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static final int SCREEN_WIDTH = 1300;
	public static final int SCREEN_HEIGHT = 750;
	public static final String TITLE = "Sky Battle";
	private MainMenu myMainMenu;

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

	public static void main(String[] args) {
		launch();
	}
}