package drawable;

import java.awt.geom.Point2D;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Movable extends Drawable {

	protected float speed;
	
	
	public Movable(){
		speed = 0;
		direction =0;
	}
	
	public void update(GameContainer gc, int delta){
		x += Math.sin(direction)*speed*delta/1000;
		y += Math.cos(direction)*-speed*delta/1000;
	}
	

	public void draw(GameContainer gc, Graphics g){
		
		Point2D.Float middle = getCenter();
		
		g.drawString("No Image", x, y);
		g.drawRect(x, y, width, height);
		g.rotate(middle.x, middle.y, getDirectionInDegree());
		g.drawLine(middle.x, middle.y, middle.x, -(height+5));
		g.rotate(middle.x, middle.y, -getDirectionInDegree());
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void pointAt(Point2D.Float targetPoint){
		Point2D.Float middle = this.getCenter();
		
		direction = (float) -Math.atan2( middle.x - targetPoint.x, middle.y - targetPoint.y);
	}


	
	

}
