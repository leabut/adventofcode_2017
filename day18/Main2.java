import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Vector;

public class Main2 {

	public static String inputPath = "input.txt";
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";
		Vector<String[]> lineVec = new Vector<String[]>();
		String split = " ";

		try {
			reader = new BufferedReader(new FileReader(Main2.inputPath));
			while ((line = reader.readLine()) != null) {
				lineVec.add(line.split(split));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap<Integer, Process> parallelProcess = new HashMap<Integer, Process>();
		Process first = new Process(0, (Vector<String[]>) lineVec.clone(), parallelProcess);
		Process second = new Process(1, (Vector<String[]>) lineVec.clone(), parallelProcess);
		
		parallelProcess.put(0, first);
		parallelProcess.put(1, second);
		
		while(!(first.isBlocking && second.isBlocking)) {
			first.step();
			second.step();
		}
		
		System.out.println(second.sndCounter);
	}
}