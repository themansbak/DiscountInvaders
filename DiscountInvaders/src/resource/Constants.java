package resource;

import javafx.scene.control.Label;

public class Constants {
	public static final String IMG = "images/";
	public static final String SND = "sounds/";
	
	public static final String SPR_NULL = IMG + "null.png";
	public static final String SPR_SHIP = IMG + "ship.jpg";
	public static final String SPR_ASTEROID = IMG + "asteroid.png";
	public static final String SPR_SMALL_ENEMY = IMG + "small_ufo.png";
	public static final String SPR_BIG_ENEMY = IMG + "big_ufo.png";
	
	public static final String SND_LASER = SND + "pew.wav";

	public static final Label intro = new Label("Discount Invaders!");
	public static final Label i0 = new Label("Instructions:");
	public static final Label i1 = new Label("The goal of the game is to destroy all UFO's!");
	public static final Label i2 = new Label("The ship loses health if it hits a UFO, asteroid, or laser.");
	public static final Label i3 = new Label("When ship health reaches 0, you lose the game."
			+ "\nUse your lasers to destroy asteroids and UFOS!");
	public static final Label i4 = new Label("Use the arrow keys to fly around");
	public static final Label i5 = new Label("and the spacebar key to fire lasers");
	public static final Label i6 = new Label("Movement:");
}
	