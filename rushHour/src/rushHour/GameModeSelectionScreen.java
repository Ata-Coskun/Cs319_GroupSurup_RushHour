package rushHour;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameModeSelectionScreen extends JPanel implements MouseListener, MouseMotionListener,ActionListener 
{
	
	MouseListener ml;
	MouseMotionListener mml;

	static int xDragged =0,yDragged=0;
	static int xClicked=0, yClicked=0;
	
	JButton singleMode;
	JButton multiMode;
	JFrame f ;
	
	public GameModeSelectionScreen() throws IOException
	{
		//initiliaze
		
		 f = new JFrame("Game Mode");
		 multiMode = new JButton();
		 singleMode = new JButton();
			//main panel
		 singleMode.setVisible(true);
		 singleMode.setBounds(350, 200, 300, 100);
		 singleMode.setForeground(Color.red);
		 singleMode.setBackground(Color.ORANGE);
		 singleMode.addActionListener(this);
			
		 multiMode.setVisible(true);
		 multiMode.setBounds(350, 350, 300, 100);
		 multiMode.setForeground(Color.red);
		 multiMode.setBackground(Color.ORANGE);
		 multiMode.addActionListener(this);
			
			
		 multiMode.setText("Multi-Player Mode");
		 multiMode.setFont(new Font("Serif", Font.ITALIC,25));
		 singleMode.setText("Single-Player Mode");
		 singleMode.setFont(new Font("Serif", Font.ITALIC,25));
		 
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null);
			BufferedImage img = ImageIO.read(new File("background.png"));
			f.setContentPane(new JLabel(new ImageIcon(img)));
			GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        f.add(singleMode, gbc);
	        f.add(multiMode, gbc);
			
			//adding to the frame
			f.setSize(1000,1000);
			f.setVisible(true);
	        f.setLocationRelativeTo(null);

		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == singleMode)
		{
			try {
				f.setVisible(false);
				DisplayLevelScreen levels = new DisplayLevelScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(evt.getSource() == multiMode)
		{
			f.setVisible(false);
			MultiGameScreen multi = new MultiGameScreen(new MultiGameEngine());
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
