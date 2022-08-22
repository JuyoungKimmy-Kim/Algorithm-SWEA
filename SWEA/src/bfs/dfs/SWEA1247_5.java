package bfs.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가지치기
public class SWEA1247_5 {

	static int T, N, comY, comX, homeY, homeX, min;
	static int[][] cust; // src
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			min = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine()); // 고객수
			cust = new int[N][2];
			visit = new boolean[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			comY = Integer.parseInt(st.nextToken());
			comX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				cust[i][0] = Integer.parseInt(st.nextToken());
				cust[i][1] = Integer.parseInt(st.nextToken());
			}

			perm (0, 0, comY, comX);
	
			System.out.println("#"+t+" "+min);
		}
	}
	
	static void perm (int tgtIdx, int sum, int y, int x) {
		if (tgtIdx==N) {
			sum+=getDist (y,x,homeY, homeX);
			
			min=Math.min(min, sum);
			return ;
		}
		
		for (int i=0; i<N; i++) {
			if (visit[i]) continue;
			
			int ny=cust[i][0];
			int nx=cust[i][1];
			int dist=getDist (y,x,ny, nx);
			
			if (sum+dist>min) continue;
			
			visit[i]=true;
			perm (tgtIdx+1, sum+dist, ny, nx);
			visit[i]=false;
			
		}
	}
	
	static int getDist (int y, int x, int ny, int nx) {
		return Math.abs(y-ny)+Math.abs(x-nx);
	}
}