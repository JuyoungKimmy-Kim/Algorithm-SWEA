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
	
	static int T,N,M,R,C,L,ans;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> q;
	
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
			q=new ArrayDeque<>();
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			ans=0;
			bfs();
			System.out.println("#"+tc+" "+ans);
			
		}

	}
	
	static void bfs () {

		q.add(new int[] {R,C});
		visited[R][C]=true;
		ans++;
		
		int ny=0, nx=0;
		
		while (!q.isEmpty()) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			int type=map[y][x];
			
			switch (type) {
			case 1: 
				for (int d=0; d<4; d++) {
					ny=y+dy[d];
					nx=x+dx[d];
					move(ny, nx);
				}
				break;
			case 2: 
				for (int d=2; d<4; d++) {
					ny=y+dy[d];
					nx=x+dx[d];

					move(ny, nx);
				}
				break;
			case 3: 
				for (int d=0; d<2; d++) {
					ny=y+dy[d];
					nx=x+dx[d];
					
					move(ny,nx);
				}
				break;
			case 4: 
				ny=y+dy[0];
				nx=x+dx[0];
				move(ny, nx);
				
				ny=y+dy[3];
				nx=x+dx[3];
				move(ny, nx);
				
				break;
			case 5: 
				ny=y+dy[0];
				nx=x+dx[0];
				move(ny, nx);
				
				ny=y+dy[2];
				nx=x+dx[2];
				move(ny, nx);
				
				break;
			case 6: 
				
				ny=y+dy[1];
				nx=x+dx[1];
				move(ny, nx);
				
				ny=y+dy[2];
				nx=x+dx[2];
				move(ny, nx);
				
				break;
			case 7: 
				ny=y+dy[1];
				nx=x+dx[1];
				move(ny, nx);
				
				ny=y+dy[3];
				nx=x+dx[3];
				move(ny, nx);
				
				break;
			}	
		}
	}
	
	static void move (int ny, int nx) {
		if (isInRange (ny,nx) && !visited[ny][nx]) {
			ans++;
			visited[ny][nx]=true;
			q.add(new int[] {ny, nx});
			
			print();
		}
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=M) return false;
		return true;
	}
	
	static void print () {
		System.out.println("===============================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(visited[i][j] ? "O " : "X ");
			}
			System.out.println();
		}
	}

}
