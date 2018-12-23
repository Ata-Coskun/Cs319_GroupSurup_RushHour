package rushHour;

import java.awt.Color;
import java.awt.Graphics;
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

public class Credits extends JPanel implements ActionListener {
	JFrame f;
	JButton returnMain;

	public Credits() throws IOException {
		f = new JFrame("Credits");

		returnMain = new JButton("RETURN");
		returnMain.setVisible(true);
		returnMain.setBounds(0, 0, 50, 50);
		returnMain.setForeground(Color.red);
		returnMain.setBackground(Color.ORANGE);
		returnMain.addActionListener(this);

		JPanel panelX = new JPanel();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(800, 700, 700, 700);
		f.setVisible(true);
		f.setSize(700, 700);
		BufferedImage credits = ImageIO.read(new File("credits.jpg"));
		JLabel logoLabel = new JLabel(new ImageIcon(credits));
		logoLabel.setBounds(900, 800, 900, 800);
		panelX.add(returnMain);

		panelX.add(logoLabel);
		panelX.add(logoLabel);

		f.add(panelX);
		f.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// return button
		if (e.getSource() == returnMain) {
			try {
				f.setVisible(false);
				new MainScreen();
			} catch (IOException evt) {
				// TODO Auto-generated catch block
				evt.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.pink);

	}

}
