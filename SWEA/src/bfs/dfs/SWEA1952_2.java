package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952_2 {
	
	static int T, ans;
	
	static int[] price, month;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		price=new int[4];
		month=new int[12];
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1;tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<4; i++)
				price[i]=Integer.parseInt(st.nextToken());
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<12; i++)
				month[i]=Integer.parseInt(st.nextToken());
			
			ans=price[3];
			
			dfs (0, 0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static void dfs (int tgtIdx, int total) {
		if (tgtIdx>=12) {
			ans=Math.min(ans, total);
			return ;
		}
		
		if (month[tgtIdx]==0) dfs (tgtIdx+1,total);
		
		dfs (tgtIdx+1, total+ (month[tgtIdx]*price[0]));
		dfs (tgtIdx+1, total+ price[1]);
		dfs (tgtIdx+3, total+ price[2]);
	}

}
