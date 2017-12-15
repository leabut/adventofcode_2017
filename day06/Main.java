import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		Vector<Integer[]> data = new Vector<Integer[]>();
		String inputPath = "input.txt";
		BufferedReader reader = null;
		String line = "";
		String splitBy = "\t";

		try {
			reader = new BufferedReader(new FileReader(inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(splitBy);
				Integer[] dataVector = new Integer[currentLine.length];

				for (int i = 0; i < currentLine.length; i++) {
					dataVector[i] = Integer.parseInt(currentLine[i]);
				}

				data.addElement(dataVector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int count = 0;

		while (checkDouplicate(data, count) == false) {
			Integer[] nextDataVector = data.get(count).clone();

			// find max
			int maxPos = 0;
			int maxValue = -1;
			for (int i = 0; i < data.get(count).length; i++) {
				if (data.get(count)[i] > maxValue) {
					maxPos = i;
					maxValue = data.get(count)[i];
				}
			}

			int tmp = nextDataVector[maxPos];
			nextDataVector[maxPos] = 0;

			// rearrange
			int whileCounter = (maxPos + 1) % data.get(count).length;
			while (tmp > 0) {
				nextDataVector[whileCounter % data.get(count).length]++;
				whileCounter++;
				tmp--;
			}

			/*
			 * System.out.println("i = " + (count+1) + " : "); for(int i = 0; i <
			 * nextDataVector.length; i++) { System.out.print(nextDataVector[i] + " , "); }
			 * 
			 * System.out.println();
			 */

			data.addElement(nextDataVector);
			count++;
		}

		System.out.println(count);
	}

	public static boolean checkDouplicate(Vector<Integer[]> data, int count) {
		Integer[] dataVectorOne = data.get(count);
		for (int k = 0; k < data.size(); k++) {
			if (count == k) {
				continue;
			}
			Integer[] dataVectorTwo = data.get(k);
			for (int j = 0; j < dataVectorTwo.length; j++) {
				if (Arrays.equals(dataVectorOne, dataVectorTwo)) {
					System.out.println(k);
					return true;
				}
			}
		}

		return false;
	}
}
