package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7465 {

	static int T,N,M;
	static List<Integer> adj[];
	static int[] parents;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents=new int[N+1];
			adj=new ArrayList[N+1];
			visited=new boolean[N+1];
			
			for (int i=0; i<=N; i++) {
				adj[i]=new ArrayList<>();
				parents[i]=i;
			}
			
			for (int i=0; i<M; i++) {
				st=new StringTokenizer (br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				
				adj[a].add(b);
				adj[b].add(a);
				
			}
			
			int cnt=0;
			for (int i=1; i<=N; i++) {
				if (!visited[i]) {
					cnt++;
					makeSet (i);
				}
			}
			System.out.println("#"+t+" "+cnt);
		}
	}
	

	
	static void makeSet (int idx) {
		
		Queue <Integer> q=new ArrayDeque<>();
		visited[idx]=true;
		q.add(idx);
		
		while (!q.isEmpty()) {
			
			int cur=q.poll();
			
			for (int i=0; i<adj[cur].size(); i++) {
				int next=adj[cur].get(i);
				if (visited[next]) continue;
				
				visited[next]=true;
				q.add(next);
			}
		}
	}
}
