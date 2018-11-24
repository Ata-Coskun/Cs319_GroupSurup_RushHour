package rushHour;
import java.util.*;
public class SingleGameEngine extends GameEngine {
	
	Board board;
	Stack<Board> stack;
	int level;
	SingleGameScreen gameScreen;
	public SingleGameEngine() {}
	public SingleGameEngine(Board board,int level) {
		this.board = board;
		this.level = level;
		stack = new Stack<Board>();
		stack.push(board);
		gameScreen = new SingleGameScreen(this);
	}
	
	//@Override 
	public boolean update(int x1,int x2, int y1, int y2) {
		boolean result = this.board.moveCar(x1,x2,y1,y2);
		stack.push(board);
		return result;
	}
	public int calculateScore(int time, int numOfMoves){
		return  1 / (time * numOfMoves) * 100;
	}
	
	

}
