package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1238 {
	
	static int N,S, ans;
	static boolean [][] adj;
	static boolean [] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		for (int tc=1; tc<=10; tc++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			S=Integer.parseInt(st.nextToken());
			
			adj=new boolean [101][101];
			visited=new boolean[101];

			st=new StringTokenizer (br.readLine());
			for (int i=0; i<N/2; i++) {

				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				
				adj[from][to]=true;
			}
			bfs();
			System.out.println("#"+tc+" "+ans);
		}
		
	}
	
	static void bfs () {
		Queue <Integer> q=new ArrayDeque<>();
		q.add(S);
		visited[S]=true;
		
		while (!q.isEmpty()) {
			
			int qSize=q.size();
			int maxNode=0;
			
			// 현재  depth에서 새롭게 add되는 것들 모두 조사하기 위해
			while (--qSize>=0) {
				int cur=q.poll();
				
				for (int i=1; i<=100; i++) {
					if (adj[cur][i] && !visited[i]) { 
						visited[i]=true;
						q.add(i);
						maxNode=Math.max(maxNode, i);
					}
				}
				if (maxNode>0) ans=maxNode;
			}
		}
	}

}
