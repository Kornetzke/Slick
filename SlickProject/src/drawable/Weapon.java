package drawable;

import hello.SimpleSlickGame;


import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Weapon {

	String name;
	float x, y, distanceFromCenterOfShip,direction;
	float fireRate;
	float fireCD;
	ArrayList<Projectile> projectiles;

	public Weapon(float x, float y,float distanceFromCenterOfShip,float direction, String name, float fireRate) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.distanceFromCenterOfShip = distanceFromCenterOfShip;
		this.name = name;
		this.fireRate = fireRate;
		this.fireCD = 1000 / fireRate;
		projectiles = new ArrayList<Projectile>();
	}

	public void update(GameContainer gc, float displacementX,float displacementY,float shipDirection, int delta,
			boolean firing) {
		// TODO Auto-generated method stub

		this.direction = shipDirection;
		boolean canFire = canFire(delta);
		
		
		System.out.println("wePD: "+direction);
		
		
		x += displacementX;
		y += displacementY;
		
		

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
