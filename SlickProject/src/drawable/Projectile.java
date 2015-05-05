package drawable;

import java.awt.geom.Point2D;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class Projectile extends Movable{
	
	
	private String name;
	private Image image;
	private float maximumRange;
	private float distanceTraveled;
	private float damage;
	private HitBox hitBox;
	private boolean hitSomething;
	private Movable target;
	
	public Projectile(float x, float y,float direction) throws SlickException{
		image = new Image("Resources/test.png");
		
		width = image.getWidth();
		height = image.getHeight();
		
		this.x = x-width/2;
		this.y = y-height/2;
		this.direction = direction;
		this.targetDirection = direction;
		this.speed = 1000;
		this.turnSpeed = 00;
		this.maximumRange = 1000;

		hitBox = new HitBox();
		hitBox.addPoint(new Point(x,y));
		hitBox.addPoint(new Point(x,y+height));
		hitBox.addPoint(new Point(x+width,y+height));
		hitBox.addPoint(new Point(x+width,y));
		hitBox.direction = direction;
		hitBox.buildHitBox();
		
		hitSomething = false;
		
	}
	
	public void setTarget(Movable target){
		this.target = target;
	}
	
	public void update(GameContainer gc, int delta){
		
		this.setTargetDirection(target.getCenter());
		
		super.update(gc, delta);
		hitBox.update(this);
		distanceTraveled += Math.sqrt(Math.pow(displacementX, 2)+Math.pow(displacementY, 2));
	}
	

	public void draw(GameContainer gc, Graphics g){
		
		Point2D.Float middle = getCenter();
		
		g.rotate(middle.x,middle.y, getDirectionInDegree());
		g.setColor(Color.white);
		g.drawImage(image, x, y);
		g.drawOval(middle.x-3, middle.y-3, 6, 6);
		g.rotate(middle.x, middle.y, -getDirectionInDegree());
		hitBox.draw(gc, g);
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
	
	public HitBox getHitBox(){
		return hitBox;
	}

	public boolean isHitSomething() {
		return hitSomething;
	}

	public void setHitSomething(boolean hitSomething) {
		this.hitSomething = hitSomething;
	}
	
	

}
