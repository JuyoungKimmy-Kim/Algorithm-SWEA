package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_낚시터자리잡기2 {

	static int T,N, total, ans=Integer.MAX_VALUE;
	static boolean done;
	static boolean[] select, visitGate;
	static int[][] gate; 
	static int[][] dist;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			visitGate=new boolean[3];
			select=new boolean[N+1];
			gate=new int[3][2];
			dist=new int[3][N+1];
			
			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				gate[i][0]=a;
				gate[i][1]=b;
				total+=gate[i][1];
			}
			getDist();
			dfs (0,total, 0);		
			System.out.println(ans);	
			init();
			dfs (1, total, 0);	
			System.out.println(ans);		
			init();
			dfs (2, total, 0);
			
			System.out.println(ans);
		}
	}
	
	static void init() {
		Arrays.fill(select, false);
		Arrays.fill(visitGate, false);
	}
	
	static void print () {
		System.out.println("===========================");
		for (int i=1; i<=N; i++) {
			System.out.print(select[i] ? "O ": "X ");
		}
	}
	
	static void getDist () {
		
		for (int i=0; i<3; i++ ) {
			int start=gate[i][0];
			
			dist[i][start]=1;
			
			int d=1;
			for (int px=start-1; px>=1; px--)
				dist[i][px]=++d;
			
			d=1;
			for (int nx=start+1; nx<=N; nx++)
				dist[i][nx]=++d;
		}
	}
	
	
	static void dfs2 (int tgtIdx, int remain, int dSum) {
		
	}
	
	
	/*
	 * tgtIdx : 현재 몇 번 째 Gate
	 * remain : 총 남아있는 사람
	 * dSum : 현재까지 누적된 거리
	 * flag : 프로그램이 끝나야 하는지 여부
	 */
	static void dfs (int tgtIdx, int remain, int dSum) {
		if (visitGate[tgtIdx]) return;
		visitGate[tgtIdx]=true;
		
		if (dSum>ans) return;
		if (remain==0) {
			ans=Math.min(dSum, ans);
			return;
		}
		
		List<Integer> willVisit=new ArrayList<> ();			//이번 회차에 방문 되는 좌표 저장
		boolean[] visited=new boolean[N+1];				//현재 select에 있는 값 backup
		for (int i=1; i<=N; i++)
			visited[i]=select[i];
		
		int sum=0;
		int start=gate[tgtIdx][0];						//Gate 위치 번호
		int num=gate[tgtIdx][1];						//이 Gate에 있는 사람
		
		int i=0;										//현재 위치로부터 0,1,2... 칸씩 떨어지면서 탐색
		int n=num;										//현재 Gate에 있는 사람 저장 -> 찾을 때마다 줄여줄것
		while (n>1) {
			
			int px=start-i;
			int nx=start+i;
			
			if (px>=1) {
				if (!visited[px]) {
					willVisit.add(px);
					visited[px]=true;
					sum+=dist[tgtIdx][px];
					n--;
				}
			}
			
			if (nx<=N) {
				if (!visited[nx]) {
					willVisit.add(nx);
					visited[nx]=true;
					sum+=dist[tgtIdx][nx];
					n--;
				}
			}
			i++;
		}		
		//남은 사람이 없다면
		if (n==0) {	
			backup(willVisit, true);
			dfs ((tgtIdx+1)%3, remain-num, dSum+sum);
			dfs ((tgtIdx+2)%3, remain-num, dSum+sum);
			backup(willVisit, false);
			return ;
		}
		
		
		PriorityQueue<int[]> pq=new PriorityQueue<>( (p1,p2)-> p1[0]-p2[0]);
		int px=start-i;
		for (int x=px; x>=1; x--) {
			if (!visited[x])
				pq.add(new int[] {dist[tgtIdx][x], x});
		}
		
		int nx=start+i;
		for (int x=nx; x<=N; x++) {
			if (!visited[x])
				pq.add(new int[] {dist[tgtIdx][x], x});
		}
		
		//갈 수 있는 곳이 한 곳이면
		if (pq.size()==1) {
			int d=pq.peek()[0];
			int x=pq.poll()[1];
			
			select[x]=true;
			dfs ((tgtIdx+1)%3, remain-num, dSum+dist[tgtIdx][x]+sum);
			dfs ((tgtIdx+2)%3, remain-num, dSum+dist[tgtIdx][x]+sum);
			select[x]=false;
			return ;
		}
		

		int prev=pq.peek()[0];
		while (!pq.isEmpty()) {
			int d=pq.peek()[0];
			int x=pq.poll()[1];
			
			if (prev!=d) break;
			
			willVisit.add(x);
			backup (willVisit, true);
			dfs ((tgtIdx+1)%3, remain-num, dSum+dist[tgtIdx][x]+sum);
			dfs ((tgtIdx+2)%3, remain-num, dSum+dist[tgtIdx][x]+sum);
			backup (willVisit,false);
		}
		pq.clear();
	}
	
	static void backup (List<Integer>list,boolean flag) {
		//a의 상태를 b에 업데이트 
		
		for (int i=0; i<list.size(); i++) {
			int no=list.get(i);
			select[no]=flag;
		}
	}
}
