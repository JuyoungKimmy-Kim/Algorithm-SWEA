package bfs.dfs;


/*
 * 
 * dfs
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import bfs.dfs.SWEA1861_2.Node;

public class SWEA1861_3 {
	
	static int[][] map;
	static int T,N,NO, COUNT;
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			NO=0;
			COUNT=1;
			
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
					dfs (i,j,map[i][j],1);
				}
			}
		
			System.out.println("#"+tc+" "+NO+" "+COUNT);
		}
	}
	
	private static void dfs (int y, int x, int no, int cnt) {
		if (cnt>COUNT) {
			COUNT =cnt;
			NO=no;
		}
		else if (cnt==COUNT) {
			NO=no<NO ? no : NO;
		}
		
		for (int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (ny<0 || nx<0 || ny>=N || nx>=N || map[ny][nx]!=map[y][x]+1)
				continue;
			
			dfs (ny, nx, no, cnt+1);
			break;
		}
	}

}
