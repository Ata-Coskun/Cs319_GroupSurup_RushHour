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

public class GameModeSelectionScreen extends JPanel implements ActionListener {
	JButton singleMode;
	JButton multiMode;
	JFrame f;

	public GameModeSelectionScreen() throws IOException {
		// initiliaze

		f = new JFrame("Game Mode");
		multiMode = new JButton();
		singleMode = new JButton();
		// main panel
		singleMode.setVisible(true);
		singleMode.setBounds(225, 200, 250, 100);
		singleMode.setForeground(Color.red);
		singleMode.setBackground(Color.ORANGE);
		singleMode.addActionListener(this);

		multiMode.setVisible(true);
		multiMode.setBounds(225, 350, 250, 100);
		multiMode.setForeground(Color.red);
		multiMode.setBackground(Color.ORANGE);
		multiMode.addActionListener(this);

		multiMode.setText("Multi-Player Mode");
		multiMode.setFont(new Font("Serif", Font.ITALIC, 25));
		singleMode.setText("Single-Player Mode");
		singleMode.setFont(new Font("Serif", Font.ITALIC, 25));

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("background.png"));
		f.setContentPane(new JLabel(new ImageIcon(img)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		f.add(singleMode, gbc);
		f.add(multiMode, gbc);

		// adding to the frame
		f.setSize(700, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		f.setVisible(false);
		if (evt.getSource() == singleMode) {
			try {
				new DisplayLevelScreen(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (evt.getSource() == multiMode) {
			try {
				new DisplayLevelScreen(true);
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
