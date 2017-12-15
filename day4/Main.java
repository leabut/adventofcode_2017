import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		String inputPath = "input.txt";
		BufferedReader reader = null;
		String line = "";
		String splitBy = "\n";
		
		int sum2 = 0;

		try {
			reader = new BufferedReader(new FileReader(inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(splitBy);
				String[] lineElements = currentLine[0].split(" ");

				for (int i = 0; i < lineElements.length; i++) {
					boolean test = true;
					int[] histogramOne = new int[255];
					char[] wordOne = lineElements[i].toCharArray();
					for (int k = 0; k < wordOne.length; k++) {
						histogramOne[wordOne[k]]++;
					}

					for (int k = 0; k < lineElements.length; k++) {
						if (test == false) {
							break;
						}

						if (i == k) {
							continue;
						}

						int[] histogramTwo = new int[255];
						char[] wordTwo = lineElements[k].toCharArray();
						for (int j = 0; j < wordTwo.length; j++) {
							histogramTwo[wordTwo[j]]++;
						}

						if (wordTwo.length == wordOne.length) {
							for (int j = 0; j < histogramOne.length; j++) {
								if (histogramOne[j] == histogramTwo[j]) {
									test = false;
								} else {
									test = true;
									break;
								}
							}
						}
						/*
						 * if(test == false) { System.out.println(String.copyValueOf(wordTwo) + " " +
						 * String.copyValueOf(wordOne)); System.out.println(currentLine[0]); }
						 */
					}
					if (test == false) {
						sum2++;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(512 - sum2);
	}
}
