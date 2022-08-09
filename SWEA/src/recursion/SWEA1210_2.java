package recursion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1210_2 {
	static int[] dy = { 0, 0, -1 };
	static int[] dx = { -1, 1 };
	static int[][] ladder = new int[100][100];
	static int sy;
	static int sx;
	static int ans;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			br.readLine();

			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = (st.nextToken().charAt(0) - '0');
					if (ladder[i][j] == 2) {
						sy = i;
						sx = j;
					}

				}

			}

			int dir = 2;
			do {
				if (dir == 2) {
					for (int d = 0; d < 3; d++) {
						int ny = sy + dy[d];
						int nx = sx + dx[d];

						if ((ny >= 0) && (nx >= 0) && (nx < 100) && (ladder[ny][nx] == 1)) {
							sy = ny;
							sx = nx;
							dir = d;
							break;
						}
					}
				} else if ((dir == 0) || (dir == 1)) {
					int ny = sy + dy[2];
					int nx = sx + dx[2];

					if ((ny >= 0) && (ladder[ny][nx] == 1)) {
						sy = ny;
						sx = nx;
						dir = 2;
					} else {
						sy += dy[dir];
						sx += dx[dir];
					}
				}
			} while (sy != 0);
			ans = sx;

			System.out.println("#" + t + " " + ans);
		}
	}
}