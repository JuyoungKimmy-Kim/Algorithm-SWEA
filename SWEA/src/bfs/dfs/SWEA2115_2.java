package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA2115_2 {

	static int T, N, M, C;
	static int aSum, bSum, ans;
	
	static int [][] map;
	static boolean [][] selected;
	static int []tgt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb=new StringBuilder ();
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			
			map=new int[N][N];
			selected=new boolean[N][N];
			tgt=new int[M*2];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			ans=0;
			comb(0,0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void subSum (int[] src, int tgtIdx, int sum, int profit, int no ) {
		if (sum>C) return ;
		if (tgtIdx==M) {
			if (no==0)
				aSum=Math.max(aSum, profit);
			else 
				bSum=Math.max(bSum, profit);
			return ;
		}
		
		int n=src[tgtIdx];
		subSum (src, tgtIdx+1, sum+n, profit+(n*n), no);
		subSum (src, tgtIdx+1, sum, profit, no);
	}
	
	static void comb (int tgtIdx, int y) {
		
		if (tgtIdx==2) {
			aSum=bSum=0;
			
			int [] src=new int[M];
			for (int i=0; i<M; i++)
				src[i]=tgt[i];
			subSum(src, 0,0,0,0);
			
			for (int i=M; i<2*M; i++)
				src[i-M]=tgt[i];
			subSum(src,0,0,0,1);
			
			ans=Math.max(ans, aSum+bSum);
			return ;
		}
		
		// backup
		Queue<int[]> q=new ArrayDeque<>();
		
		for (int i=y; i<N; i++) {
			for (int j=0; j<N; j++) {
				
				if (j+M-1>=N) break;
				
				boolean flag=true;
				for (int k=0; k<M; k++) {
					if (selected[i][j+k]) {
						flag=false;
						break;
					}
				}
				
				if (!flag) continue;
				for (int k=0; k<M; k++) {
					selected[i][j+k]=true;
					
					if (tgtIdx==1) tgt[k]=map[i][j+k];
					else tgt[M+k]=map[i][j+k];
					
					q.add(new int[] {i, j+k});
				}
				
				comb (tgtIdx+1, i);
				
				while (!q.isEmpty()) 
					selected[q.peek()[0]][q.poll()[1]]=false;

			}
		}
	}
	
	static void print () {
		System.out.println("===================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(selected[i][j] ? "O ":"X ");
			}
			System.out.println();
		}
	}

}
