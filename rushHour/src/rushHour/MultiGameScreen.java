package rushHour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiGameScreen extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
	static int iDragged = 0, jDragged = 0;
	static int iClicked = 0, jClicked = 0;
	Board board;

	int numberOfMoves;
    int levelNo;
	boolean x;
	boolean win;
	MultiGameEngine engine;
	JButton[] cards;
	JLabel text, p1, p2;
	PrintWriter resumeWriter;
	File resumeFile;
	public MultiGameScreen(MultiGameEngine engine) throws FileNotFoundException {
		this.engine = engine;
		this.board = engine.board;
		this.levelNo = engine.level;
		System.out.println("multi engine level: " +  engine.level);
		setLayout(null);
		cards = new JButton[8];
		setLayout(null);

		cards[0] = new JButton("CARDS1[0]");
		cards[1] = new JButton("CARDS1[1]");
		cards[2] = new JButton("CARDS1[2]");
		cards[3] = new JButton("CARDS1[3]");

		cards[4] = new JButton("CARDS2[0]");
		cards[5] = new JButton("CARDS2[1]");
		cards[6] = new JButton("CARDS2[2]");
		cards[7] = new JButton("CARDS2[3]");

		cards[0].addActionListener(this);
		cards[1].addActionListener(this);
		cards[2].addActionListener(this);
		cards[3].addActionListener(this);
		cards[4].addActionListener(this);
		cards[5].addActionListener(this);
		cards[6].addActionListener(this);
		cards[7].addActionListener(this);

		cards[0].setBounds(450, 50, 100, 145);
		cards[1].setBounds(550, 50, 100, 145);
		cards[2].setBounds(650, 50, 100, 145);
		cards[3].setBounds(750, 50, 100, 145);

		p1 = new JLabel();
		p1.setBounds(450, 15, 200, 30);
		p1.setText("Player 1's Cards");
		text = new JLabel();
		text.setBounds(450, 200, 400, 30);
		p2 = new JLabel();
		p2.setBounds(450, 400, 200, 30);
		p2.setText("Player 2's Cards");

		cards[4].setBounds(450, 250, 100, 145);
		cards[5].setBounds(550, 250, 100, 145);
		cards[6].setBounds(650, 250, 100, 145);
		cards[7].setBounds(750, 250, 100, 145);

		add(cards[0]);
		add(cards[1]);
		add(cards[2]);
		add(cards[3]);
		add(cards[4]);
		add(p1);
		add(p2);
		add(text);
		add(cards[5]);
		add(cards[6]);
		add(cards[7]);

		setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		resumeFile = new File("resume.txt");
		System.out.println("multi level no:" + levelNo);
		resumeWriter = new PrintWriter(resumeFile);
		resumeWriter.println(levelNo + "");
		resumeWriter.close();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image boardBackGround = new ImageIcon("backGround.jpg").getImage();
		g.drawImage(boardBackGround, 0, 0, 420, 660, this);

		// ******Draw the board**********
		for (int j = 0; j < 14; j++)
			for (int i = 0; i < 22; i++)
				if (board.coordinates[i][j] == 3 && (j < 5 || j > 8)) {
					Image leftBoard = new ImageIcon("boardLR.png").getImage();
					g.drawImage(leftBoard, j * 30, i * 30, 150, 240, this);
					i = 21;
					j = j + 4;
				}
		Image leftBoard = new ImageIcon("boardM.png").getImage();
		g.drawImage(leftBoard, 5 * 30, 7 * 30, 120, 240, this);
		// *******Draw the car************
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 14; j++) {

				if (board.coordinates[i][j] == 2) {
					Image rock = new ImageIcon("rock.png").getImage();
					g.drawImage(rock, j * 30, i * 30, 30, 30, this);
				}

				else if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);

					if (tempt != null && !tempt.direction) {// horizontal

						if (tempt.carType != 0) {// if car is red
							Image car = new ImageIcon("7.png").getImage();
							g.drawImage(car, j * 30, i * 30, 60, 30, this);
							j++;
						}

						else if (tempt.size == 2) {// if car is size 2 // 2
							Image car = new ImageIcon("6.png").getImage();
							g.drawImage(car, j * 30, i * 30, 60, 30, this);
							j++;
						} else if (tempt.size == 3) {// if car is size 3
							Image car = new ImageIcon("4.png").getImage();
							g.drawImage(car, j * 30, i * 30, 90, 30, this);
							j += 2;
						}
					}
				}
			}
		}
		for (int j = 0; j < 14; j++) {
			for (int i = 0; i < 22; i++) {

				if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);
					if (tempt != null && tempt.direction)
						if (tempt.size == 2) {// if car is vertical & size 2
							Image car = new ImageIcon("10.png").getImage();
							g.drawImage(car, j * 30, i * 30, 30, 60, this);
							i++;
						} else if (tempt.size == 3) {// if car is vertical & size 3
							Image car = new ImageIcon("10.png").getImage();
							g.drawImage(car, j * 30, i * 30, 30, 90, this);
							i += 2;
						}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			cards[i].setIcon(new ImageIcon(
					new ImageIcon("cards/" + engine.cards1[i].numberOfMoves + engine.cards1[i].shift + ".png")
							.getImage()));
			cards[i + 4].setIcon(new ImageIcon(
					new ImageIcon("cards/" + engine.cards2[i].numberOfMoves + engine.cards2[i].shift + ".png")
							.getImage()));
		}

		if (engine.turn && (engine.shift || engine.numberOfMoves != 0))
			text.setText("Player 1's turn to play.    Remaining Number of Moves: " + engine.numberOfMoves + "   Shift: "
					+ engine.shift);
		else if (engine.turn && !(engine.shift || engine.numberOfMoves != 0))
			text.setText("Player 1's turn to pick a card");
		else if (!engine.turn && (engine.shift || engine.numberOfMoves != 0))
			text.setText("Player 2's turn to play.    Remaining Number of Moves: " + engine.numberOfMoves + "   Shift: "
					+ engine.shift);
		else if (!engine.turn && !(engine.shift || engine.numberOfMoves != 0))
			text.setText("Player 2's turn to pick a card");

		if (x) {
			g.setColor(Color.MAGENTA);
			for (int i = 1; i < 22; i++)
				g.drawLine(0, 30 * i, 420, 30 * i);
			g.setColor(Color.CYAN);
			for (int i = 1; i < 14; i++)
				g.drawLine(30 * i, 0, 30 * i, 660);
		}

		repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (engine.numberOfMoves == 0 && !engine.shift)
			if (engine.turn) {
				if (e.getSource() == cards[0]) {
					engine.numberOfMoves = engine.cards1[0].numberOfMoves;
					engine.shift = engine.cards1[0].shift;
					engine.choice = 0;
				}

				else if (e.getSource() == cards[1]) {
					engine.numberOfMoves = engine.cards1[1].numberOfMoves;
					engine.shift = engine.cards1[1].shift;
					engine.choice = 1;
				}

				else if (e.getSource() == cards[2]) {
					engine.numberOfMoves = engine.cards1[2].numberOfMoves;
					engine.shift = engine.cards1[2].shift;
					engine.choice = 2;
				}

				else if (e.getSource() == cards[3]) {
					engine.numberOfMoves = engine.cards1[3].numberOfMoves;
					engine.shift = engine.cards1[3].shift;
					engine.choice = 3;
				}
			} else {
				if (e.getSource() == cards[4]) {
					engine.numberOfMoves = engine.cards2[0].numberOfMoves;
					engine.shift = engine.cards2[0].shift;
					engine.choice = 0;
				}

				else if (e.getSource() == cards[5]) {
					engine.numberOfMoves = engine.cards2[1].numberOfMoves;
					engine.shift = engine.cards2[1].shift;
					engine.choice = 1;
				}

				else if (e.getSource() == cards[6]) {
					engine.numberOfMoves = engine.cards2[2].numberOfMoves;
					engine.shift = engine.cards2[2].shift;
					engine.choice = 2;
				}

				else if (e.getSource() == cards[7]) {
					engine.numberOfMoves = engine.cards2[3].numberOfMoves;
					engine.shift = engine.cards2[3].shift;
					engine.choice = 3;
				}
			}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getX() < 420 && (engine.numberOfMoves != 0 || engine.shift)) {
			x = true;
			iClicked = e.getY() / 30;
			jClicked = e.getX() / 30;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (x) {
			iDragged = e.getY() / 30;
			jDragged = e.getX() / 30;
			if (!board.getWin())
				engine.update(iClicked, jClicked, iDragged, jDragged);
			x = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
