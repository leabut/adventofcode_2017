public class Main {

	public static String inputPath = "input.txt";

	public static int count = 0;

	public static void main(String[] args) {
		long seedA = 883;
		long seedB = 879;

		long noA = seedA;
		long noB = seedB;

		for (int i = 0; i < 5 * Math.pow(10, 6); i++) {

			seedA = (seedA * 16807) % 2147483647;
			while ((seedA & 0x03) != 0) {
				seedA = (seedA * 16807) % 2147483647;
			}

			seedB = (seedB * 48271) % 2147483647;
			while ((seedB & 0x07) != 0) {
				seedB = (seedB * 48271) % 2147483647;
			}

			// tmp
			noA = seedA;
			noB = seedB;

			// take only last 16Bit
			noA &= 0xFFFF;
			noB &= 0XFFFF;

			if ((noA ^ noB) == 0) {
				count++;
			}
		}

		System.out.println(count);
	}
}