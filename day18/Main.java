import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Vector;

public class Main {

	public static String inputPath = "input.txt";

	public static HashMap<Character, Long> hashMap = new HashMap<Character, Long>();
	public static long freq = 0;

	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";
		Vector<String[]> lineVec = new Vector<String[]>();
		String split = " ";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null) {
				lineVec.add(line.split(split));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < lineVec.size(); i++) {
			if (lineVec.get(i).length > 2) {
				i += binaryCMD(lineVec.get(i));
			} else {
				if(!unaryCMD(lineVec.get(i))) {
					break;
				}
			}
		}

		System.out.println(freq);
	}

	public static void set(char reg, long value) {
		hashMap.put(reg, value);
	}

	public static void add(char reg, long value) {
		hashMap.put(reg, hashMap.get(reg) + value);
	}

	public static void mul(char reg, long value) {
		hashMap.put(reg, hashMap.get(reg) * value);
	}

	public static void mod(char reg, long value) {
		hashMap.put(reg, hashMap.get(reg) % value);
	}

	public static long binaryCMD(String[] s) {
		long result = 0;
		long value = 0;

		if (!hashMap.containsKey(s[1].toCharArray()[0])) {
			hashMap.put(s[1].toCharArray()[0], (long) 0);
		}

		if (s[2].matches("\\D")) {
			if (!hashMap.containsKey(s[2].toCharArray()[0])) {
				hashMap.put(s[2].toCharArray()[0], (long) 0);
			}
			value = hashMap.get(s[2].toCharArray()[0]);
		} else {
			value = Integer.parseInt(s[2]);
		}

		switch (s[0]) {
		case "set":
			set(s[1].toCharArray()[0], value);
			break;
		case "add":
			add(s[1].toCharArray()[0], value);
			break;
		case "mul":
			mul(s[1].toCharArray()[0], value);
			break;
		case "mod":
			mod(s[1].toCharArray()[0], value);
			break;
		case "jgz":
			if (hashMap.get(s[1].toCharArray()[0]) > 0) {
				result = value - 1;
			} else {
				result = 0;
			}
			break;
		default:
			result = Integer.MIN_VALUE;
		}

		return result;
	}

	public static boolean unaryCMD(String[] s) {
		if (!hashMap.containsKey(s[1].toCharArray()[0])) {
			hashMap.put(s[1].toCharArray()[0], (long) 0);
		}

		switch (s[0]) {
		case "snd":
			freq = hashMap.get(s[1].toCharArray()[0]);
			return true;
		case "rcv":
			if (hashMap.get(s[1].toCharArray()[0]) > 0) {
				hashMap.put(s[1].toCharArray()[0], freq);
				return false;
			}
			return true;
		default:
			return false;
		}
	}
}