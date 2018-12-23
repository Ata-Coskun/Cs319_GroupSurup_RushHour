/*
*	Author : Muhammed Said Demir
*/

package rushHour;

/*
 * Card object. It holds information about the number of moves that a player can perform and shift operation.
 * These attributes are assigned with a random generator. 
 * For more information about the meanings of these attributes, check MultiGameScreen class
 */
public class Card {

	int numberOfMoves;
	boolean shift;

	public Card() {
		shift = Math.random() < 0.25;
		if (!shift)
			if (Math.random() < 0.25)
				numberOfMoves = -1;

		if (numberOfMoves != -1)
			numberOfMoves = (int) (Math.random() * 5);
		if (numberOfMoves == 0)
			shift = true;
	}
}
