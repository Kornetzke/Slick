package drawable;

import java.awt.geom.Point2D;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile extends Movable{
	
	
	private String name;
	private Image image;
	private float maximumRange;
	private float distanceTraveled;
	private float damage;
	
	public Projectile(float x, float y,float direction) throws SlickException{
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.speed = 500;
		this.turnSpeed = 100;
		this.maximumRange = 200000;
		image = new Image("Resources/test.png");
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public void update(GameContainer gc, int delta){
		
		super.update(gc, delta);
		
		distanceTraveled += Math.sqrt(Math.pow(displacementX, 2)+Math.pow(displacementY, 2));
	}
	

	public void draw(GameContainer gc, Graphics g){
		
		Point2D.Float middle = getCenter();
		
		g.rotate(x,y, getDirectionInDegree());
		image.drawCentered(x, y);
		g.rotate(x, y, -getDirectionInDegree());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public float getMaximumRange() {
		return maximumRange;
	}

	public void setMaximumRange(float maximumRange) {
		this.maximumRange = maximumRange;
	}

	public float getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(float distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

}
