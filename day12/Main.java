import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Main {

	public static String inputPath = "input.txt";
	public static String splitBy = " ";

	public static Vector<Pipe> pipeVector = new Vector<Pipe>();
	public static Vector<Pipe> unconnectedVector = new Vector<Pipe>();

	static int searchId = 0;	
	static int count = 0;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(Main.splitBy);

				pipeVector.addElement(new Pipe(Integer.parseInt(currentLine[0])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(Main.splitBy);

				int id = 0;
				int[] connections = new int[currentLine.length - 2];
				for (int i = 0; i < currentLine.length; i++) {
					if (i == 0) {
						id = Integer.parseInt(currentLine[0]);
						continue;
					}
					if (currentLine[i].equals("<->")) {
						continue;
					}
					connections[i - 2] = Integer.parseInt(currentLine[i].replaceAll(",", ""));
				}
				searchPipe(id).addConnections(connections);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < pipeVector.size(); i++) {
			System.out.println("Main: " + pipeVector.get(i).id);
			System.out.print("Childs: ");
			for (int k = 0; k < pipeVector.get(i).connections.size(); k++) {
				System.out.print(pipeVector.get(i).connections.get(k).id + " , ");
			}
			System.out.println();
		}

		searchId = 0;
		visitPipe(0);
		count++;
		
		unconnectedVector = (Vector<Pipe>) pipeVector.clone();
		
		while(unconnectedVector.size() > 0) {
			for(int i = 0; i < unconnectedVector.size(); i++) {
				if(unconnectedVector.get(i).isConnected) {
					unconnectedVector.removeElement(unconnectedVector.get(i));
					i--;
				}
			}
			
			if(unconnectedVector.size() == 0) {
				continue;
			}
			
			for(int i = 0; i < unconnectedVector.size(); i++) {
				unconnectedVector.get(i).isVisited = false;
			}
			
			searchId = unconnectedVector.get(0).id;
			visitPipe(unconnectedVector.get(0).id);
			count++;
		}

		System.out.println(count);
	}

	public static void visitPipe(int id) {
		Pipe pipe = searchPipe(id);

		//recursive return
		if (pipe.isVisited) {
			return;
		}

		pipe.isVisited = true;

		//check if connected childs are connected
		for (int i = 0; i < pipe.connections.size(); i++) {
			if (pipe.connections.get(i).id == 0 || pipe.connections.get(i).isConnected || pipe.id == searchId) {
				if(pipe.isConnected == false) {
					pipe.isConnected = true;
					//count++;
				}
			}
		}

		//check if this pipe is connected
		//update its connected childs
		if (pipe.isConnected) {
			for (int i = 0; i < pipe.connections.size(); i++) {
				if (pipe.connections.get(i).isConnected == false) {
					pipe.connections.get(i).isConnected = true;
					//count++;
				}
			}
		}

		//recursive call on all connected childs
		for (int i = 0; i < pipe.connections.size(); i++) {
			visitPipe(pipe.connections.get(i).id);
		}
	}

	public static Pipe searchPipe(int id) {
		for (int i = 0; i < pipeVector.size(); i++) {
			if (pipeVector.get(i).id == id) {
				return pipeVector.get(i);
			}
		}
		return null;
	}
}