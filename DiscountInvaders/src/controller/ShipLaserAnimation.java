package controller;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import model.Asteroid;
import model.LargeUFO;
import model.Laser;
import model.SmallUFO;

/*
 * TODO:
 * lasers aren't coming from center of image
 * lasers not firing at set intervals
 */
public class ShipLaserAnimation extends AnimationTimer{
	private Laser l;
	private Pane p;
	private ArrayList<LargeUFO> lu;
	private ArrayList<SmallUFO> su;
	private ArrayList<Asteroid> al;
	private int dY = 3;
	
	public ShipLaserAnimation(Laser l, Pane p) {
		super();
		this.l = l;
		this.p = p;
	}
	public ShipLaserAnimation(Laser l, Pane p, ArrayList<LargeUFO> lu, ArrayList<SmallUFO> su,
			ArrayList<Asteroid> al) {
		super();
		this.l = l;
		this.p = p;
		this.lu = lu;
		this.su = su;
		this.al = al;
	}
	/**
	 * animation to move the laser up 
	 * once the laser moves up it will be destroyed
	 * will have to add collision to it
	 * lasers also aren't  
	 */
	@Override
	public void handle(long now) {
		double delta = l.getY() - dY;
		//if laser hits another object, remove it from the pane and from memory
		onCollision();
		if (delta <= 0) {
			p.getChildren().remove(l);
			this.stop();
		}
		else l.setY(delta);	
	}
	/**
	 * collision method
	 * if it intersects an asteroid or ufo, destroy it
	 */
	public void onCollision() {
		for (int i = 0; i < lu.size(); i++) {
			if (l.getBoundsInLocal().intersects(lu.get(i).getBoundsInLocal())) {
				LargeUFO u = lu.get(i);
				p.getChildren().remove(l);
				p.getChildren().remove(u);
				u.stopAnimation();
				lu.remove(u);
				this.stop();
			}
		}
		for (int i = 0; i < su.size(); i++) {
			if (l.getBoundsInLocal().intersects(su.get(i).getBoundsInLocal())) {
				SmallUFO s = su.get(i);
				p.getChildren().remove(s);
				p.getChildren().remove(l);
				s.stopAnimation();
				su.remove(s);
				this.stop();
			}
		}
		for (int i = 0; i < al.size(); i++) {
			if (l.getBoundsInLocal().intersects(al.get(i).getBoundsInLocal())) {
				Asteroid a = al.get(i);
				p.getChildren().remove(a);
				p.getChildren().remove(l);
				a.stopAnimation();
				al.remove(a);
				this.stop();
			}
		}
	}
}
