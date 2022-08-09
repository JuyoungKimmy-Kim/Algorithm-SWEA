package bfs.dfs;

/*
 * 
 * for문을 사용하지 않고
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9229_3 {

	static int T,N,M,ans;
	static int[] src, tgt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			src=new int[N];
			tgt=new int[2];
			
			ans=-1;
			
			st=new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++)
				src[i]=Integer.parseInt(st.nextToken());

			comb(0,0);
			
			System.out.println("#"+t+" "+ans);
		}

	}

	private static void comb(int srcIdx, int tgtIdx) {
		
		// tgt 기저조건
		if (tgtIdx==2) {
			int sum=tgt[0]+tgt[1];
			if (sum>M) return ;
			ans=Math.max(ans, sum);
			
			return ;
			
		}
		
		// src 기저조건
		if (srcIdx==N) return ;
		
		tgt[tgtIdx]=src[srcIdx];
		
		comb (srcIdx+1, tgtIdx+1); //현재 선택을 받아 들임
		comb (srcIdx+1, tgtIdx);
		 
		
	}

}
