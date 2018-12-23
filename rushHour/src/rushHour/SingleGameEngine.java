/*
*	Author : Ata Coþkun, Zeynep Nur Öztürk, Muhammet Said Demir
*/

package rushHour;

import java.io.IOException;
import java.util.Stack;

/*
 * This is the engine of single game. It updates the board with the info coming from it's screen
 */
public class SingleGameEngine extends GameEngine {

	Board board;
	int level;

	public SingleGameEngine(Board board, int level, Stack<Integer> Q) throws IOException {
		this.board = board;
		this.level = level;
		new SingleGameScreen(this, Q);
	}

	// @Override
	public boolean update(int iPressed, int jPressed, int iReleased, int jReleased) {
		return board.moveCar(iPressed, jPressed, iReleased, jReleased, true);
	}

	public int calculateScore(int time, int numOfMoves) {
		System.out.println(time);
		System.out.println(numOfMoves);
		System.out.println(level);
		return (100 / (time * (numOfMoves +1))) * 100 * level;
	}

}
