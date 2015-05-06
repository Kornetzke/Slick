package hello;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import drawable.Background;
import drawable.Movable;
import drawable.Ship;
import drawable.Target;

public class SimpleSlickGame extends BasicGame{

	public static Target[] target;
	public static Ship playerShip;
	public static Background background;
	
	
	
	
	Movable focus;
	
	long updateTime=0,drawTime=0;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		
		target = new Target[5];
		
		
		for(int x = 0;x< target.length;x++ ){
			target[x] = new Target(gc.getWidth()/2,gc.getHeight()/2,24,24);
		}
		
		playerShip = new Ship();
		
		background = new Background();
		
		focus = playerShip;
		
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		gc.getInput();
		if(gc.getInput().isKeyPressed(Input.KEY_RBRACKET)){
		focus = target[0];
		}else if(gc.getInput().isKeyPressed(Input.KEY_LBRACKET)){
			focus = playerShip;
		}
		
		long time = gc.getTime();
		

		for(Target t : target){
			
			t.update(gc, delta);
		}
		
		playerShip.update(gc, delta);
		//System.out.println(testShip.getDirection());
		
		updateTime = gc.getTime() - time;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		long time = gc.getTime();
		
		
		
		
		float xOffset = gc.getWidth()/2 - focus.getCenter().x;
		float yOffset = gc.getHeight()/2 - focus.getCenter().y;
		
		
		g.translate(xOffset,yOffset);	
		background.draw(gc, g);
		
		for(Target t : target){
			
			t.draw(gc, g);
		}
		playerShip.draw(gc, g);
		
		g.translate(-xOffset, -yOffset);
	
		drawTime = gc.getTime() - time;
		g.drawString("drawTime: "+drawTime, 5, 30);
		g.drawString("updateTime: "+updateTime,5,50);
		g.drawString(String.format("X:%2d\nY:%2d", gc.getInput().getMouseX(),gc.getInput().getMouseY()), 5, 70);
		
	}


	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setAlwaysRender(true);
			//appgc.setMultiSample(8);
			//appgc.setTargetFrameRate(60);
			//appgc.setVSync(true);
			//appgc.setTargetFrameRate(120);
			appgc.setDisplayMode(640, 480, false);

			//appgc.setDisplayMode(1920, 1080, true);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
