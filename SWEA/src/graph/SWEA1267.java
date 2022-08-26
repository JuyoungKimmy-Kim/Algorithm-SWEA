package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1267 {

	static int V,E;
	static int[] inDegree;
	static List<Integer> adj[];
	static Queue<Integer> q=new ArrayDeque<>();
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		for (int tc=1; tc<=10; tc++) {
			st=new StringTokenizer (br.readLine());
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			
			adj=new ArrayList[V+1];
			for (int i=0; i<=V; i++)
				adj[i]=new ArrayList<>();
			
			inDegree=new int[V+1];
			Arrays.fill(inDegree, 0);
			
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<E; i++) {
				int u=Integer.parseInt(st.nextToken());
				int v=Integer.parseInt(st.nextToken());
				
				inDegree[v]++;
				adj[u].add(v);
			}
			sb=new StringBuilder();

			topologicalSort();
			System.out.println("#"+tc+" "+sb.toString());
			
		}
		
		

	}
	
	static void topologicalSort () {
		
		for (int i=1; i<=V; i++) 
			if (inDegree[i]==0)
				q.offer(i);
		
		while (!q.isEmpty()) {
			int x=q.poll();
			sb.append(x).append(' ');
			
			for (int j=0; j<adj[x].size(); j++) {
				int y=adj[x].get(j);
				if (--inDegree[y]==0) q.offer(y);
			}
		}
	}

}
