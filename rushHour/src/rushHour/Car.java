/*
*	Author : Muhammet Said Demir
*/


package rushHour;

/*
 * Car object. Movable objects that has 2 or 3 grids as size and a direction and a type.
 */
public class Car {

	// Type is 0 if it's a regular car, 1 if it's the player 1's red car, 2 if it's
	// the player 2's red car. Size can be 2 or 3
	int size, carType;
	boolean direction; // true if it's vertical false if it's horizontal
	int i1, i2, j1, j2; // Coordinates of the car, Matrix order. i-row, j-column

	Car(int i1, int i2, int j1, int j2, int carType) {
		this.i1 = i1;
		this.i2 = i2;
		this.j1 = j1;
		this.j2 = j2;

		// sets the size and direction
		if (i2 - i1 == 0) {
			size = j2 - j1 + 1;
			direction = false;
		} else {
			size = i2 - i1 + 1;
			direction = true;
		}
		this.carType = carType;

	}

	// To see if this car is in the given locations
	public boolean searchCar(int i, int j) {
		if ((i1 <= i && i <= i2) && (j1 <= j && j <= j2))
			return true;
		return false;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	// To change the coordinates of a car, moveCar method calls this.
	public void setCoordinates(int iDragged, int jDragged) {
		i1 = iDragged;
		j1 = jDragged;
		if (direction) {
			i2 = i1 + size - 1;
			j2 = j1;
		} else {
			j2 = j1 + size - 1;
			i2 = i1;
		}

	}

	// To change the vertical coordinate of a car, moveBoard method calls this.
	public void elevate(int i) {
		i1 = i;
		if (direction)
			i2 = i1 + size - 1;
		else
			i2 = i1;
	}
}
