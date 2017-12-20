package model;

import controller.UFOAnimation;

public class LargeUFO extends Character{
	public UFOAnimation ua;
	public LargeUFO() {
		super(resource.Constants.SPR_BIG_ENEMY);
	}
	/**
	 * constructor that creates ufo at given parameters
	 * @param xLoc
	 * @param yLoc
	 */
	public LargeUFO(double xLoc, double yLoc) {
		super(resource.Constants.SPR_BIG_ENEMY);
		setX(xLoc);
		setY(yLoc);
	}
	public String toString() {
		return "LargeUFO at x=" + this.getX() + ", y=" + this.getY();
	}
	public void setAnimation(UFOAnimation ua) {
		this.ua = ua;
	}
	public void stopAnimation() {
		ua.stop();
	}
}
