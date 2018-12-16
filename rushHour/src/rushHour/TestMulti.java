package rushHour;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestMulti {
	static Board board;
	static JFrame f;
	
	public static void main(String[] args) {
		board = new Board(true);
		board.setInitialBoard();
		board.setCar(9, 9, 0, 1, 1);
		board.setCar(9, 11, 3, 3, 0);
		board.setCar(8, 9, 4, 4, 0);
		board.setCar(12, 13, 4, 4, 0);
		board.setCar(10, 11, 5, 5, 0);
		board.setCar(10, 11, 8, 8, 0);
		board.setCar(8, 9, 9, 9, 0);
		board.setCar(12, 13, 9, 9, 0);
		board.setCar(10, 12, 10, 10, 0);
		board.setCar(12, 12, 12, 13, 2);
			
		MultiGameEngine engine = new MultiGameEngine(board);
		MultiGameScreen multi = new MultiGameScreen(engine);
		f = new JFrame("Multi");
		f.setBackground(Color.MAGENTA);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(900, 900, 900, 900);
		//f3.add(game);
		f.setResizable(false);
		f.add(multi );//multigame engine
		f.setLocationRelativeTo(null);
		f.setSize(2000,2000);
		f.setVisible(true);
		
		

		
		
		/*board = new Board(1);
		board.setInitialBoard();
			board.setCar(9, 9, 0, 1, 1);
			board.setCar(9, 11, 3, 3, 0);
			board.setCar(8, 9, 4, 4, 0);
			board.setCar(12, 13, 4, 4, 0);
			board.setCar(10, 11, 5, 5, 0);
			board.setCar(10, 11, 8, 8, 0);
			board.setCar(8, 9, 9, 9, 0);
			board.setCar(12, 13, 9, 9, 0);
			board.setCar(10, 12, 10, 10, 0);
			board.setCar(12, 12, 12, 13, 1);
			for( int i = 0; i < 22; i++) {
 				for(int j = 0; j < 14; j++) {
 					System.out.print( board.coordinates[i][j] + " ");
 				}
 				System.out.print("\n");
 			}
			
			System.out.println("*************moveBoard(2, 7, 2, 4);***************\n");
			board.moveBoard(2, 7, 2, 4);
			for( int i = 0; i < 22; i++) {
 				for(int j = 0; j < 14; j++) {
 					System.out.print( board.coordinates[i][j] + " ");
 				}
 				System.out.print("\n");
 			}
			System.out.println("*************moveBoard(2, 4, 2, 8);***************\n");
			board.moveBoard(2, 4, 2, 8);
			for( int i = 0; i < 22; i++) {
 				for(int j = 0; j < 14; j++) {
 					System.out.print( board.coordinates[i][j] + " ");
 				}
 				System.out.print("\n");
 			}
			System.out.println("*************moveBoard(10, 14, 10, 17);***************\n");
			board.moveBoard(10, 14, 10, 17);
			for( int i = 0; i < 22; i++) {
 				for(int j = 0; j < 14; j++) {
 					System.out.print( board.coordinates[i][j] + " ");
 				}
 				System.out.print("\n");
 			}*/
	}
}
