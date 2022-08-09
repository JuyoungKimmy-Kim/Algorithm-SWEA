package recursion;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SWEA1208_2 {
	static int[] floor;
	static int dump;
	static int minIdx;
	static int maxIdx;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		long start = System.nanoTime();

		for (int tc = 1; tc <= 10; tc++) {
			dump = sc.nextInt();
			floor = new int[100];

			for (int i = 0; i < 100; i++) {
				floor[i] = sc.nextInt();
			}
			minIdx = 0;
			maxIdx = 0;
			for (int i = 0; i < dump; i++) {
				reset();

				int gap = floor[maxIdx] - floor[minIdx];
				if ((gap == 0) || (gap == 1)) {
					break;
				}
				floor[maxIdx] -= 1;
				floor[minIdx] += 1;
			}

			reset();
			int result = floor[maxIdx] - floor[minIdx];
			System.out.println("#" + tc + " " + result);
		}

		long end = System.nanoTime();
		System.out.println("duration : " + (end - start));
	}

	static void reset() {
		for (int i = 0; i < 100; i++) {
			if (floor[i] < floor[minIdx])
				minIdx = i;
			if (floor[i] > floor[maxIdx])
				maxIdx = i;
		}
	}
}
