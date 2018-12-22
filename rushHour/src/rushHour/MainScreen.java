package rushHour;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainScreen implements ActionListener {

	// RushHour game;

	JButton getHelpBtn;
	JPanel panel2;
	JButton playBtn;
	JButton settingsBtn;
	JButton credits;
	JButton resumeBtn;
	BufferedImage logoGame;
	JLabel logoLabel;
	JFrame f3;
	FileReader reader;

	public MainScreen() throws IOException {
		// initiliaze
		getHelpBtn = new JButton();
		// game = new RushHour();
		panel2 = new JPanel();
		playBtn = new JButton();
		settingsBtn = new JButton();
		credits = new JButton();
		resumeBtn = new JButton();
		logoGame = ImageIO.read(new File("Logo11.png"));
		logoLabel = new JLabel(new ImageIcon(logoGame));
		f3 = new JFrame("Rush Hour");
		// main panel
		panel2.setLayout(null);

		resumeBtn.setVisible(true);
		resumeBtn.setBounds(275, 150, 150, 50);
		resumeBtn.setForeground(Color.red);
		resumeBtn.setBackground(Color.ORANGE);
		resumeBtn.addActionListener(this);

		playBtn.setVisible(true);
		playBtn.setBounds(275, 250, 150, 50);
		playBtn.setForeground(Color.red);
		playBtn.setBackground(Color.ORANGE);
		playBtn.addActionListener(this);

		settingsBtn.setVisible(true);
		settingsBtn.setBounds(275, 350, 150, 50);
		settingsBtn.setForeground(Color.red);
		settingsBtn.setBackground(Color.ORANGE);
		settingsBtn.addActionListener(this);

		getHelpBtn.setVisible(true);
		getHelpBtn.setBounds(275, 450, 150, 50);
		getHelpBtn.setForeground(Color.red);
		getHelpBtn.setBackground(Color.ORANGE);
		getHelpBtn.addActionListener(this);

		credits.setVisible(true);
		credits.setBounds(275, 550, 150, 50);
		credits.setForeground(Color.red);
		credits.setBackground(Color.ORANGE);
		credits.addActionListener(this);

		// logo
		logoLabel.setBounds(50, 5, 600, 200);

		f3.setBackground(Color.MAGENTA);
		f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f3.setBounds(900, 800, 900, 800);
		// f3.add(game);
		f3.setResizable(false);

		f3.add(panel2);
		f3.setLocationRelativeTo(null);
		f3.setSize(700, 700);
		f3.setVisible(true);

		resumeBtn.setText("Resume");
		resumeBtn.setFont(new Font("Serif", Font.ITALIC, 25));

		playBtn.setText("Play");
		playBtn.setFont(new Font("Serif", Font.ITALIC, 25));

		settingsBtn.setText("Settings");
		settingsBtn.setFont(new Font("Serif", Font.ITALIC, 25));

		getHelpBtn.setText("Get Help");
		getHelpBtn.setFont(new Font("Serif", Font.ITALIC, 25));

		credits.setText("Credits");
		credits.setFont(new Font("Serif", Font.ITALIC, 25));

		panel2.setBackground(Color.white);
		panel2.add(resumeBtn);
		panel2.add(playBtn);
		panel2.add(settingsBtn);
		panel2.add(getHelpBtn);
		panel2.add(credits);
		panel2.add(logoLabel);
		f3.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == getHelpBtn) {
			try {
				f3.setVisible(false);
				new GetHelpScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (evt.getSource() == credits) {
			System.out.println("here");
			try {
				new Credits();
				f3.setVisible(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (evt.getSource() == playBtn) {
			try {
				f3.setVisible(false);
				new GameModeSelectionScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (evt.getSource() == settingsBtn) {
			try {

				new Settings();
				f3.setVisible(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (evt.getSource() == resumeBtn) {
			try {
				Scanner scanner = new Scanner(new File("resume.txt"));
				int levelNumber;
				levelNumber = Integer.valueOf(scanner.nextLine());
				System.out.println("levelNUMber:" + levelNumber);
				new DisplayLevelScreen(levelNumber);
				scanner.close();
				f3.setVisible(false);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
