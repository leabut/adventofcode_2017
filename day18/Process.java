import java.util.HashMap;
import java.util.Vector;

public class Process {

	public HashMap<Character, Long> registers = null;
	private Vector<String[]> instructions = null;
	public Vector<Long> queue = null;

	public HashMap<Integer, Process> parallelProcess = null;

	public long id = 0;
	public int programCounter = 0;
	public boolean isBlocking = false;

	public long sndCounter = 0;

	public Process(long id, Vector<String[]> instructions, HashMap<Integer, Process> parallelProcess) {
		this.id = id;
		this.instructions = instructions;
		this.parallelProcess = parallelProcess;
		queue = new Vector<Long>();
		registers = new HashMap<Character, Long>();
		registers.put('p', id);
	}

	public void step() {
		if (instructions.get(programCounter).length > 2) {
			programCounter += binaryCMD(instructions.get(programCounter));
		} else {
			isBlocking = unaryCMD(instructions.get(programCounter));
		}
		if (!isBlocking) {
			programCounter++;
		}
	}

	private void set(char reg, long value) {
		registers.put(reg, value);
	}

	private void add(char reg, long value) {
		registers.put(reg, registers.get(reg) + value);
	}

	private void mul(char reg, long value) {
		registers.put(reg, registers.get(reg) * value);
	}

	private void mod(char reg, long value) {
		registers.put(reg, registers.get(reg) % value);
	}

	private long binaryCMD(String[] s) {
		long result = 0;
		long secondParam = 0;

		if (s[1].matches("\\D")) {
			if (!registers.containsKey(s[1].toCharArray()[0])) {
				registers.put(s[1].toCharArray()[0], (long) 0);
			}
		}

		if (s[2].matches("\\D")) {
			if (!registers.containsKey(s[2].toCharArray()[0])) {
				registers.put(s[2].toCharArray()[0], (long) 0);
			}
			secondParam = registers.get(s[2].toCharArray()[0]);
		} else {
			secondParam = Integer.parseInt(s[2]);
		}

		switch (s[0]) {
		case "set":
			set(s[1].toCharArray()[0], secondParam);
			break;
		case "add":
			add(s[1].toCharArray()[0], secondParam);
			break;
		case "mul":
			mul(s[1].toCharArray()[0], secondParam);
			break;
		case "mod":
			mod(s[1].toCharArray()[0], secondParam);
			break;
		case "jgz":
			if (s[1].matches("\\D")) {
				if (registers.get(s[1].toCharArray()[0]) > 0) {
					result = secondParam - 1;
				} else {
					result = 0;
				}
			} else {
				if (Integer.parseInt(s[1]) > 0) {
					result = secondParam - 1;
				} else {
					result = 0;
				}
			}
			break;
		default:
			result = Integer.MIN_VALUE;
		}

		return result;
	}

	private boolean unaryCMD(String[] s) {
		long param = 0;
		
		if (s[1].matches("\\D")) {
			if (!registers.containsKey(s[1].toCharArray()[0])) {
				registers.put(s[1].toCharArray()[0], (long) 0);
			}
			param = registers.get(s[1].toCharArray()[0]);
		} else {
			param = Integer.parseInt(s[1]);
		}

		switch (s[0]) {
		case "snd":
			if (id == 0) {
				parallelProcess.get(1).queue.add(param);
			}
			if (id == 1) {
				parallelProcess.get(0).queue.add(param);
			}

			sndCounter++;

			return false;
		case "rcv":
			if (queue.size() == 0) {
				return true;
			} else {
				registers.put(s[1].toCharArray()[0], queue.get(0));
				queue.remove(0);
				return false;
			}
		default:
			return true;
		}
	}
}