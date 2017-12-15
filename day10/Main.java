import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static String inputPath = "input.txt";

	public static void main(String[] args) {
		char[] charInput = null;
		int[] list = new int[256];
		int[] suffix = { 17, 31, 73, 47, 23 };
		int skipSize = 0;
		int startingPos = 0;

		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			line = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		charInput = line.toCharArray();
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

		int[] result = new int[16];

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
			System.out.print(Integer.toHexString(result[i]));
		}
	}
}