package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1767_2 {

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };

	static int T, N, coreMax, powerMin;
	static int[][] map;

	static List<Core> core;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			core = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n;

					if (n == 1 && i!=0 && j!=0 && i!=N-1 && j!=N-1)
						core.add(new Core(i, j));
				}
			}

			coreMax=0;
			powerMin=Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(powerMin).append("\n");
		}
		System.out.println(sb.toString());

	}

	static void dfs(int tgtIdx, int coreCnt, int powerCnt) {
		
		//print();
		
		if (tgtIdx == core.size()) {

			if (coreMax==coreCnt) {
				powerMin=Math.min(powerMin, powerCnt);
			} else if (coreMax<coreCnt) {
				coreMax=coreCnt;
				powerMin=powerCnt;
			} 
			
			return;
		}

		int y = core.get(tgtIdx).y;
		int x = core.get(tgtIdx).x;

		Queue<int[]> queue=new ArrayDeque<>();
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			while (isInRange (ny, nx) && map[ny][nx]==0) {
				ny+=dy[d];
				nx+=dx[d];
			}
			
			if (isInRange (ny, nx)) continue;
			
			ny=y+dy[d]; nx=x+dx[d];
			while (isInRange (ny, nx)) {
				map[ny][nx]=2;
				queue.add(new int[] {ny, nx});
				
				ny+=dy[d];
				nx+=dx[d];
			}
			
			dfs (tgtIdx+1, coreCnt+1, powerCnt+queue.size());
			
			while (!queue.isEmpty()) 
				map[queue.peek()[0]][queue.poll()[1]]=0;
				
		}
		dfs (tgtIdx+1, coreCnt, powerCnt);
		
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}
	
	static void print () {
		
		System.out.println("===============================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	static class Core {
		int y, x;

		Core(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
