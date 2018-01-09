package Display;

import java.awt.Color;
import java.awt.Graphics;

public class bullet {
private int x;
private int y;
private int speed;
	public bullet(int x,int y){
		this.x=x;
		this.y=y;
	speed=10;
	}
	public void tick(){
		y-=speed;
	}
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(x, y,6,12);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
