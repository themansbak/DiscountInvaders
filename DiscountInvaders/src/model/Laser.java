package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Laser extends Rectangle{
	protected static double height = 12.5;
	protected static double width = 10.0;
	
	public Laser() {
		super(width, height);
		setFill(Color.RED);
	}
	/**
	 * constructor that creates a new laser at given parameters
	 * @param xLoc
	 * @param yLoc
	 */
	public Laser(double xLoc, double yLoc) {
		super(width, height);
		setX(xLoc);
		setY(yLoc);
		setFill(Color.RED);
	}
	public String toString() {
		return "laser at x= " + this.getX() + ", y=" + this.getY();
	}
}
