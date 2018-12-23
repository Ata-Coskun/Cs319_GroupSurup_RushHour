/*
*	Author : Ata Coþkun, Zeynep Nur Öztürk, Muhammet Said Demir
*/

package rushHour;

import java.io.IOException;

/*
 * This is the engine of multi game. It updates the board with the info coming from it's screen
 */
public class MultiGameEngine extends GameEngine {

	Board board;
	int level;
	// To indicate who's turn is this. True for player 1, False for 2
	boolean turn;
	// Cards of the players
	Card[] cards1, cards2;
	int choice;

	// Amount of movements that a player can do. If it's 3, that means the player
	// can move a car for 3 grids, or 1 car for 1 grid, another for 2 grid and etc.
	// If it's -1, that means the player can move a single car for any number of
	// grids that player wants
	int numberOfMoves;

	// To indicate whether the player can move the board or not
	boolean shift;

	public MultiGameEngine(Board board, int level) throws IOException {
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

		new MultiGameScreen(this);

		// It starts with turn swap for the player 1 to start and draw a card.
		turnSwap();
	}

	@Override
	boolean update(int i, int j, int iDragged, int jDragged) {

		// If the player clicked on the movable parts of the board. Board's mid location
		// cannot move.
		if ((i >= 0 && i < 22 && iDragged >= 0 && iDragged < 22)) {

			// if player tries to move the board
			if (shift && ((j >= 0 && j < 5) || (j >= 9 && j < 14)) && board.coordinates[i][j] == 3 && i != iDragged)
				shift = !board.moveBoard(i, j, iDragged);

			// if the player clicks within the board.
			else if (board.coordinates[i][j] < 3) {

				// if it's 1x
				if (numberOfMoves == -1) {
					if (board.moveCar(i, j, iDragged, jDragged, turn))
						numberOfMoves = 0;
				}

				// if it has a limited number of movements
				else if (numberOfMoves > 0) {

					// if it moves vertically and more than numberOfMoves. Since the player can move
					// maximum number of remaining number of moves
					if (Math.abs(i - iDragged) > numberOfMoves)

						// if drag up
						if (i > iDragged)
							iDragged = i - numberOfMoves;

						// if drag down
						else
							iDragged = i + numberOfMoves;

					// if it moves horizontally and more than numberOfMoves.

					else if (Math.abs(j - jDragged) > numberOfMoves)
						// if drag right
						if (j > jDragged)
							jDragged = j - numberOfMoves;
						// if drag left
						else
							jDragged = j + numberOfMoves;

					// finally, move the car and update the remaining number of moves
					if (board.moveCar(i, j, iDragged, jDragged, turn))
						numberOfMoves -= Math.max(Math.abs(i - iDragged), Math.abs(j - jDragged));
				}
			}
		}

		// if the player got nothing left to do, turn swap.
		if (!shift && numberOfMoves == 0)
			turnSwap();
		return true;
	}

	// removes the chosen card, creates a new card, then swaps the turn, makes the
	// player choose a card
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
