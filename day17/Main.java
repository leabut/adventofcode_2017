import java.util.Vector;

public class Main {
	public static Vector<Integer> circBuff = new Vector<Integer>();
	public static int currentPos = 1;
	
	public static int input = 312;
	public static int insertions = 2017;
	public static int count = 2;

	public static void main(String[] args) {
		part1();
		part2();
	}
	
	public static void part2() {
		currentPos = 1;
		
		insertions = (int) (5*Math.pow(10, 7));
		count = 2;
		
		int posOneElement = 0;
		int buffSize = 2;
		
		//calc last element on circBuff[1]
		for(int i = 0; i < insertions; i++) {
			//if circBuff[1] gets updated
			if(currentPos == 1) {
				posOneElement = count-1;
			}
			
			int tmp = (currentPos + input) % buffSize;
			currentPos = tmp + 1;
			count++;
			buffSize++;
		}
		
		//print solution
		System.out.println("part2()");
		System.out.println(posOneElement);
	}
	
	public static void part1() {		
		//initialize circBuff
		circBuff.add(0);
		circBuff.add(1);
		
		//do all amount of insertions into cirbuff
		for(int i = 0; i < insertions; i++) {
			int tmp = (currentPos + input) % circBuff.size();
			circBuff.add(tmp, count);
			currentPos = tmp + 1;
			count++;
		}
		
		//print solution
		System.out.println("part1()");
		for(int i = 0; i < circBuff.size(); i++) {
			if(circBuff.get(i) == 2017) {
				System.out.print(circBuff.get(i-1) + " , ");
				System.out.print(circBuff.get(i) + " , ");
				System.out.print(circBuff.get(i+1) + " , ");
				
			}
		}
		
		System.out.println();
	}

}