package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import MAIN.load_image;

public class enemy {

	private int x, y;
	private boolean red,bl;

	public enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick() {
		y += (int)1.5;
	}

	public void render(Graphics g) {
		Random rand = new Random();
		int randx = rand.nextInt(4);
		g.setColor(Color.BLACK);
		//g.fillOval(x, y, 25, 25);
	g.drawImage(load_image.enemy, x, y,50,50,null);
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}
}
