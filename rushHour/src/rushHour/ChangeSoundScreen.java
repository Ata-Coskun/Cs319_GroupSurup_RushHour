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

public class ChangeSoundScreen extends JPanel implements MouseListener, MouseMotionListener,ActionListener {
	MouseListener ml;
	MouseMotionListener mml;

	static int xDragged =0,yDragged=0;
	static int xClicked=0, yClicked=0;
	JButton btn;
	JButton btn2;
	JFrame f;
	int theme;
	public ChangeSoundScreen() throws IOException
	{
	
		JPanel panel = new JPanel() ;
		panel.setLayout(null);
		btn = new JButton();
		btn.addActionListener(this);
		btn.setBackground(Color.WHITE);
		btn.setBounds(100, 100, 200, 200);
		btn2 = new JButton();
		btn2.setBackground(Color.WHITE);
		btn2.addActionListener(this);
		btn2.setBounds(100,500,200,200);
		//level1 
		btn.setIcon(new ImageIcon("mute.png"));
		//level2
		btn2.setIcon(new ImageIcon("unmute.png"));
		//texts
		
		JLabel label1 = new JLabel("Mute " );
		label1.setBounds(500, 150, 200, 200);
		label1.setFont(new Font("Serif", Font.PLAIN,25));
		JLabel label2 = new JLabel("Unmute ");
		label2.setBounds(500, 500, 300, 300);
		label2.setFont(new Font("Serif", Font.PLAIN,25));

		

		//frame
		 f = new JFrame("Change Sound");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("white.png"));
		 f.setContentPane(new JLabel(new ImageIcon(img)));
		 GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        f.add(label1, gbc);
        f.add(label2, gbc);
        f.add(btn, gbc);
        f.add(btn2,gbc);
		
		//adding to the frame
		f.setSize(1000,1000);
		f.setVisible(true);
        f.setLocationRelativeTo(null);

		//LevelSelection selection = new LevelSelection(1);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn ) {
			f.setVisible(false);
			try {
				MainScreen mainScreen = new MainScreen(theme, true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btn2) {
			f.setVisible(false);
			try {
				MainScreen mainScreen = new MainScreen(theme, false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
