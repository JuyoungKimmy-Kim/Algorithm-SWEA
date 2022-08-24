package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA7465_2 {

	static int T,N,M,ans;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents=new int[N+1];
			makeSet();
			
			for (int i=0; i<M; i++) {
				st=new StringTokenizer (br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				
				union(a,b);
			}
			// 무리의 수, 집합의 수 => parent[i]==i 갯수 
			ans=0;
			for (int i=1; i<=N; i++) {
				if (parents[i]==i) ans++;
			}
			System.out.println("#"+t+" "+ans);
		}
	}
	

	static void makeSet () {
		for (int i=1; i<=N; i++)
			parents[i]=i;
	}
	
	static int find (int u) {
		if (parents[u]==u) return u;
		else return parents[u]=find(parents[u]);
	}
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u!=v) {
			if (u>v) parents[u]=v;
			else parents[v]=u;
			return true;
		}
		return false;
	}
}
