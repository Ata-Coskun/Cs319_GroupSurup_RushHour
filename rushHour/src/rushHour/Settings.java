package rushHour;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Settings extends JPanel implements MouseListener, MouseMotionListener,ActionListener  {
	
	MouseListener ml;
	MouseMotionListener mml;

	static int xDragged =0,yDragged=0;
	static int xClicked=0, yClicked=0;
	
	JButton changeTheme;
	JButton changeSoundSettings;
	JFrame f ;
	
	public Settings() throws IOException
	{
		//initiliaze
		
		 f = new JFrame("Settings");
		 changeSoundSettings = new JButton();
		 changeTheme = new JButton();
			//main panel
		 changeTheme.setVisible(true);
		 changeTheme.setBounds(350, 200, 300, 100);
		 changeTheme.setForeground(Color.red);
		 changeTheme.setBackground(Color.ORANGE);
		 changeTheme.addActionListener(this);
			
		 changeSoundSettings.setVisible(true);
		 changeSoundSettings.setBounds(350, 350, 300, 100);
		 changeSoundSettings.setForeground(Color.red);
		 changeSoundSettings.setBackground(Color.ORANGE);
		 changeSoundSettings.addActionListener(this);
			
			
		 changeSoundSettings.setText("Sound Settings");
		 changeSoundSettings.setFont(new Font("Serif", Font.ITALIC,25));
		 changeTheme.setText("Theme settings");
		 changeTheme.setFont(new Font("Serif", Font.ITALIC,25));
		 
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null);
			BufferedImage img = ImageIO.read(new File("background.png"));
			f.setContentPane(new JLabel(new ImageIcon(img)));
			GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        f.add(changeTheme, gbc);
	        f.add(changeSoundSettings, gbc);
			
			//adding to the frame
			f.setSize(1000,1000);
			f.setVisible(true);
	        f.setLocationRelativeTo(null);

		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == changeTheme)
		{
			
		}
		if(evt.getSource() == changeSoundSettings)
		{
			try {
				
				DisplayLevel level =  new DisplayLevel();
				f.setVisible(false); //you can't see me!
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.pink);
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
