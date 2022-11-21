package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1227_미로2 {
	
	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int T,sy,sx;
	static char [][] map;
	static boolean done;
	static boolean [][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;

		for (int tc=1; tc<=100; tc++) {
			br.readLine();
			
			map=new char[100][];
			for (int i=0; i<100; i++) {
				map[i]=br.readLine().toCharArray();
				for (int j=0; j<100; j++) {
					if (map[i][j]=='2') {
						sy=i; sx=j;
					}
				}
			}
			
			visited=new boolean[100][100];
			visited[sy][sx]=true;
			done=false;
			
			dfs (sy,sx);
			if (done) System.out.println("#"+tc+" 1");
			else System.out.println("#"+tc+" 0");
			
			
			
		}
	}
	
	static void dfs (int y, int x) {
		if (map[y][x]=='3') {
			done=true;
			return ;
		}
		
		for (int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (ny<0 || nx<0 || ny>=100 || nx>=100 || visited[ny][nx]) continue;
			if (map[ny][nx]=='1') continue;
			
			visited[ny][nx]=true;
			dfs (ny, nx);
			if (done) return ;
		}
	}

}
