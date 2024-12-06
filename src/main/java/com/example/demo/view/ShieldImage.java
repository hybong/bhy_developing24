package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {
	
	private static final String SHIELD_NAME = "/com/example/demo/images/shield.png";
	private static final int SHIELD_SIZE = 100;
	
	public ShieldImage() {

		//this.setImage(new Image(IMAGE_NAME));
		this.setImage(new Image(getClass().getResource(SHIELD_NAME).toExternalForm()));
//		this.setLayoutX(xPosition);
//		this.setLayoutY(yPosition);
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	public void showShield() {
		this.setVisible(true);
	}
	
	public void hideShield() {
		this.setVisible(false);
	}

	public void updateShieldPosition(double bossX, double bossY) {
		this.setLayoutX(bossX); // Adjust as needed
		this.setLayoutY(bossY); // Adjust as needed
	}

}
