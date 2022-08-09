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

public class SWEA9229_4 {

	static int T,N,M,ans;
	static int[] src1, src2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			src1=new int[N];
			src2=new int[N];
			
			ans=-1;
			
			st=new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				int n=Integer.parseInt(st.nextToken());
				src1[i]=src2[i]=n;
			}
			
			//중복 계산

//			for (int i=0; i<N; i++) {
//				for (int j=0; j<N; j++) {
//					if (i==j) continue;
//					
//					
//					if (src1[i] + src2[j]<=M && src1[i] +src2[j] >ans)
//						ans=src1[i]+src2[j];
//				}
//			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<i; j++) {
					
					int sum=src1[i] + src2[j];
					if (sum<=M && sum >ans)
						ans=sum;
				}
			}
			
			System.out.println("#"+t+" "+ans);
		}

	}


}
