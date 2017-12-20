package model;

import controller.AsteroidAnimation;

public class Asteroid extends Character{
	private double xLoc;
	private double yLoc;
	private double xSpeed = 5;
	private double ySpeed = 5;
	public AsteroidAnimation aa;
	
	public Asteroid() {
		super(resource.Constants.SPR_ASTEROID);
		aa = new AsteroidAnimation(this);
	}
	
	/**
	 * creates an asteroid at the specified x and y location
	 * @param xLoc
	 * @param yLoc
	 */
	public Asteroid(double xLoc, double yLoc) {
		super(resource.Constants.SPR_ASTEROID);
		setX(xLoc);
		setY(yLoc);
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}	
	public void setAnimation(AsteroidAnimation aa) {
		this.aa = aa;
	}
	public void stopAnimation() {
		aa.stop();
	}
	public double getxLoc() {
		return xLoc;
	}

	public double getyLoc() {
		return yLoc;
	}

	public double getXSpeed() {
		return xSpeed;
	}

	public double getYSpeed() {
		return ySpeed;
	}
	
	public void setXSpeed(double speed) {
		xSpeed = speed;
	}
	
	public void setYSpeed(double speed) {
		ySpeed = speed;
	}
	
	public void setxLoc(double xLoc) {
		this.xLoc = xLoc;
		setX(xLoc);
	}

	public void setyLoc(double yLoc) {
		this.yLoc = yLoc;
		setY(yLoc);
	}
}
