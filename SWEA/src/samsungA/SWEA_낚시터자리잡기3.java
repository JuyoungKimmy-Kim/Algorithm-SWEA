package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_낚시터자리잡기3 {
	
	static class Gate {
		int pos;
		int fisher;
		
		public Gate (int pos, int fisher) {
			this.pos=pos;
			this.fisher=fisher;
		}
	}
	
	static int T,N, fisherCnt, ans;
	static boolean[] selected;
	static int[] map;
	static Gate[] gates;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			selected=new boolean[4];
			gates=new Gate[4];
			map=new int[N+1];
			ans=Integer.MAX_VALUE;
			
			for (int i = 1; i <= 3; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				gates[i]=new Gate(a,b);
			}
			
			dfs (0,0);
			System.out.println("#"+tc+" "+ans);

		}
	}
	
	private static void dfs (int tgtIdx, int sum) {
		if (sum>=ans) return ;
		if (tgtIdx==3) {
			ans=Math.min(ans, sum);
			return ;
		}
		
		for (int i=1; i<=3; i++) {
			if (selected[i]) continue;
			
			// #1. 게이트 선택
			selected[i]=true;
			
			// #2. 낚시꾼 배치
			dfs (tgtIdx+1, sum+inFishers(i, gates[i].fisher, true));
			outFishers(i);

			//낚시꾼의 수가 짝수인 경우
			if (gates[i].fisher%2==0) {
				dfs (tgtIdx+1, sum+inFishers (i, gates[i].fisher, false));
				outFishers(i);
			}
			
			// #3. 선택 해제
			selected[i]=false;
		}
	}
	
	
	// 낚시꾼 배치 함수
	private static int inFishers (int idx, int fishers, boolean isOdd) {
		int distance=0;
		fisherCnt=0;
		int sum=0;
		
		while (fisherCnt < fishers) {
			sum+= isOdd ? left (idx, distance) : right (idx, distance);
			if (fisherCnt==fishers) break;
			sum+= isOdd ? right (idx, distance) : left (idx, distance);
			distance++;
		}
		
		return sum;
	}
	
	private static int left (int idx, int distance) {
		int left=gates[idx].pos-distance;
		
		if (left > 0 && map[left]==0) {
			map[left]=idx;
			fisherCnt++;
			return distance +1;
		}
		return 0;
	}
	
	private static int right (int idx, int distance) {
		int right=gates[idx].pos+distance;
		
		if (right <= N && map[right]==0) {
			map[right]=idx;
			fisherCnt++;
			return distance +1;
		}
		return 0;
	}
	
	private static void outFishers (int idx) {
		//int cnt=0;
		for (int i=1; i<=N; i++) {
			if (map[i]==idx) {
				map[i]=0;
				//cnt++;
			}
		}
	}
}
