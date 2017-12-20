package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Asteroid;
import model.Ship;
import view.Main;

public class AsteroidAnimation extends AnimationTimer{
	Asteroid a;
	double xRand;
	double yRand;
	private Ship s;
	private Pane p;
	private Label shipHealth;
	
	public AsteroidAnimation(Asteroid a) {
		super();
		this.a = a;
		xRand = Math.random() * 2;
		yRand = Math.random() * 2;
	}
	public AsteroidAnimation(Asteroid a, Ship s, Pane p, Label shipHealth) {
		super();
		this.a = a;
		this.s = s;
		this.p = p;
		this.shipHealth = shipHealth;
		xRand = Math.random() * 2;
		yRand = Math.random() * 2;
	}
	/**
	 * Asteroid should bounce around randomly
	 * not sure how to get the bounds just from the parent without having to pass back the pane
	 * @param now
	 */
	@Override
	public void handle(long now) {
		double x = a.getX() + xRand;
		double y = a.getY() + yRand;
		if (x > p.getWidth() || x <= 0) xRand *= -1;
		if (y > p.getHeight() || y <= 0) yRand *= -1;	
		a.setX(x);
		a.setY(y);
		collision();
	}
	
	public void collision() {
		if (a.getBoundsInLocal().intersects(s.getBoundsInLocal())) {
			s.takeDamage();
			System.out.println("Ship integrity at %" + s.getHealth());
			shipHealth.setText("Health: " + s.getHealth() + "%");
			if (s.getHealth() <= 0) {
				//pane loseGame function
			}
			p.getChildren().remove(a);
			this.stop();			
		}
	}
}
