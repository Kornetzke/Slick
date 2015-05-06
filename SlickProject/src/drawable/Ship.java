package drawable;



import hello.SimpleSlickGame;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class Ship extends Movable {

	Image image;
	String name;
	int health;
	int sheilds;
	boolean firing;
	HitBox hitBox;
	Throttle throttle;
	boolean followTarget;
	Movable target;
	
	ArrayList<Weapon> weapons;

	public Ship() {
		try {
			image = new Image("Resources/Fighter150W.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		x = 300;
		y = 300;

		width = image.getWidth();
		height = image.getHeight();
		
		followTarget = false;
		
		target = SimpleSlickGame.target[0];
		
		turnSpeed = 200;
		speed = 20;

		weapons = new ArrayList<Weapon>();
		//weapons.add(new Weapon(this,19,81, "hey", 100.0f));
		//weapons.add(new Weapon(this,105,81, "hey", 100.0f));
		weapons.add(new Weapon(this,62,15, "hey", 5f));
		
		Point2D.Float middle = getCenter();
		
		hitBox = new HitBox();
		hitBox.addPoint(new Point(middle.x-10,middle.y-10));
		hitBox.addPoint(new Point(middle.x+10,middle.y-10));
		hitBox.addPoint(new Point(middle.x+10,middle.y+10));
		hitBox.addPoint(new Point(middle.x-10,middle.y+10));
		hitBox.buildHitBox();
		
		throttle = new Throttle(500,1,0,1);
		
		name = "ship";
		health = 1;
		sheilds = 1;
		firing = true;

	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub

		if(gc.getInput().isMousePressed(1)){
			followTarget = !followTarget;
		}
		
		if (gc.getInput().isMouseButtonDown(0))
			firing = true;
		else
			firing = false;

		float mX = (gc.getInput().getMouseX()-gc.getWidth()/2)+getCenter().x;
		float mY = (gc.getInput().getMouseY()-gc.getHeight()/2)+getCenter().y;

		setTargetDirection(mX, mY);
		
		if(followTarget){
		setTargetDirection(target.getCenter());
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_S)){
			if(gc.getInput().isKeyDown(Input.KEY_LSHIFT)){
				throttle.changeThrottleBy(-throttle.maxThrottle);
			}
			
			throttle.decreaseThrottle();
		}else if(gc.getInput().isKeyDown(Input.KEY_W)){
			if(gc.getInput().isKeyDown(Input.KEY_LSHIFT)){
				throttle.changeThrottleBy(throttle.maxThrottle);
			}
			throttle.increaseThrottle();
		}
		
		
		
		throttle.update(gc, delta);
		//direction += Math.toRadians(turnSpeed)*delta/1000;

		updateDirection(delta);

		System.out.println(throttle.getCurrentThrottle());
		
		x += displacementX = (float) Math.sin(direction) * throttle.getSpeed() * delta / 1000;
		y += displacementY = (float) Math.cos(direction) * -throttle.getSpeed() * delta/ 1000;
		
		
		
		hitBox.update(this);
		
		//System.out.println(String.format("Ship: d=%2.2f x=%2.2f y=%2.2f", direction, x,y));
		
		//updatePosition(delta);
		updateFiring(gc, delta);

	}

	@Override
	public void draw(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub

		for(Weapon wep : weapons){
			wep.draw(gc,g);
		}

		g.rotate(getCenter().x,getCenter().y,
				(float) Math.toDegrees(direction));
		g.drawImage(image, x, y);
		g.rotate(getCenter().x, getCenter().y,
				(float) Math.toDegrees(-direction));
		
		hitBox.draw(gc, g);
		
		
		throttle.draw(gc,g,getCenter());

	}
	
	protected Weapon addWeapon(){
		Weapon tempWep = new Weapon(this,displacementX, displacementX, name, displacementX);
		return tempWep;
		
		
	}


	private void updateFiring(GameContainer gc, int delta) {

		for(Weapon wep : weapons){
			wep.update(gc, delta, firing||followTarget);
		}
		
	}
	
	public ArrayList<Projectile> getProjectiles(){
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		
		for(Weapon w : weapons){
			projectiles.addAll(w.getProjectiles());
		}
		
		return projectiles;
	}

}
