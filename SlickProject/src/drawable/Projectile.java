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
	private float damage;
	
	public Projectile(float x, float y) throws SlickException{
		this.x = x;
		this.y = y;
		this.speed = 90;
		image = new Image("Resources/test.png");
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public void update(GameContainer gc, int delta){
		x += Math.sin(direction)*speed*delta/1000;
		y += Math.cos(direction)*-speed*delta/1000;
	}
	

	public void draw(GameContainer gc, Graphics g){
		
		Point2D.Float middle = getCenter();
		
		g.rotate(middle.x, middle.y, getDirectionInDegree());
		image.drawCentered(x, y);
		g.rotate(middle.x, middle.y, -getDirectionInDegree());
	}

}
