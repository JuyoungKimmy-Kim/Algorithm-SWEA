package a형대비;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_4727_견우와직녀_2 {

	static int N, M, ans;
	static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static List<Pos> cliff;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			cliff = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 0) {
						cliff.add(new Pos(i, j));
					}
				}
			}

			ans = Integer.MAX_VALUE;
			for (Pos node : cliff) {
				map[node.y][node.x] = M;
				int res = bfs();
				map[node.y][node.x] = 0;

				if (res == 0)
					continue;
				else
					ans = Math.min(ans, res);
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	static int bfs() {
		int res = 0;
		Queue<Pos> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		queue.offer(new Pos(0, 0, 0));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			int y = cur.y;
			int x = cur.x;
			int sec = cur.sec;

			if (y == N - 1 && x == N - 1) {
				res = sec;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] == true)
					continue;

				if (map[ny][nx] == 0)
					continue;

				else if (map[ny][nx] == 1) {
					queue.offer(new Pos(ny, nx, sec + 1));
					visited[ny][nx] = true;
				}

				else if (map[ny][nx] > 1) {
					if (map[y][x] == 1) {
						if ((sec + 1) % map[ny][nx] == 0) {
							queue.offer(new Pos(ny, nx, sec + 1));
							visited[ny][nx] = true;
						} else {
							queue.offer(new Pos(y, x, sec + 1));
						}
					}
				}
			}
		}
		return res;
	}

	static class Pos {
		int y, x, sec;
		boolean used;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

		Pos(int y, int x, int sec) {
			this.y = y;
			this.x = x;
			this.sec = sec;
		}
	}
}
