package rushHour;

import java.awt.Color;
import java.util.Scanner;
import javax.swing.JFrame;

public class LevelSelection {
	SingleGameEngine singleEngine;
	
	JFrame f;
     public LevelSelection(int x) {
    	System.out.println("Welcome to the rush hour");
 		Board board = new Board(0);

 		if (x == 1) {
 			board.setCar(0, 1, 0, 0, 0);
 			board.setCar(0, 0, 1, 2, 0);
 			board.setCar(0, 1, 3, 3, 0);
 			board.setCar(1, 2, 2, 2, 0);
 			board.setCar(2, 2, 0, 1, 1);
 			board.setCar(3, 3, 0, 1, 0);
 			board.setCar(3, 3, 2, 3, 0);
 			board.setCar(2, 4, 5, 5, 0);
 			board.setCar(3, 5, 4, 4, 0);
 			board.setObstacle(1, 5);
 			singleEngine = new SingleGameEngine(board, 1);
 		} else {
 			board.setCar(0, 0, 0, 1, 0);
 			board.setCar(0, 1, 2, 2, 0);
 			board.setCar(0, 1, 3, 3, 0);
 			board.setCar(0, 0, 4, 5, 0);
 			board.setCar(1, 1, 0, 1, 1);
 			board.setCar(2, 2, 2, 3, 0);
 			board.setCar(1, 2, 4, 4, 0);
 			board.setCar(1, 2, 5, 5, 0);
 			board.setCar(2, 4, 1, 1, 0);
 			board.setCar(5, 5, 3, 5, 0);
 			board.setObstacle(4, 2);
 			singleEngine = new SingleGameEngine(board, 2);
 		}
 		//S.close();
 		f= new JFrame("Rush Hour");
 		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		f.add(singleEngine.gameScreen);
 		f.setSize(700,485);
 		f.setVisible(true);
		f.setLocationRelativeTo(null);
		
     }
     
	//public static void main(String[] args) {
		//System.out.println("Welcome to the rush hour");
		//LevelSelection selection = new LevelSelection(1);		
	//}

}
