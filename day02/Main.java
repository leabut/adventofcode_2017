import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		Vector<String[]> dataString = new Vector<String[]>();
		String inputPath = "input.txt";
		BufferedReader reader = null;
		String line = "";
		String cvsSplitBy = "\t";

		int sum = 0;
		int sum2 = 0;

		try {
			reader = new BufferedReader(new FileReader(inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(cvsSplitBy);
				dataString.add(currentLine);				

				int min = Integer.MAX_VALUE;
				int max = 0;
				for (int i = 0; i < currentLine.length; i++) {
					if (Integer.parseInt(currentLine[i]) > max) {
						max = Integer.parseInt(currentLine[i]);
					}
					if (Integer.parseInt(currentLine[i]) < min) {
						min = Integer.parseInt(currentLine[i]);
					}
				}
				sum += max - min;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(sum);
		
		
		for(int i = 0; i < dataString.size(); i++)  {
			for(int k = 0; k < dataString.get(i).length; k++) {
				for(int j = 0; j < dataString.get(i).length; j++) {
					if(k == j) {
						continue;
					}
					if((Integer.parseInt(dataString.get(i)[k]) % Integer.parseInt(dataString.get(i)[j])) == 0) {
						sum2 += Integer.parseInt(dataString.get(i)[k]) / Integer.parseInt(dataString.get(i)[j]);
						k = dataString.get(i).length;
						break;
					}
					if((Integer.parseInt(dataString.get(i)[j]) % Integer.parseInt(dataString.get(i)[k])) == 0) {
						sum2 += Integer.parseInt(dataString.get(i)[j]) / Integer.parseInt(dataString.get(i)[k]);
						k = dataString.get(i).length;
						break;
					}
				}
			}
		}
		
		System.out.println(sum2);
	}
}
