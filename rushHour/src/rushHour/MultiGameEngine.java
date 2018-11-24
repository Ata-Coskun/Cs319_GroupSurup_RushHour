package rushHour;

import java.util.Stack;

public class MultiGameEngine extends GameEngine {
	
	Board board;
	Stack<Board> stack;
	int level;
	MultiGameScreen gameScreen;
	
	public MultiGameEngine() {}
	public MultiGameEngine(Board board) {
		this.board = board;
		MultiGameScreen gameScreen = new MultiGameScreen(this);
	}

	@Override
	boolean update(int x1, int x2, int y1, int y2) {
		// TODO Auto-generated method stub
		return false;
	}

}
