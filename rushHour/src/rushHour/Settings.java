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

public class Settings extends JPanel implements ActionListener {

	JButton changeTheme;
	JButton changeSoundSettings;
	JFrame f, f2;
	JButton theme1, theme2;

	public Settings() throws IOException {
		// initiliaze

		f = new JFrame("Settings");
		changeSoundSettings = new JButton();
		changeTheme = new JButton();
		// main panel
		changeTheme.setVisible(true);
		changeTheme.setBounds(200, 200, 300, 100);
		changeTheme.setForeground(Color.red);
		changeTheme.setBackground(Color.ORANGE);
		changeTheme.addActionListener(this);

		changeSoundSettings.setVisible(true);
		changeSoundSettings.setBounds(200, 300, 300, 100);
		changeSoundSettings.setForeground(Color.red);
		changeSoundSettings.setBackground(Color.ORANGE);
		changeSoundSettings.addActionListener(this);

		changeSoundSettings.setText("Sound Settings");
		changeSoundSettings.setFont(new Font("Serif", Font.ITALIC, 25));
		changeTheme.setText("Theme settings");
		changeTheme.setFont(new Font("Serif", Font.ITALIC, 25));

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("background.png"));
		f.setContentPane(new JLabel(new ImageIcon(img)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		f.add(changeTheme, gbc);
		f.add(changeSoundSettings, gbc);

		// adding to the frame
		f.setSize(700, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == changeTheme) {
			try {
				f.setVisible(false);
				ChangeThemeScreen themeScreen = new ChangeThemeScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (evt.getSource() == changeSoundSettings) {
			try {
				// change here
				DisplayLevelScreen level = new DisplayLevelScreen();
				f.setVisible(false); // you can't see me!
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
	}

}
