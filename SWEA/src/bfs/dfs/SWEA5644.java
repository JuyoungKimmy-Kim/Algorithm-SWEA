package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5644 {

	static final int dy[] = { 0, -1, 0, 1, 0 };
	static final int dx[] = { 0, 0, 1, 0, -1 };

	static int pathA[], pathB[];
	static int ay, ax, by, bx;

	static BC[] bcList;
	static int T, M, A, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			pathA = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++)
				pathA[i] = Integer.parseInt(st.nextToken());

			pathB = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++)
				pathB[i] = Integer.parseInt(st.nextToken());

			bcList = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				bcList[i] = new BC(y, x, c, p);
			}

			ay = ax = 1;
			by = bx = 10;
			ans = 0;

			simulation();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	static int getDist(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}

	static void simulation() {
		for (int m = 0; m <= M; m++) {
			ay += dy[pathA[m]];
			ax += dx[pathA[m]];

			by += dy[pathB[m]];
			bx += dx[pathB[m]];

			int powerA = 0, powerB = 0;
			int max = 0, sum=0;

			for (int i = 0; i < A; i++) {

				int y1 = bcList[i].y;
				int x1 = bcList[i].x;

				int dist = getDist(y1, x1, ay, ax);
				if (dist <= bcList[i].c)
					powerA = bcList[i].p;
				else
					powerA = 0;

				for (int j = 0; j < A; j++) {
					int y2 = bcList[j].y;
					int x2 = bcList[j].x;

					dist = getDist(y2, x2, by, bx);
					if (dist <= bcList[j].c)
						powerB = bcList[j].p;
					else
						powerB = 0;

					if (i == j) {
						sum = Math.max(powerA, powerB);
					} else
						sum = powerA + powerB;
					
					max=Math.max(max, sum);
				}
			}
			ans += max;
		}
	}

	static class BC {
		int y, x, c, p;

		BC(int y, int x, int c, int p) {
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
	}
}
