package bfs.dfs;

/*
 * 조합으로
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215_2 {

	static int T, N,L,max;
	static Item[] src;
	static Item[] tgt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			max=0;
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			src=new Item[N];
			tgt=new Item[N];
			
			for (int n=0; n<N; n++) {
				st=new StringTokenizer (br.readLine());
				src[n]=new Item (Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// tgt -> 1개, 2개, ... N 개 재료를 사용
			
			for (int i=0; i<N; i++) {
				tgt=new Item [i]; // 1개짜리 배열, 2개짜리 배열... N개 짜리 배열
				comb (0,0);
			}
			
			System.out.println("#"+tc+" "+max);
		}

	}
	
	static void comb (int srcIdx, int tgtIdx) {
		// 기저 조건
		if (tgtIdx == tgt.length) {
			//complete code : L을 초과하지 않으면서 최대값을 따져본다
			
			int cal=0;
			int point =0;
			
			for (int i=0; i<tgtIdx; i++) 
				cal+=tgt[i].c;
			
			if (cal<=L) {
				for (int i=0; i<tgtIdx; i++) 
					point+=tgt[i].p;
			}
			
			max=Math.max(max, point);
			
			return ;
		}
		
		if (srcIdx==N) return;
		
		tgt[tgtIdx]=src[srcIdx];
		comb (srcIdx+1, tgtIdx+1);
		comb (srcIdx+1, tgtIdx);
	}
	
	 static class Item {
		 int p; // 맛에 대한 포인트
		 int c; // 칼로리
		 
		 Item (int p, int c) {
			 this.p=p;
			 this.c=c;
		 }
	 }

}
