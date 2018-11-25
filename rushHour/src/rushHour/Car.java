package rushHour;

public class Car {

	int size, carType;
	boolean direction; // true if it's vertical false if it's horizontal
	int i1, i2, j1, j2;

	Car(int i1, int i2, int j1, int j2, int carType) {
		this.i1 = i1;
		this.i2 = i2;
		this.j1 = j1;
		this.j2 = j2;

		if (i2 - i1 == 0) {
			size = j2 - j1 + 1;
			direction = false;
		} else {
			size = i2 - i1 + 1;
			direction = true;
		}
		this.carType = carType;

	}

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

	public void setCoordinates(int iDragged, int jDragged) {
		if (direction) {
			i1 = iDragged;
			i2 = i1 + size - 1;
		} else {
			j1 = jDragged;
			j2 = j1 + size - 1;
		}
	}
	
	public void elevate(int i) {
		i1 = i;
		if(direction)
			i2 = i1 + size - 1;
		else
			i2 = i1;
	}
}
