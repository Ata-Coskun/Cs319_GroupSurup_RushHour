package rushHour;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GetHelpScreen extends JPanel implements MouseListener, MouseMotionListener {
	
	MouseListener ml;
	MouseMotionListener mml;
	static int xDragged =0,yDragged=0;
	static int xClicked=0, yClicked=0;

	 public GetHelpScreen() throws IOException
	 {
			//help frame
			JFrame f2 = new JFrame("How to Play");
			JPanel panelX = new JPanel();
			f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setBounds(800, 700, 700, 700);
			f2.setVisible(true);
			f2.setSize(1000, 1000);
			 BufferedImage logoGame = ImageIO.read(new File("help.png"));
			 JLabel logoLabel= new JLabel(new ImageIcon(logoGame));
			 logoLabel.setBounds(900, 800, 900, 800);
			 panelX.add(logoLabel);
			 f2.add(panelX);
			f2.setLocationRelativeTo(null);
			
	 }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.orange);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		repaint();
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