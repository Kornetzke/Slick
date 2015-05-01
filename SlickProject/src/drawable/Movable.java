package drawable;

import java.awt.geom.Point2D;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Movable extends Drawable {

	protected float speed, turnSpeed, targetDirection, displacementX,
			displacementY;

	public Movable() {
		speed = 0;
		direction = 0;
		targetDirection = 0;
		turnSpeed = 0;
		displacementX = 0;
		displacementY = 0;

	}

	public void update(GameContainer gc, int delta) {

		updateDirection(delta);

		x += displacementX = (float) Math.sin(direction) * speed * delta / 1000;
		y += displacementY = (float) Math.cos(direction) * -speed * delta
				/ 1000;
	}

	public void draw(GameContainer gc, Graphics g) {

		Point2D.Float middle = getCenter();

		g.drawString("No Image", x, y);
		g.drawRect(x, y, width, height);
		g.rotate(middle.x, middle.y, getDirectionInDegree());
		g.drawLine(middle.x, middle.y, middle.x, -(height + 5));
		g.rotate(middle.x, middle.y, -getDirectionInDegree());
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void pointAt(Point2D.Float targetPoint) {
		Point2D.Float middle = this.getCenter();

		direction = (float) -Math.atan2(middle.x - targetPoint.x, middle.y
				- targetPoint.y);
		direction = (float) ((direction < 0) ? direction + Math.PI * 2
				: direction);
	}

	public float getTargetDirection() {
		return targetDirection;
	}

	public void setTargetDirection(float targetDirection) {
		this.targetDirection = targetDirection;
	}

	public void setTargetDirection(Point2D.Float targetPoint) {
		Point2D.Float middle = this.getCenter();
		targetDirection = (float) -Math.atan2(middle.x - targetPoint.x,
				middle.y - targetPoint.y);
		targetDirection = (float) ((targetDirection < 0) ? targetDirection
				+ Math.PI * 2 : targetDirection);
	}

	public void setTargetDirection(float x, float y) {
		Point2D.Float middle = this.getCenter();
		targetDirection = (float) -Math.atan2(middle.x - x, middle.y - y);
		targetDirection = (float) ((targetDirection < 0) ? targetDirection
				+ Math.PI * 2 : targetDirection);
	}

	public float getTurnSpeed() {
		return turnSpeed;
	}

	public void setTurnSpeed(float turnSpeed) {
		this.turnSpeed = turnSpeed;
	}

	public void updateDirection(int delta) {

		float changeInDirection = getRotationRadians();
		float MaxTurnRadians = (float) Math.toRadians(turnSpeed) * delta / 1000;

		if (Math.abs(changeInDirection) < MaxTurnRadians) {
			direction += changeInDirection;
		} else {
			if (changeInDirection > 0) {
				direction += MaxTurnRadians;
			} else {
				direction -= MaxTurnRadians;
			}

		}
		if (direction > Math.PI * 2 || direction < 0)
			direction = (float) (direction % (Math.PI * 2));
		
		direction = (direction < 0) ? (float) (Math.PI*2) - direction : direction;

	}

	protected float getRotationRadians() {

		float tempTargetDirection = targetDirection;

		float change = (float) Math.PI - direction;

		float temp = (float) Math.PI;
		tempTargetDirection += change;

		if (tempTargetDirection > Math.PI * 2)
			tempTargetDirection %= Math.PI * 2;
		else if (tempTargetDirection < 0)
			tempTargetDirection += Math.PI * 2;

		return tempTargetDirection - temp;

	}

	public float getDisplacementX() {
		return displacementX;
	}

	public void setDisplacementX(float displacementX) {
		this.displacementX = displacementX;
	}

	public float getDisplacementY() {
		return displacementY;
	}

	public void setDisplacementY(float displacementY) {
		this.displacementY = displacementY;
	}

}
