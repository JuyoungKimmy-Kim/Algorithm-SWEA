package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;



class Pair {
	int y; int x;
	Pair (int y, int x) {
		this.y=y; this.x=x;
	}
}

public class SWEA1861 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};

	static int ansCnt, ansNum;
	static int N,T;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			ansCnt=1; ansNum=0;
			
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];

			
			for (int i=0; i<N; i++) {
				StringTokenizer st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					count(i,j);
				}
			}
			
			System.out.println("#"+tc+" "+ansNum+" "+ansCnt);
			
		}
	}
	
	private static void count (int i, int j) {

		int cnt=1;
		
		Queue<Pair> q=new ArrayDeque<>();
		q.add(new Pair(i,j));
		
		while (!q.isEmpty()) {
			Pair cur=q.poll();
			int y=cur.y;
			int x=cur.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N) continue;
				if (map[ny][nx]==map[y][x]+1 ) {
					q.add(new Pair (ny, nx));
					cnt++;
				}
			}	
		}
		
		if (cnt>ansCnt)  {
			ansCnt=cnt;
			ansNum=map[i][j];
		}
		else if (cnt==ansCnt) {
			if (map[i][j]<ansNum) {
				ansNum=map[i][j];
			}
		}
	}
}
