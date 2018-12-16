package rushHour;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayLevelScreen extends JPanel implements ActionListener {

	JButton btn;
	JButton btn2;
	JFrame f;
	public DisplayLevelScreen() throws IOException
	{
		JPanel panel = new JPanel() ;
		panel.setLayout(null);
		btn = new JButton();
		btn.addActionListener(this);
		btn.setBounds(100, 100, 300, 300);
		btn2 = new JButton();
		btn2.addActionListener(this);
		btn2.setBounds(100,500,300,300);
		//level1 
		btn.setIcon(new ImageIcon("level1.png"));
		//level2
		btn2.setIcon(new ImageIcon("level2.png"));
		//texts

				JLabel label1 = new JLabel("Level 1 Score: 100 " );
				label1.setBounds(500, 150, 300, 300);
				label1.setFont(new Font("Serif", Font.PLAIN,25));
				JLabel label2 = new JLabel("Level 2 Score:  ");
				label2.setBounds(500, 500, 300, 300);
				label2.setFont(new Font("Serif", Font.PLAIN,25));
		//score star image example
		BufferedImage toScore = null;
		try {
			toScore = ImageIO.read(new File("star.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel labelMe= new JLabel(new ImageIcon(toScore));
		labelMe.setBounds(500, 180, 100, 100);
		

		//frame
		 f = new JFrame("Select Levels");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("background.png"));
		 f.setContentPane(new JLabel(new ImageIcon(img)));
		 GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        f.add(label1, gbc);
        f.add(label2, gbc);
        f.add(labelMe, gbc);
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
			try {
				SingleLevelSelection selection = new SingleLevelSelection(1);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			f.setVisible(false);
		}
		if(e.getSource() == btn2) {
			try {
				SingleLevelSelection selection = new SingleLevelSelection(2);
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
		
	}

		
	}

