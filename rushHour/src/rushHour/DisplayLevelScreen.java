/*
*	Author : Ata Coþkun, Zeynep Nur Öztürk, Asuman Aydýn, Muhammet Said Demir
*/

package rushHour;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This is the screen that comes when player chooses get help. Player can choose the initial theme and mute setting from here
 */
public class DisplayLevelScreen extends JPanel implements ActionListener {

	JButton single1;
	JButton single2;
	JButton single3;
	JButton single4;
	JButton multi1;
	JButton multi2;
	JButton gameModeReturn;
	JFrame f;
	Board board;

	public DisplayLevelScreen(boolean gameMode) throws IOException {
		// Initializing
		setLayout(null);
		board = new Board(gameMode);

		// Frame of this scene
		f = new JFrame("Select Levels");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));
		f.setSize(700, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		// If player chooses single mode
		if (!gameMode) {

			// Initializing buttons
			single1 = new JButton();
			single1.addActionListener(this);
			single1.setBounds(50, 30, 150, 150);
			single1.setIcon(new ImageIcon(
					new ImageIcon("level1.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
			f.add(single1, gbc);

			single2 = new JButton();
			single2.addActionListener(this);
			single2.setBounds(50, 190, 150, 150);
			single2.setIcon(new ImageIcon(
					new ImageIcon("level2.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
			f.add(single2, gbc);

			single3 = new JButton();
			single3.addActionListener(this);
			single3.setBounds(50, 350, 150, 150);
			single3.setIcon(new ImageIcon(
					new ImageIcon("level3.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
			f.add(single3, gbc);

			single4 = new JButton();
			single4.addActionListener(this);
			single4.setBounds(50, 510, 150, 150);
			single4.setIcon(new ImageIcon(
					new ImageIcon("level4.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
			f.add(single4, gbc);

			// Initializing text labels
			JLabel label1 = new JLabel("Level 1 Score: " + readFile(1));
			label1.setBounds(250, 50, 300, 100);
			label1.setFont(new Font("Serif", Font.PLAIN, 25));
			f.add(label1, gbc);

			JLabel label2 = new JLabel("Level 2 Score:  " + readFile(2));
			label2.setBounds(250, 210, 300, 100);
			label2.setFont(new Font("Serif", Font.PLAIN, 25));
			f.add(label2, gbc);

			JLabel label3 = new JLabel("Level 3 Score:  " + readFile(3));
			label3.setBounds(250, 370, 300, 100);
			label3.setFont(new Font("Serif", Font.PLAIN, 25));
			f.add(label3, gbc);

			JLabel label4 = new JLabel("Level 4 Score:  " + readFile(4));
			label4.setBounds(250, 530, 300, 100);
			label4.setFont(new Font("Serif", Font.PLAIN, 25));
			f.add(label4, gbc);
		} else {

			// Initializing buttons
			multi1 = new JButton();
			multi1.addActionListener(this);
			multi1.setBounds(100, 100, 400, 200);
			multi1.setIcon(new ImageIcon(
					new ImageIcon("level5.png").getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT)));
			f.add(multi1, gbc);

			multi2 = new JButton();
			multi2.addActionListener(this);
			multi2.setBounds(100, 400, 400, 200);
			multi2.setIcon(new ImageIcon(
					new ImageIcon("level6.png").getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT)));
			f.add(multi2, gbc);

			// Initializing text labels
			JLabel label5 = new JLabel("Level 1");
			label5.setBounds(100, 20, 300, 100);
			label5.setFont(new Font("Serif", Font.PLAIN, 25));
			f.add(label5, gbc);

			JLabel label6 = new JLabel("Level 2");
			label6.setBounds(100, 320, 300, 100);
			label6.setFont(new Font("Serif", Font.PLAIN, 25));
			f.add(label6, gbc);
		}

		// return button
		gameModeReturn = new JButton("RETURN");
		gameModeReturn.addActionListener(this);
		gameModeReturn.setBounds(0, 0, 200, 20);
		gameModeReturn.setBackground(Color.yellow);
		gameModeReturn.setForeground(Color.red);
		f.add(gameModeReturn);
	}

	// Second constructor for resume
	public DisplayLevelScreen(int level) throws IOException {

		// it is single level
		if (level <= 4)
			board = new Board(false);
		else
			board = new Board(true);
		startTheGame(level);
	}

	/*
	 * For high score purposes
	 */
	public int readFile(int levelNo) throws IOException {

		Scanner scanner = new Scanner(new File("high_score.txt"));
		String scoreDatas = scanner.nextLine();
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
		scanner.close();

		return Integer.valueOf(score);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		f.setVisible(false);

		if (e.getSource() == single1)
			try {
				startTheGame(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (e.getSource() == single2)
			try {
				startTheGame(2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (e.getSource() == single3)
			try {
				startTheGame(3);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		else if (e.getSource() == single4)
			try {
				startTheGame(4);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (e.getSource() == multi1)
			try {
				startTheGame(5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (e.getSource() == multi2)
			try {
				startTheGame(6);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (e.getSource() == gameModeReturn)
			try {
				new MainScreen();
			} catch (IOException evt) {
				evt.printStackTrace();
			}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.pink);

	}

	public void startTheGame(int level) throws IOException {
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

			new SingleGameEngine(board, 1, Q);
		} else if (level == 2) {
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

			new SingleGameEngine(board, 2, Q);
		} else if (level == 3) {
			board.setCar(3, 3, 0, 1, 0);
			board.setCar(2, 2, 0, 1, 1);
			board.setCar(0, 1, 2, 2, 0);
			board.setCar(2, 3, 2, 2, 0);
			board.setCar(4, 4, 2, 3, 0);
			board.setCar(4, 5, 1, 1, 0);
			board.setCar(5, 5, 2, 4, 0);
			board.setCar(0, 0, 3, 5, 0);
			board.setCar(1, 2, 5, 5, 0);
			board.setCar(3, 4, 4, 4, 0);
			board.setCar(3, 5, 5, 5, 0);

			Q.push(5);
			Q.push(2);
			Q.push(4);
			Q.push(2);

			Q.push(5);
			Q.push(0);
			Q.push(5);
			Q.push(1);

			Q.push(2);
			Q.push(0);
			Q.push(3);
			Q.push(0);

			Q.push(2);
			Q.push(2);
			Q.push(2);
			Q.push(1);

			Q.push(4);
			Q.push(2);
			Q.push(1);
			Q.push(2);

			Q.push(4);
			Q.push(4);
			Q.push(4);
			Q.push(2);

			Q.push(2);
			Q.push(0);
			Q.push(2);
			Q.push(1);

			Q.push(5);
			Q.push(0);
			Q.push(4);
			Q.push(0);

			Q.push(5);
			Q.push(2);
			Q.push(5);
			Q.push(1);

			Q.push(5);
			Q.push(5);
			Q.push(5);
			Q.push(4);

			Q.push(2);
			Q.push(5);
			Q.push(3);
			Q.push(5);

			Q.push(2);
			Q.push(3);
			Q.push(2);
			Q.push(4);

			Q.push(0);
			Q.push(3);
			Q.push(3);
			Q.push(3);

			Q.push(2);
			Q.push(5);
			Q.push(2);
			Q.push(4);

			Q.push(5);
			Q.push(5);
			Q.push(4);
			Q.push(5);

			Q.push(5);
			Q.push(2);
			Q.push(5);
			Q.push(3);

			Q.push(5);
			Q.push(0);
			Q.push(5);
			Q.push(1);

			Q.push(2);
			Q.push(0);
			Q.push(3);
			Q.push(0);

			Q.push(2);
			Q.push(2);
			Q.push(2);
			Q.push(1);

			Q.push(2);
			Q.push(4);
			Q.push(2);
			Q.push(3);

			Q.push(0);
			Q.push(4);
			Q.push(3);
			Q.push(4);

			Q.push(2);
			Q.push(2);
			Q.push(2);
			Q.push(3);

			Q.push(0);
			Q.push(2);
			Q.push(2);
			Q.push(2);

			Q.push(1);
			Q.push(0);
			Q.push(1);
			Q.push(4);

			Q.push(3);
			Q.push(2);
			Q.push(1);
			Q.push(2);

			Q.push(2);
			Q.push(0);
			Q.push(2);
			Q.push(1);

			Q.push(5);
			Q.push(0);
			Q.push(4);
			Q.push(0);

			Q.push(5);
			Q.push(2);
			Q.push(5);
			Q.push(1);

			Q.push(5);
			Q.push(5);
			Q.push(5);
			Q.push(4);

			Q.push(2);
			Q.push(5);
			Q.push(3);
			Q.push(5);

			Q.push(2);
			Q.push(3);
			Q.push(2);
			Q.push(4);

			Q.push(4);
			Q.push(3);
			Q.push(1);
			Q.push(3);

			Q.push(2);
			Q.push(5);
			Q.push(2);
			Q.push(4);

			Q.push(2);
			Q.push(5);
			Q.push(2);
			Q.push(4);

			Q.push(5);
			Q.push(5);
			Q.push(4);
			Q.push(5);

			Q.push(5);
			Q.push(2);
			Q.push(5);
			Q.push(3);

			Q.push(5);
			Q.push(0);
			Q.push(5);
			Q.push(1);

			Q.push(2);
			Q.push(0);
			Q.push(3);
			Q.push(0);

			Q.push(2);
			Q.push(2);
			Q.push(2);
			Q.push(1);

			Q.push(2);
			Q.push(4);
			Q.push(2);
			Q.push(3);

			Q.push(4);
			Q.push(4);
			Q.push(3);
			Q.push(4);

			Q.push(4);
			Q.push(1);
			Q.push(4);
			Q.push(3);

			new SingleGameEngine(board, 3, Q);
		} else if (level == 4) {
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

			new SingleGameEngine(board, 4, Q);
		} else if (level == 5) {
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

			new MultiGameEngine(board, 5);
		} else {
			board.setCar(8, 8, 0, 1, 0);
			board.setCar(8, 10, 0, 0, 0);
			board.setCar(11, 11, 1, 3, 0);
			board.setCar(10, 10, 2, 3, 1);
			board.setCar(8, 9, 2, 2, 0);
			board.setCar(12, 13, 3, 3, 0);
			board.setObstacle(11, 5);
			board.setCar(10, 11, 8, 8, 0);
			board.setCar(8, 9, 9, 9, 0);
			board.setCar(12, 13, 9, 9, 0);
			board.setCar(10, 11, 10, 10, 0);
			board.setCar(10, 10, 11, 12, 2);
			board.setCar(8, 10, 13, 13, 0);
			board.setCar(11, 12, 12, 12, 0);
			board.setCar(12, 12, 9, 10, 0);

			new MultiGameEngine(board, 6);
		}
	}

}
