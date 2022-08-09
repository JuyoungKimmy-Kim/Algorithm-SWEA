package recursion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1208_3 {
	static int[] floor;
	static int dump;
	static int minIdx;
	static int maxIdx;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long start = System.nanoTime();

		for (int tc = 1; tc <= 10; tc++) {
			dump = Integer.parseInt(br.readLine());
			floor = new int[100];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 100; i++) {
				floor[i] = Integer.parseInt(st.nextToken());
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
