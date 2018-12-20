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

public class ChangeThemeScreen extends JPanel implements ActionListener {

	JButton theme1;
	JButton theme2;
	JFrame f2;
	public ChangeThemeScreen() throws IOException
	{
		f2 = new JFrame("ChangeTheme");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		theme1 = new JButton();
		theme1.addActionListener(this);
		theme1.setBounds(250, 100, 200, 200);
		theme2 = new JButton();
		theme2.addActionListener(this);
		theme2.setBounds(250, 400, 200, 200);
		theme1.setIcon(new ImageIcon("sing.png"));
		// level2
		theme2.setIcon(new ImageIcon("single.png"));
		f2 = new JFrame("Select Levels");
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("background.png"));
		f2.setContentPane(new JLabel(new ImageIcon(img)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		f2.add(theme1, gbc);
		f2.add(theme2, gbc);

		// adding to the frame
		f2.setSize(700, 700);
		f2.setVisible(true);
		f2.setLocationRelativeTo(null);
		/*JPanel panel = new JPanel() ;
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

				JLabel label1 = new JLabel("Theme City " );
				label1.setBounds(500, 150, 300, 300);
				label1.setFont(new Font("Serif", Font.PLAIN,25));
				JLabel label2 = new JLabel("Theme Space ");
				label2.setBounds(500, 500, 300, 300);
				label2.setFont(new Font("Serif", Font.PLAIN,25));

		

		//frame
		 f = new JFrame("Select Theme");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("background.png"));
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
        f.setLocationRelativeTo(null);*/

		//LevelSelection selection = new LevelSelection(1);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == theme1 ) {
			f2.setVisible(false);
			try {
				MainScreen mainScreen = new MainScreen(1,false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == theme2) {
			f2.setVisible(false);
			try {
				MainScreen mainScreen = new MainScreen(2,false);
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
