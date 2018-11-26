package rushHour;

public class BoardSelection {
	MultiGameEngine multiEngine;
	public BoardSelection() {
		Board board = new Board(false);
		
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
	}
}
