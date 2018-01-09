package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import MAIN.*;

public class setup implements Runnable {

	private String title;
	private int width;
	private int height;
	private Thread thread;
	private boolean running;
	private display display;
	private BufferStrategy buffer;
	private Graphics g;
	private int y = 0;
	private manager manage;
	public static final int gamewidth = 400;
	public static final int gameheight = 400;

	public setup(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	public void initialize() {
		display = new display(title, width, height);
		load_image.initialize();
		//change it if u want rectangle
		manage = new manager();
		// gamewidth = 400;
		// gameheight = 400;
		manage.initialize();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public synchronized void stop() {
		if (running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		manage.tick();
		y += 1;
	}

	public void render() {
		buffer = display.getCanvas().getBufferStrategy();
		if (buffer == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		// draw_here

		g.setColor(Color.white);
		//g.drawImage(load_image.image, 50, 50, gamewidth, gameheight, null);
		 g.fillRect(50, 50, gamewidth, gameheight);
		manage.render(g);
		// end draw
		buffer.show();
		g.dispose();
	}

	public void run() {
		initialize();

		int fps = 100;
		double tpt = 1000000000 / fps;
		double delta = 0;
		long current = System.nanoTime();

		while (running) {
			delta = delta + (System.nanoTime() - current) / tpt;
			current = System.nanoTime();
			if (delta >= 1) {

				render();
				tick();
				delta--;
			}
		}
	}

}
