/*
*	Author : Ata Coþkun, Asuman Aydýn, Zeynep Nur Öztürk
*/

package rushHour;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * The screen that you see when you've entered the game. Main Menu
 */
public class MainScreen extends JPanel implements ActionListener {

	// Buttons of each option
	JButton getHelpBtn;
	JButton playBtn;
	JButton settingsBtn;
	JButton creditsBtn;
	JButton resumeBtn;
	JFrame f;

	public MainScreen() throws IOException {
		// main panel
		setLayout(null);

		// Frame of game
		f = new JFrame("Rush Hour");
		f.setBackground(Color.MAGENTA);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(900, 800, 900, 800);
		f.setResizable(false);
		f.add(this);
		f.setLocationRelativeTo(null);
		f.setSize(700, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

		// Photo that says rush hour
		JLabel logoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Logo11.png"))));
		logoLabel.setBounds(50, 5, 600, 200);
		add(logoLabel);

		// Initializing the buttons
		resumeBtn = new JButton();
		resumeBtn.setVisible(true);
		resumeBtn.setBounds(275, 150, 150, 50);
		resumeBtn.setForeground(Color.red);
		resumeBtn.setBackground(Color.ORANGE);
		resumeBtn.addActionListener(this);
		resumeBtn.setText("Resume");
		resumeBtn.setFont(new Font("Serif", Font.ITALIC, 25));
		add(resumeBtn);

		playBtn = new JButton();
		playBtn.setVisible(true);
		playBtn.setBounds(275, 250, 150, 50);
		playBtn.setForeground(Color.red);
		playBtn.setBackground(Color.ORANGE);
		playBtn.addActionListener(this);
		playBtn.setText("Play");
		playBtn.setFont(new Font("Serif", Font.ITALIC, 25));
		add(playBtn);

		settingsBtn = new JButton();
		settingsBtn.setVisible(true);
		settingsBtn.setBounds(275, 350, 150, 50);
		settingsBtn.setForeground(Color.red);
		settingsBtn.setBackground(Color.ORANGE);
		settingsBtn.addActionListener(this);
		settingsBtn.setText("Settings");
		settingsBtn.setFont(new Font("Serif", Font.ITALIC, 25));
		add(settingsBtn);

		getHelpBtn = new JButton();
		getHelpBtn.setVisible(true);
		getHelpBtn.setBounds(275, 450, 150, 50);
		getHelpBtn.setForeground(Color.red);
		getHelpBtn.setBackground(Color.ORANGE);
		getHelpBtn.addActionListener(this);
		getHelpBtn.setText("Get Help");
		getHelpBtn.setFont(new Font("Serif", Font.ITALIC, 25));
		add(getHelpBtn);

		creditsBtn = new JButton();
		creditsBtn.setVisible(true);
		creditsBtn.setBounds(275, 550, 150, 50);
		creditsBtn.setForeground(Color.red);
		creditsBtn.setBackground(Color.ORANGE);
		creditsBtn.addActionListener(this);
		creditsBtn.setText("Credits");
		creditsBtn.setFont(new Font("Serif", Font.ITALIC, 25));
		add(creditsBtn);

		setBackground(Color.white);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// Choosing help option
		if (evt.getSource() == getHelpBtn) {
			try {
				f.setVisible(false);
				new GetHelpScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Choosing credits option
		else if (evt.getSource() == creditsBtn) {
			System.out.println("here");
			try {
				new Credits();
				f.setVisible(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Choosing play option
		else if (evt.getSource() == playBtn) {
			try {
				f.setVisible(false);
				new GameModeSelectionScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Choosing settings option
		else if (evt.getSource() == settingsBtn) {
			try {

				new Settings();
				f.setVisible(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Choosing resume option
		else if (evt.getSource() == resumeBtn) {
			try {
				Scanner scanner = new Scanner(new File("resume.txt"));
				int levelNumber;
				levelNumber = Integer.valueOf(scanner.nextLine());
				new DisplayLevelScreen(levelNumber);
				scanner.close();
				f.setVisible(false);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
