public class Register {
	public String name = null;
	public int value = 0;
	public int max = 0;

	public enum myOperator {
		GREATER, LESS, LESSEQ, GREATEREQ, EQUAL, UNEQUAL
	}

	public Register(String name) {
		this.name = name;
	}

	public void updateRegister(boolean plusMinus, int plusMinusValue, String opName, myOperator op, int opValue) {
		int opNameValue = 0;
		for (int i = 0; i < Main.registerVector.size(); i++) {
			if (Main.registerVector.get(i).name.equals(opName)) {
				opNameValue = Main.registerVector.get(i).value;
				break;
			}
		}

		boolean opCheck = false;
		switch (op) {
		case GREATER:
			if (opNameValue > opValue) {
				opCheck = true;
			}
			break;
		case GREATEREQ:
			if (opNameValue >= opValue) {
				opCheck = true;
			}
			break;
		case LESS:
			if (opNameValue < opValue) {
				opCheck = true;
			}
			break;
		case LESSEQ:
			if (opNameValue <= opValue) {
				opCheck = true;
			}
			break;
		case EQUAL:
			if (opNameValue == opValue) {
				opCheck = true;
			}
			break;
		case UNEQUAL:
			if (opNameValue != opValue) {
				opCheck = true;
			}
			break;
		}

		if (opCheck) {
			if (plusMinus) {
				this.value += plusMinusValue;
			} else {
				this.value -= plusMinusValue;
			}
		}
		
		if(this.value > max) {
			max = this.value;
		}
	}
}
