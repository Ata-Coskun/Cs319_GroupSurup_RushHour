package rushHour;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class RushHour extends JPanel implements MouseListener, MouseMotionListener {
	MouseListener ml;
	MouseMotionListener mml;

	static int xDragged =0,yDragged=0;
	static int xClicked=0, yClicked=0;
	 

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.RED);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		xClicked = e.getX()/75;
	    yClicked = e.getY()/75;
	    repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e){
		
	}
	@Override
	public void mouseReleased(MouseEvent e){
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e){
		xDragged = e.getX()/75;
		yDragged = e.getY()/75;
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}

}
