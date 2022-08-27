package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Pos {
	int y; int x;
	
	Pos (int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class SWEA_헌터 {

	static int T, N, K, ans;
	static int[][] map;
	static boolean []select;
	
	static Pos[] monster, customer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			
			map=new int[N+1][N+1];
			
			monster=new Pos[5];
			customer=new Pos[5];
			
			K=0;
			
			for (int i=1; i<=N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=1; j<=N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if (map[i][j]!=0) {
						K++;
						
						// 고객인 경우
						if (map[i][j]<0) {
							customer[map[i][j]*-1]=new Pos (i,j);
						} else {// 몬스터인 경우
							monster[map[i][j]]=new Pos (i,j);
						}
					}
				}
			}

			select=new boolean[K+1];
		
			for (int i=K; i>=K/2+1; i--)
				select[i]=true;
		
			ans=Integer.MAX_VALUE;
			comb (0,1,1,0);
			System.out.println("#"+tc+" "+ans);
		}
		
	}
	
	//select 0 / 1 2 3 / 4 5 6 ===> 
	static void comb (int tgtIdx, int y, int x, int sum) {
	
		if (tgtIdx==K) {
			ans=Math.min(ans, sum);
			return;
		}
		if (sum>ans) return ;
		
		for (int i=1; i<=K; i++) {
			if (select[i]) continue;
			
			if (i<=K/2) {
				select[i]=true;
				select[i+K/2]=false;
				
				int ny=monster[i].y;
				int nx=monster[i].x;
				
				int dist=Math.abs(ny-y)+Math.abs(nx-x);
				comb (tgtIdx+1, ny, nx, sum+dist);
				
				select[i]=false;
				select[i+K/2]=true;
			}
			else {
				select[i]=true;
				int ny=customer[i-K/2].y;
				int nx=customer[i-K/2].x;
				
				int dist=Math.abs(ny-y)+Math.abs(nx-x);
				comb (tgtIdx+1, ny, nx, sum+dist);
				select[i]=false;
				
			}
		}
	}
}
