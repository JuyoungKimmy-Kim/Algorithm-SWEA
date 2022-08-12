package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4012 {

	
	static int T,N;
	static int[][] score;
	static int[] A,B;
	static boolean[] selected;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++ ) {

			min=Integer.MAX_VALUE;
			
			N=Integer.parseInt(br.readLine());
			score=new int [N][N];
			selected=new boolean[N];
			
			for (int i=0; i<N; i++ ) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					score[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			comb(0,0);
			System.out.println("#"+tc+" "+min);
		}
	}
	
	private static void comb (int cnt, int idx) {
		
		if (cnt==N/2) {
			calc();
			return ;
		}

		if (idx==N) return ;
		selected[idx]=true;
		comb (cnt+1, idx+1);
		selected[idx]=false;
		comb (cnt, idx+1);

	}
	
	private static void calc () {
		A=new int[N/2];
		B=new int[N/2];
		
		int a=0; 
		int b=0;
		
		for (int i=0; i<N; i++) {
			if (selected[i]) A[a++]=i;
			else if (!selected[i]) B[b++]=i;
		}
		
		int a_sum=0;
		int b_sum=0;
	
		for (int i=0; i<N/2; i++) {
			for (int j=i+1; j<N/2; j++) {
				
				int first=A[i];
				int second=A[j];
				
				a_sum+=score[first][second];
				a_sum+=score[second][first];
				
				first=B[i];
				second=B[j];
				
				b_sum+=score[first][second];
				b_sum+=score[second][first];
				
			}
		}

		min=Math.min(min, Math.abs(a_sum-b_sum));	
	}

}
