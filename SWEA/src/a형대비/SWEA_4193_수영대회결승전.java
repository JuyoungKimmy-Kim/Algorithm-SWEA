package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_4193_수영대회결승전 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int[][] map;
	static int T,N,sy,sx,ey,ex;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			visited=new boolean[N][N];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			st=new StringTokenizer (br.readLine());
			sy=Integer.parseInt(st.nextToken());
			sx=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer (br.readLine());
			ey=Integer.parseInt(st.nextToken());
			ex=Integer.parseInt(st.nextToken());
			
			System.out.println("#"+tc+" "+bfs());
		}
		
	}
	
	static int bfs () {
		// 2,5,8,11
		PriorityQueue<Pos> pq=new PriorityQueue<>( (p1, p2)->p1.dist-p2.dist);
		pq.add(new Pos (sy,sx,0));
		visited[sy][sx]=true;
		
		while (!pq.isEmpty()) {
			Pos pos=pq.poll();
			if (ey==pos.y && ex==pos.x) {
				return pos.dist;
			}
			
			//System.out.println(pos);
			
			for (int d=0; d<4; d++) {
				int ny=pos.y+dy[d];
				int nx=pos.x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N || visited[ny][nx]|| map[ny][nx]==1) continue;
				
				if (map[ny][nx]==2) {
					if (pos.dist%3==2) {
						visited[ny][nx]=true;
						pq.add(new Pos (ny, nx, pos.dist+1));
					} else {
						pq.add(new Pos (pos.y, pos.x, pos.dist+1));
					}
				} else {
					visited[ny][nx]=true;
					pq.add(new Pos (ny, nx, pos.dist+1));
				}
				
				
			}
		}
		
		
		return -1;
		
	}
	
	
	static class Pos {
		int y, x, dist;
		Pos (int y, int x, int dist) {
			this.y=y;
			this.x=x;
			this.dist=dist;
		}
		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + ", dist=" + dist + "]";
		}
		
		
	}


}
