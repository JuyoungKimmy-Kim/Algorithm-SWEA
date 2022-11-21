package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7733_치즈도둑 {

	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int T, N, ans;
	static int [][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			ans=1;
			
			for (int k=1; k<=100; k++) {
				
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						if (map[i][j]==k) map[i][j]=0;
					}
				}
				
				visited=new boolean[N][N];
				int count=0;
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						if (map[i][j]!=0 && !visited[i][j]) {
							count++;
							bfs (i,j);
						}
					}
				}
				
				ans=Math.max(ans, count);
				if (count==0) break;
			}
			
			System.out.println("#"+tc+" "+ans);
		}
		
	}
	
	static void bfs (int i ,int j) {
		Queue<int[]> queue=new ArrayDeque<>();
		queue.add(new int[] {i,j});
		visited[i][j]=true;
		
		while (!queue.isEmpty()) {
			int y=queue.peek()[0];
			int x=queue.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N || visited[ny][nx]) continue;
				if (map[ny][nx]==0) continue;
				
				queue.add(new int[] {ny, nx});
				visited[ny][nx]=true;
			}
		}
	}

}
