package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable <Edge>{
	int u,v,cost;
	Edge (int u, int v, int cost ) {
		this.u=u;
		this.v=v;
		this.cost=cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost-o.cost;
	}
}

public class SWEA3124_Kruskal {

	static int T, V,E;
	static int[] parents;
	static PriorityQueue <Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			
			parents=new int[V+1];
			for (int i=1; i<=V; i++)
				parents[i]=i;
			
			pq=new PriorityQueue<>();
			
			for (int i=0; i<E; i++) {
				st=new StringTokenizer (br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
			
				pq.add(new Edge (a,b,c));
			}
			
			System.out.println("#"+tc+" "+makeSet());	
		}
	}
	
	static int find (int u) {
		if (parents[u]==u) return u;
		else return find (parents[u]);
	}
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u!=v) {
			if (u>v) parents[u]=v;
			else parents[v]=u;
			return true;
		}
		else return false;
	}
	
	static long makeSet () {
		int cnt=1; 
		long sum=0;
		
		while (!pq.isEmpty()) {
			Edge cur=pq.poll();
			int u=cur.u;
			int v=cur.v;
			int cost=cur.cost;
			
			if (union (u,v)) {
				sum+=cost;
			} 
		}
		
		return sum;
	}
}
