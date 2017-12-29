import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static Matrix cluster = null;

	public static void main(String[] args) {

		readIn("input.txt");

		for(int i = 0; i < 10000000; i++) {
			cluster.step2();
		}
		
		System.out.println(cluster.count);
	}

	public static void readIn(String path) {
		BufferedReader reader = null;
		String line = "";
		String matrixString = "";

		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				matrixString = matrixString.concat(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cluster = new Matrix(matrixString, (int) Math.sqrt(matrixString.length()));
	}
}