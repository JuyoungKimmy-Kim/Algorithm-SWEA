package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SWEA1249 {

	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int T,N;
	static int [][] map, distance;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			distance=new int[N][N];
			
			for (int i=0; i<N; i++)
				Arrays.fill(distance[i], -1);
			
			for (int i=0; i<N; i++) {
				String line=br.readLine();
				for (int j=0; j<N; j++) {
					map[i][j]=line.charAt(j)-'0';
				}
			}
			
			System.out.println("#"+tc+" "+bfs());
		}
	}
	
	static int bfs () {
		PriorityQueue<Pos> pq=new PriorityQueue<>();
		pq.add(new Pos (0,0, map[0][0]));
		distance[0][0]=map[0][0];
		
		while (!pq.isEmpty()) {
			Pos p=pq.poll();
			int y=p.y;
			int x=p.x;
			int dist=p.dist;
			
			if (y==N-1 && x==N-1) {
				return dist;
			}
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N) continue;
				
				
				if (distance[ny][nx]==-1 || map[ny][nx]+dist<distance[ny][nx]) {
					distance[ny][nx]=map[ny][nx]+dist;
					pq.add(new Pos (ny, nx, distance[ny][nx]));
				}
			}
		}
		
		return -1;
	}
	
	static class Pos implements Comparable <Pos>{
		int y, x;
		int dist;
		
		Pos (int y, int x, int dist) {
			this.y=y;
			this.x=x;
			this.dist=dist;
		}

		@Override
		public int compareTo(Pos o) {
			return this.dist-o.dist;
		}	
	}
}
