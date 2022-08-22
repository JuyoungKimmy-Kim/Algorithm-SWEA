package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3234_4 {

	static int N, T, ans;
	static int[] choo;
	static int[][] memoi;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			choo = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());

			int sum = 0;
			for (int i = 0; i < N; i++) {
				choo[i] = Integer.parseInt(st.nextToken());
				sum += choo[i];
			}

			memoi = new int[sum + 1][1 << N];
			ans=perm (0,0,0);
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int perm (int lsum, int rsum, int mask) {
		if (mask == (1<<N)-1) 
			return 1;
		
		if (memoi[lsum][mask]!=0)
			return memoi[lsum][mask];
		
		int cnt=0;
		for (int i=0; i<N; i++) {
			if ((mask & 1<<i)!=0) continue;
			
			cnt+=perm (lsum+choo[i], rsum, mask | 1<<i);
			if (rsum+choo[i]<=lsum)
				cnt+=perm (lsum, rsum+choo[i], mask | 1<<i);
		}
		
		memoi[lsum][mask]=cnt;
		return cnt;
	}
	
}
