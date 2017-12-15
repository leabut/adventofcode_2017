import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static String inputPath = "input.txt";
	public static String splitBy = " ";

	public static int[][] fireWall = new int[99][];
	public static boolean[] fireWallStatus = new boolean[99];
	public static int myPos = 0;
	public static int severity = -1;
	public static int delay = 0;

	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(Main.splitBy);
				currentLine[0] = currentLine[0].replaceAll(":", "");
				fireWall[Integer.parseInt(currentLine[0])] = new int[Integer.parseInt(currentLine[1])];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (severity != 0) {
			severity = 0;
			
			// initial state
			for (int i = 0; i < fireWall.length; i++) {
				if (fireWall[i] == null) {
					continue;
				}
				for (int k = 0; k < fireWall[i].length; k++) {
					fireWall[i][k] = 0;
				}
				fireWall[i][0] = 1;
			}

			//delay
			for(int i = 0; i < delay; i++) {
				updateFireWall();
			}
			
			// move through the fireWall
			for (int i = 0; i < fireWall.length; i++) {
				if (isDetected()) {
					severity += i * fireWall[i].length;
				}
				updateFireWall();
				myPos++;
			}
			
			myPos = 0;
			delay++;
		}

		System.out.println(delay);
	}

	public static void updateFireWall() {
		for (int i = 0; i < fireWall.length; i++) {
			if (fireWall[i] == null) {
				continue;
			}

			int scannerPos = getScannerPos(i);

			if (scannerPos == fireWall[i].length - 1) {
				fireWallStatus[i] = true;
			}
			if (scannerPos == 0) {
				fireWallStatus[i] = false;
			}
			if (fireWallStatus[i] == false) {
				fireWall[i][scannerPos] = 0;
				fireWall[i][scannerPos + 1] = 1;
			} else {
				fireWall[i][scannerPos] = 0;
				fireWall[i][scannerPos - 1] = 1;
			}
		}
	}

	public static int getScannerPos(int depth) {
		for (int i = 0; i < fireWall[depth].length; i++) {
			if (fireWall[depth][i] == 1) {
				return i;
			}
		}

		return -1;
	}

	public static boolean isDetected() {
		if (fireWall[myPos] == null) {
			return false;
		}

		if (fireWall[myPos][0] == 1) {
			return true;
		}

		return false;
	}
}