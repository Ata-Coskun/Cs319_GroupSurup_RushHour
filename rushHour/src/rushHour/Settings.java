/*
*	Author : Ata Coþkun, Zeynep Nur Öztürk, Asuman Aydýn
*/

package rushHour;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This is the screen that comes when player chooses get help. Player can choose the initial theme and mute setting from here
 */
public class Settings extends JPanel implements ActionListener {

	JButton okButton;
	JButton muteButton;
	JButton theme1, theme2, theme3;
	JFrame f;
	int theme;
	int mute;

	public Settings() throws IOException {
		// initiliazing the data
		theme = 1;
		mute = 0;
		
		// Frame of this scene
		f = new JFrame("Settings");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));
		f.setSize(700, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		// Initiazing buttons
		theme1 = new JButton();
		theme1.setVisible(true);
		theme1.setBounds(225, 20, 150, 150);
		theme1.setIcon(new ImageIcon(
				new ImageIcon("level1.png").getImage().getScaledInstance(160, 150, Image.SCALE_DEFAULT)));
		theme1.addActionListener(this);
		f.add(theme1, gbc);


		JLabel th1 = new JLabel();
		th1.setText("Theme 1");
		th1.setBounds(390, 50, 300, 100);
		th1.setFont(new Font("Serif", Font.PLAIN, 25));
		f.add(th1, gbc);
		
		theme2 = new JButton();
		theme2.setVisible(true);
		theme2.setBounds(225, 170, 150, 150);
		theme2.setIcon(new ImageIcon(
				new ImageIcon("uzaylý.png").getImage().getScaledInstance(160, 150, Image.SCALE_DEFAULT)));
		theme2.addActionListener(this);
		f.add(theme2, gbc);

		JLabel th2 = new JLabel();
		th2.setText("Theme 2");
		th2.setBounds(390, 190, 300, 100);
		th2.setFont(new Font("Serif", Font.PLAIN, 25));
		f.add(th2, gbc);
		
		theme3 = new JButton();
		theme3.setVisible(true);
		theme3.setBounds(225, 320, 150, 150);
		theme3.setBackground(Color.yellow);
		theme3.setIcon(new ImageIcon(
				new ImageIcon("ques.png").getImage().getScaledInstance(160, 150, Image.SCALE_DEFAULT)));
		theme3.addActionListener(this);
		f.add(theme3, gbc);

		JLabel th3 = new JLabel();
		th3.setText("Theme 3");
		th3.setBounds(390, 330, 300, 100);
		th3.setFont(new Font("Serif", Font.PLAIN, 25));
		f.add(th3, gbc);
		
		muteButton = new JButton();
		muteButton.setVisible(true);
		muteButton.setBounds(225, 470, 150, 50);
		muteButton.setBackground(Color.yellow);
		if(mute == 1)
			muteButton.setIcon(new ImageIcon(
					new ImageIcon("muted.png").getImage().getScaledInstance(70, 50, Image.SCALE_DEFAULT)));
		else 
			muteButton.setIcon(new ImageIcon(
					new ImageIcon("mute.png").getImage().getScaledInstance(70, 50, Image.SCALE_DEFAULT)));
		
		muteButton.addActionListener(this);
		f.add(muteButton, gbc);

		
		okButton = new JButton();
		okButton.setVisible(true);
		okButton.setBounds(225, 520, 150, 50);
		okButton.setForeground(Color.red);
		okButton.setBackground(Color.ORANGE);
		okButton.addActionListener(this);
		okButton.setText("OK");
		okButton.setFont(new Font("Serif", Font.ITALIC, 25));
		f.add(okButton, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// return button
		if (evt.getSource() == okButton)
			try {
				f.setVisible(false);
				PrintWriter settingsWriter = new PrintWriter(new File("settings.txt"));
				settingsWriter.println(theme + "," + mute + ",");
				settingsWriter.close();
				new MainScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (evt.getSource() == theme1)
			theme = 1;
		else if (evt.getSource() == theme2)
			theme = 2;
		else if (evt.getSource() == theme3)
			theme = 3;
		else if (evt.getSource() == muteButton) {
			mute = (mute + 1) % 2;
			if(mute == 1)
				muteButton.setIcon(new ImageIcon(
						new ImageIcon("muted.png").getImage().getScaledInstance(70, 50, Image.SCALE_DEFAULT)));
			else 
				muteButton.setIcon(new ImageIcon(
						new ImageIcon("mute.png").getImage().getScaledInstance(70, 50, Image.SCALE_DEFAULT)));
				
		}
	}

}
