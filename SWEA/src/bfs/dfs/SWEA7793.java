package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7793 {

	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int T,N,M,devilY, devilX, sy,sx;
	static char[][] map;
	static int [][] dist;
	
	static boolean done=false;

	static Queue<int[]> q1,q2;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			map=new char[N][M];
			dist=new int[N][M];

			for (int i=0; i<N; i++)
				Arrays.fill(dist[i], -1);
			
			for (int i=0; i<N; i++) {
				String line=br.readLine();
				for (int j=0; j<M; j++) {
					map[i][j]=line.charAt(j);
					
					if (map[i][j]=='S') {
						sy=i; sx=j;
					} else if (map[i][j]=='*') {
						devilY=i; devilX=j;
					}
				}
			}
			
			q1=new ArrayDeque<>();
			q2=new ArrayDeque<>();
			
			q1.offer(new int[] {devilY, devilX});
			q2.offer(new int[] {sy, sx});
			
			done=false;
			int Time=1;
			while (true) {
				
				spreadDevil();
				if (!move()) break;
				if (done) break;
				
				//print(Time);
				
				Time++;
			}
			

			if (!done) 
				sb.append("#").append(tc).append(" GAME OVER\n");
			else sb.append("#").append(tc).append(" ").append(Time).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void spreadDevil () {
		int qSize=q1.size();
		
		// 악마의 확산
		for (int k=0; k<qSize; k++) {
			int y=q1.peek()[0];
			int x=q1.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=M) continue;
				if (map[ny][nx]=='D' || map[ny][nx]=='X' || map[ny][nx]=='*') continue;
				
				map[ny][nx]='*';
				q1.offer(new int[] {ny, nx});				
			}
		}
	}
	
	static boolean move () {
		
		boolean flag=false;
		
		int qSize=q2.size();
		for (int k=0; k<qSize; k++) {
			int y=q2.peek()[0];
			int x=q2.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0|| ny>=N || nx>=M ) continue;
				if (map[ny][nx]=='D') {
					done=true;
					return true;
				}
				
				if (map[ny][nx]=='X' || map[ny][nx]=='*' || map[ny][nx]=='S') continue;
	
				map[ny][nx]='S';
				q2.offer(new int[] {ny, nx});
				flag=true;
			}
		}
		
		return flag;
	}
	
	static void print (int Time) {
		System.out.println("======="+Time+"=========");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.printf("%3c", map[i][j]);
			}
			System.out.println();
		}
	}

}
