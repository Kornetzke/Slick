package drawable;

import hello.SimpleSlickGame;






import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Weapon  {

	String name;
	float xMiddle, yMiddle,direction;
	float offSetFromCenterOfShipX,offSetFromCenterOfShipY;
	Ship ship;
	float fireRate;
	float fireCD;
	float coneOfFire;
	
	ArrayList<Projectile> projectiles;

	public Weapon(Ship ship,float x, float y, String name, float fireRate) {
		this.ship = ship;
		this.xMiddle = ship.getX()+x;
		this.yMiddle = ship.getY()+y;
		this.offSetFromCenterOfShipX = this.xMiddle - ship.getCenter().x;
		this.offSetFromCenterOfShipY = this.yMiddle - ship.getCenter().y;
		this.coneOfFire = (float) Math.toRadians(15);
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

		
		
		float deltaX = (float)(Math.cos(deg)*offSetFromCenterOfShipX-Math.sin(deg)*offSetFromCenterOfShipY);
		float deltaY = (float)(Math.cos(deg)*offSetFromCenterOfShipY+Math.sin(deg)*offSetFromCenterOfShipX);
		
		//System.out.println(String.format("cos(deg): %2.2f  sin(deg): %2.2f", Math.cos(deg),Math.sin(deg)));
		
		//System.out.println(String.format("deg: %2.2f  shipCenter: %2.2f %2.2f  offSetX: %2.2f  offSetY: %2.2f  offSetXAngle: %2.2f  offSetYAngle: %2.2f",deg,ship.getCenter().x,ship.getCenter().y, offSetFromCenterOfShipX,offSetFromCenterOfShipY,deltaX,deltaY));
		
		xMiddle = ship.getCenter().x+deltaX;
		yMiddle = ship.getCenter().y+deltaY;
		
		

		updateProjectiles(gc, delta);

		if (canFire && firing)
			try {
				
				int index = (int)(Math.random()*10);
				
				
				Projectile p = new Projectile(xMiddle, yMiddle, direction+(float)(Math.random()*coneOfFire-coneOfFire/2));
				p.setTarget(SimpleSlickGame.target[index]);
				
				projectiles.add(p);
				
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
			
			int index = (int)(Math.random()*10);
			
			p.setTargetDirection(SimpleSlickGame.target[index].getCenter());
			
			p.update(gc, delta);
			
			if (p.getDistanceTraveled() > p.getMaximumRange() || p.isHitSomething()) {
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
	
	public ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}

}
