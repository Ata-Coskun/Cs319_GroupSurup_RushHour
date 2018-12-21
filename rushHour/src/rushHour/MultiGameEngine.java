package rushHour;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MultiGameEngine extends GameEngine {

	Board board;
	Stack<Board> stack;
	int level;
	MultiGameScreen gameScreen;
	boolean turn;
	Card[] cards1, cards2;
	int choice;
	int numberOfMoves;
	boolean shift;
	Scanner scan = new Scanner(System.in);

	public MultiGameEngine() {
	}

	public MultiGameEngine(Board board,int level) throws FileNotFoundException {
		this.board = board;
		turn = false;
		this.level = level;
		cards1 = new Card[4];
		cards2 = new Card[4];

		for (int i = 0; i < 4; i++) {
			cards1[i] = new Card();
			cards2[i] = new Card();
		}

		numberOfMoves = 0;
		shift = false;

		gameScreen = new MultiGameScreen(this);

		turnSwap();
	}

	@Override
	boolean update(int i, int j, int iDragged, int jDragged) {

		if ((i >= 0 && i < 22 && iDragged >= 0 && iDragged < 22)) {
			if (shift && ((j >= 0 && j < 5) || (j >= 9 && j < 14)) && board.coordinates[i][j] == 3 && i != iDragged) { // if shift is enabled
				shift = !board.moveBoard(i, j, iDragged);
				System.out.println("axaxa");
				// gameScreen.repaint();
			} else if (board.coordinates[i][j] < 3) {
				if (numberOfMoves == -1) { // if it's 1x
					if (board.moveCar(i, j, iDragged, jDragged, turn))
						numberOfMoves = 0;
					// gameScreen.repaint();
				} else if (numberOfMoves > 0) { // if it has a number
					if (Math.abs(i - iDragged) > numberOfMoves) { // if it moves vertically and more than numberOfMoves
						if (i > iDragged) // if drag up
							iDragged = i - numberOfMoves;
						else // if drag down
							iDragged = i + numberOfMoves;
					} else if (Math.abs(j - jDragged) > numberOfMoves) {// if it moves horizontally and more than
																		// numberOfMoves
						if (j > jDragged)// if drag right
							jDragged = j - numberOfMoves;
						else
							jDragged = j + numberOfMoves;
					}
					if (board.moveCar(i, j, iDragged, jDragged, turn))
						numberOfMoves -= Math.max(Math.abs(i - iDragged), Math.abs(j - jDragged));
				}
			}
		}

		if (!shift && numberOfMoves == 0) 
			turnSwap();
		return true;
	}

	public void turnSwap() {
		Card[] cards;
		if (turn)
			cards = cards1;
		else
			cards = cards2;
		for (int i = 0; i < 3; i++)
			if (i >= choice) {
				cards[i].shift = cards[i + 1].shift;
				cards[i].numberOfMoves = cards[i + 1].numberOfMoves;
			}

		cards[3] = new Card();
		turn = !turn;
	}
}
