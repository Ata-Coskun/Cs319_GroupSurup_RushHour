package rushHour;

public class Card {

	int numberOfMoves;
	boolean shift;

	public Card() {
		shift = Math.random() < 0.25;
		if (!shift)
			if (Math.random() < 0.33)
				numberOfMoves = -1;

		if (numberOfMoves != -1)
			numberOfMoves = (int) (Math.random() * 5);
		if (numberOfMoves == 0)
			shift = true;
	}
}
