package model;

import javafx.scene.paint.Color;
/**
 * Different lasers in order to differentiate between the ship and ufo lasers
 * @author Alex Man
 *
 */
public class UFOLaser extends Laser{
	/**
	 * constructor that creates a new laser at given parameters
	 * @param xLoc
	 * @param yLoc
	 */
	public UFOLaser(double xLoc, double yLoc) {
		super(width, height);
		setX(xLoc);
		setY(yLoc);
		setFill(Color.RED);
	}
}
