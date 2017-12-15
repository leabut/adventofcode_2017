import java.awt.Point;
import java.util.Vector;

public class Main {

	public static Vector<DataPoint> map = new Vector<DataPoint>();

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		
		DataPoint root = new DataPoint(1);
		root.myNumber = 1;
		map.addElement(root);
		
		for (int i = 2; i < 64; i++) {
			map.addElement(new DataPoint(i));
		}

		for(int i = 0; i < map.size(); i++) {
			if(map.get(i).myNumber > 347991) {
				System.out.println(map.get(i).myNumber);
				break;
			}
		}
		
		System.out.println(System.currentTimeMillis() - time);
	}

	public static void checkNeighborhood(DataPoint dataPoint) {
		Vector<DataPoint> neighbors = new Vector<DataPoint>();

		neighbors.add(findNeighbor((int) dataPoint.myPosition.getX(), (int) dataPoint.myPosition.getY() + 1));
		neighbors.add(findNeighbor((int) dataPoint.myPosition.getX() - 1, (int) dataPoint.myPosition.getY() + 1));
		neighbors.add(findNeighbor((int) dataPoint.myPosition.getX() - 1, (int) dataPoint.myPosition.getY()));
		neighbors.add(findNeighbor((int) dataPoint.myPosition.getX() - 1, (int) dataPoint.myPosition.getY() - 1));
		neighbors.add(findNeighbor((int) dataPoint.myPosition.getX(), (int) dataPoint.myPosition.getY() - 1));
		neighbors.add(findNeighbor((int) dataPoint.myPosition.getX() + 1, (int) dataPoint.myPosition.getY() - 1));
		neighbors.add(findNeighbor((int) dataPoint.myPosition.getX() + 1, (int) dataPoint.myPosition.getY()));
		neighbors.add(findNeighbor((int) dataPoint.myPosition.getX() + 1, (int) dataPoint.myPosition.getY() + 1));

		int sum = 0;
		for (int i = 0; i < neighbors.size(); i++) {
			if (neighbors.get(i) != null) {
				sum += neighbors.get(i).myNumber;
			}
		}

		dataPoint.myNumber = sum;
	}

	public static DataPoint findNeighbor(int x, int y) {
		for (int i = 0; i < map.size(); i++) {
			if (map.get(i).myPosition.getX() == x && map.get(i).myPosition.getY() == y) {
				return map.get(i);
			}
		}

		return null;
	}

	public static Point coordsOfElement(int element) {
		if (element == 1) {
			return new Point(0, 0);
		}
		if (element == 2) {
			return new Point(1, 0);
		}
		if (element == 3) {
			return new Point(1, 1);
		}
		if (element == 4) {
			return new Point(0, 1);
		}
		if (element == 5) {
			return new Point(-1, 1);
		}
		if (element == 6) {
			return new Point(-1, 0);
		}
		if (element == 7) {
			return new Point(-1, -1);
		}

		// False = TopRightCorner
		// True = BottomLeftCorner
		boolean pos = true;
		int sum = 1 + 2 + 2 + 2;
		int i = 3;
		while (sum < element) {
			pos = !pos;
			sum += 2 * i;
			i++;
		}

		if (sum == element) {
			if (pos == false) {
				return new Point(i / 2, i / 2);
			} else {
				return new Point(-i / 2, -i / 2);
			}
		}

		int diff = sum - element;
		// on BottomLeft
		if (pos) {
			// on BottomRight
			if (diff >= i) {
				// on LeftSide
				diff = diff - (i - 1);
				return new Point(-i / 2 + diff, -i / 2 + (i - 1));
			} else {
				// on TopSide
				return new Point(-i / 2, -i / 2 + diff);
			}
		} else {
			// on TopRight
			if (diff >= i) {
				// on BottomSide
				diff = diff - (i - 1);
				return new Point(i / 2 - diff, i / 2 - (i - 1));
			} else {
				// on RightSide
				return new Point(i / 2, i / 2 - diff);
			}
		}
	}
}
