package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class samsung2022_03_꼬리잡기 {

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };

	static int N, M, K, V, ans;
	static int[][] map, pmap;
	static boolean[][] visited;

	static List<Pos> pos = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		pmap = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] || map[i][j] == 0)
					continue;

				pos.add(new Pos());
				dfs(i, j, V);
			}
		}

		int Time = 1;
		while (Time <= K) {

			move();
			throwBall(Time % (4 * K));

			System.out.println("==========" + Time + "===========");
			//print(map);
			print(pmap);
			Time++;

		}

		System.out.println(ans);

	}

	static void throwBall(int round) {
		int y = 0, x = 0;

		if (round <= N) {
			y = round - 1;
			x = 0;

			while (x < N && pmap[y][x] == 0)
				x++;
			if (x == N)
				return;

		} else if (round <= 2 * N) {
			round -= N;
			y = N - 1;
			x = round;

			while (y >= 0 && pmap[y][x] == 0)
				y--;
			if (y == -1)
				return;

		} else if (round <= 3 * N) {
			round -= (N * 2);
			y = N - round;
			x = N - 1;

			while (x >= 0 && pmap[y][x] == 0)
				x--;
			if (x == -1)
				return;

		} else if (round <= 4 * N) {
			round -= (N * 3);
			y = 0;
			x = N - round;

			while (y < N && pmap[y][x] == 0)
				y++;
			if (y == N)
				return;
		}

		int idx = map[y][x];
		getScore(idx * (-1) - 1, y, x);
	}

	// idx번 째 팀의 y,x에 있는 사람을 맞춤
	static void getScore(int idx, int y, int x) {
		int tgtIdx = 0;
		for (int i = 0; i < pos.get(idx).coord.size(); i++) {
			int ty = pos.get(idx).coord.get(i)[0];
			int tx = pos.get(idx).coord.get(i)[1];

			if (ty == y && tx == x) {
				tgtIdx = i;
				break;
			}
		}

		int head = pos.get(idx).head;
		int tail = pos.get(idx).tail;

		tgtIdx -= head;
		if (tgtIdx < 0)
			tgtIdx *= -1;
		ans += ((tgtIdx + 1) * (tgtIdx + 1));

		// System.out.println(head + " " + tail + " " + tgtIdx);

		pos.get(idx).head = tail;
		pos.get(idx).tail = head;

		head = pos.get(idx).head;
		tail = pos.get(idx).tail;

		// System.out.println(head + " " + tail + " " + tgtIdx);

	}

	static void move() {
		for (int i = 0; i < pos.size(); i++) {
			Pos p = pos.get(i);

			int size = p.size;
			int head = p.head;
			int tail = p.tail;

			int sy = 0, sx = 0;

			int idx = head;
			if (head > tail) {
				idx = (head + 1) % size;
				sy = p.coord.get(idx)[0];
				sx = p.coord.get(idx)[1];
			} else {
				if (head == 0)
					idx = size - 1;
				else
					idx -= 1;
				sy = p.coord.get(idx)[0];
				sx = p.coord.get(idx)[1];
			}

			int ey = p.coord.get(tail)[0];
			int ex = p.coord.get(tail)[1];

			pmap[ey][ex] = 0;
			pmap[sy][sx] = i + 1;

			if (head < tail) {
				p.head = (head + 1) % size;
				p.tail = (tail + 1) % size;
			} else {
				if (head==0) head=size-1;
				else head-=1;
				p.head=head;
				
				if (tail==0) tail=size-1;
				else tail-=1;
				p.tail=tail;
			}
		}
	}

	static void dfs(int y, int x, int V) {

		visited[y][x] = true;
		pos.get(V).coord.add(new int[] { y, x });
		pos.get(V).size++;

		if (map[y][x] == 1) {
			pos.get(V).head = pos.get(V).coord.size() - 1;
			pmap[y][x] = V + 1;
		} else if (map[y][x] == 3) {
			pos.get(V).tail = pos.get(V).coord.size() - 1;
			pmap[y][x] = V + 1;
		} else if (map[y][x] == 2) {
			pmap[y][x] = V + 1;
		}

		map[y][x] = (V + 1) * (-1);

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (!isInRange(ny, nx) || visited[ny][nx] || map[ny][nx] == 0)
				continue;
			dfs(ny, nx, V);
		}
	}

	static boolean isInRange(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= N)
			return false;
		return true;
	}

	static class Pos {
		List<int[]> coord;
		int size, head, tail;
		boolean reverse;
		Pos() {
			coord = new ArrayList<>();
		}
	}

	static void print(int[][] map) {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
	}
}
