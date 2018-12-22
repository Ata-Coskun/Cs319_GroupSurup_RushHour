package rushHour;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.PrintWriter;
import java.io.File;

public class Settings extends JPanel implements ActionListener {

	JButton okButton;
	JButton muteButton;
	JFrame f, f2;
	JButton theme1, theme2,theme3;
	PrintWriter settingsWriter;
	File settingsFile;
	int theme;
	int mute;
	public Settings() throws IOException {
		// initiliaze

		f = new JFrame("Settings");
		theme1 = new JButton();
		theme2 = new JButton();
		theme3 = new JButton();
		muteButton = new JButton();
		okButton= new JButton();
		// main panel
		theme1.setVisible(true);
		theme1.setBounds(200, 200, 300, 100);
		theme1.setForeground(Color.red);
		theme1.setBackground(Color.ORANGE);
		theme1.addActionListener(this);

		theme2.setVisible(true);
		theme2.setBounds(200, 300, 300, 100);
		theme2.setForeground(Color.red);
		theme2.setBackground(Color.ORANGE);
		theme2.addActionListener(this);
		
		theme3.setVisible(true);
		theme3.setBounds(200, 400, 400, 100);
		theme3.setForeground(Color.red);
		theme3.setBackground(Color.ORANGE);
		theme3.addActionListener(this);
		
		muteButton.setVisible(true);
		muteButton.setBounds(200, 500, 300, 100);
		muteButton.setForeground(Color.red);
		muteButton.setBackground(Color.ORANGE);
		muteButton.addActionListener(this);
		
		okButton.setVisible(true);
		okButton.setBounds(200, 600, 300, 100);
		okButton.setForeground(Color.red);
		okButton.setBackground(Color.ORANGE);
		okButton.addActionListener(this);


		theme1.setText("Theme 1");
		theme1.setFont(new Font("Serif", Font.ITALIC, 25));
		theme2.setText("Theme 2");
		theme2.setFont(new Font("Serif", Font.ITALIC, 25));
		theme3.setText("Theme 3");
		theme3.setFont(new Font("Serif", Font.ITALIC, 25));
		
		muteButton.setText("Mute ");
		muteButton.setFont(new Font("Serif", Font.ITALIC, 25));
		
		okButton.setText("OK");
		okButton.setFont(new Font("Serif", Font.ITALIC, 25));


		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("background.png"));
		f.setContentPane(new JLabel(new ImageIcon(img)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		f.add(theme1, gbc);
		f.add(theme2, gbc);
		f.add(theme3, gbc);
		f.add(muteButton, gbc);
		f.add(okButton, gbc);
		settingsFile = new File("settings.txt");

		// adding to the frame
		f.setSize(700, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		mute = 0;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == okButton) {
			try {
				f.setVisible(false);
				settingsWriter = new PrintWriter(settingsFile);
				System.out.println("yazılan data: " + theme+","+mute+",");
				settingsWriter.println(theme+","+mute+",");
				settingsWriter.close();
				new MainScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (evt.getSource() == theme1) {
			theme = 1;
		}
		else if (evt.getSource() == theme2) {
			theme = 2;
		}
		else if (evt.getSource() == theme3) {
			theme = 3;
		}
		else if (evt.getSource() == muteButton) {
			if(mute == 0)
				mute = 1;
			else 
				mute = 0;
		}
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.pink);
	}

}
