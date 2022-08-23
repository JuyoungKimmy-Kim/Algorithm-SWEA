package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3289 {

	static int T, N,M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			StringBuilder sb=new StringBuilder ();
			
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			parents=new int[N+1];
			for (int i=1; i<=N; i++)
				parents[i]=i;
			
			for (int i=0; i<M; i++) {
				st=new StringTokenizer (br.readLine());
				int op=Integer.parseInt(st.nextToken());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				
				//a, b를 합침
				if (op==0) {
					union(a,b);
				}
				//a와 b가 함께 있는지 확인
				else if (op==1) {
					if (find(a)==find(b))
						sb.append(1);
					else sb.append(0);
				}
			}
			String ans=sb.toString();
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static int find (int u) {
		if (parents[u]==u) return u;
		else return find (parents[u]);
	}
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u==v) return false;
		if (u>v) parents[u]=v;
		else parents[v]=u;
		
		return true;
	}
}
