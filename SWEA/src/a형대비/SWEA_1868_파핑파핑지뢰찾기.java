package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA_1868_파핑파핑지뢰찾기 {

	static int dy[] = {0,1,1,1,0,-1,-1,-1};
	static int dx[] = {1,1,0,-1,-1,-1,0,1};
	
	static int T,N, ans;
	static char[][] map;
	static int [][] count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			ans=0;
			
			N=Integer.parseInt(br.readLine());
			map=new char[N][];
			for (int i=0; i<N; i++) {
				String line=br.readLine();
				map[i]=line.toCharArray();
			}
		
			count=new int[N][N];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j]=='.')
						counting_adj(i,j);
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (count[i][j]==0 && map[i][j]!='*') {
						click (i,j);
						ans++;
					}
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (count[i][j]>0 && map[i][j]!='*')
						ans++;
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
		
		
	}
	
	static void click (int i, int j) {
		
		Queue <int []> queue=new ArrayDeque<>();
		queue.add(new int[] {i,j});
		count[i][j]=-1;
		
		while (!queue.isEmpty()) {
			int y=queue.peek()[0];
			int x=queue.poll()[1];
			
			for (int d=0; d<8; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (!isInRange(ny,nx) || count[i][j]==-1 || map[ny][nx]=='*')
					continue;
				
				if (count[ny][nx]==0) 
					queue.add(new int[] {ny,nx});
				
				count[ny][nx]=-1;
			}
		}
		
	}
	
	static void counting_adj (int y, int x) {
		int cnt=0;
		for (int d=0; d<8; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (!isInRange (ny, nx)) continue;
			if (map[ny][nx]=='*') cnt++;
		}
		count[y][x]=cnt;
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}

}
