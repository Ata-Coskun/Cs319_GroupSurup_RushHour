/*
*	Author : Ata Coþkun, Zeynep Nur Öztürk, Muhammed Said Demir
*/

package rushHour;

import java.util.ArrayList;

/*
 * Board object. It contains cars, obstacles and sometimes portals.
 */
public class Board {

	// 0 if it's in board and valid, 1 if it's in board and invalid (meaning that
	// something (car or obstacle) is in that area), 2 if it's the board , 3 if it's
	// board's grab place 4 outside the board (2-3-4 for the move board purposes in
	// Multi-Game
	int[][] coordinates;

	ArrayList<Car> cars;
	ArrayList<Obstacle> obstacles;
	// There can only be 1 pair of portals in a game max, that's why this is not a
	// list.
	Portal portals;

	// Scales of the board
	int height;
	int width;

	// This is to check the red car's location, in the name of object oriented
	// programming, we shouldn't reach car's attributes from the engines, only board
	// can reach car's attributes. Only exception is car's type to enhance the
	// performance
	boolean win;

	// gameMode false is it's single, true if it's multi. Because they have
	// different board settings.
	Board(boolean gameMode) {
		if (gameMode) {
			height = 22;
			width = 14;
			coordinates = new int[height][width];
			// Initializing board's coordinates
			for (int i = 0; i < 22; i++)
				for (int j = 0; j < 14; j++)
					if (i < 7 || i > 14)
						coordinates[i][j] = 4;
					else if (i == 7 || i == 14)
						coordinates[i][j] = 3;
					else if (i > 7 && i < 14)
						coordinates[i][j] = 0;

		} else {
			height = width = 6;
			coordinates = new int[height][width];
		}
		cars = new ArrayList<Car>();
		obstacles = new ArrayList<Obstacle>();
		win = false;
	}

	// Method to create a car.
	public void setCar(int i1, int i2, int j1, int j2, int carType) {
		for (int i = i1; i <= i2; i++)
			for (int j = j1; j <= j2; j++)
				coordinates[i][j] = 1;
		cars.add(new Car(i1, i2, j1, j2, carType));
	}

	// Method to create an obstacle.
	public void setObstacle(int i, int j) {
		coordinates[i][j] = 2;
		obstacles.add(new Obstacle(i, j));
	}

	// Method to create a portal.
	public void setPortal(int i1, int i2, int j1, int j2) {
		portals = new Portal(i1, i2, j1, j2);
	}

	// Method that moves a car. It first checks if there's a car in the clicked
	// location, then checks if the player can touch that car (for multi-game
	// purposes), then checks if the player can move that car to the desired
	// location.
	public boolean moveCar(int i, int j, int iDragged, int jDragged, boolean turn) {
		Car tempt = searchCoordinates(i, j);
		// If no car found, return
		if (tempt == null)
			return false;

		// Multi game purposes
		if ((turn && tempt.carType == 2) || (!turn && tempt.carType == 1))
			return false;

		// Player can click on a car's different parts, this code makes it like the
		// player touched always on the first part, for consistency
		if (tempt.size == 3) {
			if (tempt.direction) {
				if (tempt.i1 < i && i < tempt.i2)
					iDragged--;
				else if (tempt.i2 == i)
					iDragged -= 2;
			} else {
				if (tempt.j1 < j && j < tempt.j2)
					jDragged--;
				else if (tempt.j2 == j)
					jDragged -= 2;
			}
		} else {
			if (tempt.direction) {
				if (tempt.i2 == i)
					iDragged--;
			} else {
				if (tempt.j2 == j)
					jDragged--;
			}
		}

		// Out of boundaries exception
		if ((tempt.direction && (iDragged + tempt.size > height || iDragged < 0))
				|| (!tempt.direction && (jDragged + tempt.size > width || jDragged < 0)))
			return false;

		// object block exception
		boolean x = true;// to check if there is an object or not

		if (tempt.direction) {
			if (tempt.i1 < iDragged) {
				for (int a = tempt.i2 + 1; a <= (iDragged + tempt.size - 1); a++)
					if (coordinates[a][tempt.j2] != 0)
						x = false;
			} else {
				for (int a = iDragged; a < tempt.i1; a++)
					if (coordinates[a][tempt.j2] != 0)
						x = false;
			}
		} else if (tempt.j1 < jDragged) {
			for (int a = tempt.j2 + 1; a <= (jDragged + tempt.size - 1); a++)
				if (coordinates[tempt.i2][a] != 0)
					x = false;
		} else {
			for (int a = jDragged; a < tempt.j1; a++)
				if (coordinates[tempt.i2][a] != 0)
					x = false;
		}

		if (!x) {
			x = true;
			return false;
		}

		// At this point, we know that car can move.
		// Erasing old coordinates
		for (int a = tempt.i1; a <= tempt.i2; a++)
			for (int b = tempt.j1; b <= tempt.j2; b++)
				coordinates[a][b] = 0;

		// Changing cars coordinates
		tempt.setCoordinates(iDragged, jDragged);

		// Inserting new coordinates
		for (int a = tempt.i1; a <= tempt.i2; a++)
			for (int b = tempt.j1; b <= tempt.j2; b++)
				coordinates[a][b] = 1;

		// Win case for the levels that have portal.
		if (portals == null
				&& ((tempt.carType == 1 && tempt.j2 == width - 1) || (tempt.carType == 2 && tempt.j1 == 0))) {
			win = true;
			return true;
		} else if (portals != null && (tempt.carType == 1 && tempt.i2 == height - 1)) {
			win = true;
			return true;
		}

		// If the red car reached to the portal.
		if (tempt.carType == 1 && portals != null && tempt.j2 == portals.j1) {
			for (int k = 4; k < 6; k++) {
				coordinates[tempt.i1][k] = 0;
				coordinates[k - 4][portals.j2] = 1;
			}
			tempt.direction = !tempt.direction;
			tempt.setCoordinates(0, portals.j2);
		}
		return true;
	}

