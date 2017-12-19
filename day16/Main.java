import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static String inputPath = "input.txt";

	public static char[] prgListOriginal = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p' };
	public static char[] prgList = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p' };

	public static int[] transform = null;

	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";
		String split = ",";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			line = reader.readLine();

			String[] data = line.split(split);
			char[] tmp = null;

			for (int i = 0; i < data.length; i++) {
				if (data[i].matches("^s.*")) {
					tmp = data[i].toCharArray();
					tmp[0] = ' ';
					data[i] = new String(tmp).replaceAll(" ", "");

					spin(Integer.parseInt(data[i]));
				}
				if (data[i].matches("^x.*")) {
					tmp = data[i].toCharArray();
					tmp[0] = ' ';
					data[i] = new String(tmp).replaceAll(" ", "");

					String[] tmp2 = data[i].split("/");

					exchange(Integer.parseInt(tmp2[0]), Integer.parseInt(tmp2[1]));
				}
				if (data[i].matches("^p.*")) {
					tmp = data[i].toCharArray();
					tmp[0] = ' ';
					data[i] = new String(tmp).replaceAll(" ", "");

					String[] tmp2 = data[i].split("/");

					partner(tmp2[0].toCharArray()[0], tmp2[1].toCharArray()[0]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// print solution
		System.out.println("part1");
		for (int i = 0; i < prgList.length; i++) {
			System.out.print(prgList[i]);
		}
		System.out.println();

		// begin part2
		transform = new int[16];

		// calculate transform vector
		// transform[3] = 7 changes transform[3+7]
		// transform[3] = -2 changes transform[3-2]
		for (int i = 0; i < prgListOriginal.length; i++) {
			for (int k = 0; k < prgList.length; k++) {
				if (prgListOriginal[i] == prgList[k]) {
					transform[i] = k;
				}
			}
		}

		// calculate 1 billion dances
		for (int i = 0; i < Math.pow(10, 9) - 1; i++) {
			doTransform();
		}

		// print solution
		System.out.println("part2");
		for (int i = 0; i < prgList.length; i++) {
			System.out.print(prgList[i]);
		}
	}

	public static void doTransform() {
		char[] update = new char[16];

		for (int i = 0; i < prgList.length; i++) {
			update[transform[i]] = prgList[i];
		}
		prgList = update;
	}

	public static void spin(int x) {
		char[] front = new char[prgList.length - x];
		char[] back = new char[x];

		for (int i = 0; i < prgList.length; i++) {
			if (i < front.length) {
				front[i] = prgList[i];
			} else {
				back[i - front.length] = prgList[i];
			}
		}

		for (int i = 0; i < prgList.length; i++) {
			if (i < back.length) {
				prgList[i] = back[i];
			} else {
				prgList[i] = front[i - back.length];
			}
		}
	}

	public static void exchange(int a, int b) {
		char tmp = prgList[a];
		prgList[a] = prgList[b];
		prgList[b] = tmp;
	}

	public static void partner(char a, char b) {
		int aPos = -1;
		int bPos = -1;

		for (int i = 0; i < prgList.length; i++) {
			if (prgList[i] == a) {
				aPos = i;
			}
			if (prgList[i] == b) {
				bPos = i;
			}
		}

		exchange(aPos, bPos);
	}
}