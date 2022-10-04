package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656_2 {
	

	static int dy[] = { 0, 0, 1, -1 };
	static int dx[] = { 1, -1, 0, 0 };

	static int T, N, W, H, ans = Integer.MAX_VALUE;
	static int[][] src;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();

		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
		
			ans=Integer.MAX_VALUE;
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			
			src=new int[H][W];
			
			for (int i=0; i<H; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<W; j++) {
					src[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			dfs (0);
			if (ans==Integer.MAX_VALUE) ans=0;
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void dfs (int tgtIdx) {
		if (tgtIdx==N) {
			ans=Math.min(ans, count());
			return ;
		}
	
		for (int i=0; i<W; i++) {
		
			int x=i, y=0;
			while (y<H && src[y][x]==0)
				y++;
			
			if (y==H) continue;
			
			int [][]tgt=new int[H][W];
			copy (tgt, src);
			
			destroy(y, x);
			gravity();
			dfs (tgtIdx+1);
			
			copy (src, tgt);	
		}
		
	}
	
	
	static void destroy (int i, int j) {
		Queue<Pos>q=new ArrayDeque<>();
		q.offer(new Pos (i,j,src[i][j]));

		boolean[][] visited=new boolean[H][W];
		visited[i][j]=true;
		
		while (!q.isEmpty()) {
			Pos pos=q.poll();
			int y=pos.y;
			int x=pos.x;
			int p=pos.p;
			
			src[y][x]=0;
			
			for (int d=0; d<4; d++) {
				
				for (int P=1; P<p; P++) {
					int ny=y+dy[d]*P;
					int nx=x+dx[d]*P;
					
					if (ny<0 || nx<0|| ny>=H || nx>=W) break;
					if (visited[ny][nx] || src[ny][nx]==0) continue;
					
					visited[ny][nx]=true;
					q.offer(new Pos (ny, nx, src[ny][nx]));
				}
			}
		}
	}
	
	static void gravity () {
		for (int j=0; j<W; j++) {
			for (int i=H-2; i>=0; i--) {
				
				if (src[i][j]==0) continue;
				int ny=i+1;
				while (ny<H && src[ny][j]==0)
					ny++;
				
				ny--;
				if (ny==i) continue;
				src[ny][j]=src[i][j];
				src[i][j]=0;
			}
		}
	}
	
	static int count () {
		int cnt=0;
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (src[i][j]!=0) cnt++;
			}
		}
		return cnt;
	}
	
	
	// b를 a에 복사
	static void copy (int[][] a, int[][] b) {
		for (int j=0; j<W; j++) {
			for (int i=0; i<H; i++) {
				a[i][j]=b[i][j];
			}
		}
	}
	
	static class Pos {
		int y,x,p;
		
		Pos (int y, int x, int p) {
			this.y=y;
			this.x=x;
			this.p=p;
		}
	}
}
