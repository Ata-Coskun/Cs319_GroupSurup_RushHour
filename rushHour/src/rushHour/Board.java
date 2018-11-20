package rushHour;
import java.util.ArrayList;


public class Board{
	
	int [][] coordinates; // 0 if it's in board and valid, 1 if it's in board and invalid, 2 if it's the board, 3 if it's out of the board
	ArrayList<Car> cars;
	ArrayList<Obstacle> obstacles;
	
	Board(){
		coordinates = new int [6][6];
		cars = new ArrayList<Car>();
		obstacles = new ArrayList<Obstacle>();
	}
	
	public void setCar(int i1, int i2, int j1, int j2, int carType) {
		for (int i = i1; i <= i2; i++) 
			for (int j = j1; j <= j2; j++) 
				coordinates[i][j] = 1;
		cars.add(new Car(i1, i2, j1, j2, carType));
	}
	
	public void setObstacle(int i, int j) {
				coordinates[i][j] = 2;
		obstacles.add(new Obstacle(i, j));
	}
	
	public boolean moveCar(int i, int j, int iDragged, int jDragged){
		Car tempt = searchCoordinates(i, j);
		if(tempt == null)
			return false;
		
		System.out.println(i +"\t" + j +"\t" + iDragged + "\t" + jDragged);
		
		if(tempt.size == 3) {
			if(tempt.direction) {
				if(tempt.i1<i && i<tempt.i2)
					iDragged--;
				else if(tempt.i2 == i)
					iDragged -=2;
			}
			else {
				if(tempt.j1<j && j<tempt.j2)
					jDragged--;
				else if(tempt.j2 == j)
					jDragged -=2;
			}
		}
		else{
			if(tempt.direction) {
				if(tempt.i2 == i)
					iDragged--;
			}
			else {
				if(tempt.j2 == j)
					jDragged --;
			}
		}
		System.out.println(i +"\t" + j +"\t" + iDragged + "\t" + jDragged);

		//Out of boundaries exception	
		if((tempt.direction && (iDragged + tempt.size > 6 || iDragged < 0)) || (!tempt.direction && (jDragged + tempt.size > 6 || jDragged < 0))) {
			System.out.println("Out of bounds error");
			return false;
		}
		//object block exception
		boolean x = true;//to check if there is an object or not
		if(tempt.direction) {
			 
			if(tempt.i1 < iDragged) {
				for (int a = tempt.i2 + 1; a < (iDragged+tempt.size -1); a++)
					if(coordinates[a][tempt.j2] != 0) 
						x = false;
				}
			else{
				for (int a = iDragged; a < tempt.i1; a++)
					if(coordinates[a][tempt.j2] != 0)  
						x = false;
				}
		}
		else
			if(tempt.j1 < jDragged) {
				for (int a = tempt.j2+1; a < (jDragged+tempt.size-1); a++) 
					if(coordinates[tempt.i2][a] != 0) 
						x = false;
				}
			else{
				for (int a = jDragged; a < tempt.j1; a++) 
					if(coordinates[tempt.i2][a] != 0) 
						x = false;
				}
		
		if(!x) {
			System.out.println("an object blocks the way");
			x = true;
			return false;
		}
		//Erasing old coordinates
		for (int a = tempt.i1; a <= tempt.i2; a++) 
			for (int b = tempt.j1; b <= tempt.j2; b++) 
				coordinates[a][b] = 0;
		//Changing cars coordinates
		tempt.setCoordinates(iDragged, jDragged);
		//Inserting new coordinates
		for (int a = tempt.i1; a <= tempt.i2; a++) 
			for (int b = tempt.j1; b <= tempt.j2; b++)
				coordinates[a][b] = 1;
		
		//System.out.println("Movement is successful");
		System.out.println(tempt.i2 + "\t" + tempt.j2 + "\t" + tempt.size);
		return true;
	}
	
	public Car searchCoordinates(int i, int j) {
		if(coordinates[i][j] == 1)
			for (Car car : cars)
				if(car.searchCar(i, j))
					return car;
		//System.out.println("No car found on that location :'(");
		return null;
	}

}
