package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6808 {
	
	static final int MAX=10;
	
	static int T;
	static int win_cnt, lose_cnt;
	
	static boolean[] card;
	static int[] user1;
	static int[] user2;
	static int[] order;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			user1=new int[MAX];
			user2=new int[MAX];
			order=new int[MAX];
			selected=new boolean[MAX];
			card=new boolean[19];
			
			win_cnt=0; lose_cnt=0;
			
			st=new StringTokenizer (br.readLine());
			for (int i=1; i<MAX; i++) {
				user1[i]=Integer.parseInt(st.nextToken());
				card[user1[i]]=true;
			}
			
			int cnt=1;
			for (int i=1; i<=18; i++) {
				if (!card[i]) {
					user2[cnt++]=i;
				}
			}
			
			dfs (1,0,0);
			
			System.out.println("#"+tc+" "+lose_cnt+" "+win_cnt);
		}
		
	}
	
	private static void dfs (int cnt, int sum1, int sum2) {
		if (cnt==MAX) {
			if (sum1>sum2) lose_cnt++;
			else if (sum1<sum2) win_cnt++;
			return ;
		}
		
		for (int i=1; i<MAX; i++) {
			if (selected[i]) continue;
			
			selected[i]=true;
			order[cnt]=user2[i];
			
			if (user1[cnt]>order[cnt])
				dfs (cnt+1, sum1+user1[cnt]+order[cnt], sum2);
			else if (user1[cnt]<order[cnt])
				dfs (cnt+1, sum1, sum2+user1[cnt]+order[cnt]);

			selected[i]=false;
		}
	}

}
