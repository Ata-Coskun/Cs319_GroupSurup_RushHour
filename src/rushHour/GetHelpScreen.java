package rushHour;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GetHelpScreen extends JPanel implements ActionListener{

	JButton helpReturn;
	JFrame f2;
	public GetHelpScreen() throws IOException {
		// help frame
		helpReturn = new JButton("RETURN");
		helpReturn.setBackground(Color.yellow);
		helpReturn.setForeground(Color.red);
		helpReturn.addActionListener(this);
		
		f2 = new JFrame("How to Play");
		//JPanel panelX = new JPanel();
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setBounds(800, 700, 700, 700);
		f2.setVisible(true);
		f2.setSize(700, 700);
		BufferedImage logoGame = ImageIO.read(new File("help.png"));
		JLabel logoLabel = new JLabel(new ImageIcon(logoGame));
		logoLabel.setBounds(900, 800, 900, 800);
		helpReturn.setBounds(0, 0, 100, 50);
		f2.add(helpReturn);
		f2.add(logoLabel);
		//f2.add(panelX);
		f2.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == helpReturn)
		{
			try {
				f2.setVisible(false);
				new MainScreen();
			} catch (IOException evt) {
				// TODO Auto-generated catch block
				evt.printStackTrace();
			}
		}

	}

}