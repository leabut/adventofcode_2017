import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Main {

	public static String inputPath = "input.txt";
	public static int[][] memoryMatrix = new int[128][128];
	public static Vector<Point> toVisit = null;
	public static Point currentPoint = new Point(-1, -1);

	public static Vector<Point> memoryMatrixVec = new Vector<Point>();

	public static int count = 0;
	public static int countGroup = 0;

	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			line = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < memoryMatrix.length; i++) {
			String toHash = line.concat("-" + i);
			char[] hash = calcKnotHash(toHash.toCharArray());
			for (int k = 0; k < hash.length; k++) {
				char[] memoryLine = toBinary(hash[k]);
				for (int m = 0; m < memoryLine.length; m++) {
					memoryMatrix[i][k * 8 + m] = memoryLine[m];
				}
			}
		}

		for (int i = 0; i < memoryMatrix.length; i++) {
			for (int k = 0; k < memoryMatrix[i].length; k++) {
				if (memoryMatrix[i][k] == 1) {
					memoryMatrixVec.addElement(new Point(i, k));
					count++;
				}
			}
		}

		System.out.println(count);

		while (memoryMatrixVec.size() != 0) {
			toVisit = new Vector<Point>();
			toVisit.addElement(memoryMatrixVec.get(0));

			while(toVisit.size() != 0) {
				Point a = new Point((int) toVisit.get(0).getX() + 1, (int) toVisit.get(0).getY());
				Point b = new Point((int) toVisit.get(0).getX() - 1, (int) toVisit.get(0).getY());
				Point c = new Point((int) toVisit.get(0).getX(), (int) toVisit.get(0).getY() + 1);
				Point d = new Point((int) toVisit.get(0).getX(), (int) toVisit.get(0).getY() - 1);
				
				if(memoryMatrixVec.contains(a)) {
					if(!toVisit.contains(a)) {
						toVisit.add(a);
						memoryMatrixVec.remove(a);
					}
				}
				
				if(memoryMatrixVec.contains(b)) {
					if(!toVisit.contains(b)) {
						toVisit.add(b);
						memoryMatrixVec.remove(b);
					}
				}
				
				if(memoryMatrixVec.contains(c)) {
					if(!toVisit.contains(c)) {
						toVisit.add(c);
						memoryMatrixVec.remove(c);
					}
				}
				
				if(memoryMatrixVec.contains(d)) {
					if(!toVisit.contains(d)) {
						toVisit.add(d);
						memoryMatrixVec.remove(d);
					}
				}
				memoryMatrixVec.remove(toVisit.remove(0));
			}
			
			countGroup++;
		}

		System.out.println(countGroup);
	}

	public static char[] toBinary(int number) {
		char[] binary = new char[8];
		int index = 0;
		int copyOfInput = number;
		while (copyOfInput > 0) {
			binary[index++] = (char) (copyOfInput % 2);
			copyOfInput = copyOfInput / 2;
		}

		return binary;
	}

	public static char[] calcKnotHash(char[] charInput) {
		int[] list = new int[256];
		int[] suffix = { 17, 31, 73, 47, 23 };
		int skipSize = 0;
		int startingPos = 0;

		char[] input = new char[charInput.length + suffix.length];

		for (int i = 0; i < charInput.length; i++) {
			input[i] = charInput[i];
		}

		for (int i = charInput.length; i < charInput.length + suffix.length; i++) {
			input[i] = (char) suffix[i - charInput.length];
		}

		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}

		for (int l = 0; l < 64; l++) {
			for (int i = 0; i < input.length; i++) {
				int in = input[i];

				for (int j = 0; j < in / 2; j++) {
					int tmp = list[(j + startingPos) % list.length];
					list[(j + startingPos) % list.length] = list[(startingPos + in - j - 1) % list.length];
					list[(startingPos + in - j - 1) % list.length] = tmp;
				}

				startingPos = (startingPos + in + skipSize) % list.length;
				skipSize++;
			}
		}

		char[] result = new char[16];

		for (int i = 0; i < result.length; i++) {
			char tmp = 0;
			for (int j = i * 16; j < i * 16 + 16; j++) {
				if (j == i * 16) {
					tmp = (char) list[j];
				} else {
					tmp ^= (char) list[j];
				}
			}
			result[i] = tmp;
		}

		return result;
	}
}