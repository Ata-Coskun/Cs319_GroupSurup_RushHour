package rushHour;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class RushHour{
	
	MainScreen screen;
	
	public RushHour() throws IOException {
		screen = new MainScreen(1,false);
	}

public static void main(String[] args) throws IOException {
		
	 RushHour rushHour = new RushHour();		
	}
}