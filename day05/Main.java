import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		Vector<Integer> data = new Vector<Integer>();
		String inputPath = "input.txt";
		BufferedReader reader = null;
		String line = "";
		String splitBy = "\n";

		try {
			reader = new BufferedReader(new FileReader(inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(splitBy);
				String[] lineElements = currentLine[0].split(" ");
				data.addElement(Integer.parseInt(lineElements[0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int count = 0;
		int i = 0;
		int tmp;
		
		while(i < data.size() && i >= 0) {
			tmp = i;
			i += data.get(i);
			
			if(data.get(tmp) >= 3) {
				data.set(tmp, data.get(tmp)-1);
			} else {
				data.set(tmp, data.get(tmp)+1);
			}
			count++;
		}
		
		System.out.println(count);
	}
}
