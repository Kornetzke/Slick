package drawable;


import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class HitBox {
	
	
	float direction;
	
	Polygon hitBox;
	
	ArrayList<Point> points;
	
	public HitBox(){
		direction = 0;
		points = new ArrayList<Point>();
		hitBox = new Polygon();
	}
	
	
	public void addPoint(Point p){
		points.add(p);
	}
	
	public boolean removePoint(Point p){
		return points.remove(p);
	}
	
	public void buildHitBox(){
		hitBox = new Polygon();
		for(Point p : points){
			hitBox.addPoint(p.getX(), p.getY());
		}
	}
	
	public void update(Movable obj){
		float deg = obj.getDirectionInDegree();
		Point2D.Float center = obj.getCenter();
		
		Transform transform = new Transform();
		transform = Transform.createRotateTransform(deg, (float)center.getX()-hitBox.getCenterX(),(float) center.getY()-hitBox.getCenterX());
		
		//hitBox = (Polygon) hitBox.transform(transform);
	}
	
	
	public void draw(GameContainer gc, Graphics g) {
		
		g.rotate(hitBox.getCenterX(), hitBox.getCenterY(), (float)Math.toDegrees(direction));
		g.setColor(Color.white);
		g.draw(hitBox);
		g.rotate(hitBox.getCenterX(), hitBox.getCenterY(),(float)-Math.toDegrees(direction));
		
		
		
	}
	
	
	
	
	

}
