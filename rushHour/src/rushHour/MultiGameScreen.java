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
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MultiGameScreen extends JPanel implements MouseListener, MouseMotionListener {
	static int iDragged = 0, jDragged = 0;
	static int iClicked = 0, jClicked = 0;
	Board board;

	int numberOfMoves;

	boolean x;
	boolean win;
	MultiGameEngine engine;

	public MultiGameScreen(MultiGameEngine engine) {
		this.engine = engine;
		this.board = engine.board;

		setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
		setLayout(null);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image boardBackGround = new ImageIcon("backGround.jpg").getImage();
		g.drawImage(boardBackGround, 0, 0, 420, 660, this);

		// ******Draw the initial board**********
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
		repaint();
	}
	/*
	 * for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++)
	 * 
	 * if (board.coordinates[i][j] == 2) { Image rock = new
	 * ImageIcon("rock.png").getImage(); g.drawImage(rock, j * 75, i * 75, 75, 75,
	 * this); }
	 * 
	 * else if (board.coordinates[i][j] == 1) { Car tempt =
	 * board.searchCoordinates(i, j);
	 * 
	 * if (tempt != null && !tempt.direction) {
	 * 
	 * if (tempt.carType != 0) {// if car is red if (tempt.j2 == 5) win = true;
	 * Image car = new ImageIcon("7.png").getImage(); g.drawImage(car, j * 75, i *
	 * 75, 150, 75, this); j++; g.setColor(Color.red); g.fillRect(425, i * 75, 25,
	 * 75); }
	 * 
	 * else if (tempt.size == 2) {// if car is size 2 // 2 Image car = new
	 * ImageIcon("6.png").getImage(); g.drawImage(car, j * 75, i * 75, 140, 75,
	 * this); j++; } else if (tempt.size == 3) {// if car is size 3 Image car = new
	 * ImageIcon("4.png").getImage(); g.drawImage(car, j * 75, i * 75, 225, 75,
	 * this); j += 2; } } }
	 * 
	 * for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++)
	 * 
	 * if (board.coordinates[j][i] == 1) { Car tempt =board.searchCoordinates(j, i);
	 * if (tempt != null && tempt.direction) if (tempt.size == 2) {// if car is
	 * vertical & size 2 Image car = new ImageIcon("10.png").getImage();
	 * g.drawImage(car, i * 75, j * 75, 75, 150, this); j++; } else if (tempt.size
	 * == 3) {// if car is vertical & size 3 Image car = new
	 * ImageIcon("10.png").getImage(); g.drawImage(car, i * 75, j * 75, 75, 225,
	 * this); j += 2; } }
	 */
	/*
	 * if(win) { this.setVisible(false); undoButton.setVisible(false);
	 * muteButton.setVisible(false); myClip.stop();
	 * JOptionPane.showMessageDialog(null, "WIN! \nYou finished in " + numberOfMoves
	 * +" number of moves"); }
	 */

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = true;

		iClicked = e.getY() / 30;
		jClicked = e.getX() / 30;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		iDragged = e.getY() / 30;
		jDragged = e.getX() / 30;
		if (x) {

			if (!board.getWin() && engine.update(iClicked, jClicked, iDragged, jDragged)) {
				numberOfMoves++;
				// board.Q.push(board);
			}
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
