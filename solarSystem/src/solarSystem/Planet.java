package solarSystem;

import java.awt.Color;
import java.util.ArrayList;

public class Planet {
	private String label;
	private SS home;
	private double x,y;
	private double G, mass, size, force, angle;
	private Color c;
	private boolean canMove = true;
	
	public Planet(String label, SS home, int x, int y, double g, double mass, double size, double force, double angle, Color c) {
		super();
		this.label = label;
		this.home = home;
		this.x = x;
		this.y = y;
		G = g;
		this.mass = mass;
		this.size = size;
		this.force = force;
		this.angle = angle * Math.PI / 180;
		this.c = c;
		this.canMove = true;
	}

	public Planet(String label, SS home, int x, int y, double g, double mass, double size, double force, double angle, Color c, boolean canMove) {
		super();
		this.label = label;
		this.home = home;
		this.x = x;
		this.y = y;
		G = g;
		this.mass = mass;
		this.size = size;
		this.force = force;
		this.angle = angle * Math.PI / 180;
		this.c = c;
		this.canMove = canMove;
	}

	//update planets coordinates
	public void update(ArrayList<Planet> planets) {
		if(!this.canMove) return;
		
        for(Planet p : planets) {
            if(p != this) {
                double fp = forceG(this, p);
                double fa = getPAngle(this, p);
                double yf = ((this.force * Math.sin(this.angle)) + (fp * Math.sin(fa)));
                double xf = ((this.force * Math.cos(this.angle)) + (fp * Math.cos(fa)));
                this.force = Math.sqrt(Math.pow(yf,2) + Math.pow(xf,2));
                this.angle = Math.atan2(yf, xf);
            }
        }
        
        //I believe this is where circle collision detection and movement will take place
        
        this.x += ((force) * Math.cos(angle) / mass);
        this.y += ((force) * Math.sin(angle) / mass);
	}
	
	//get the magnitude of force between two planets
	public double forceG(Planet p1, Planet p2) {
		double off1 = p1.getSize() / 2;
		double off2 = p2.getSize() / 2;
        double di = getDist(p1.getX() + off1, p2.getX() + off2, p1.getY() + off1, p2.getY() + off2);
        //Try to slow down the collision
        if(di < 50) di = 50;
        final double massT = p1.getMass() * p2.getMass();
        double fg = G * (massT)/(Math.pow(di, 2));
        return fg;
    } 
    
    public double getDist(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1, 2));
    }
    
    //get the angle between two planets
    public double getPAngle(Planet p1, Planet p2) {
    	return Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX());
    }

	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public SS getHome() {
		return home;
	}

	public void setHome(SS home) {
		this.home = home;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getG() {
		return G;
	}

	public void setG(double g) {
		G = g;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getForce() {
		return force;
	}

	public void setForce(double force) {
		this.force = force;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
	
	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	@Override
	public String toString() {
		return "Planet [label=" + label + ", home=" + home + ", x=" + x + ", y=" + y + ", G=" + G + ", mass=" + mass
				+ ", size=" + size + ", force=" + force + ", angle=" + angle + ", c=" + c + ", canMove=" + canMove
				+ "]";
	}
}
