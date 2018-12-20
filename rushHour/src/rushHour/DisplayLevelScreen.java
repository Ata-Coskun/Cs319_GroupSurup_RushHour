package rushHour;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayLevelScreen extends JPanel implements ActionListener {

	JButton btn;
	JButton btn2;
	JFrame f, f1;
	SingleGameEngine singleEngine;
	Board board = new Board(false);

	public DisplayLevelScreen(/*boolean gameMode*/) throws IOException {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		btn = new JButton();
		btn.addActionListener(this);
		btn.setBounds(50, 50, 300, 300);
		btn2 = new JButton();
		btn2.addActionListener(this);
		btn2.setBounds(50, 350, 300, 300);
		// level1
		Image img2 = new ImageIcon("level1.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		btn.setIcon(new ImageIcon(img2));
		// level2
		Image img3 = new ImageIcon("level2.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		btn2.setIcon(new ImageIcon(img3));
		// texts

		JLabel label1 = new JLabel("Level 1 Score: 100 ");
		label1.setBounds(400, 50, 300, 300);
		label1.setFont(new Font("Serif", Font.PLAIN, 25));
		JLabel label2 = new JLabel("Level 2 Score:  ");
		label2.setBounds(400, 350, 300, 300);
		label2.setFont(new Font("Serif", Font.PLAIN, 25));
		// score star image example
		BufferedImage toScore = null;
		try {
			toScore = ImageIO.read(new File("star.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel labelMe = new JLabel(new ImageIcon(toScore));
		labelMe.setBounds(500, 180, 100, 100);

		// frame
		f = new JFrame("Select Levels");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		BufferedImage img = ImageIO.read(new File("background.png"));
		f.setContentPane(new JLabel(new ImageIcon(img)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		f.add(label1, gbc);
		f.add(label2, gbc);
		f.add(labelMe, gbc);
		f.add(btn, gbc);
		f.add(btn2, gbc);

		// adding to the frame
		f.setSize(700, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

		// LevelSelection selection = new LevelSelection(1);
	}

	public DisplayLevelScreen(int level) throws IOException {
		startSingleLevel(level);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// S.close();

		if (e.getSource() == btn) {
			f.setVisible(false);
			try {
				startSingleLevel(1);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == btn2) {
			f.setVisible(false);
			try {
				startSingleLevel(2);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.pink);

	}

	public void startSingleLevel(int level) throws IOException {
		Stack Q = new Stack<Integer>();
		if (level == 1) {
			board.setCar(0, 1, 0, 0, 0);
			board.setCar(0, 0, 1, 2, 0);
			board.setCar(0, 1, 3, 3, 0);
			board.setCar(1, 2, 2, 2, 0);
			board.setCar(2, 2, 0, 1, 1);
			board.setCar(3, 3, 0, 1, 0);
			board.setCar(3, 3, 2, 3, 0);
			board.setCar(2, 4, 5, 5, 0);
			board.setCar(3, 5, 4, 4, 0);
			board.setObstacle(1, 5);

			
			singleEngine = new SingleGameEngine(board, 1, Q);
			f1 = new JFrame("Rush Hour");
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f1.add(singleEngine.gameScreen);
			f1.setSize(700, 485);
			f1.setVisible(true);
			f1.setLocationRelativeTo(null);
		} else if (level == 2) {
			board.setCar(0, 0, 0, 1, 0);
			board.setCar(0, 1, 2, 2, 0);
			board.setCar(0, 1, 3, 3, 0);
			board.setCar(0, 0, 4, 5, 0);
			board.setCar(1, 1, 0, 1, 1);
			board.setCar(2, 2, 2, 3, 0);
			board.setCar(1, 2, 4, 4, 0);
			board.setCar(1, 2, 5, 5, 0);
			board.setCar(2, 4, 1, 1, 0);
			board.setCar(5, 5, 3, 5, 0);
			board.setObstacle(4, 2);
			
			Q.push(5);
			Q.push(1);
			Q.push(1);
			Q.push(1);
			
			Q.push(2);
			Q.push(3);
			Q.push(2);
			Q.push(1);
			
			Q.push(3);
			Q.push(4);
			Q.push(3);
			Q.push(1);
			
			Q.push(4);
			Q.push(2);
			Q.push(2);
			Q.push(2);
			
			Q.push(4);
			Q.push(4);
			Q.push(4);
			Q.push(2);
			
			Q.push(5);
			Q.push(4);
			Q.push(5);
			Q.push(2);
			
			singleEngine = new SingleGameEngine(board, 2, Q);
			f1 = new JFrame("Rush Hour");
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f1.add(singleEngine.gameScreen);
			f1.setSize(700, 485);
			f1.setVisible(true);
			f1.setLocationRelativeTo(null);
		}
	}

}
