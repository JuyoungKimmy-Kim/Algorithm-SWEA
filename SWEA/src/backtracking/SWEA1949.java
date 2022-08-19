package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 가장 높은 봉우리에서 시작
 * 반드시 높은 지형 -> 낮은 지형으로 이동 (가로 또는 세로 방향으로 연결)
 * 딱 한 곳을 정해져서 최대 K만큼 깎음
 * 
 * 
 * 1. n*n 중에 1개 선택 -> k 깎고
 * 2. 가장 높은 점에서  깊이 우선 탐색
 */

public class SWEA1949 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int T, N, K, highest, ans;
	static int [][] map;
	static boolean [][] visited;
	static List<int[]> top;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;

		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			ans=0;
			highest=0;
			
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			map=new int[N][N];
			top=new ArrayList<> ();
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if (map[i][j]>highest)
						highest=map[i][j];
				}
			}
			
			solution ();
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	private static void solution () {
		
		// 가장 높은 위치 저장 
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==highest) {
					top.add(new int[] {i,j});
				}
			}
		}
		
		// N*N 중에 한 위치를 정해서 K 만큼 깎고, 제일 높은 탑 여러개를 돌아보면서 확인
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {		
				for (int k=0; k<=K; k++) {
					
					map[i][j]-=k;
					for (int l=0; l<top.size(); l++) {
						
						int sy=top.get(l)[0];
						int sx=top.get(l)[1];
						
						if (map[sy][sx]!=highest) continue;
						
						visited=new boolean[N][N];
						visited[sy][sx]=true;
						dfs (1,sy, sx);
					}
					map[i][j]+=k;
				}
			}
		}
	}
	
	private static void dfs (int cnt, int y, int x) {
		
		if (cnt>ans) ans=cnt;
		
		for (int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (ny<0 || nx<0 || ny>=N || nx>=N ) continue;
			if (map[ny][nx]>=map[y][x] || visited[ny][nx]) continue;
			
			visited[ny][nx]=true;			
			dfs (cnt+1, ny, nx);
			visited[ny][nx]=false;
		}
	}
	
	private static void print () {
		
		System.out.println("==================================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				
				if (visited[i][j])
					System.out.print("X ");
				else
					System.out.print("0 ");
			}
			System.out.println();
		}
	}

}
