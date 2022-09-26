package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952 {

	static int T, ans;
	static int[] cost, month;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			cost=new int[4];	// 1일, 1달, 3달, 1년
			month=new int[13];	// 1월, 2월, 3월...
			
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<4; i++) {
				cost[i]=Integer.parseInt(st.nextToken());
			}
			
			st=new StringTokenizer (br.readLine());
			for (int i=1; i<=12; i++) {
				month[i]=Integer.parseInt(st.nextToken());
			}
			
			ans=cost[3]; // 처음 1년 이용권을 최소비용으로 넣고 시작
			
			dfs (1,0);
			System.out.println("#"+tc+" "+ans);
		}
		
	}
	
	private static void dfs (int tgtIdx, int sum) {
		if (sum>ans) return ;
		
		if (tgtIdx>12) {
			ans=Math.min(ans, sum);
			return ;
		}	
		
		if (month[tgtIdx]==0) {
			dfs (tgtIdx+1, sum);
		} else {
			dfs (tgtIdx+1, sum+(month[tgtIdx]*cost[0]));	// 1일권 이용
			dfs (tgtIdx+1, sum+cost[1]);					// 1달권 이용
			dfs (tgtIdx+3, sum+cost[2]);					// 3달권 이용
		}
	}
}
