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

import drawable.Target;

public class SimpleSlickGame extends BasicGame{
	float x = 200,y = 200,deg = 0;
	float turnSpeed = 40;
	Image image;
	Target ship;
	
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
		
		Image x =new Image("Resources/blah.png");
		
		System.out.println(String.format("x: %5s \ny: %5s", x.getWidth()/2, x.getHeight()/2));
		
		//gc.setMouseCursor(x, x.getWidth()/2, x.getHeight()/2);
		
		ship = new Target(20,20,50,50);
		
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		float mouseX = gc.getInput().getAbsoluteMouseX();
		float mouseY = gc.getInput().getAbsoluteMouseY();	

		ship.update(gc, i);
		
		mouseX = ship.getCenterX();
		mouseY = ship.getCenterY();
		
		double angle = -Math.atan2( gc.getWidth()/2 - mouseX, gc.getHeight()/2 - mouseY);
		
		deg = (float) Math.toDegrees(angle);
		
		System.out.println("x:"+gc.getInput().getAbsoluteMouseX()+" y:"+gc.getInput().getAbsoluteMouseY());
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		g.rotate(gc.getWidth()/2, gc.getHeight()/2, deg);
		g.drawImage(image, x, y);
		g.drawLine(gc.getWidth()/2, gc.getHeight()/2, gc.getWidth()/2, -600);
		g.rotate(gc.getWidth()/2, gc.getHeight()/2, -deg);
		
		ship.draw(gc, g);
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