	// Checks if there's a car in the given location or not.
	public Car searchCoordinates(int i, int j) {
		if (coordinates[i][j] == 1)
			for (Car car : cars)
				if (car.searchCar(i, j))
					return car;
		return null;
	}

	// Method to move the board. It first checks which part of the board that player
	// clicked, then whether that player can move the board or not, then moves the
	// board
	public boolean moveBoard(int i, int j, int iDragged) {

		// Starting index of the board part that had been clicked
		int jFor = 0;

		if (j < 5)
			jFor = 0;
		if (j > 8)
			jFor = 9;

		// Car in the middle of the board parts.
		for (Car car : cars)
			if (!car.direction
					&& ((jFor == 0 && (car.j1 == 4 || car.j2 == 5)) || (jFor == 9 && (car.j1 == 8 || car.j2 == 9))))
				return false;

		// To keep the result of the movement
		boolean successful = false;

		// To shift it down
		if (i < iDragged) {
			for (int b = jFor; b < jFor + 5; b++)
				if (iDragged <= 14 && coordinates[i + 1][b] < 3) {// if upper part is grabbed
					for (int a = 7 + iDragged; a >= iDragged; a--)
						coordinates[a][b] = coordinates[a - iDragged + i][b];
					for (int a = iDragged - 1; a >= i; a--)
						coordinates[a][b] = 4;
					successful = true;
				} else if (i >= 7 && coordinates[i - 1][b] < 3) {// if lower part is grabbed
					for (int a = iDragged; a >= iDragged - 7; a--)
						coordinates[a][b] = coordinates[a - iDragged + i][b];
					for (int a = iDragged - 8; a >= i - 7; a--)
						coordinates[a][b] = 4;
					successful = true;
				}
		}
		// To shift it up
		else
			for (int b = jFor; b < jFor + 5; b++)
				if (i <= 14 && coordinates[i + 1][b] < 3) {// if upper part is grabbed
					for (int a = iDragged; a <= iDragged + 7; a++)
						coordinates[a][b] = coordinates[i + a - iDragged][b];
					for (int a = iDragged + 8; a < i + 8; a++)
						coordinates[a][b] = 4;
					successful = true;
				} else if (iDragged >= 7) {// if lower part is grabbed
					for (int a = iDragged - 7; a <= iDragged; a++)
						coordinates[a][b] = coordinates[i + a - iDragged][b];
					for (int a = iDragged + 1; a < i + 1; a++)
						coordinates[a][b] = 4;
					successful = true;
				}

		// Change the coordinates of the elements within that part of the board, if it's
		// Successful
		if (successful) {
			for (Car car : cars)
				if ((jFor == 0 && car.j1 < 5) || (jFor == 9 && car.j1 > 8))
					car.elevate(car.i1 + (iDragged - i));
			for (Obstacle obstacle : obstacles)
				if ((jFor == 0 && obstacle.j < 5) || (jFor == 9 && obstacle.j > 8))
					obstacle.i += (iDragged - i);
		}
		return successful;
	}

	public boolean getWin() {
		return win;
	}

}
