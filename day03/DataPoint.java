import java.awt.Point;

public class DataPoint {
	public int myElementID;
	public Point myPosition;
	public int myNumber;
	
	public DataPoint(int myElementID) {
		this.myElementID = myElementID;
		myPosition = Main.coordsOfElement(myElementID);
		Main.checkNeighborhood(this);
	}
}
