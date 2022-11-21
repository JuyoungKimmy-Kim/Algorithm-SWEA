package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_2819_격자판의숫자이어붙이기 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int T;
	static int[][] map;
	
	static HashSet<String> set=new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			set.clear();
			map=new int[4][4];
			
			for (int i=0; i<4; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<4; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					dfs (0,i,j,""+map[i][j]);
				}
			}
			System.out.println("#"+tc+" "+set.size());
		}
		
	}
	
	static void dfs (int tgtIdx, int y, int x, String str) {
		if (tgtIdx==6) {
			//String str=String.valueOf(tgt);
			set.add(str);
			return ;
		}
		
		for (int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (ny<0 || nx<0 || ny>=4 || nx>=4) continue;
			//tgt[tgtIdx]=map[ny][nx];
			dfs (tgtIdx+1, ny, nx, str+map[ny][nx]);
		}
	}

}
