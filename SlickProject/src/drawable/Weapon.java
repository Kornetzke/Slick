package drawable;

import hello.SimpleSlickGame;




import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Weapon {

	String name;
	float x, y,direction;
	float offSetFromCenterOfShipX,offSetFromCenterOfShipY;
	Ship ship;
	float fireRate;
	float fireCD;
	ArrayList<Projectile> projectiles;

	public Weapon(Ship ship,float x, float y, String name, float fireRate) {
		this.ship = ship;
		this.x = ship.getX()+x;
		this.y = ship.getY()+y;
		this.offSetFromCenterOfShipX = this.x - ship.getCenter().x;
		this.offSetFromCenterOfShipY = this.y - ship.getCenter().y;
		this.direction = ship.getDirection();
		this.name = name;
		this.fireRate = fireRate;
		this.fireCD = 1000 / fireRate;
		projectiles = new ArrayList<Projectile>();
	}

	public void update(GameContainer gc, int delta,
			boolean firing) {
		// TODO Auto-generated method stub

		this.direction = ship.getDirection();
		float deg = (direction);
		boolean canFire = canFire(delta);

		
		
		float deltaX = (float)(Math.cos(deg)*offSetFromCenterOfShipX+Math.sin(deg)*offSetFromCenterOfShipY);
		float deltaY = (float)(Math.cos(deg)*offSetFromCenterOfShipY-Math.sin(deg)*offSetFromCenterOfShipX);
		
		//System.out.println(String.format("cos(deg): %2.2f  sin(deg): %2.2f", Math.cos(deg),Math.sin(deg)));
		
		System.out.println(String.format("deg: %2.2f  offSetX: %2.2f  offSetY: %2.2f  offSetXAngle: %2.2f  offSetYAngle: %2.2f",deg, offSetFromCenterOfShipX,offSetFromCenterOfShipY,deltaX,deltaY));
		
		x = ship.getCenter().x+deltaX;
		y = ship.getCenter().y+deltaY;
		
		

		updateProjectiles(gc, delta);

		if (canFire && firing)
			try {
				projectiles.add(new Projectile(x, y, direction));
				fireCD = 1000 / fireRate;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	private void updateProjectiles(GameContainer gc, int delta) {

		Iterator<Projectile> projectilesIterator = projectiles.iterator();

		while (projectilesIterator.hasNext()) {
			Projectile p = projectilesIterator.next();
			
			p.setTargetDirection(SimpleSlickGame.target.getCenter());
			p.update(gc, delta);
			
			if (p.getDistanceTraveled() > p.getMaximumRange()) {
				projectilesIterator.remove();
			}
		}

	}


	private boolean canFire(int delta) {

		fireCD = (fireCD > 0) ? fireCD - delta : 0;

		if (fireCD == 0) {
			return true;
		}
		return false;
	}

	public void draw(GameContainer gc, Graphics g) {
		for (Projectile p : projectiles) {
			p.draw(gc, g);
		}
	}

}
