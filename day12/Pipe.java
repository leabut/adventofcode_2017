import java.util.Vector;

public class Pipe {
	public int id = 0;
	public Vector<Pipe> connections = new Vector<Pipe>();
	public boolean isVisited = false;
	public boolean isConnected = false;
	
	public Pipe(int id) {
		this.id = id;
	}
	
	public void addConnections(int[] intConnections) {
		for(int i = 0; i < intConnections.length; i++) {
			connections.addElement(Main.searchPipe(intConnections[i]));
		}
	}
}
