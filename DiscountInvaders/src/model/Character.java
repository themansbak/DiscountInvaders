package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character extends ImageView{
	protected Image sprite = new Image(resource.Constants.SPR_NULL);
	protected double width = 50.0;
	protected double height = 50.0;
	
	/**
	 * Default constructor that creates a character with null image
	 */
	public Character() {
		super.setImage(sprite);
		setFitHeight(height);
		setFitWidth(width);
	}
	/**
	 * getters and setters
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() { return width; }
	public double getHeight() { return height; }
	
	/**
	 * Constructor that creates a character with the specified sprite name 
	 * @param spriteName
	 */
	public Character(String spriteName) {
		sprite  = new Image(spriteName);
		super.setImage(sprite);
		setFitHeight(height);
		setFitWidth(width);
	}
	
	/**
	 * Constructor that creates a character with the specified sprite name and dimensions
	 * will resize the image to the passe din height and width
	 * @param spriteName
	 * @param width
	 * @param height
	 */
	public Character(String spriteName, double width, double height) {
		sprite = new Image(spriteName);
		super.setImage(sprite);
		this.width = width;
		this.height = height;
		setFitHeight(height);
		setFitWidth(width);		
	}
	
}
