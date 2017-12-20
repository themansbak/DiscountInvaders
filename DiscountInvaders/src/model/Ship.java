package model;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Ship extends Character{
	private int health = 100;
	private MediaPlayer soundPlayer;

	public Ship() {
		super(resource.Constants.SPR_SHIP);	
	}
	public Ship(double xLoc, double yLoc) {
		super(resource.Constants.SPR_SHIP);
		setX(xLoc);
		setY(yLoc);
		setWidth(20.0);
		setHeight(30.0);

		File f = new File("./src/sounds/pew.wav");
		soundPlayer = new MediaPlayer(new Media(f.toURI().toString()));
	}
	/**
	 * this causes the game to lag when you shoot a laser for some reason
	 */
	public void playLaser() {
		System.out.println("playing sound");
		//write about lag in design document
		soundPlayer.play();
		soundPlayer.seek(Duration.ZERO);
	}
	/**
	 * function that reduces the amount of health of the ship when it is struck by
	 * an asteroid or a laser
	 */
	public void takeDamage() {
		health -= 5;
	}
	public int getHealth() {
		return health;
	}
}
