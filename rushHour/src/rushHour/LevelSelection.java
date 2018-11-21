package rushHour;

import java.util.Scanner;
import javax.swing.JFrame;

public class LevelSelection {

	public static void main(String[] args) {
		System.out.println("Welcome to the rush hour");
		System.out.println("Please enter the level you wanna play 1-2");
		Scanner S = new Scanner(System.in);
		int x = S.nextInt();
		GameEngine engine;
		Board board = new Board();

		if (x == 1) {
			board.setCar(0, 0, 0, 2, 0);
			board.setCar(2, 4, 3, 3, 0);
			board.setCar(2, 2, 0, 1, 1);
			board.setObstacle(0, 4);
			engine = new GameEngine(board, 1);
		} else {
			board.setCar(2, 4, 1, 1, 0);
			board.setCar(4, 4, 3, 5, 0);
			board.setCar(0, 0, 2, 3, 1);
			board.setObstacle(0, 4);
			engine = new GameEngine(board, 2);
		}
		S.close();
		JFrame f = new JFrame("Rush Hour");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(engine);
		f.setSize(700,485);
		f.setVisible(true);
	}

}
