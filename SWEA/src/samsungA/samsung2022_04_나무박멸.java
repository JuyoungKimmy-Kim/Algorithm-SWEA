package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class samsung2022_04_나무박멸 {

	static final int cdy[] = { -1, -1, 1, 1 };
	static final int cdx[] = { -1, 1, 1, -1 };

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };

	static int N, M, K, C, ans;
	static int[][] map, tmp, kill;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		tmp = new int[N][N];
		kill = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		simulation();
		System.out.println(ans);

	}

	static void simulation() {
		for (int m = 1; m <= M; m++) {

			growTree();

			propagate();

			updateTimeMap(m);

			selectLocation(m);


		}
	}

	static void selectLocation(int m) {
		List<Pos> pos=new ArrayList<>();

		
		
	}

	static void propagate() {
		for (int i = 0; i < N; i++)
			Arrays.fill(tmp[i], 0);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				// 빈칸, 벽, 제초제가 있는 곳 -> 번식 불가
				if (map[i][j] <= 0)
					continue;

				int cnt = 0;

				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];

					if (!isInRange(ny, nx) || kill[ny][nx]>0)
						continue;
					if (map[ny][nx] == 0)
						cnt++;
				}

				if (cnt == 0)
					continue;
				
				int spread = map[i][j] / cnt;
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];

					if (!isInRange(ny, nx) || kill[ny][nx] > 0)
						continue;
					if (map[ny][nx] == 0) {
						tmp[ny][nx] += spread;
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += tmp[i][j];
			}
		}
	}

	static void growTree() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (map[i][j] <= 0)
					continue;

				int cnt = 0;

				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];

					if (!isInRange(ny, nx))
						continue;
					if (map[ny][nx] > 0)
						cnt++;
				}

				map[i][j] += cnt;
			}
		}
	}

	static void updateTimeMap(int m) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (kill[i][j] > 0) {
					kill[i][j] -= 1;
				}
			}
		}
	}

	static boolean isInRange(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= N)
			return false;
		return true;
	}

	static void print() {
		System.out.println("==============================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
	}

	static class Pos implements Comparable<Pos> {
		int y, x;
		int cnt;

		Pos(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.cnt==o.cnt)
				if (this.y==o.y)
					return this.x-o.x;
				else return this.y-o.y;
			else return o.cnt-this.cnt;

		}

	}

}
