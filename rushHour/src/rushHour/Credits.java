/*
*	Author : Asuman Aydýn, Ata Coþkun, Zeynep Nur Öztürk
*/

package rushHour;

import java.awt.Color;
import java.awt.Graphics;
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
 * This is the screen that comes when player chooses credits. Player can see the credits from here.
 */
public class Credits extends JPanel implements ActionListener {

	JFrame f;
	JButton returnMain;

	public Credits() throws IOException {

		// Frame of this scene
		f = new JFrame("Credits");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(800, 700, 700, 700);
		f.setVisible(true);
		f.setSize(700, 700);

		// Initializing return button
		returnMain = new JButton("RETURN");
		returnMain.setVisible(true);
		returnMain.setBounds(0, 0, 50, 50);
		returnMain.setForeground(Color.red);
		returnMain.setBackground(Color.ORANGE);
		returnMain.addActionListener(this);
		add(returnMain);

		// Credits image
		JLabel logoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("credits.jpg"))));
		logoLabel.setBounds(900, 800, 900, 800);
		add(logoLabel);

		f.add(this);
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
				evt.printStackTrace();
			}
		}
	}
}
