import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static String inputPath = "input.txt";

	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";
		int currentDepth = 0;
		int counter = 0;
		int counter2 = 0;

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null) {
				
				char[] data = line.toCharArray();
				
				boolean ignoreNextChar = false;
				boolean isGarbage = false;
				for(int i = 0; i < data.length; i++) {
					if(ignoreNextChar) {
						ignoreNextChar = false;
						continue;
					}
					if(data[i] == '!') {
						ignoreNextChar = true;
						continue;
					}
					if(data[i] == '>') {
						isGarbage = false;
						continue;
					}
					if(isGarbage) {
						counter2++;
						continue;
					}
					
					switch (data[i]) {
					case '{':
						currentDepth++;
						counter += currentDepth;
						break;
					case '}':
						currentDepth--;
						break;
					case '<':
						isGarbage = true;
						break;
					}
				}
				
				System.out.println(data.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(counter);
		System.out.println(counter2);
		
	}
}