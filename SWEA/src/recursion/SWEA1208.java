package recursion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Box implements Comparable<Box> {
	int idx;
	int high;

	Box(int idx, int high) {
		this.idx = idx;
		this.high = high;
	}

	public int compareTo(Box b) {
		if (this.high > b.high)
			return 1;
		return -1;
	}
}

public class SWEA1208 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long start = System.nanoTime();
		for (int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(br.readLine());

			List boxes = new ArrayList();

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 100; i++) {
				int high = Integer.parseInt(st.nextToken());
				boxes.add(new Box(i, high));
			}

			for (int i = 0; i <= N; i++) {
				Collections.sort(boxes);

				if (i == N) {
					int ret = ((Box) boxes.get(99)).high - ((Box) boxes.get(0)).high;
					System.out.println("#" + tc + " " + ret);
					break;
				}
				((Box) boxes.get(0)).high += 1;
				((Box) boxes.get(99)).high -= 1;
			}
		}
		long end = System.nanoTime();
		System.out.println("duration : " + (end - start));
	}
}