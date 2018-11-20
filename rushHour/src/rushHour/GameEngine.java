package rushHour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameEngine extends JPanel implements MouseListener, MouseMotionListener {
	static int xDragged = 0, yDragged = 0;
	static int xClicked = 0, yClicked = 0;
	Board board;
	int numberOfMoves;
	int levelNo;
	MouseListener ml;
	MouseMotionListener mml;
	boolean x;
	boolean win;

	public GameEngine(Board board, int level) {
		numberOfMoves = 0;
		this.board = board;
		System.out.println("The initial board");
	}

	void paint() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(board.coordinates[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.yellow);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		Image boardImage = new ImageIcon("board.png").getImage();
		g.drawImage(boardImage, 0, 0, 450, 450, this);

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)

				if (board.coordinates[i][j] == 2) {
					Image rock = new ImageIcon("rock.png").getImage();
					g.drawImage(rock, j * 75, i * 75, 75, 75, this);
				}

				else if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);

					if (tempt != null && !tempt.direction) {

						if (tempt.carType != 0) {// if car is red
							if (tempt.j2 == 5)
								win = true;
							Image car = new ImageIcon("7.png").getImage();
							g.drawImage(car, j * 75, i * 75, 150, 75, this);
							j++;
							g.setColor(Color.red);
							g.fillRect(425, i * 75, 25, 75);
						}

						else if (tempt.size == 2) {// if car is size 2 // 2
							Image car = new ImageIcon("6.png").getImage();
							g.drawImage(car, j * 75, i * 75, 150, 75, this);
							j++;
						} else if (tempt.size == 3) {// if car is size 3
							Image car = new ImageIcon("4.png").getImage();
							g.drawImage(car, j * 75, i * 75, 225, 75, this);
							j += 2;
						}
					}
				}

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)

				if (board.coordinates[j][i] == 1) {
					Car tempt = board.searchCoordinates(j, i);
					if (tempt != null && tempt.direction)
						if (tempt.size == 2) {// if car is vertical & size 2
							Image car = new ImageIcon("10.png").getImage();
							g.drawImage(car, i * 75, j * 75, 75, 150, this);
							j++;
						} else if (tempt.size == 3) {// if car is vertical & size 3
							Image car = new ImageIcon("10.png").getImage();
							g.drawImage(car, i * 75, j * 75, 75, 225, this);
							j += 2;
						}
				}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = true;
		xClicked = e.getX() / 75;
		yClicked = e.getY() / 75;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		xDragged = e.getX() / 75;
		yDragged = e.getY() / 75;
		if (x) {
			if (board.moveCar(yClicked, xClicked, yDragged, xDragged))
				numberOfMoves++;
			System.out.println(numberOfMoves);
			x = false;
		}
		repaint();
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
