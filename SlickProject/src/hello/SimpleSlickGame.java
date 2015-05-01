package hello;
import java.util.logging.Level;
import java.util.logging.Logger;




import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import drawable.Ship;
import drawable.Target;

public class SimpleSlickGame extends BasicGame{
	float x = 100,y = 200,deg = 0,width=125,height=138;
	
	float oldX = x,oldY = y;
	float movementSpeed = 0,turnSpeed = 25;
	Image image;
	public static Target target;
	Ship testShip;
	
	long updateTime=0,drawTime=0;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setAlwaysRender(true);
		gc.setMultiSample(0);
		image = new Image("Resources/Fighter150W.png");
		//x = gc.getWidth()/2-image.getWidth()/2;
		//y = gc.getHeight()/2-image.getHeight()/2;
		
		
		
		target = new Target(20,20,50,50);
		
		testShip = new Ship();
		
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		long time = gc.getTime();
		
		float mouseX = gc.getInput().getAbsoluteMouseX();
		float mouseY = gc.getInput().getAbsoluteMouseY();	

		target.update(gc, delta);
		
		testShip.update(gc, delta);
		
		mouseX = target.getCenterX();
		mouseY = target.getCenterY();
			
		
		double angle = -Math.atan2( x+width/2 - mouseX, y+height/2 - mouseY);
		
		if(angle < 0){
			angle += Math.PI*2;
		}
		
		deg = (float) Math.toDegrees(angle);
		//deg += turnSpeed*delta/1000;
			
		//System.out.println(String.format("Angle: %2.3f  Degrees: %2.3f  Olddeg: %2.3f  OldAngle: %2.3f", angle,deg,oldDeg,oldAngle));
		
		
		
		x += Math.sin(angle)*movementSpeed*delta/1000;
		y += Math.cos(angle)*-movementSpeed*delta/1000;
		
		//System.out.println(String.format("X: %2.3f  Y: %2.3f  OldX: %2.3f  OldY: %2.3f", x,y,oldX,oldY));
		
		updateTime = gc.getTime() - time;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		long time = gc.getTime();
		
		
		g.rotate(x+width/2, y+height/2, deg);
		//g.drawImage(image, x, y);
		g.drawLine(x+width/2, y+height/2, x+width/2, -600);
		g.rotate(x+width/2, y+height/2, -deg);
		
		target.draw(gc, g);
		testShip.draw(gc, g);
		
		drawTime = gc.getTime() - time;
		g.drawString("drawTime: "+drawTime, 5, 30);
		g.drawString("updateTime: "+updateTime,5,50);
		g.drawString(String.format("X:%2d", gc.getInput().getAbsoluteMouseX()), 5, 70);
		g.drawString(String.format("Y:%2d", gc.getInput().getAbsoluteMouseY()), 5, 90);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			//appgc.setMultiSample(8);
			//appgc.setTargetFrameRate(60);
			//appgc.setVSync(true);
			//appgc.setTargetFrameRate(120);
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
