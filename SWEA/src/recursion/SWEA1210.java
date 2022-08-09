package recursion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1210 {
	public static final int MAX = 100;
	static int[][] map;
	static int x;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			map = new int[100][100];
			int N = Integer.parseInt(br.readLine());

			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 2) {
						x = j;
					}
				}
			}
			char prev = 'D';
			for (int i = 98; i >= 0; i--) {
				if ((x - 1 >= 0) && (map[i][(x - 1)] == 1) && (prev != 'L')) {
					prev = 'R';
					x -= 1;
					i++;
				} else if ((x + 1 < 100) && (map[i][(x + 1)] == 1) && (prev != 'R')) {
					prev = 'L';
					x += 1;
					i++;
				} else {
					prev = 'D';
				}
			}

			System.out.println("#" + tc + " " + x);
		}
	}
}