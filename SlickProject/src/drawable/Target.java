package drawable;

import hello.SimpleSlickGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Target extends Movable{


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
		
		speed = (float)(70.0f+Math.random()*30);

	}

	public void update(GameContainer gc, int delta) {

		
		super.update(gc, delta);
		
		bounce(gc);

		
		hitBox.update(this);
		
		for(Projectile p : SimpleSlickGame.testShip.getProjectiles()){
			if(hitBox.checkIfIntersects(p.getHitBox())){
				p.setHitSomething(true);
				System.out.println("Hit");
			}
		}
		System.out.println("direction: "+direction);
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
	
	public void bounce(GameContainer gc){
		if( x < 0 ){
			direction = reflectYaxis(direction);
			x = 0;
		} else if ( x > gc.getWidth()-width ){
			direction = reflectYaxis(direction);
			x = gc.getWidth()-width;
		}
		
		if(y < 0){
			direction =  reflectXaxis(direction);
			y = 0;
		}else if( y > gc.getHeight() - height){
			direction = reflectXaxis(direction);
			y = gc.getHeight() - height;
		}
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
	
	
	public float reflectYaxis(float dir){
		return (float) (Math.PI*4 - direction);
	}
	
	public float reflectXaxis(float dir){
		return (float) (Math.PI*.5 - direction);
	}

}
