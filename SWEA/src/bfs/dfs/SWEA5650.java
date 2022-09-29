package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA5650 {
	
	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};

	static int T, N, ans;
	static int[][] map;
	static Pair pair[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		StringTokenizer st=null;
		
		for (int tc=1; tc<=T; tc++) {
			
			
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			pair=new Pair[11];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine().trim());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					
					if (map[i][j]>=6) {
						pair[map[i][j]].list.add(new int[] {i,j});
					}
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j]==0) {
						
						for (int d=0; d<4; d++) 
							dfs (i, j, i,j, d, 0);
					}
				}
			}
		}
	}
	
	static void dfs (int sy, int sx, int y, int x, int dir, int cnt) {
		if ((cnt!=0 && sy==y && sx==x) || map[y][x]==-1) {
			ans=Math.max(ans, cnt);
			return ;
		}
		
		int nx=x; int ny=y;
		
		switch (dir) {
		case 0: 
			nx=x;
			while (nx<N && map[y][nx]==0)
				nx++;
			if (nx==N) {
				dfs (sy, sx, y, nx-1, 1, cnt+1);
			} else if (map[y][nx]==1 || map[y][nx]==2 || map[y][nx]==5) {
				dfs (sy, sx, y, nx-1, 1, cnt);
			} else if (map[y][nx]==3) {
				dfs (sy, sx, y, nx, 2, cnt);
			} else if (map[y][nx]==4) {
				dfs (sy, sx, y, nx, 3, cnt);
			}
			break;
		case 1: 
			nx=x;
			while (nx>=0 && map[y][nx]==0)
				nx--;
			if (nx==-1) {
				dfs (sy, sx, y, nx+1, 0, cnt+1);
			} else if (map[y][nx]==3 || map[y][nx]==4 || map[y][nx]==5) {
				dfs (sy, sx, y, nx+1, 0, cnt);
			} else if (map[y][nx]==1) {
				dfs (sy, sx, y, nx, 3, cnt);
			} else if (map[y][nx]==2) {
				dfs (sy, sx, y, nx, 2, cnt);
			}
			break;
		case 2: 
			ny=y;
			while (ny<N && map[ny][x]==0)
				ny++;
			if (ny==N) {
				dfs (sy, sx, ny-1, x, 3, cnt+1);
			} else if (map[ny][x]==2 || map[ny][x]==3 || map[ny][x]==5) {
				dfs (sy, sx, ny-1, x, 3, cnt);
			} else if (map[ny][x]==1) {
				dfs (sy, sx, ny, x, 0, cnt);
			} else if (map[ny][x]==4) {
				dfs (sy, sx, ny, x, 1, cnt);
			}
			break;
		case 3: 
			ny=y;
			while (ny>=0 && map[ny][x]==0)
				ny--;
			if (ny==-1) {
				dfs (sy, sx, ny+1, x, 2, cnt+1);
			} else if (map[ny][x]==1 || map[ny][x]==4 || map[ny][x]==5) {
				dfs (sy, sx, ny+1, x, 2, cnt);
			} else if (map[ny][x]==2) {
				dfs (sy, sx, ny, x, 0, cnt);
			} else if (map[ny][x]==3) {
				dfs (sy, sx, ny, x, 1, cnt);
			}
			break;
		}
		

		if (map[ny][nx]>=6 && map[ny][nx]<=10) {
			int holeNo=map[ny][nx];
			if (pair[holeNo].list.get(0)[0]==ny && pair[holeNo].list.get(0)[1]==nx) {
				dfs (sy, sx, pair[holeNo].list.get(1)[0],pair[holeNo].list.get(1)[1], dir, cnt );
			} else {
				dfs (sy, sx, pair[holeNo].list.get(0)[0],pair[holeNo].list.get(0)[1], dir, cnt );
			}
		}
		
		// 웜홀인 경우
		
	}
	
	static boolean isInRange (int ny, int nx) {
		if (ny<0 || nx<0 || ny>=N || nx>=N) return false;
		return true;
	}

	static class Pair {
		List<int[]> list;
		
		Pair () {
			list=new ArrayList<>();
		}
	}
}
