package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class BC {
	int y, x;
	int C; // 충전 범위
	int P; // 성능
}

public class SWEA5644_2 {

	static final int dy[] = { 0, -1, 0, 1, 0 };
	static final int dx[] = { 0, 0, 1, 0, -1 };

	static int T, M, A, ans;
	static int ay, ax, by,bx;
	static int[][] path;
	static List<Integer>[][] map;
	static BC[] bc;
	

	// M: 이동시간, A: BC의 개수
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			// init
			ans = 0;

			map = new ArrayList[11][11];
			for (int i = 0; i < 11; i++)
				for (int j = 0; j < 11; j++)
					map[i][j] = new ArrayList<Integer>();

			path = new int[2][M];
			bc = new BC[A + 1];
			
			ay=ax=1;
			by=bx=10;

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					path[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());

				bc[i] = new BC();
				bc[i].x = Integer.parseInt(st.nextToken());
				bc[i].y = Integer.parseInt(st.nextToken());
				bc[i].C = Integer.parseInt(st.nextToken());
				bc[i].P = Integer.parseInt(st.nextToken());

				initMap(i); // map[][]에는 각 영역에 분포하고 있는 성능 표시

			}		
			//print();
			move();
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void move() {

		
		//초기 위치 최댓값 구하기
		if (map[ay][ax].size()>0) {
			int max=0;
			for (int i=0; i<map[ay][ax].size(); i++) {
				int p=bc[map[ay][ax].get(i)].P;
				if (p>max)
					max=p;
			}
			ans+=max;
		}
		if (map[by][bx].size()>0) {
			int max=0;
			for (int i=0; i<map[by][bx].size(); i++) {
				int p=bc[map[by][bx].get(i)].P;
				if (p>max)
					max=p;
			}
			ans+=max;
		}
		
		for (int i=0; i<M; i++) {
			
			int dirA=path[0][i];
			int dirB=path[1][i];
			
			ay+=dy[dirA]; ax+=dx[dirA];
			by+=dy[dirB]; bx+=dx[dirB];
			
			int sizeA=map[ay][ax].size();	//현재 A가 위치한 곳의 충전기 개수
			int sizeB=map[by][bx].size();	//현재 B가 위치한 곳의 충전기 개수
			
			if (sizeA==0 && sizeB==0) continue;
			else if (sizeA==0 && sizeB!=0) {
				int max=0;
				for (int k=0; k<sizeB; k++) {
					int p=bc[map[by][bx].get(k)].P;
					if (p > max)
						max=p;
				}
				ans+=max;
			}
			else if (sizeA!=0 && sizeB==0) {
				int max=0;
				for (int k=0; k<sizeA; k++) {
					int p=bc[map[ay][ax].get(k)].P;
					if (p > max)
						max=p;
				}
				ans+=max;
			}
			
			else {
				int max=0;
				for (int k=0; k<sizeA; k++) { 
					
					int aPower= bc[map[ay][ax].get(k)].P; 	
					
					for (int l=0; l<sizeB; l++) { 
						int sum=0;					
						int bPower= bc[map[by][bx].get(l)].P; 
						
						if (aPower!=bPower) sum=aPower + bPower; 
						else if (aPower==bPower){
							if (map[ay][ax].get(k) == map[by][bx].get(l))
								sum = aPower;
							else sum=2*aPower;
						}
						max=Math.max(max, sum); 			
					}
				}
				ans+=max;
			}
		}
	}

	private static void initMap(int i) {
		int y = bc[i].y;
		int x = bc[i].x;
		int C = bc[i].C;
		//int P = bc[i].P;

		int[][] distance = new int[11][11];
		for (int k = 1; k <= 10; k++)
			Arrays.fill(distance[k], -1);
		distance[y][x] = 0;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { y, x });

		map[y][x].add(i);

		outer: while (!q.isEmpty()) {

			int cy = q.peek()[0];
			int cx = q.poll()[1];

			for (int d = 1; d <= 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];

				if (ny < 1 || nx < 1 || ny > 10 || nx > 10 || distance[ny][nx] != -1)
					continue;

				distance[ny][nx] = distance[cy][cx] + 1;
				q.add(new int[] { ny, nx });

				if (distance[ny][nx] > C)
					break outer;

				map[ny][nx].add(i);
			}
		}
	}

	private static void print() {
		System.out.println("====================");

		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				if (map[i][j] == null)
					System.out.print("0 ");
				else
					System.out.print(map[i][j].size() + " ");
			}
			System.out.println();
		}
	}
}
