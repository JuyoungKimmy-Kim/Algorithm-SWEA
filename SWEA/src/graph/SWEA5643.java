package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * inDegree, outDegree 를 더한 값이 N-1이 되게 풀려고 했음
 * => 예전에도 비슷한 문제 풀었을 때 똑같은 실수 반복 -> 이렇게 풀리지 않는 경우가 있음
 * 
 * 따라서 i가 k를 이기고,k가 j를 이기면 i는 k를 이긴다는 생각 -> floydWarshall
 * 승패를 알 수 있는 matrix를 만들고, 
 * 
 * matrix[i][j] ==true || matrix[j][i]==true 인 경우를 따져봄 -> 둘 중 하나라도 참이면 i와 j 사이의 승패를 알 수 있다
 * 
 * 
 */
public class SWEA5643 {

	static int T,N,M, ans;
	static int[] inDegree, outDegree;
	static boolean[][] matrix;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			ans=0;
			
			N=Integer.parseInt(br.readLine());
			M=Integer.parseInt(br.readLine());
			
			matrix=new boolean[N+1][N+1];
		
						
			for (int i=0; i<M; i++) {
				st=new StringTokenizer (br.readLine());
				int s=Integer.parseInt(st.nextToken());
				int t=Integer.parseInt(st.nextToken());

				matrix[t][s]=true;
			}
			
			for (int k=1; k<=N; k++) {
				for (int i=1; i<=N; i++) {
					if (i==k) continue;
					for (int j=1; j<=N; j++) {
						if (i==j || j==k) continue;
						
						if (matrix[i][k] && matrix[k][j]) 
							matrix[i][j]=true;
					}
				}
			}
			
			for (int i=1; i<=N; i++) {
				int cnt=0;
				for (int j=1; j<=N; j++) {
					if (i==j) continue;
					if (matrix[i][j] || matrix[j][i]) cnt++;
				}
				if (cnt==N-1) ans++;
			}
			
			
			System.out.println("#"+tc+" "+ans);
			//print();
		}

	}
	
	static void print () {
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				System.out.print(matrix[i][j] ? "O ":"X ");
			}
			System.out.println();
		}
	}
	

}
