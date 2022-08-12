package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215 {

	static int T, N,L,ans;
	static int[] score, calo;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			ans=0;
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			score=new int[N];
			calo=new int[N];
			selected=new boolean[N];
			
			for (int n=0; n<N; n++) {
				st=new StringTokenizer (br.readLine());
				score[n]=Integer.parseInt(st.nextToken());
				calo[n]=Integer.parseInt(st.nextToken());
			}
			
			comb(0,0,0);
			System.out.println("#"+tc+" "+ans);
		}

	}
	private static void comb (int idx, int total_cal, int total_score) {
	
		if (total_cal>L) return;
		if (idx==N) {
			ans=Math.max(ans, total_score);
			return;
		}
			
//		for (int i=start; i<N; i++) {
//			if (selected[i]) continue;
//			if (total_cal+calo[i]>L) continue;
//			
//			if (total_score+score[i]>ans)
//				ans=total_score+score[i];
//			
//			selected[i]=true;
//			comb (idx+1, i+1, total_cal+calo[i], total_score+score[i]);
//			selected[i]=false;	
//		}
		
		comb (idx+1, total_cal, total_score);
		comb (idx+1, total_cal+calo[idx], total_score+score[idx]);
		
		
	}

}
