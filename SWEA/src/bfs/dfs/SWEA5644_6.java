package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5644_6 {

	static final int dy[] = {0,-1,0,1,0};
	static final int dx[] = {0,0,1,0,-1};
	
	static int T,M,A, ans,ay,ax,by,bx;
	static int[][] move;
	static List<BC> bcList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			M=Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			
			move=new int[2][M+1];
			
			for (int i=0; i<2; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=1; j<=M; j++) {
					move[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			bcList=new ArrayList<>();
			for (int i=0; i<A; i++) {
				st=new StringTokenizer (br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				int p=Integer.parseInt(st.nextToken());
				
				bcList.add(new BC (x,y,c,p));
			}

			ay=ax=1;
			by=bx=10;
			
			ans=0;
			charge();
			System.out.println("#"+tc+" "+ans);
						
		}
	}
	
	
	static void charge () {
		for (int T=0; T<=M; T++) {
			int aDir=move[0][T];
			int bDir=move[1][T];
			
			ay+=dy[aDir]; ax+=dx[aDir];
			by+=dy[bDir]; bx+=dx[bDir];
			
			int max=0;
			int sum=0;
			
			for (int i=0; i<A; i++) {
				for (int j=0; j<A; j++) {
					int aPower=getPower (i, ay,ax);
					int bPower=getPower (j, by,bx);
					
					if (aPower==0 && bPower==0) continue;
					else {
						if (i==j) sum=Math.max(aPower, bPower); 
						else sum=Math.max(sum, aPower+bPower); 	// 각각 다른 충전소에서 충전-> 단순히 더함
					}
					max=Math.max(max, sum);
				}
			}
			ans+=max;
		}
	}
	
	static int getPower (int idx, int y, int x) {
		
		int cy=bcList.get(idx).y;
		int cx=bcList.get(idx).x;
		int dist=bcList.get(idx).c;
		
		if (Math.abs(cy-y)+Math.abs(cx-x)<=dist) 
			return bcList.get(idx).p;
		else return 0;
		
	}
	
	static class BC {
		int x,y,c,p;
		
		BC (int x, int y, int c, int p) {
			this.x=x;
			this.y=y;
			this.c=c;
			this.p=p;
		}
	}
}
