import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Node {
	public int weight = 0;
	public Node parent = null;
	public Vector<Node> childs = new Vector<Node>();
	public String name;
	public int mySum;
	
	public Node(String name) {
		int weight = 0;
		boolean finalCall = false;
		
		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null && finalCall == false) {
				String[] currentLine = line.split(Main.splitBy);

				for (int i = 0; i < currentLine.length; i++) {
					if(i == 0) {
						if(!currentLine[i].equals(name)) {
							break;
						} else {
							finalCall = true;
						}
					}
					if (currentLine[i].matches("\\(.*\\)")) {
						currentLine[i] = currentLine[i].replaceAll("\\(", "");
						currentLine[i] = currentLine[i].replaceAll("\\)", "");
						weight = Integer.parseInt(currentLine[i]);
					}
					if(i > 2) {
						this.addChild(currentLine[i].replace(",", ""));						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.name = name;
		this.weight = weight;
		
		boolean mismatch = false;		
		for(int i = 0; i < childs.size(); i++) {
			for(int k = 0; k < childs.size(); k++) {
				if(i == k) {
					continue;
				}
				if(childs.get(i).mySum != childs.get(k).mySum) {
					mismatch = true;
					i = childs.size();
					break;
				}
			}
		}
		
		if(mismatch) {
			System.out.println("Mismatch: " + this.name);
			for(int i = 0; i < childs.size(); i++) {
			System.out.print(childs.get(i).name + "\t");
			}
			System.out.println();
			for(int i = 0; i < childs.size(); i++) {
			System.out.print(childs.get(i).mySum + "\t");
			}
			System.out.println();
		}
		
		for(int i = 0; i < childs.size(); i++) {
			mySum += childs.get(i).mySum;
		}
		mySum += this.weight;
	}
	
	public void addChild(String name) {
		Node child = Main.searchNode(name);
		
		if(child != null) {
			child.parent = this;
			childs.addElement(child);
		} else {
			Main.nodeVector.addElement(new Node(name));
			this.addChild(name);
		}
	}
}
