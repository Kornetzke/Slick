package drawable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Target extends Drawable{

	float xSpeed,ySpeed,totalSpeed;

	public Target() {
		x = y = width = height = 0;
	}

	public Target(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		xSpeed = (float)(70.0f+Math.random()*30);
		ySpeed = (float)(70.0f+Math.random()*30);

	}

	public void update(GameContainer gc, int delta) {

		if( x < 0 || x > gc.getWidth()-width)
			xSpeed = -xSpeed;
		
		if(y < 0 || y > gc.getHeight() - height)
			ySpeed = -ySpeed;
		
		x += xSpeed*delta/1000;
		y += ySpeed*delta/1000;
		
		totalSpeed = (float)Math.sqrt(Math.pow(xSpeed, 2)+Math.pow(ySpeed, 2));
		//System.out.println("Total Speed:"+totalSpeed);
		System.out.println("Target Direction: "+getCenter());
	}

	public void draw(GameContainer gc, Graphics g) {

		//g.setAntiAlias(true);
		g.setLineWidth(2.0f);
		g.setColor(Color.pink);
		g.drawLine(x, y + height, x + width / 2, y);
		g.drawLine(x + width / 2, y, x+width, y + height);
		g.drawOval(getCenterX()-3, getCenterY()-3, 6, 6);
		g.drawLine(x + width, y + height, x, y + height);
		g.setColor(Color.red);
		/*
		g.drawRect(x+width/2-2, y+height/2-2, 4, 4);
		g.drawRect(x+width-2, y+height/2-2, 4, 4);
		g.drawRect(x+width-2, y+height-2, 4, 4);
		g.drawRect(x+width/2-2, y+height-2, 4, 4);
		g.drawRect(x-2, y+height-2, 4, 4);
		g.drawRect(x+width-2, y-2, 4, 4);
		g.drawRect(x+width/2-2, y-2, 4, 4);
		g.drawRect(x-2, y+height/2-2, 4, 4);
		g.drawRect(x-2, y-2, 4, 4);
*/
	}

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

	public float getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

	public float getCenterX(){
		return x+width/2;
	}
	public float getCenterY(){
		return y+height/2;
	}
	public float getTotalSpeed(){
		return totalSpeed;
	}
}
