package solarSystem;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SS extends JPanel{
	private static final long serialVersionUID = 1L;
	public static JFrame f = new JFrame("screen");
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static BGStar[] stars = BGStar.generateStars((int)(Math.random() * 2000 + 1000), WIDTH, HEIGHT);
    public static final int speed = 1;
    public static ArrayList<Planet> planets = new ArrayList<>();
    
    public Queue<double[]> tp = new LinkedList<double[]>();
    
    public void paintComponent(Graphics g){
    	for(int i = 0; i < BGStar.getCount(); i++) {
    		g.setColor(stars[i].getColor());
    		g.fillRect(stars[i].getX(), stars[i].getY(), stars[i].getSize(), stars[i].getSize());
    	}
    	
    	generateTrails(g);
    	for(Planet p : planets) {
    		g.setColor(p.getC());
    		g.fillOval((int)(p.getX()), (int)(p.getY()), (int)p.getSize(), (int)p.getSize());
    		generateVector(g, p);
    	}
    	
    }

    public static void main(String[] args) {
    	SS p = new SS();
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setBackground(new Color(20, 20, 20));
    	f.setSize(WIDTH, HEIGHT);
        f.add(p);
        f.setVisible(true);
        //=====================Label, Solar S, X, Y, G, Mass, Size, Force, Angle, Color
        planets.add(new Planet("P1", new SS(), 400, 300, 1, 130, 50, 65, 315, new Color(255, 0, 0), false));
        planets.add(new Planet("P2", new SS(), 600, 500, 1, 130, 50, 65, 135, new Color(255, 255, 0)));
        planets.add(new Planet("P3", new SS(), 500, 800, 1, 20, 20, 20, 0, new Color(255, 255, 0)));
        
        Object monitor = new Object();
        synchronized(monitor) {
            while(true) {
                f.repaint();
                for(Planet pl : planets) 
                    pl.update(planets);
                
                try{Thread.sleep(speed);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
            }
        }
        
    }
    
    //generate trail points
    public void generateTrails(Graphics g) {
    	g.setColor(new Color(100, 255, 255));
    	
    	for(Planet p : planets) {
    		double[] coord = new double[2];
    		coord[0] = p.getX() + (p.getSize() / 2);
    		coord[1] = p.getY() + (p.getSize() / 2);
    		tp.add(coord);
    	}
    	
    	if(tp.size() > 10000) tp.remove();
    	ArrayList<double[]> coords = new ArrayList<double[]>(tp);
    	for(double[] coord : coords) 
    		g.fillRect((int)coord[0], (int)coord[1], 1, 1);
    	
    }
    
    //generate planet direction vectors
    public void generateVector(Graphics g, Planet p) {
    	g.setColor(new Color(0, 255, 0));
		System.out.println(p);
		if(!p.isCanMove()) return;
		double angle = p.getAngle();
		double x = p.getX() + (p.getSize() / 2);
		double y = p.getY() + (p.getSize() / 2);
		double f = p.getForce();
		g.drawLine((int)x, (int)y, (int)(f * Math.cos(angle) + x), (int)(f * Math.sin(angle) + y));
    }
}
