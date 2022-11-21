package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_4727_견우와직녀 {

	static final int dy [] = {0,0,1,-1};
	static final int dx [] = {1,-1,0,0};
	
	static int T,N,M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			map=new int[N][N];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#"+tc+" "+bfs());
		}
	}
	
	static int bfs () {
		
		PriorityQueue<Pos> pq=new PriorityQueue<>( (p1, p2)-> p1.dist-p2.dist);
		pq.add(new Pos (0,0,0,false));
		
		boolean visited[][]=new boolean[N][N];
		visited[0][0]=true;
		
		while (!pq.isEmpty()) {
			Pos pos=pq.poll();
			
			//System.out.println(pos.toString());
			int y=pos.y;
			int x=pos.x;
			int dist=pos.dist;
			boolean flag=pos.flag;
			
			if (y==N-1 && x==N-1) return dist;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (!isInRange(ny,nx) || visited[ny][nx]) continue;
				
				if (map[ny][nx]==1) {
					visited[ny][nx]=true;
					pq.add(new Pos (ny, nx, dist+1, flag));
				
				// 다리를 새로 만드는 경우	
				} else if (map[ny][nx]==0) {
					// 이미 만든 경우
					if (flag) continue;
					
					// 크로스 된 경우 || 이미 다리를 건너온 경우
					if (!crossCheck (ny,nx) || map[y][x]!=1) continue;

					if (dist%M==0 && dist>=M) {
						visited[ny][nx]=true;
						pq.add(new Pos (ny, nx, dist, true));
					} else {
						pq.add(new Pos (y,x,dist+1, false));
					}
					
				// 이미 있는 다리를 건너는 경우	
				} else {
					
					if (dist%map[ny][nx]==0 && dist>=map[ny][nx]) {
						visited[ny][nx]=true;
						pq.add(new Pos (ny, nx, dist, flag));
					} else {
						pq.add(new Pos (y, x, dist+1, flag));
					}

				}
				
			}
		}
		
		return -1;
	}
	
	static boolean crossCheck (int y, int x) {
		
		boolean row, col;
		row=col=false;
		
		for (int d=0; d<2; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (!isInRange(ny,nx)) continue;
			if (map[ny][nx]!=1) 
				row=true;
		}
		for (int d=2; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (!isInRange(ny,nx)) continue;
			if (map[ny][nx]!=1) 
				col=true;
		}
		
		if (row==true && col==true) return false;
		return true;
		
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}

	static class Pos {
		int y,x;
		int dist;
		boolean flag;
		
		Pos (int y, int x, int dist, boolean flag) {
			this.y=y;
			this.x=x;
			this.dist=dist;
			this.flag=flag;
		}

		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + ", dist=" + dist + ", flag=" + flag + "]";
		}
		
	}

}
