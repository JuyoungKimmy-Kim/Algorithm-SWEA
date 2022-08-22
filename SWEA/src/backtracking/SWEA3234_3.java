package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 실패 
 */
public class SWEA3234_3 {

	static int N, ans;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		int T=Integer.parseInt(br.readLine());

		for (int tc=1; tc<=T; tc++) {
			
			ans=0;
			int total=0;
			N=Integer.parseInt(br.readLine());
			
			
			StringTokenizer st=new StringTokenizer (br.readLine());
			int[] choo=new int[N];

					
			for (int i=0; i<N; i++) {
				choo[i]=Integer.parseInt(st.nextToken());
				total+=choo[i];
			}
			
			perm (0,total,0,0, choo);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	// 오른쪽 < 왼쪽 만족해야 함
	
	static void perm (int rightNum, int leftSum, int rightSum, int mask, int[] choo) {
		
		int leftNum=N-rightNum;
		ans+=factorial(leftNum);
		
		if (rightNum==N) return ;
		
		for (int i=0; i<N; i++ ) {
			if ((mask & 1<<i)!=0) continue;

			perm (rightNum+1, leftSum-choo[i], rightSum+choo[i], mask | 1<<i, choo);
	
			if (leftSum>=rightSum+choo[i]) 
				perm (rightNum+1, leftSum+choo[i], rightSum-choo[i], mask | 1<<i, choo);

		}
	}
	
	static int factorial (int n) {
		if (n==1) return 1;
		return n*factorial (n-1);	
	}
	
	
}
