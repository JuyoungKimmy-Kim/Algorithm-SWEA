package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4014 {

	static int T,N,X;
	static int[][] map1, map2;
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			X=Integer.parseInt(st.nextToken());
			
			map1=new int[N][N];
			map2=new int[N][N];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map1[i][j]=Integer.parseInt(st.nextToken());
					map2[j][i]=map1[i][j];
				}
			}
			
			
		}

	}
	
	static void check (int[][] map)

}
