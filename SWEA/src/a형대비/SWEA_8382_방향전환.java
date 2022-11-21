package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_8382_방향전환 {

	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int T,sy,sx,ey,ex;
	static Integer[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		

		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			sy=Integer.parseInt(st.nextToken())+100;
			sx=Integer.parseInt(st.nextToken())+100;
			ey=Integer.parseInt(st.nextToken())+100;
			ex=Integer.parseInt(st.nextToken())+100;
			
			dp=new Integer[201][201][2];
			System.out.println("#"+tc+" "+bfs());
	
		}
	}
	
	static int bfs () {
		// 0은 가로에서 온 것
		// 1은 세로에서 온 것
		
		Queue<Pos> queue=new ArrayDeque<>();
		queue.add(new Pos (sy,sx,true));
		queue.add(new Pos (sy,sx,false));
		dp[sy][sx][0]=0;
		dp[sy][sx][1]=0;
		
		while (!queue.isEmpty()) {

			Pos pos=queue.poll();
			int y=pos.y;
			int x=pos.x;
			boolean fromRow=pos.fromRow;
			
			if (y==ey && x==ex) {
				if (fromRow) {
					return dp[y][x][0];
				} else {
					return dp[y][x][1];
				}
			}
			
			if (fromRow) {
				for (int d=2; d<4; d++) {
					int ny=y+dy[d];
					int nx=x+dx[d];
					
					if (!isInRange(ny,nx)) continue;
					if (dp[ny][nx][1]==null || (dp[ny][nx][1]>dp[y][x][0]+1)) {
						dp[ny][nx][1]=dp[y][x][0]+1;
						queue.add(new Pos (ny, nx, !fromRow));
					}
				}
			} else {
				for (int d=0; d<2; d++) {
					int ny=y+dy[d];
					int nx=x+dx[d];
					
					if (!isInRange(ny,nx)) continue;
					if (dp[ny][nx][0]==null || (dp[ny][nx][0]>dp[y][x][1]+1)) {
						dp[ny][nx][0]=dp[y][x][1]+1;
						queue.add(new Pos (ny, nx, !fromRow));
					}
				}
			}
		}	
		return 0;
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=201 || x>=201) return false;
		return true;
	}
	
	static class Pos {
		int y, x;
		boolean fromRow;
		
		Pos (int y, int x, boolean fromRow) {
			this.y=y;
			this.x=x;
			this.fromRow=fromRow;
		}
	}
}
