package drawable;

import hello.SimpleSlickGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Target extends Movable{

	float xSpeed,ySpeed,totalSpeed;
	HitBox hitBox;

	public Target() {
		x = y = width = height = 0;
	}

	public Target(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		
		hitBox = new HitBox();
		hitBox.addPoint(new Point(x+width/2,y));
		hitBox.addPoint(new Point(x,y+height));
		hitBox.addPoint(new Point(x+width,y+height));
		hitBox.buildHitBox();
		
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
		
		hitBox.update(this);
		
		for(Projectile p : SimpleSlickGame.testShip.getProjectiles()){
			if(hitBox.checkIfIntersects(p.getHitBox())){
				p.setHitSomething(true);
				System.out.println("Hit");
			}
		}
		//System.out.println("Total Speed:"+totalSpeed);
		//System.out.println("Target Direction: "+getCenter());
	}

	public void draw(GameContainer gc, Graphics g) {

		//g.setAntiAlias(true);
		g.setLineWidth(2.0f);
		g.setColor(Color.pink);
		g.drawOval(x, y, width, height);
		g.drawOval(getCenterX()-3, getCenterY()-3, 6, 6);
		
		hitBox.draw(gc, g);

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
