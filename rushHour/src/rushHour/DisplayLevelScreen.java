package rushHour;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
	JFrame f;
	Board board;
	File scoreFile;
	String scoreDatas;
	int highScore;
	public DisplayLevelScreen(boolean gameMode) throws IOException {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		scoreFile = new File("high_score.txt");
		if (gameMode) {
			board = new Board(true);
			startMultiLevel(5);
		} else {
			board = new Board(false);
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

			JLabel label1 = new JLabel("Level 1 Score: " + readFile(1));
			label1.setBounds(400, 50, 300, 300);
			label1.setFont(new Font("Serif", Font.PLAIN, 25));
			JLabel label2 = new JLabel("Level 2 Score:  " + readFile(2));
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
			
			
		}
		// LevelSelection selection = new LevelSelection(1);
	}

	public DisplayLevelScreen(int level) throws IOException {
		if (level <= 4) { // it is single level
			board = new Board(false);
			startSingleLevel(level);
		} else {
			board = new Board(true);
			startMultiLevel(level);
		}
	}
	
	public int readFile(int levelNo) throws IOException {
		Scanner scanner = new Scanner(scoreFile);
		scoreDatas = scanner.nextLine();
		System.out.println("datas: " + scoreDatas);
		String score = scoreDatas.charAt(0) + "";
		int i = 0;
		int counter = 0;
		int charCounter = 0;
		while (counter != levelNo) {
			if (scoreDatas.charAt(i) == ',')
				counter++;
			if (counter == levelNo - 1)
				charCounter++;
			i++;
		}
		if (counter > 1)
			charCounter--;
		score = scoreDatas.substring(i - 1 - charCounter, i - 1);
		highScore = Integer.valueOf(score);
		System.out.println("High score: " + highScore);
		scanner.close();
		return highScore;
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
		SingleGameEngine singleEngine;
		Stack<Integer> Q = new Stack<Integer>();
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
			board.setCar(5, 5, 0, 1, 0);
			board.setPortal(1, 0, 5, 0);
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
		} else {
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
			board.setCar(5, 5, 0, 1, 0);
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

			singleEngine = new SingleGameEngine(board,3,Q);
		}

		f = new JFrame("Single Rush Hour");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(singleEngine.gameScreen);
		f.setSize(700, 485);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

	public void startMultiLevel(int level) throws IOException {
		if (level == 5) {
			board.setCar(9, 9, 0, 1, 1);
			board.setCar(9, 11, 3, 3, 0);
			board.setCar(8, 9, 4, 4, 0);
			board.setCar(12, 13, 4, 4, 0);
			board.setCar(10, 11, 5, 5, 0);
			board.setObstacle(10, 6);
			board.setObstacle(10, 7);
			board.setObstacle(11, 6);
			board.setObstacle(11, 7);
			board.setCar(10, 11, 8, 8, 0);
			board.setCar(8, 9, 9, 9, 0);
			board.setCar(12, 13, 9, 9, 0);
			board.setCar(10, 12, 10, 10, 0);
			board.setCar(12, 12, 12, 13, 2);
		} else { // add new level as an 6th level. 1-2-3-4 are single level don't change it.

		}

		MultiGameEngine multiEngine = new MultiGameEngine(board, level);
		f = new JFrame("Multi Rush Hour");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(multiEngine.gameScreen);
		f.setSize(900, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

}
