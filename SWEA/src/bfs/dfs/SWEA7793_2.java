package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7793_2 {

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };

	static int T, N, M, devilY, devilX, sy, sx;
	static char[][] map;
	static Queue<int[]> qS, qD;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];

			qS=new ArrayDeque<>();
			qD=new ArrayDeque<>();
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);

					if (map[i][j] == 'S') {
						qS.add(new int[] {i,j});
					} else if (map[i][j] == '*') {
						qD.add(new int[] {i,j});
					}
				}
			}

			int ret = move();
			if (ret==-1) {
				System.out.println("#" + tc + " GAME OVER");
			} else {
				System.out.println("#" + tc + " " + ret);
			}
		}
		System.out.println(sb.toString());
	}

	static int move() {
		int Time = 1;

		while (!qS.isEmpty()) {

			int qSize = qD.size();

			for (int k = 0; k < qSize; k++) {
				int y = qD.peek()[0];
				int x = qD.poll()[1];

				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M)
						continue;
					if (map[ny][nx] == 'D' || map[ny][nx] == '*' || map[ny][nx] == 'X')
						continue;

					map[ny][nx] = '*';
					qD.offer(new int[] { ny, nx });
				}
			}

			qSize = qS.size();
			for (int k = 0; k < qSize; k++) {
				int y = qS.peek()[0];
				int x = qS.poll()[1];

				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M)
						continue;
					if (map[ny][nx] == 'D')
						return Time;

					if (map[ny][nx] == '*' || map[ny][nx] == 'S' || map[ny][nx] == 'X')
						continue;

					map[ny][nx] = 'S';
					qS.offer(new int[] { ny, nx });
				}
			}

			Time++;
		}

		return -1;
	}
}
