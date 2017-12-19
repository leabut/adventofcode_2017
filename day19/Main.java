import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Main {

	public enum Direction {
		NORTH,SOUTH,EAST,WEST
	}
	
	public static String inputPath = "input.txt";
	
	public static char[][] data = new char[201][202];
	public static Point currentPos;
	public static Point nextPos;
	
	public static int stepCount = 0; 
	
	public static Vector<Character> result = new Vector<Character>();

	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";
		
		int rowPos = 0;
		boolean end = false;

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null) {
				char[] tmp = line.toCharArray();
				data[rowPos] = tmp;
				rowPos++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for(int i = 0; i < data[0].length; i++) {
			if(data[0][i] == '|') {
				currentPos = new Point(i,0);
				break;
			}
		}
		
		Direction dir = Direction.SOUTH;
		
		while(!end) {			
			switch (dir) {
			case EAST:
				nextPos = new Point((int) currentPos.getX()+1, (int) currentPos.getY());
				break;
			case NORTH:
				nextPos = new Point((int) currentPos.getX(), (int) currentPos.getY()-1);
				break;
			case SOUTH:
				nextPos = new Point((int) currentPos.getX(), (int) currentPos.getY()+1);
				break;
			case WEST:
				nextPos = new Point((int) currentPos.getX()-1, (int) currentPos.getY());
				break;
			default:
				break;
			}
			
			char tmp = data[(int) nextPos.getY()][(int) nextPos.getX()];
			if(tmp == ' ') {
				end = true;
			}
			if(Character.isLetter(tmp)) {
				result.add(tmp);
			}			
			if(tmp == '+') {
				dir = getDir(dir);
			}
			
			currentPos = nextPos;
			
			stepCount++;
		}
		
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i));
		}
		
		System.out.println();
		System.out.println(stepCount);
	}
	
	public static Direction getDir(Direction old_dir) {
		if(old_dir == Direction.NORTH || old_dir == Direction.SOUTH) {
			if(data[(int) nextPos.getY()][(int) (nextPos.getX()+1)] == ' ') {
				return Direction.WEST;
			}
			
			if(data[(int) nextPos.getY()][(int) (nextPos.getX()-1)] == ' ') {
				return Direction.EAST;
			}
		}
		
		if(old_dir == Direction.EAST || old_dir == Direction.WEST) {
			if(data[(int) nextPos.getY()+1][(int) (nextPos.getX())] == ' ') {
				return Direction.NORTH;
			}
			
			if(data[(int) nextPos.getY()-1][(int) (nextPos.getX())] == ' ') {
				return Direction.SOUTH;
			}
		}
		
		return Direction.NORTH;
	}
}