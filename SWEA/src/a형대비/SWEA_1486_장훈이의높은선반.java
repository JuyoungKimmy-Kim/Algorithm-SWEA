package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1486_장훈이의높은선반 {

	static int T, N,B,S, ans; // test case/ 점원 수 / 높이가 B인 선반 / 점원들의 키의 합
	static int[] H;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			B=Integer.parseInt(st.nextToken());
			S=0;
			
			H=new int[N];
			selected =new boolean[N];
			
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<N; i++) {
				H[i]=Integer.parseInt(st.nextToken());
				S+=H[i];
			}
			
			ans=Integer.MAX_VALUE;
			powerSet(0,0);
			
			System.out.println("#"+tc+" "+ans);
		}
		
	}
	
	static void powerSet (int tgtIdx, int sum) {
		
		if (sum>=B) {
			ans=Math.min(ans, sum-B);
			return ;
		}
		if (tgtIdx==N) return ;
		
		selected[tgtIdx]=true;
		powerSet (tgtIdx+1, sum+H[tgtIdx]);
		
		selected[tgtIdx]=false;
		powerSet (tgtIdx+1, sum);
	}

}
