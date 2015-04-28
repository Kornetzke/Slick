package drawable;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ship extends Movable{

	Image image;
	String name;
	int health;
	int sheilds;
	boolean firing;
	Weapon weapon;
	
	public Ship(){
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
		
		weapon = new Weapon(x+width/2, y, "hey", 5.0f);
		name = "ship";
		health = 1;
		sheilds = 1;
		firing = true;
		
		
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		
		Point2D.Float displacement;
		
		displacement = updatePosition(delta);
		updateFiring(gc,displacement, delta);
		
	}

	@Override
	public void draw(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		
		weapon.draw(gc, g);
		
		g.rotate(x+width/2, y+height/2, (float)Math.toDegrees(direction));
		g.drawImage(image, x, y);
		g.rotate(x+width/2, y+height/2, (float)Math.toDegrees(-direction));
		
		
	}
	
	private Point2D.Float updatePosition(int delta){
		
		Point2D.Float displacement = new Point2D.Float(0.0f,0.0f);
		x += displacement.x = (float) Math.sin(direction)*speed*delta/1000;
		y += displacement.y = (float)  Math.cos(direction)*-speed*delta/1000;
		
		
		return displacement;
	}
	
	private void updateFiring(GameContainer gc,Float displacement, int delta){
		
		weapon.update(gc,displacement, delta, firing);
	}

}
