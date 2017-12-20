package controller;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Character;
import model.Ship;
import model.UFOLaser;
import view.Main;

/**
 * Base animation for ufo's
 * will fire lasers every 1.5 seconds
 * @author Alex Man
 *
 */
public class UFOAnimation extends AnimationTimer{
	private Character c;
	private Pane p;
	private Ship s;
	private double dX = 1.5;
	private Label shipHealth;
	
	public UFOAnimation() {
		super();
	}
	
	public UFOAnimation(Pane p, Character c, Ship s, Label shipHealth) {
		super();
		this.p = p;
		this.c = c;
		this.s = s;
		this.shipHealth = shipHealth;
	}

	@Override
	public void handle(long now) {
		fireLaser(now);
		move();
		onCollision();
	}
	/*
	 * fires laser at random intervals
	 */
	private void fireLaser(long now) {
		Random r = new Random();
		int num = r.nextInt(100);
		if (num == 0) {
			UFOLaser l = new UFOLaser(c.getX(), c.getY());
			UFOLaserAnimation la = new UFOLaserAnimation(l, p, s, shipHealth);
			p.getChildren().add(l);
			la.start();
		}
	}
	/*
	 * Moves the UFO in a line
	 */
	private void move() {
		double delta = c.getX() - dX;
		double borderWidth = p.getWidth();
		if (delta <= 0 || delta >= borderWidth) dX *= -1;
		else c.setX(delta);	
	}
	/**
	 * standard collision function for all animations
	 */
	private void onCollision() {
		if (c.getBoundsInLocal().intersects(s.getBoundsInLocal())) {
			s.takeDamage();
			System.out.println("Ship integrity at %" + s.getHealth());
			shipHealth.setText("Health: " + s.getHealth() + "%");
			if (s.getHealth() <= 0) {
				//pane loseGame function
			}
			p.getChildren().remove(c);
			this.stop();
		}
	}
}

