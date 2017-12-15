
public class Main {

	public static void main(String[] args) {
		char[] blub = args[0].toCharArray();
		
		int sum = 0;
		int sum2 = 0;
		
		if(blub[0] == blub[blub.length-1]) {
			sum += Character.getNumericValue(blub[0]);
		}
		
		for(int i = 0; i < blub.length-1; i++) {
			if(blub[i] == blub[i+1]) {
				sum += Character.getNumericValue(blub[i]);
			}
		}
		
		for(int i = 0; i < blub.length; i++) {
			if(blub[i] == blub[(i+blub.length/2)%blub.length]) {
				sum2 += Character.getNumericValue(blub[i]);
			}
		}
		
		System.out.println(sum2);
		System.out.println(blub.length);

	}

}
