package MAIN;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class load_image {

	public static BufferedImage image;
	public static BufferedImage entity;
	public static BufferedImage player;
	public static BufferedImage enemy;
	
	public static void initialize(){
		image=imageLoader("/INP.jpg");
		player=imageLoader("/PLAYER.jpg");
		enemy=imageLoader("/ENEMY.jpg");
	}

	public static BufferedImage imageLoader(String path){
		try {
			return ImageIO.read(load_image.class.getResource(path));
		} catch (IOException e) {
			System.exit(1);
		}
		return null;
	}
//public static void crop(){
//	player=entity.getSubimage(x, y, w, h)
//}
}
