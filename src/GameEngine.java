public class GameEngine{

	Board board;
	int numberOfMoves;
	
	
	public GameEngine(Board board, int level) {
		numberOfMoves = 0;
		this.board = board;
		
		System.out.println("The initial board");
		paint();
		
		if(level == 1) {
		System.out.println("For board.moveCar(4, 3, 3, 4) case:");
		if(board.moveCar(4, 3, 3, 4))
			numberOfMoves++;
		paint();
		
		System.out.println("For board.moveCar(0, 1, 4, 4) case:");
		if(board.moveCar(0, 1, 4, 4))
			numberOfMoves++;
		paint();
		
		System.out.println("For board.moveCar(0, 1, 0, 1) case:");
		if(board.moveCar(0, 1, 0, 1))
			numberOfMoves++;
		paint();

		System.out.println("For board.moveCar(2, 1, 3, 4) case:");
		if(board.moveCar(2, 1, 3, 4))
			numberOfMoves++;
		paint();
		}
		else {
			System.out.println("For board.moveCar(0, 3, 0, 0) case:");
			if(board.moveCar(0, 3, 0, 0))
				numberOfMoves++;
			paint();
			
			System.out.println("For board.moveCar(3, 1, 0, 0) case:");
			if(board.moveCar(3, 1, 0, 0))
				numberOfMoves++;
			paint();
			
			System.out.println("For board.moveCar(5, 1, 0, 1) case:");
			if(board.moveCar(5, 1, 0, 1))
				numberOfMoves++;
			paint();

			System.out.println("For board.moveCar(3, 4, 4, 2) case:");
			if(board.moveCar(3, 4, 4, 2))
				numberOfMoves++;
			paint();
		}
		System.out.println("Total number of moves: " + numberOfMoves);
	}
	
	void paint(){
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(board.coordinates[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	

}
