package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1263 {

	static final int INF=99999;
	static int T, N, ans;
	static int [][] matrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			matrix=new int[N][N];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					int n=Integer.parseInt(st.nextToken());
					if (n==0 && i!=j) matrix[i][j]=INF;
					else matrix[i][j]=n;
				}
			}
			
			ans=Integer.MAX_VALUE;
			floydWarshall();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static void floydWarshall () {
		
		for (int k=0; k<N; k++) {
			for (int i=0; i<N; i++) {
				int sum=0;
				
				if (i==k) continue;
				for (int j=0; j<N; j++) {
					
					if (i==j || k==j) continue;
					if (matrix[i][j]>matrix[i][k]+matrix[k][j]) {
						matrix[i][j]=matrix[i][k]+matrix[k][j];
					}
					sum+=matrix[i][j];
				}
				ans=Math.min(ans, sum);
			}
		}
	}

}
