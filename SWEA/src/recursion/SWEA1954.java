package recursion;

import java.util.Scanner;

public class SWEA1954 {

	public static final int dy[] = { 0, 1, 0, -1 };
	public static final int dx[] = { 1, 0, -1, 0 };

	static int T, N;
	static int y, x, dir;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];

			int cnt = 0;
			int dist = N;

			boolean flag = false;
			
			dir = 0;
			y = 0;
			x = -1;
			int idx = 0;
			while (idx <= N * N) {

				for (int i = 0; i < dist; i++) {
					y += dy[dir];
					x += dx[dir];

					map[y][x] = idx++;
				}

				dir = (dir + 1) % 4;
				cnt++;

				if (cnt == 2) {
					cnt = 0;
					dist--;
				}
			}

			System.out.println("#" + tc);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.printf("%d ", map[i][j]);
				}
				System.out.println();
			}

		}

	}

	static void print() {
		System.out.println("=====================================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%2d", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
