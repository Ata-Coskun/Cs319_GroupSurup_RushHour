/*
*	Author : Ata Coþkun, Asuman Aydýn
*/

package rushHour;

import java.awt.Color;
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

/*
 * This is the screen that comes when player chooses get help. Player can see the help pictures from here.
 */
public class GetHelpScreen extends JPanel implements ActionListener {

	JButton helpReturn;
	JFrame f;

	public GetHelpScreen() throws IOException {

		// Frame of this scene
		f = new JFrame("How to Play");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(800, 700, 700, 700);
		f.setVisible(true);
		f.setSize(700, 700);
		f.setLocationRelativeTo(null);

		// Help images
		BufferedImage logoGame = ImageIO.read(new File("help.png"));
		JLabel logoLabel = new JLabel(new ImageIcon(logoGame));
		logoLabel.setBounds(900, 800, 900, 800);

		// Initializing return button
		helpReturn = new JButton("RETURN");
		helpReturn.setBackground(Color.yellow);
		helpReturn.setForeground(Color.red);
		helpReturn.addActionListener(this);
		helpReturn.setBounds(0, 0, 100, 50);
		f.add(helpReturn);
		f.add(logoLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == helpReturn)
			try {
				f.setVisible(false);
				new MainScreen();
			} catch (IOException evt) {
				// TODO Auto-generated catch block
				evt.printStackTrace();
			}

	}

}