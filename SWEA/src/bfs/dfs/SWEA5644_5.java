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

public class SWEA5644_5 {

	static final int dy[] = {0,-1,0,1,0};
	static final int dx[] = {0,0,1,0,-1};
	
	static int T,M,A;
	static int[][] move;
	static List<BC> bcList;
	static List<Integer>[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			M=Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			
			move=new int[2][M];
			
			for (int i=0; i<2; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<M; j++) {
					move[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			bcList=new ArrayList<>();
			for (int i=0; i<A; i++) {
				st=new StringTokenizer (br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				int p=Integer.parseInt(st.nextToken());
				
				bcList.add(new BC (x,y,c,p));
			}
			
			map=new ArrayList[11][11];
			for (int i=0; i<=10; i++) {
				for (int j=0; j<=10; j++) {
					map[i][j]=new ArrayList<>();
				}
			}
			
			// update map
			for (int i=0; i<bcList.size(); i++) {
				bfs (i);
			}
			
			
		}
	}
	

	static void bfs (int n) {
		Queue<int[]> q=new ArrayDeque<> ();
		
		int [][] dist=new int[11][11];
		for (int i=0; i<=10; i++)
			Arrays.fill(dist[i], -1);
		
		int y=bcList.get(n).y;
		int x=bcList.get(n).x;
		int C=bcList.get(n).c;
		
		q.add(new int [] {y,x});
		map[y][x].add(n);
		dist[y][x]=0;
		
		while (!q.isEmpty()) {
			int cy=q.peek()[0];
			int cx=q.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=cy+dy[d];
				int nx=cx+dx[d];
				
				if (isInRagne(ny, nx) && dist[ny][nx]!=-1) {
					dist[ny][nx]=dist[y][x]+1;
					if (dist[ny][nx]>C) return ;
					
					q.add(new int[] {ny, nx});
				}
			}	
		}
	}
	
	static boolean isInRagne (int ny, int nx) {
		if (ny<1 || nx<1 || ny>10 || nx>10 ) return false;
		return true;
	}
	
	static class BC {
		int x,y,c,p;
		
		BC (int x, int y, int c, int p) {
			this.x=x;
			this.y=y;
			this.c=c;
			this.p=p;
		}
	}

}
