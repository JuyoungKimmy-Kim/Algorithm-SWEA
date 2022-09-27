package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953 {
							//동,서,남,북
	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int T,N,M,R,C,L;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			map=new int[N][M];
			visited=new boolean[N][M];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			

			System.out.println("#"+tc+" "+bfs());
			
		}

	}
	
	static int bfs () {
		int ans=0;
		
		Queue<int[]> q=new ArrayDeque<>();
		q.add(new int[] {R,C});
		visited[R][C]=true;
		ans++;
		
		while (!q.isEmpty()) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			int type=map[y][x];
			
			switch (type) {
			case 1: 
				for (int d=0; d<4; d++) {
					int ny=y+dy[d];
					int nx=x+dx[d];
					
					if (isInRange (ny,nx) && !visited[ny][nx]) {
						ans++;
						visited[ny][nx]=true;
						q.add(new int[] {ny, nx});
					}
				}
				break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 7: break;
			}
			
		}
		
		return ans;
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=M) return false;
		return true;
	}

}
