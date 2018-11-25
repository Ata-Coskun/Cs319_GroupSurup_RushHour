package rushHour;

import java.util.Stack;

public class MultiGameEngine extends GameEngine {

	Board board;
	Stack<Board> stack;
	int level;
	MultiGameScreen gameScreen;
	boolean turn;

	public MultiGameEngine() {
	}

	public MultiGameEngine(Board board) {
		this.board = board;
		turn = true;
		MultiGameScreen gameScreen = new MultiGameScreen(this);
	}

	@Override
	boolean update(int i, int j, int iDragged, int jDragged) {
		if ((i >= 0 && i < 22 && iDragged >= 0 && iDragged < 22) )
			if (((j >= 0 && j < 5) || (j >= 9 && j < 14)) && board.coordinates[i][j] == 3  &&  i != iDragged) {
				if(board.moveBoard(i,j,iDragged))
					turnSwap();
		}
			else if(board.coordinates[i][j] < 3) {
				System.out.println(i + "\t" + j + "\t" + iDragged + "\t" + jDragged);
				if(board.moveCar(i, j, iDragged, jDragged, turn))
					turnSwap();
			}
		return true;
	}

	private void turnSwap() {
		turn = !turn;
		//Cards[3] = new Card();
	}

}
