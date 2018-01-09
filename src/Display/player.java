package Display;

import Display.display.*;
import MAIN.load_image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class player implements KeyListener {
	private int x;
	private int y;
	private boolean right, left, up, down;
	private boolean fire;
	private long current;
	private long delay;
	private int health;

	public player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void initialize() {
		display.frame.addKeyListener(this);
		current = System.nanoTime();
		delay = 100;
		health = 10;
	}

	public void tick() {
		if (health > 0) {
			if (left) {
				if (x >= 54)
					x -= 4;
			}
			if (right) {
				if (x <= 384)
					x += 4;
			}
			if (up) {
				if (y >= 54)
					y -= 4;
			}
			if (down) {
				if (y <= 384)
					y += 4;
			}
			if (fire) {
				long breaks = (System.nanoTime() - current) / 1000000;
				if (breaks > delay)
					manager.bullet.add(new bullet(x + 30, y));
				current = System.nanoTime();
			}
		}
	}

	public void render(Graphics g) {
		if (health > 0) {
			g.setColor(Color.red);
			// g.fillRect(x, y, 30, 30);
			g.drawImage(load_image.player, x, y, 60, 60, null);
		}
	}

	public void keyPress(KeyEvent e) {
		int source = e.getKeyCode();
	}

	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();
		if (source == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (source == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (source == KeyEvent.VK_DOWN) {
			down = true;
		}
		if (source == KeyEvent.VK_UP) {
			up = true;
		}
		if (source == KeyEvent.VK_Z) {
			fire = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int source = e.getKeyCode();

		if (source == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (source == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (source == KeyEvent.VK_DOWN) {
			down = false;
		}
		if (source == KeyEvent.VK_UP) {
			up = false;
		}
		if (source == KeyEvent.VK_Z) {
			fire = false;
		}

	}

	public void keyTyped(KeyEvent e) {

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int h) {
		health = h;
	}
}
