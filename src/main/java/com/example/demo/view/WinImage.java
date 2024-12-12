package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final double WIDTH = 300;
	private static final double XPosition = (double) Main.SCREEN_WIDTH /2 - WIDTH /2;
	private static final double YPosition = (double) Main.SCREEN_HEIGHT /2 - WIDTH /2;
	
	public WinImage() {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitWidth(WIDTH);
		this.setPreserveRatio(true);
		this.setLayoutX(XPosition);
		this.setLayoutY(YPosition);
		this.toFront();
	}
	
	public void showWinImage() {
		this.setVisible(true);
	}

}
