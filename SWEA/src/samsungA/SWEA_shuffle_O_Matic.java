package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_shuffle_O_Matic {

	static int N, ans;
	static int[] src, tgt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;

		int T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			
			src=new int[N+1];
			tgt=new int[N+1];
			
			st=new StringTokenizer (br.readLine());
			for (int n=1; n<=N; n++) 
				tgt[n]=src[n]=Integer.parseInt(st.nextToken());

			ans=6;
			perm (0, 0);
			
			if (ans==6) ans=-1;
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	private static void perm (int cnt, int prev) {

		
		if (isDescend(tgt)) 
			ans=Math.min(cnt, ans);
		if (isAscend(tgt)) 
			ans=Math.min(cnt, ans);
		
		if (cnt==5 || cnt>ans) return ;
		
		int[] tmp=new int[N+1];
		backup (tmp, tgt);
		                      
		for (int x=1; x<N; x++) {
			if (prev==x) continue;
			
			for (int k=1; k<=x; k++)
				shuffle (k);
					
			perm (cnt+1, x);
			backup (tgt, tmp);		
		}
	}
	
	private static void backup (int[] a, int []b) {
		for (int i=1; i<=N; i++)
			a[i]=b[i];
	}
	
	private static void shuffle (int x) {
		
		if (x>N/2) 
			x=N-x;
		
		int diff=x;
		for (int i=1; i<=x; i++) {
			int temp=tgt[N/2+diff];
			tgt[N/2+diff]=tgt[N/2+diff-1];
			tgt[N/2+diff-1]=temp;
			
			diff-=2;
		}
	}
	
	private static boolean isDescend(int[] tgt) {
		
		int idx=N;
		for (int i=1; i<=N; i++) {
			if (tgt[i]!=idx) 
				return false;
			idx--;
		}
		return true;
	}
	
	private static boolean isAscend(int[] tgt) {
		
		for (int i=1; i<=N; i++)
			if (tgt[i]!=i) return false;
		
		return true;
	}
}
