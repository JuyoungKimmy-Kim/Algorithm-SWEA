package bfs.dfs;

/*
 * 부분집합
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215_3 {

	static int T, N,L,max;
	static Item[] src;
	static boolean[] select;
	
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
			select=new boolean[N];
			
			for (int n=0; n<N; n++) {
				st=new StringTokenizer (br.readLine());
				src[n]=new Item (Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			subset (0);
			System.out.println("#"+tc+" "+max);
		}

	}
	
	static void subset (int srcIdx) {
		if (srcIdx==N) {
			
			//complete code
			
			int cal=0;
			int point=0;
			
			for (int i=0; i<N; i++) {	//N개의 재료를 순회하면서 선택된 것만 고려
				if (!select[i]) continue;
				cal+=src[i].c;
				point+=src[i].p;
			}
			if (cal<=L) max=Math.max(max, point);
			
			
			return;
		}
		
		select[srcIdx]=true; //현재 선택
		subset (srcIdx+1);
		
		select[srcIdx]=false; //현재 선택 XsrcIdx
		subset (srcIdx+1);
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
