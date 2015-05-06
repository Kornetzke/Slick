package drawable;

import java.awt.geom.Point2D.Float;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;

public class Throttle {

	float speed, maxSpeed, acceleration, currentThrottle, maxThrottle;

	public Throttle() {
		speed = 0;
		maxSpeed = 0;
		acceleration = 0;
		currentThrottle = 0;
		maxThrottle = 0;
	}

	public Throttle(float maxSpeed, float acceleration, float currentThrottle,
			float maxThrottle) {
		this.speed = 0;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.currentThrottle = currentThrottle;
		this.maxThrottle = maxThrottle;
	}

	public void update(GameContainer gc, int delta) {

		float targetSpeed = maxSpeed * currentThrottle;

		if (speed < targetSpeed){
			speed += acceleration;
		}else if(speed > targetSpeed){
			speed -= acceleration;
		}else if(speed <= targetSpeed-acceleration && speed >= targetSpeed+acceleration){
			speed = targetSpeed;
		}

	}
	
	public void draw(GameContainer gc, Graphics g, Float shipCenter) {
		
		float x = shipCenter.x+gc.getWidth()/2-30;
		float y = shipCenter.y-gc.getHeight()/2+10;
		
		float width = 20;
		float height = 50;
		float startX = x + width;
		float startY = y + height+g.getLineWidth()/2;
		
		ShapeFill fill= new GradientFill(startX, startY, Color.green, startX-width, startY-height, Color.red);
		Rectangle r = new Rectangle(startX,startY,-width,-height*currentThrottle/maxThrottle);
	
		ShapeRenderer.fill(r, fill);
		
		ShapeRenderer.draw(new Rectangle(x,y,width,height), new GradientFill(startX, startY, Color.green, startX-width, startY-height, Color.transparent));
		//g.drawRect(x, y, width, height);
		
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public float getCurrentThrottle() {
		return currentThrottle;
	}

	public void setCurrentThrottle(float currentThrottle) {
		if(currentThrottle < 0 ){
			this.currentThrottle = 0;
		}else if (currentThrottle <= maxThrottle) {
			this.currentThrottle = currentThrottle;
		} else {
			this.currentThrottle = this.maxThrottle;
		}
	}

	public void changeThrottleBy(float inc) {
		setCurrentThrottle(this.currentThrottle + inc);

	}
	
	public void increaseThrottle(){
		setCurrentThrottle(this.currentThrottle + .005f);
	}
	public void decreaseThrottle(){
		setCurrentThrottle(this.currentThrottle - .005f);
	}

	public float getMaxThrottle() {
		return maxThrottle;
	}

	public void setMaxThrottle(float maxThrottle) {
		this.maxThrottle = maxThrottle;
	}

	

}
