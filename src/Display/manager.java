package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class manager implements KeyListener {

	private player player;
	public static ArrayList<bullet> bullet;
	private ArrayList<enemy> enemies;
	private long current, delay,time=0,var=0;
	private int health;
	private int score;
	private boolean start;

	public manager() {

	}

	public void initialize() {
		display.frame.addKeyListener(this);
		player = new player(setup.gamewidth / 2 + 50 - 30, setup.gameheight - 30 + 50 - 30);
		player.initialize();
		bullet = new ArrayList<bullet>();
		enemies = new ArrayList<enemy>();
		current = System.nanoTime();
		delay = 1000;
		health = player.getHealth();
		score = 0;
		time=20;
	}

	public void tick() {
		if (start) {
			player.tick();
			for (int i = 0; i < bullet.size(); i++)
				bullet.get(i).tick();

			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).tick();
			}
			long breaks = (System.nanoTime() - current) / 1000000;
			if (breaks > delay) {
				for (int i = 0; i < 2; i++) {
					Random rand = new Random();
					int randx = rand.nextInt(450);
					int randy = rand.nextInt(450);
					if (health > 0)
						enemies.add(new enemy(randx, -randy));
				}
				current = System.nanoTime();
			}
		}
	}

	public void render(Graphics g) {
		if (start) {
			player.render(g);
			for (int i = 0; i < bullet.size(); i++)
				bullet.get(i).render(g);
			for (int i = 0; i < bullet.size(); i++) {
				if (bullet.get(i).getY() <= 50) {
					bullet.remove(i);
					i--;
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				if (!((enemies.get(i).getx() <= 50) || (enemies.get(i).getx() >= 400) || (enemies.get(i).gety() >= 425)
						|| (enemies.get(i).gety() <= 50)))
					enemies.get(i).render(g);
			}

			for (int i = 0; i < enemies.size(); i++) {
				int ex = enemies.get(i).getx();
				int ey = enemies.get(i).gety();
				if (ey > 390) {
					enemies.remove(i);
					i++;
				}
				for (int j = 0; j < bullet.size(); j++) {

					int bx = bullet.get(j).getX();
					int by = bullet.get(j).getY();

					if ((ex < bx + 6) && (ex + 50 > bx) && (ey < by + 15) && (ey + 50 > by) && (ey > 50)) {
						enemies.remove(i);
						i--;
						bullet.remove(j);
						j--;
						score += 5;
					}
				}
			}

			int px = player.getX();
			int py = player.getY();

			for (int i = 0; i < enemies.size(); i++) {
				int ex = enemies.get(i).getx();
				int ey = enemies.get(i).gety();

				if ((ex < px + 60) && (ex + 50 > px) && (ey < py + 60) && (ey + 50 > py)) {
					enemies.remove(i);
					i--;
					health--;
					System.out.println(health);
					if (health <= 0) {
						player.setHealth(health);
						enemies.removeAll(enemies);
						start = false;
					}
				}
			}
			g.setColor(Color.blue);
			g.setFont(new Font("arial", Font.BOLD, 40));
			g.drawString("SCORE :- " + score, 10, 520);
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.PLAIN, 30));
			g.drawString("HEALTH :- " + health, 300, 520);
			g.setColor(Color.black);
//			var++;
//			time--;
//			if(var==500)
//				time=time-1;
//			g.drawString("TIME :- " + time, 10, 560);
//if(time<=0)
//	start=false;
		}
		if (start == false) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial", Font.PLAIN, 33));
			g.drawString("press enter to start", 100, setup.gameheight / 2 + 50);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();
		if (source == KeyEvent.VK_ENTER) {
			start = true;
			initialize();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
