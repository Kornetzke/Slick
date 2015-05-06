package drawable;

import java.awt.geom.Point2D.Float;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

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
		
		
		g.drawRect(x, y, 20, 50);
		g.drawLine(x+10, y+50, x+10, y+50-(50*currentThrottle/maxThrottle));
		
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
