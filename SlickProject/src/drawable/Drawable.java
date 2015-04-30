package drawable;

import java.awt.geom.Point2D.Float;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Drawable {
	
	protected float x, y, width, height;
	
	/**
	 * an angle, in radians
	 */
	protected float direction;
	
	public Drawable(){
		x=y=width=height=direction=0;
	}
	public Drawable(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Float getCenter(){
		return new Float(x+width/2,y+height/2);
	}
	
	public float getDirectionInDegree(){
		return (float)Math.toDegrees(direction);
	}
	
	public abstract void draw(GameContainer gc, Graphics g);
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	/**
	 * 
	 * @return an angle, in radians
	 */
	public float getDirection() {
		return direction;
	}
	public void setDirection(float direction) {
		this.direction = direction;
	}
	
	
}
