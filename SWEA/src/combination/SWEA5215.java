package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215 {

	static int T,N,L, ans;
	static int[][] score; // 0- 맛, 1-calo
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			
			st=new StringTokenizer (br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			score=new int[N][2];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				score[i][0]=Integer.parseInt(st.nextToken());
				score[i][1]=Integer.parseInt(st.nextToken());
			}
			ans=0;
			comb (0,0,0);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void comb (int tgtIdx, int taste, int calo) {
		if (taste>ans) ans=taste;
		
		if (tgtIdx==N) return ;
		
		if (calo+score[tgtIdx][1]<=L)
			comb (tgtIdx+1, taste+score[tgtIdx][0], calo+score[tgtIdx][1]);
		comb (tgtIdx+1, taste, calo);
	}
}
