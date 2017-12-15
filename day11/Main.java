import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Main {

	public static String inputPath = "input.txt";

	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";
		String split = ",";
		String[] data = null;
		Vector<Integer> allSteps = new Vector<Integer>();

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			line = reader.readLine();
			data = line.split(split);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		for(int i = 1; i < data.length; i++) {
			String[] param = new String[i];
			for(int j = 0; j < param.length; j++) {
				param[j] = data[j];
			}
			allSteps.add(neededSteps(param));
		}
		
		int max = -1;
		for(int i = 0; i < allSteps.size(); i++) {
			if(allSteps.get(i) > max) {
				max = allSteps.get(i);
			}
		}
		
		System.out.println(max);
	}
	
	
	public static int neededSteps(String[] data) {
		int nCount = 0;
		int nwCount = 0;
		int swCount = 0;
		int sCount = 0;
		int seCount = 0;
		int neCount = 0;
		
		for(int i = 0; i < data.length; i++) {
			if(data[i].matches("n")) {
				nCount++;
			}
			if(data[i].matches("nw")) {
				nwCount++;
			}
			if(data[i].matches("sw")) {
				swCount++;
			}
			if(data[i].matches("s")) {
				sCount++;
			}
			if(data[i].matches("se")) {
				seCount++;
			}
			if(data[i].matches("ne")) {
				neCount++;
			}
		}
		
		int steps = 0;
		
		//eliminate NW NE and SW SE
		if(nwCount > neCount) {
			nwCount = Math.abs(nwCount - neCount);
			nCount += neCount;
			neCount = 0;
		} else {
			neCount = Math.abs(nwCount - neCount);
			nCount += nwCount;
			nwCount = 0;
		}
		if(swCount > seCount) {
			swCount = Math.abs(swCount - seCount);
			sCount += seCount;
			seCount = 0;
		} else {
			seCount = Math.abs(swCount - seCount);
			sCount += swCount;
			swCount = 0;
		}
		
		//eliminate S NE and S NW and N SE and N SW
		int northSouth = nCount - sCount;
		if(northSouth > 0) {
			nCount = northSouth;
			sCount = 0;
			//SW
			if(swCount > 0) {
				if(nCount > swCount) {
					nwCount += swCount;
					nCount = nCount - swCount;
					swCount = 0;
				} else {
					nwCount += nCount;
					swCount = swCount - nCount;
					nCount = 0;
				}
			} else {
				//SE
				if(nCount > seCount) {
					neCount += seCount;
					nCount = nCount - seCount;
					seCount = 0;
				} else {
					neCount += nCount;
					seCount = seCount - nCount;
					nCount = 0;
				}
			}
		} else {
			sCount = Math.abs(northSouth);
			nCount = 0;
			//NW
			if(nwCount > 0) {
				if(sCount > nwCount) {
					swCount += nwCount;
					sCount = sCount - nwCount;
					nwCount = 0;
				} else {
					swCount += sCount;
					nwCount = nwCount - sCount;
					sCount = 0;
				}
			} else {
				//NE
				if(sCount > neCount) {
					seCount += neCount;
					sCount = sCount - neCount;
					neCount = 0;
				} else {
					seCount += sCount;
					neCount = neCount - sCount;
					sCount = 0;
				}
			}
		}
		
		//eliminate SE NW and SW NE and N S
		nCount = Math.max(nCount, sCount);
		neCount = Math.max(neCount, nwCount);
		seCount = Math.max(seCount, swCount);
		
		steps += nCount + neCount + seCount;
		
		return steps;
	}
		
}