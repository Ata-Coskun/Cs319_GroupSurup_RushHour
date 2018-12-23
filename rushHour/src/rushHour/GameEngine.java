/*
*	Author : Ata Coþkun
*/

package rushHour;

/*
 * This is the super class of the game engines. This is an abstract class
 */

public abstract class GameEngine {
	abstract boolean update(int iClicked, int jClicked, int iDragged, int jDragged);
}
