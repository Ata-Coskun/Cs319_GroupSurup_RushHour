/*
*	Author : Ata Coþkun, Zeynep Nur Öztürk, Asuman Aydýn
*/

package rushHour;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This is the screen that comes when player chooses play. Player chooses the game mode from here. Multi or single
 */
public class GameModeSelectionScreen extends JPanel implements ActionListener {

	JButton singleMode;
	JButton multiMode;
	JButton returnMain;
	JFrame f;

	public GameModeSelectionScreen() throws IOException {
		// Frame of this scene
		f = new JFrame("Game Mode");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));
		f.setSize(700, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		// Initiazing buttons
		singleMode = new JButton();
		singleMode.setVisible(true);
		singleMode.setBounds(225, 100, 250, 100);
		singleMode.setForeground(Color.red);
		singleMode.setBackground(Color.ORANGE);
		singleMode.addActionListener(this);
		singleMode.setText("Single-Player Mode");
		singleMode.setFont(new Font("Serif", Font.ITALIC, 25));
		f.add(singleMode, gbc);

		multiMode = new JButton();
		multiMode.setVisible(true);
		multiMode.setBounds(225, 250, 250, 100);
		multiMode.setForeground(Color.red);
		multiMode.setBackground(Color.ORANGE);
		multiMode.addActionListener(this);
		multiMode.setText("Multi-Player Mode");
		multiMode.setFont(new Font("Serif", Font.ITALIC, 25));
		f.add(multiMode, gbc);

		returnMain = new JButton();
		returnMain.setVisible(true);
		returnMain.setBounds(225, 400, 250, 100);
		returnMain.setForeground(Color.red);
		returnMain.setBackground(Color.ORANGE);
		returnMain.addActionListener(this);
		returnMain.setText("Return");
		returnMain.setFont(new Font("Serif", Font.ITALIC, 25));
		f.add(returnMain, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		f.setVisible(false);
		if (evt.getSource() == singleMode)
			try {
				new DisplayLevelScreen(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (evt.getSource() == multiMode)
			try {
				new DisplayLevelScreen(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (evt.getSource() == returnMain)
			try {
				new MainScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
