package model;

import controller.UFOAnimation;

public class SmallUFO extends Character{
	public UFOAnimation ua;
	public SmallUFO() {
		super(resource.Constants.SPR_SMALL_ENEMY);
	}
	/**
	 * constructor that creates ufo at given parameters
	 * @param xLoc
	 * @param yLoc
	 */
	public SmallUFO(double xLoc, double yLoc) {
		super(resource.Constants.SPR_SMALL_ENEMY);
		setX(xLoc);
		setY(yLoc);
	}
	public void setAnimation(UFOAnimation ua) {
		this.ua = ua;
	}
	public void stopAnimation() {
		ua.stop();
	}
}
