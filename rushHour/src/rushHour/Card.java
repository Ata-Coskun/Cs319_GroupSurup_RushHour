package rushHour;

public class Card {

	int numberOfMoves;
	boolean shift;

	public Card() {
		shift = Math.random() < 0.25;
		if (!shift)
			if (Math.random() < 0.33)
				numberOfMoves = -2;

		if (numberOfMoves != -2)
			numberOfMoves = (int) (Math.random() * 5);
		if(numberOfMoves == 0)
			shift = true;
	}
}
