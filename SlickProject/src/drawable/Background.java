package drawable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background extends Drawable{
	
	Image image;
	
	
	
	public Background(){
		super();
		try {
			image = new Image("Resources/Space.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		
		
		
	}
	

	@Override
	public void draw(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, x, y);
	}
	
	

}
