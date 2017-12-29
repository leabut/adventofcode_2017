import java.awt.Point;
import java.util.Vector;

public class Matrix {

	public enum Direction {
		NORTH, SOUTH, WEST, EAST
	}
	
	public int count = 0;

	public Direction dir = Direction.NORTH;

	public Vector<Vector<Character>> data = new Vector<Vector<Character>>();
	public int dim = 0;

	public Point currentPos = null;

	public Matrix(String matrixString, int dim) {
		this.dim = dim;
		char[] tmp = matrixString.toCharArray();

		Vector<Character> tmpVec = new Vector<Character>();
		for (int i = 0; i < tmp.length; i++) {
			if (i % dim == 0 && i != 0) {
				data.add(tmpVec);
				tmpVec = new Vector<Character>();
			}
			tmpVec.add(tmp[i]);
		}

		data.addElement(tmpVec);

		currentPos = new Point(dim / 2, dim / 2);
	}

	public int active() {
		int count = 0;

		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				if (data.get(i).get(j) == '#') {
					count++;
				}
			}
		}

		return count;
	}

	//part01
	public void step() {
		// rules
		if (isInfected(currentPos)) {
			turnRight();
			set((int) currentPos.getX(), (int) currentPos.getY(), '.');
		} else {
			turnLeft();
			set((int) currentPos.getX(), (int) currentPos.getY(), '#');
			count++;
		}

		// check borders
		if (currentPos.getX() + 1 >= data.size() || currentPos.getY() + 1 >= data.get(0).size()
				|| currentPos.getX() - 1 <= 0 || currentPos.getY() - 1 <= 0) {
			extend();
		}

		// step forward
		stepForward();
	 }
	
	public void step2() {
		// rules
		switch(getCurrentState()) {
		case '.':
			turnLeft();
			set((int) currentPos.getX(), (int) currentPos.getY(), 'W');
			break;
		case 'W':
			//dont turn
			set((int) currentPos.getX(), (int) currentPos.getY(), '#');
			count++;
			break;
		case '#':
			turnRight();
			set((int) currentPos.getX(), (int) currentPos.getY(), 'F');
			break;
		case 'F':
			turnBack();
			set((int) currentPos.getX(), (int) currentPos.getY(), '.');
			break;
		}

		// check borders
		if (currentPos.getX() + 1 >= data.size() || currentPos.getY() + 1 >= data.get(0).size()
				|| currentPos.getX() - 1 <= 0 || currentPos.getY() - 1 <= 0) {
			extend();
		}

		// step forward
		stepForward();
	}
	
	public char getCurrentState() {
		return (data.get((int) currentPos.getX()).get((int) currentPos.getY()));
	}
	
	public void set(int x, int y, char c) {
		data.get(x).set(y, c);
	}
	
	private void extend() {
		Vector<Character> firstRow = new Vector<Character>();
		Vector<Character> lastRow = new Vector<Character>();

		for (int i = 0; i < dim + 2; i++) {
			firstRow.add('.');
			lastRow.add('.');
		}

		for (int i = 0; i < data.size(); i++) {
			data.get(i).add(0, '.');
			data.get(i).add(data.get(i).size(), '.');
		}

		data.add(0, firstRow);
		data.add(data.size(), lastRow);

		dim++;
		dim++;
		
		currentPos.setLocation(currentPos.getX()+1, currentPos.getY()+1);
	}
	
	private void stepForward() {
		switch (dir) {
		case EAST:
			currentPos.setLocation(currentPos.getX(), currentPos.getY()+1);
			break;
		case NORTH:
			currentPos.setLocation(currentPos.getX()-1, currentPos.getY());
			break;
		case SOUTH:
			currentPos.setLocation(currentPos.getX()+1, currentPos.getY());
			break;
		case WEST:
			currentPos.setLocation(currentPos.getX(), currentPos.getY()-1);
			break;
		default:
			break;
		}
	}
	
	private void turnBack() {
		switch (dir) {
		case EAST:
			dir = Direction.WEST;
			break;
		case NORTH:
			dir = Direction.SOUTH;
			break;
		case SOUTH:
			dir = Direction.NORTH;
			break;
		case WEST:
			dir = Direction.EAST;
			break;
		default:
			break;
		}
	}

	private void turnLeft() {
		switch (dir) {
		case EAST:
			dir = Direction.NORTH;
			break;
		case NORTH:
			dir = Direction.WEST;
			break;
		case SOUTH:
			dir = Direction.EAST;
			break;
		case WEST:
			dir = Direction.SOUTH;
			break;
		default:
			break;
		}
	}

	private void turnRight() {
		switch (dir) {
		case EAST:
			dir = Direction.SOUTH;
			break;
		case NORTH:
			dir = Direction.EAST;
			break;
		case SOUTH:
			dir = Direction.WEST;
			break;
		case WEST:
			dir = Direction.NORTH;
			break;
		default:
			break;
		}
	}

	private boolean isInfected(Point point) {
		if (data.get((int) point.getX()).get((int) point.getY()) == '#') {
			return true;
		}
		
		return false;
	}
}
