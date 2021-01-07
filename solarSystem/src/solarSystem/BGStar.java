package solarSystem;

import java.awt.Color;

public class BGStar {
	public static int count;
	private int x, y, size;
	private Color c;
	
	public BGStar(int size, int x, int y, Color c) {
		this.size = size;
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	public static BGStar[] generateStars(int count, int width, int height) {
		BGStar.count = count;
		BGStar[] stars = new BGStar[count];
		for(int i = 0; i < stars.length; i++)
			stars[i] = new BGStar((int)(Math.random() * 2 + 1), (int)(Math.random() * width), (int)(Math.random() * height), new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 155 + 100)));
		
		return stars;
	}
	
	public static int getCount() {
		return count;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getColor() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
}
