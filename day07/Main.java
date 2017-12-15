import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Main {

	public static String inputPath = "input.txt";
	public static String splitBy = " ";
	
	public static Vector<Node> nodeVector = new Vector<Node>();
	
	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(Main.splitBy);

				for (int i = 0; i < currentLine.length; i++) {
					if(i == 0) {
						if(searchNode(currentLine[0]) != null) {
							break;
						} else {
							nodeVector.addElement(new Node(currentLine[0]));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < nodeVector.size(); i++) {
			if(nodeVector.get(i).parent == null) {
				System.out.println(nodeVector.get(i).name);
			}
		}
	}
	
	public static Node searchNode(String name) {
		for(int i = 0; i < nodeVector.size(); i++) {
			if(nodeVector.get(i).name.equals(name)) {
				return nodeVector.get(i);
			}
		}
		return null;
	}
}