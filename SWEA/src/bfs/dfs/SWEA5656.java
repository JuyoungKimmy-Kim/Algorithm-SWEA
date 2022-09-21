package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656 {

	static int dy[] = {0,0,1,-1};
	static int dx[] = {1,-1,0,0};
	
	static int T,N,W,H;
	static int[][] map;
	static int[] tgt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		st=new StringTokenizer (br.readLine());
		N=Integer.parseInt(st.nextToken());
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		
		map=new int[H][W];
		tgt=new int[W];
		
		for (int i=0; i<H; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<W; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		perm (0,0);
	}
	
	private static void perm (int tgtIdx, int mask) {
		if (tgtIdx==N) {
			
			play();
			
			return ;
		} 
		
		for (int i=0; i<W; i++) {
			if ((mask & 1<<i)!=0) continue;
			tgt[tgtIdx]=i;
			perm (tgtIdx+1, mask | 1<<i);
		}
	}
	
	private static void play () {
		for (int i=0; i<N; i++) {
			int x=tgt[i];
			int y=0;
			
			while (y<H && map[y][x]!=0)
				y++;
			
			if (y==H) continue;
			
			destroy (y,x);
			
			print(map);
			
			gravity ();
		}
	}
	
	private static void copy_map (int [][]a, int[][]b) {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				b[i][j]=a[i][j];
			}
		}
	}
	
	private static void destroy (int i, int j) {
		Queue <int[]> q=new ArrayDeque<>();
		q.add(new int[] {i,j});
		map[i][j]=0;
		
		while (q.isEmpty()) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			int p=map[y][x];
			for (int d=0; d<4; d++) {

				for (int P=1; P<p; P++) {
					int ny=y+dy[d]*P;
					int nx=x+dx[d]*P;
					
					if (!isInRange (ny, nx)) break;
					
					if (P==p-1 && map[ny][nx]!=0) {
						q.add(new int[] {ny, nx});
					}
					map[ny][nx]=0;		
				}
			}
		}
	}
	
	private static void gravity () {
		
	}
	
	private static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=H || x>=W) return false;
		return true;
	}
	
	private static void print (int[][] a) {
		System.out.println("===========================");
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}

}
