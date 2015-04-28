package hello;
import java.util.logging.Level;
import java.util.logging.Logger;




import org.lwjgl.input.Cursor;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import drawable.Ship;
import drawable.Target;

public class SimpleSlickGame extends BasicGame{
	float x = 200,y = 200,deg = 0,width=125,height=138;
	float turnSpeed = 40;
	Image image;
	Target ship;
	Ship testShip;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setAlwaysRender(true);
		gc.setMultiSample(0);
		image = new Image("Resources/Fighter150W.png");
		x = gc.getWidth()/2-image.getWidth()/2;
		y = gc.getHeight()/2-image.getHeight()/2;
		
		
		
		ship = new Target(20,20,50,50);
		
		testShip = new Ship();
		
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		float mouseX = gc.getInput().getAbsoluteMouseX();
		float mouseY = gc.getInput().getAbsoluteMouseY();	

		ship.update(gc, delta);
		
		testShip.update(gc, delta);
		
		//mouseX = ship.getCenterX();
		//mouseY = ship.getCenterY();
		
		double angle = -Math.atan2( x+width/2 - mouseX, y+height/2 - mouseY);
		
		deg = (float) Math.toDegrees(angle);
		
		x += Math.sin(angle)*turnSpeed*delta/1000;
		y += Math.cos(angle)*-turnSpeed*delta/1000;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		g.rotate(x+width/2, y+height/2, deg);
		g.drawImage(image, x, y);
		g.drawLine(x+width/2, y+height/2, x+width/2, -600);
		g.rotate(x+width/2, y+height/2, -deg);
		
		ship.draw(gc, g);
		testShip.draw(gc, g);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setMultiSample(8);
			//appgc.setTargetFrameRate(60);
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
