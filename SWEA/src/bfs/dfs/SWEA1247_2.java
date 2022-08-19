package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1247_2 {

	static int T,N, ans;
	static int hy,hx,oy,ox;
	static List<int[]> customer;
	static int[] index;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			customer=new ArrayList<>();
			N=Integer.parseInt(br.readLine());
			
			st=new StringTokenizer (br.readLine());
			
			oy=Integer.parseInt(st.nextToken());
			ox=Integer.parseInt(st.nextToken());
			hy=Integer.parseInt(st.nextToken());
			hx=Integer.parseInt(st.nextToken());
			
			for (int i=0; i<N; i++) {
				int y=Integer.parseInt(st.nextToken());
				int x=Integer.parseInt(st.nextToken());
				
				customer.add(new int[] {y,x});
			}

			ans=Integer.MAX_VALUE;
			
			index=new int[N];
			for (int i=0; i<N; i++)
				index[i]=i;
			
			// 가장 작은 수로 정렬 
			// 정렬된 현재 시작 배열도 경우의 수 중 하나
			while (true) {
				
				check();
				if (!np()) break;
			}
			System.out.println("#"+tc+" "+ans);
		}
	}

	static boolean np () {
		int i=index.length-1;
		while (i>0 && index[i-1]>=index[i]) i--;
		
		if (i==0) return false;
		
		int j=index.length-1;
		while (index[i-1] >=index[j]) j--;
		
		swap ( index, i-1, j);
		
		int k=index.length-1;
		while (i<k) swap (index, i++, k--);
		
		return true;
	}
	
	static void swap (int[] array, int i, int j) {
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	

	
	private static void check () {
		int y=oy; int x=ox;
		
		int dist=0;
		for (int i=0; i<N; i++) {
			int idx=index[i];
			
			int ny=customer.get(idx)[0];
			int nx=customer.get(idx)[1];
			
			dist+=(Math.abs(ny-y) + Math.abs(nx-x));
						
			y=ny; x=nx;
		}
		
		dist+=(Math.abs(hy-y)+Math.abs(hx-x));
		
		if (dist<ans) ans=dist;
	}

}
