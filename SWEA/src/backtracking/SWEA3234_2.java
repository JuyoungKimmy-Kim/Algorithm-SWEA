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

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		int T=Integer.parseInt(br.readLine());

		for (int tc=1; tc<=T; tc++) {
			
			ans=0;
			int N=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer (br.readLine());
			int[] choo=new int[N];

					
			for (int i=0; i<N; i++) {
				choo[i]=Integer.parseInt(st.nextToken());
			}
			
			perm (0,0,0,0, choo);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	
	static void perm (int tgtIdx, int leftSum, int rightSum, int mask, int[] choo) {
		
		if (tgtIdx==N) {
			ans++;
			return ;
		}
		
		for (int i=0; i<N; i++ ) {
			if ((mask & 1<<i)!=0) continue;

			// 2재귀 호출
			// #1. 왼쪽에 추를 올리는 경우
			
			perm (tgtIdx+1, leftSum+choo[i], rightSum, mask | 1<<i, choo);
			// #2. 오른쪽에 추를 올리는 경우
			
			if (leftSum>=rightSum+choo[i]) 
				perm (tgtIdx+1, leftSum, rightSum+choo[i], mask | 1<<i, choo);

		}
	}
}
