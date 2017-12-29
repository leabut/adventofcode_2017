import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

	public static HashMap<Integer, Particle> hashMap = new HashMap<Integer, Particle>();

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		readIn("input.txt");

		//simulate particles
		for (int i = 0; i < 1000; i++) {
			Iterator it = hashMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				((Particle) pair.getValue()).update();
			}
		}
		
		//find minimum
		long min = Long.MAX_VALUE;
		int minId = -1;
		Iterator iter = hashMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry pair = (Map.Entry) iter.next();
			if (min > ((Particle) pair.getValue()).distanceFromOrigin()) {
				min = ((Particle) pair.getValue()).distanceFromOrigin();
				minId = ((Particle) pair.getValue()).id;
			}
		}
		
		System.out.println("part1");
		System.out.println(minId);
		
		// part2
		hashMap = new HashMap<Integer, Particle>();
		readIn("input.txt");
		
		//simulate particles
		for (int i = 0; i < 1000; i++) {
			Iterator it = hashMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();

				//find colliding particles
				Iterator it2 = hashMap.entrySet().iterator();
				while (it2.hasNext()) {
					Map.Entry pair2 = (Map.Entry) it2.next();

					if (((Particle) pair.getValue()).equals2((Particle) pair2.getValue())
							&& ((Particle) pair.getValue()).id != ((Particle) pair2.getValue()).id) {
						((Particle) pair2.getValue()).isFlagged = true;
						((Particle) pair.getValue()).isFlagged = true;
					}
				}
			}

			//delete collided particles
			it = hashMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				if (((Particle) pair.getValue()).isFlagged) {
					hashMap.remove(pair.getKey());
					it = hashMap.entrySet().iterator();
				}
			}

			//simulate particles
			it = hashMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				((Particle) pair.getValue()).update();
			}
		}

		System.out.println("part2");
		System.out.println(hashMap.size());
	}
	
	public static void readIn(String path) {		
		BufferedReader reader = null;
		String line = "";
		String split = ", ";
		
		int counter = 0;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				String[] attribute = line.split(split);

				Point3D position = null;
				Point3D velocity = null;
				Point3D acceleration = null;

				for (int i = 0; i < attribute.length; i++) {
					char[] attributeChar = attribute[i].toCharArray();
					char attributeName = attributeChar[0];

					attributeChar[0] = ' ';
					attributeChar[1] = ' ';
					attributeChar[2] = ' ';
					attributeChar[attributeChar.length - 1] = ' ';

					String attributeString = new String(attributeChar).replaceAll(" ", "");
					String[] attributeCoords = attributeString.split(",");

					switch (attributeName) {
					case 'p':
						int pX = Integer.parseInt(attributeCoords[0]);
						int pY = Integer.parseInt(attributeCoords[1]);
						int pZ = Integer.parseInt(attributeCoords[2]);
						position = new Point3D(pX, pY, pZ);
					case 'v':
						int vX = Integer.parseInt(attributeCoords[0]);
						int vY = Integer.parseInt(attributeCoords[1]);
						int vZ = Integer.parseInt(attributeCoords[2]);
						velocity = new Point3D(vX, vY, vZ);
					case 'a':
						int aX = Integer.parseInt(attributeCoords[0]);
						int aY = Integer.parseInt(attributeCoords[1]);
						int aZ = Integer.parseInt(attributeCoords[2]);
						acceleration = new Point3D(aX, aY, aZ);
					}
				}

				hashMap.put(counter, new Particle(counter, velocity, acceleration, position));
				counter++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}