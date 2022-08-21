package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 시간 초과
 */
public class SWEA3234_2 {
	
	static int T,N,ans;
	static int[] choo;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());

		for (int tc=1; tc<=T; tc++) {
			
			ans=0;
			N=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer (br.readLine());
			choo=new int[N];

					
			for (int i=0; i<N; i++) 
				choo[i]=Integer.parseInt(st.nextToken());
			
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void perm (int tgtIdx, int lsum, int rsum, int mask) {
		if (tgtIdx==N) {
			if (lsum>=rsum) ans++;
			return ;
		}
		
		for (int i=0; i<N; i++) {
			if ((mask & 1<<i)!=0) continue;
			
			perm (tgtIdx+1, lsum+choo[i], rsum, mask | 1<<i);
			if (rsum+choo[i]<=lsum)
				perm (tgtIdx+1, lsum, rsum+choo[i], mask | 1<<i);
		}
	}
	
	

}
