package drawable;



import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class Ship extends Movable {

	Image image;
	String name;
	int health;
	int sheilds;
	boolean firing;
	HitBox hitBox;
	
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
		
		turnSpeed = 50;
		speed = 0;

		weapons = new ArrayList<Weapon>();
		//weapons.add(new Weapon(this,19,81, "hey", 100.0f));
		weapons.add(new Weapon(this,105,81, "hey", 1.0f));
		//weapons.add(new Weapon(this,62,15, "hey", 100.0f));
		
		hitBox = new HitBox();
		hitBox.addPoint(new Point(0,0));
		hitBox.addPoint(new Point(10,0));
		hitBox.addPoint(new Point(10,10));
		hitBox.addPoint(new Point(0,10));
		hitBox.buildHitBox();
		
		
		name = "ship";
		health = 1;
		sheilds = 1;
		firing = true;

	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub

		if (gc.getInput().isMouseButtonDown(0))
			firing = true;
		else
			firing = false;

		float mX = gc.getInput().getAbsoluteMouseX();
		float mY = gc.getInput().getAbsoluteMouseY();

		setTargetDirection(mX, mY);
		//direction += Math.toRadians(turnSpeed)*delta/1000;

		super.update(gc, delta);
		
		hitBox.update(this);
		
		//System.out.println("ShipD: "+direction);
		
		//updatePosition(delta);
		updateFiring(gc, delta);

	}

	@Override
	public void draw(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub

		for(Weapon wep : weapons){
			wep.draw(gc,g);
		}

		g.rotate(x + width / 2, y + height / 2,
				(float) Math.toDegrees(direction));
		//g.drawImage(image, x, y);
		g.rotate(x + width / 2, y + height / 2,
				(float) Math.toDegrees(-direction));
		
		hitBox.draw(gc, g);

	}
	
	protected Weapon addWeapon(){
		Weapon tempWep = new Weapon(this,displacementX, displacementX, name, displacementX);
		return tempWep;
		
		
	}

	private void updatePosition(int delta) {

		x += (float) Math.sin(direction) * speed * delta / 1000;
		y += (float) Math.cos(direction) * -speed * delta / 1000;

	}

	private void updateFiring(GameContainer gc, int delta) {

		for(Weapon wep : weapons){
			wep.update(gc, delta, firing);
		}
		
	}

}
