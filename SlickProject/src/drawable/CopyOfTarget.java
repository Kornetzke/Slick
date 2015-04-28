package drawable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class CopyOfTarget extends Movable{

	

	public CopyOfTarget() {
		x = y = width = height = 0;
	}

	public CopyOfTarget(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.direction = 0;
		
		speed = (float)(70.0f+Math.random()*30);

	}

	public void update(GameContainer gc, int delta) {

		x += Math.sin(direction)*speed*delta/1000;
		y += Math.cos(direction)*-speed*delta/1000;
	}

	public void draw(GameContainer gc, Graphics g) {

		//g.setAntiAlias(true);
		g.setLineWidth(2.0f);
		g.setColor(Color.pink);
		g.drawLine(x, y + height, x + width / 2, y);
		g.drawLine(x + width / 2, y, x+width, y + height);
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

	public float getCenterX(){
		return x+width/2;
	}
	public float getCenterY(){
		return y+height/2;
	}

}
