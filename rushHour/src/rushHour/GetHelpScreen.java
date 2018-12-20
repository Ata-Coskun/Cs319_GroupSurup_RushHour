package rushHour;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GetHelpScreen extends JPanel {

	public GetHelpScreen() throws IOException {
		// help frame
		JFrame f2 = new JFrame("How to Play");
		JPanel panelX = new JPanel();
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setBounds(800, 700, 700, 700);
		f2.setVisible(true);
		f2.setSize(700, 700);
		BufferedImage logoGame = ImageIO.read(new File("help.png"));
		JLabel logoLabel = new JLabel(new ImageIcon(logoGame));
		logoLabel.setBounds(900, 800, 900, 800);
		panelX.add(logoLabel);
		f2.add(panelX);
		f2.setLocationRelativeTo(null);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.orange);
		repaint();
	}

}