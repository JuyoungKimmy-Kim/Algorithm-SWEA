package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA2805 {


	
	static int T,N;
	static int[][] map;
	static int sum=0;
	static int sx, count;
	static boolean visited[][];
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			
			sum=0;
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];

			for (int i=0; i<N; i++) {
				String line=br.readLine();
				for (int j=0; j<N; j++) {
					map[i][j]=line.charAt(j)-'0';
				}
			}
			
			sx=N/2; count=N;
			
			for (int i=0; i<N; i++)
				sum+=map[i][sx];
			
			for (int i=1; i<=N/2; i++) {
				int nx=sx+i;
				int px=sx-i;
				
				for (int j=i; j<N-i; j++) {
					sum+=map[j][nx];
					sum+=map[j][px];
				}
			}
			
		}
	}
	

	
	static void print() {
		System.out.println("=====================================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%2d", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
