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
import javax.swing.JTextArea;

public class Credits extends JPanel implements MouseListener, MouseMotionListener,ActionListener {
	MouseListener ml;
	MouseMotionListener mml;

	static int xDragged =0,yDragged=0;
	static int xClicked=0, yClicked=0;
	
	JFrame f;
	public Credits() throws IOException
	{
	
		JTextArea label1 = new JTextArea("Thanks to\n\tMuhammed Said Demir \nAta Coşkun \nZeynep Nur Öztürk \nAsuman Aydın \nTarık Emin Kaplan" );
		label1.setBounds(500, 150, 200, 200);
		label1.setFont(new Font("Serif", Font.PLAIN,25));
		label1.setForeground(Color.YELLOW);

		//frame
		 f = new JFrame("Thanks to");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("stars.png"));
		 f.setContentPane(new JLabel(new ImageIcon(img)));
		 GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        f.add(label1, gbc);
		//adding to the frame
		f.setSize(1000,1000);
		f.setVisible(true);
        f.setLocationRelativeTo(null);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
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
