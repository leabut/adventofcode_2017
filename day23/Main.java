import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Vector;

public class Main {

	public static String inputPath = "input.txt";

	public static HashMap<Character, Long> hashMap = new HashMap<Character, Long>();
	public static int count = 0;

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

		//part01
		for (int i = 0; i < lineVec.size(); i++) {
			if (lineVec.get(i).length > 2) {
				i += binaryCMD(lineVec.get(i));
			}
		}
		System.out.println(count);
		
		//part02
		hashMap = new HashMap<Character, Long>();
		hashMap.put('a', (long) 1);
		
		for (int i = 0; i < lineVec.size(); i++) {
			if (lineVec.get(i).length > 2) {
				i += binaryCMD(lineVec.get(i));
			}
		}
		
		System.out.println(((Long) hashMap.get('h')));
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

	public static void sub(char reg, long value) {
		hashMap.put(reg, hashMap.get(reg) - value);
	}

	public static long binaryCMD(String[] s) {
		long result = 0;
		long secondParam = 0;

		if (s[1].matches("\\D")) {
			if (!hashMap.containsKey(s[1].toCharArray()[0])) {
				hashMap.put(s[1].toCharArray()[0], (long) 0);
			}
		}

		if (s[2].matches("\\D")) {
			if (!hashMap.containsKey(s[2].toCharArray()[0])) {
				hashMap.put(s[2].toCharArray()[0], (long) 0);
			}
			secondParam = hashMap.get(s[2].toCharArray()[0]);
		} else {
			secondParam = Integer.parseInt(s[2]);
		}

		switch (s[0]) {
		case "set":
			set(s[1].toCharArray()[0], secondParam);
			break;
		case "add":
			add(s[1].toCharArray()[0], secondParam);
			break;
		case "mul":
			mul(s[1].toCharArray()[0], secondParam);
			count++;
			break;
		case "sub":
			sub(s[1].toCharArray()[0], secondParam);
			break;
		case "jnz":
			if (s[1].matches("\\D")) {
				if (hashMap.get(s[1].toCharArray()[0]) != 0) {
					result = secondParam - 1;
				} else {
					result = 0;
				}
			} else {
				if (Integer.parseInt(s[1]) > 0) {
					result = secondParam - 1;
				} else {
					result = 0;
				}
			}
			break;
		default:
			result = Integer.MIN_VALUE;
		}

		return result;
	}
}