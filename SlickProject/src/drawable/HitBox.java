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
		hitBox = (Polygon) hitBox.transform(Transform.createRotateTransform(direction,hitBox.getCenterX(),hitBox.getCenterY()));
	}
	
	public void update(Movable obj){
		float deg = obj.getDirection();
		Point2D.Float center = obj.getCenter();
		float rotateOffSet = deg - direction;
		direction = deg;
		
		float deltaX = hitBox.getCenterX() - center.x;
		float deltaY = hitBox.getCenterY() - center.y;
		
		//System.out.println(String.format("HBX: %3.2f  HBY: %3.2f  OBJX: %3.2f  OBJY: %3.2f", hitBox.getCenterX(),hitBox.getCenterY(),obj.getCenter().x,obj.getCenter().y));
		
		
		hitBox = (Polygon) hitBox.transform(Transform.createRotateTransform(rotateOffSet,center.x,center.y));
		hitBox = (Polygon) hitBox.transform(Transform.createTranslateTransform(-deltaX, -deltaY));

	}
	
	public boolean checkIfIntersects(HitBox otherHitBox){
		return hitBox.intersects(otherHitBox.hitBox);
	}
	
	
	public void draw(GameContainer gc, Graphics g) {
		/*
		g.setAntiAlias(true);
		g.setColor(Color.orange);
		g.draw(hitBox);
		*/
		
		
		
	}
	
	
	
	
	

}
