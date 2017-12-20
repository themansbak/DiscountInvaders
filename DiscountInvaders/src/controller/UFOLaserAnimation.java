package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Laser;
import model.Ship;

public class UFOLaserAnimation extends AnimationTimer{
	private Laser l;
	private Pane p;
	private Ship s;
	private int dY = 3;
	private Label shipHealth;
	
	public UFOLaserAnimation(Laser l, Pane p, Ship s, Label shipHealth) {
		super();
		this.l = l;
		this.p = p;
		this.s = s;
		this.shipHealth = shipHealth;
	}
	/**
	 * animation to move the laser up
	 * once the laser moaves up it will be destroyed
	 * will have to add collision to it
	 * lasers also aren't  
	 */
	@Override
	public void handle(long now) {
		onCollision();
		double delta = l.getY() + dY;
		if (delta > p.getHeight()) {
			p.getChildren().remove(l);
			this.stop();
		}
		else l.setY(delta);
	}
	/**
	 * collision method
	 * check if laser collides with ship
	 * 	if it does then just minus that ship health by 5
	 * 	if the health is < 5, 
	 */
	private void onCollision() {
		if (l.getBoundsInLocal().intersects(s.getBoundsInLocal())) {
			s.takeDamage();
			System.out.println("Ship integrity at %" + s.getHealth());
			shipHealth.setText("Health: " + s.getHealth() + "%");
			p.getChildren().remove(l);
			this.stop();
		}
	}
}
