package controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Asteroid;
import model.LargeUFO;
import model.Ship;
import model.ShipLaser;
import model.SmallUFO;

public class ShipKeyHandler implements EventHandler<KeyEvent>{
	private int delta = 40;
	private Pane p;
	private Ship s;
	private ArrayList<LargeUFO> lu;
	private ArrayList<SmallUFO> su;
	private ArrayList<Asteroid> al;
	
	public ShipKeyHandler() {
		super();
	}
	public ShipKeyHandler(Pane p, Ship s) {
		super();
		this.p = p;
		this.s = s;
	}
	public ShipKeyHandler(Pane p, Ship s, ArrayList<LargeUFO> lu, ArrayList<SmallUFO> su,
			ArrayList<Asteroid> al) {
		super();
		this.p = p;
		this.s = s;
		this.lu = lu;
		this.su = su;
		this.al = al;
	}
	/**
	 * handle arrow and spacebar presses
	 */
	@Override
	public void handle(KeyEvent e) {
		Ship s = (Ship) e.getSource();
		if (e.getEventType() == KeyEvent.KEY_PRESSED) {
			if (e.getCode() == KeyCode.UP) {
				s.setY(bounds(s.getY() - delta));
			}	
			if (e.getCode() == KeyCode.DOWN) {
				s.setY(bounds(s.getY() + delta));
			}
			if (e.getCode() == KeyCode.LEFT) {
				s.setX(bounds(s.getX() - delta));
			}
			if (e.getCode() == KeyCode.RIGHT) {
				s.setX(bounds(s.getX() + delta));
			}
			if (e.getCode() == KeyCode.SPACE) {
				ShipLaser l = new ShipLaser(bounds(s.getX()+20), s.getY());
				ShipLaserAnimation la = new ShipLaserAnimation(l, p, lu, su, al);
				p.getChildren().add(l);
				la.start();
				s.playLaser();
			}
		}
	}
	/**
	 * ensures ship is within bounds
	 * @param deltaLoc
	 * @return
	 */
	private double bounds(double deltaLoc) {
		double width = p.getWidth();
		if (deltaLoc >= width) return width;
		else if (deltaLoc <= 0) return 0;
		return deltaLoc;
	}
}
