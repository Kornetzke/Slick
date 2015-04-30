package drawable;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ship extends Movable {

	Image image;
	String name;
	int health;
	int sheilds;
	boolean firing;
	Weapon weapon;

	public Ship() {
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
		
		turnSpeed = 500;

		weapon = new Weapon(x + 19, y + 81,(float)Math.sqrt(Math.pow(19,2)+Math.pow(81, 2)),direction, "hey", 5.0f);
		name = "ship";
		health = 1;
		sheilds = 1;
		firing = true;

	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub

		if (gc.getInput().isMouseButtonDown(0))
			firing = true;
		else
			firing = false;

		float mX = gc.getInput().getAbsoluteMouseX();
		float mY = gc.getInput().getAbsoluteMouseY();

		setTargetDirection(mX, mY);

		super.update(gc, delta);
		System.out.println("ShipD: "+direction);
		updatePosition(delta);
		updateFiring(gc, delta);

	}

	@Override
	public void draw(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub

		weapon.draw(gc, g);

		g.rotate(x + width / 2, y + height / 2,
				(float) Math.toDegrees(direction));
		g.drawImage(image, x, y);
		g.rotate(x + width / 2, y + height / 2,
				(float) Math.toDegrees(-direction));

	}
	
	protected Weapon addWeapon(){
		Weapon tempWep = new Weapon(displacementX, displacementX, displacementX,direction, name, displacementX);
		return tempWep;
		
		
	}

	private void updatePosition(int delta) {

		x += (float) Math.sin(direction) * speed * delta / 1000;
		y += (float) Math.cos(direction) * -speed * delta / 1000;

	}

	private void updateFiring(GameContainer gc, int delta) {

		getCenter();
		
		weapon.update(gc, displacementX, displacementY,direction, delta, firing);
	}

}
