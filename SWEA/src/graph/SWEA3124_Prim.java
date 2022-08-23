package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge_ {
	int u, cost;
	
	Edge_ (int u, int cost) {
		this.u=u;
		this.cost=cost;
	}
}

public class SWEA3124_Prim {

	static int T, V,E;
	static List<Edge_> graph[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			
			graph=new ArrayList[V+1];
			for (int i=1; i<=V; i++) {
				graph[i]=new ArrayList<>();
			}
			
			for (int i=0; i<E; i++) {
				st=new StringTokenizer (br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
			
				graph[a].add(new Edge_(b,c));
				graph[b].add(new Edge_(a,c));
			}	
			System.out.println("#"+tc+" "+prim());
		}

	}
	
	static long prim () {
		boolean[] connected=new boolean[V+1];
		PriorityQueue <Edge_> pq=new PriorityQueue<>(
				(e1, e2) -> e1.cost-e2.cost);
		
		// #1.  시작 정점 선택 , 1번 부터
		int cnt=1;
		long sum=0;
		
		connected[1]=true;
		
		for (int i=0; i<graph[1].size(); i++)
			pq.add(graph[1].get(i));
		
		//pq.addAll(graph.get(1)); -> ArrayList로 만들었을 경우
		
		while (!pq.isEmpty()) {
			Edge_ edge=pq.poll();
			
			if ( connected[edge.u]) continue;	//새로 최소비용으로 연결하려는 정점이 이미 방문한 정점이면 skip
			connected[edge.u]=true;				//새로운 정점을 연결 
			sum+=edge.cost;
			cnt++;
			
			if (cnt==V) break;
			
			for (int i=0; i<graph[edge.u].size(); i++)
				pq.add(graph[edge.u].get(i));
		}
	
		return sum;
		
	}
}
