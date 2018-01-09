package Display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class display {

	private String title;
	private int width;
	private int height;
	public static JFrame frame;
	public static Canvas canvas;
	
	
	public display(String title,int width,int height){
		this.width=width;
		this.height=height;
		this.title=title;
		createDisplay();
	}
	
	public void createDisplay(){

		frame =new JFrame(title);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(null);
		canvas=new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
	    canvas.setBackground(Color.CYAN);
		canvas.setFocusable(false);
	    
	    frame.add(canvas);
	    frame.pack();
	}
	
	public Canvas getCanvas(){
		return canvas; 
	}
}
