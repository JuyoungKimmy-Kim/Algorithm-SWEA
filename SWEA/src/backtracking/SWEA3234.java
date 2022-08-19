package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 시간 초과
 */
public class SWEA3234 {
	
	static int ans;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		int T=Integer.parseInt(br.readLine());

		for (int tc=1; tc<=T; tc++) {
			
			int N=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer (br.readLine());
			int[] choo=new int[N];
			boolean[] select=new boolean[N];

					
			for (int i=0; i<N; i++) {
				choo[i]=Integer.parseInt(st.nextToken());
			}
			ans=0;
			perm ( choo, select, 0,0,0);
			System.out.println("#"+tc+" "+ans);
			
		}
	}
	
	
	static void perm (int[] choo, boolean[] select, int tgtIdx, int leftSum, int rightSum) {
		
		if (rightSum>leftSum) return;
		
		if (tgtIdx==choo.length) {
			ans++;
			return ;
		}
		
		for (int i=0; i<choo.length; i++ ) {
			if (select[i]) continue;
			
			select[i]=true;
			// 2재귀 호출
			// #1. 왼쪽에 추를 올리는 경우
			
			perm (choo, select, tgtIdx+1, leftSum+choo[i], rightSum);
			// #2. 오른쪽에 추를 올리는 경우
			
			if (leftSum>=rightSum+choo[i]) 
				perm (choo, select, tgtIdx+1, leftSum, rightSum+choo[i]);
			select[i]=false;
		}
	}
}
