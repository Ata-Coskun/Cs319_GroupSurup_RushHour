package rushHour;
import java.io.IOException;
import java.util.*;
public class SingleGameEngine extends GameEngine {
	
	Board board;
	Stack<Integer> stack;
	int level;
	SingleGameScreen gameScreen;
	public SingleGameEngine() {}
	public SingleGameEngine(Board board,int level) throws IOException {
		this.board = board;
		this.level = level;
		stack = new Stack<Integer>();
		//stack.push(board);
		gameScreen = new SingleGameScreen(this);
	}
	
	//@Override 
	public boolean update(int x1,int x2, int y1, int y2) {
		boolean result = this.board.moveCar(x1,x2,y1,y2, true);
		stack.push(x2);
		stack.push(x1);
		stack.push(y2);
		stack.push(y1);
		return result;
	}
	public int calculateScore(int time, int numOfMoves){
		return  1 / (time * numOfMoves) * 100;
	}
	
	

}
