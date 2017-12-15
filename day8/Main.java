import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class Main {

	public static String inputPath = "input.txt";
	public static String splitBy = " ";

	public static Vector<Register> registerVector = new Vector<Register>();

	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(Main.inputPath));
			while ((line = reader.readLine()) != null) {
				String[] currentLine = line.split(Main.splitBy);

				boolean plusMinus = false;
				int plusMinusValue = 0;
				String opName = null;
				Register.myOperator op = Register.myOperator.GREATER;
				int opValue = 0;

				if (searchRegister(currentLine[0]) == null) {
					registerVector.addElement(new Register(currentLine[0]));
				}

				if (currentLine[1].matches("inc")) {
					plusMinus = true;
				}
				
				plusMinusValue = Integer.parseInt(currentLine[2]);
				opName = currentLine[4];
				
				switch (currentLine[5]) {
				case ">":
					op = Register.myOperator.GREATER;
					break;
				case ">=":
					op = Register.myOperator.GREATEREQ;
					break;
				case "<":
					op = Register.myOperator.LESS;
					break;
				case "<=":
					op = Register.myOperator.LESSEQ;
					break;
				case "==":
					op = Register.myOperator.EQUAL;
					break;
				case "!=":
					op = Register.myOperator.UNEQUAL;
					break;
				}
				
				opValue = Integer.parseInt(currentLine[6]);
				
				searchRegister(currentLine[0]).updateRegister(plusMinus, plusMinusValue, opName, op, opValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < registerVector.size(); i++) {
			if(registerVector.get(i).value > max) {
				max = registerVector.get(i).value;
			}
		}
		
		System.out.println(max);
		
		
		max = Integer.MIN_VALUE;
		for(int i = 0; i < registerVector.size(); i++) {
			if(registerVector.get(i).max > max) {
				max = registerVector.get(i).max;
			}
		}
		
		System.out.println(max);
	}

	public static Register searchRegister(String name) {
		for (int i = 0; i < registerVector.size(); i++) {
			if (registerVector.get(i).name.equals(name)) {
				return registerVector.get(i);
			}
		}
		return null;
	}
}