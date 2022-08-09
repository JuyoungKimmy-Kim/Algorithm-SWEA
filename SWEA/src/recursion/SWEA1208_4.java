package recursion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1208_4 {
	static ArrayList<Integer> floor;
	static int dump;
	static int minIdx;
	static int maxIdx;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long start = System.nanoTime();

		for (int tc = 1; tc <= 10; tc++) {
			dump = Integer.parseInt(br.readLine());
			floor = new ArrayList();

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 100; i++) {
				floor.add(Integer.valueOf(Integer.parseInt(st.nextToken())));
			}
			minIdx = 0;
			maxIdx = 0;
			for (int i = 0; i < dump; i++) {
				reset();

				int gap = ((Integer) floor.get(maxIdx)).intValue() - ((Integer) floor.get(minIdx)).intValue();
				if ((gap == 0) || (gap == 1)) {
					break;
				}
				floor.set(maxIdx, Integer.valueOf(((Integer) floor.get(maxIdx)).intValue() - 1));
				floor.set(minIdx, Integer.valueOf(((Integer) floor.get(minIdx)).intValue() + 1));
			}

			reset();
			int result = ((Integer) floor.get(maxIdx)).intValue() - ((Integer) floor.get(minIdx)).intValue();
			System.out.println("#" + tc + " " + result);
		}

		long end = System.nanoTime();
		System.out.println("duration : " + (end - start));
	}

	static void reset() {
		for (int i = 0; i < 100; i++) {
			if (((Integer) floor.get(i)).intValue() < ((Integer) floor.get(minIdx)).intValue())
				minIdx = i;
			if (((Integer) floor.get(i)).intValue() > ((Integer) floor.get(maxIdx)).intValue())
				maxIdx = i;
		}
	}
}
