import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Vector;

public class Main {

	public static Vector<Vector<Port>> allPaths = new Vector<Vector<Port>>();
	public static Vector<Port> startPorts = new Vector<Port>();
	public static Vector<Port> ports = new Vector<Port>();
	public static HashMap<Integer, Vector<Port>> hashMap = new HashMap<Integer, Vector<Port>>();

	public static void main(String[] args) {
		readIn("input.txt");

		// part01
		// find all possible start ports
		for (int i = 0; i < ports.size(); i++) {
			if (ports.get(i).sideA == 0 || ports.get(i).sideB == 0) {
				startPorts.add(ports.get(i));
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < startPorts.size(); i++) {
			int ret = recursiveAction(startPorts.get(i));
			if (ret > max) {
				max = ret;
			}
		}

		System.out.println(max);
	}

	@SuppressWarnings("unchecked")
	private static int recursiveAction(Port currPort) {

		Vector<Port> possibleConnections = null;
		if (hashMap.containsKey(currPort.sideA)) {
			possibleConnections = hashMap.get(currPort.sideA);
		}
		if (hashMap.containsKey(currPort.sideB)) {
			possibleConnections = hashMap.get(currPort.sideB);
		}

		if (possibleConnections == null) {
			return currPort.stability();
		}

		// backup hashMap
		HashMap<Integer, Vector<Port>> hashMapBefore = (HashMap<Integer, Vector<Port>>) hashMap.clone();
		hashMap.remove(currPort.sideA);
		hashMap.remove(currPort.sideB);
		HashMap<Integer, Vector<Port>> hashMapReset = (HashMap<Integer, Vector<Port>>) hashMap.clone();

		int max = 0;
		for (int i = 0; i < possibleConnections.size(); i++) {
			if (possibleConnections.get(i).equals2(currPort)) {
				continue;
			}

			int ret = recursiveAction(possibleConnections.get(i));
			if (ret > max) {
				max = ret;
			}
			hashMap = hashMapReset;
		}
		
		hashMap = hashMapBefore;
		return max + currPort.stability();
	}

	public static void readIn(String path) {
		BufferedReader reader = null;
		String line = "";
		String split = "/";
		String[] tmp = null;

		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				tmp = line.split(split);

				ports.add(new Port(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// init hashMap
		Vector<Port> curVec = null;
		for (int i = 0; i < ports.size(); i++) {
			if (!hashMap.containsKey(ports.get(i).sideA)) {
				curVec = new Vector<Port>();
				hashMap.put(ports.get(i).sideA, curVec);
			} else {
				curVec = hashMap.get(ports.get(i).sideA);
			}

			for (int k = 0; k < ports.size(); k++) {
				if (ports.get(k).sideA == ports.get(i).sideA || ports.get(k).sideB == ports.get(i).sideA) {
					if (!curVec.contains(ports.get(k))) {
						curVec.add(ports.get(k));
					}
				}
			}
			hashMap.put(ports.get(i).sideA, curVec);
		}

		curVec = null;
		for (int i = 0; i < ports.size(); i++) {
			if (!hashMap.containsKey(ports.get(i).sideB)) {
				curVec = new Vector<Port>();
				hashMap.put(ports.get(i).sideB, curVec);
			} else {
				curVec = hashMap.get(ports.get(i).sideB);
			}

			for (int k = 0; k < ports.size(); k++) {
				if (ports.get(k).sideA == ports.get(i).sideB || ports.get(k).sideB == ports.get(i).sideB) {
					if (!curVec.contains(ports.get(k))) {
						curVec.add(ports.get(k));
					}
				}
			}
			hashMap.put(ports.get(i).sideB, curVec);
		}
	}
}