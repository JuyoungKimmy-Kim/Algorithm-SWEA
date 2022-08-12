package bfs.dfs;

/*
 * 재귀호출, 파라미터, 가지치기
 * 파라미터 -> 2차원 배열
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215_4 {

	static int T, N,L,max;
	static int[][] src;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			max=0;
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());

			src=new int[N][2]; //0: point 1: calo
			
			for (int n=0; n<N; n++) {
				st=new StringTokenizer (br.readLine());
				src[n][0]=Integer.parseInt(st.nextToken());
				src[n][1]=Integer.parseInt(st.nextToken());
			}

			dfs (0,0,0);
			System.out.println("#"+tc+" "+max);
		}

	}
	
	private static void dfs (int srcIdx, int point, int cal) {
		if (cal>L) return ;
		
		//기저조건
		if (srcIdx==N) {
			if (point>max) 
				max=point;
			return ;
		}
		
		dfs (srcIdx+1, point, cal);
		dfs (srcIdx+1, point +src[srcIdx][0], cal +src[srcIdx][1]);
	}


}
