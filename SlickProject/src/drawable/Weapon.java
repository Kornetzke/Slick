package drawable;

import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Weapon {
	
	String name;
	float x,y;
	float fireRate;
	ArrayList<Projectile> projectiles;
	
	public Weapon(float x,float y,String name,float fireRate){
		this.x = x;
		this.y = y;
		this.name = name;
		this.fireRate = fireRate;
		projectiles = new ArrayList<Projectile>();
	}
	
	
	public void update(GameContainer gc,Float displacement, int delta, boolean firing) {
		// TODO Auto-generated method stub
		
		x += displacement.x;
		y += displacement.y;
		
		for(Projectile p : projectiles){
			p.update(gc, delta);
		}
		
		if(firing)
			try {
				projectiles.add(new Projectile(x, y));
				System.out.println("added Projectile");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void draw(GameContainer gc, Graphics g){
		for(Projectile p : projectiles){
			p.draw(gc, g);
		}
	}
	

}
