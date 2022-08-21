package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3234_5 {

	static int T, N, sum, ans;
	static int[] choo;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			ans=0;
			
			N = Integer.parseInt(br.readLine());
			choo = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				choo[i] = Integer.parseInt(st.nextToken());
				sum+=choo[i];
			}
			
			Arrays.sort(choo);
			while (true) {
				perm (0,0,0);
				if (np()==false) break;
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static boolean np () {
		int i=choo.length-1;
		while (i>0 && choo[i-1]>= choo[i]) i--;
		
		if (i==0) return false;
		
		int j=choo.length-1;
		while (j>0 && choo[i-1]>=choo[j]) j--;
		
		swap (choo, i-1, j);
		
		int k=choo.length-1;
		while (i<k)
			swap (choo, i++, k--);
		return true;
		
	}
	static void swap (int[] array, int i, int j) {
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	
	static void perm (int tgtIdx, int lsum, int rsum) {
		if (tgtIdx==N) {
			if (lsum>=rsum) ans++;
			return ;
		}
		
		perm (tgtIdx+1, lsum+choo[tgtIdx], rsum);
		if (rsum+choo[tgtIdx]<=lsum)
			perm (tgtIdx+1, lsum, rsum+choo[tgtIdx]);
	}

}
