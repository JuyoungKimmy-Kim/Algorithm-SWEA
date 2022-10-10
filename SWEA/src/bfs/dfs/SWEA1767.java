package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int T, N;
	static int coreCnt, powerCnt;
	static int[][] map;
	
	static List<Coord> core;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			core=new ArrayList<>();
			
			map=new int[N][N];
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					
					if (map[i][j]==1 && i!=0 && j!=0 && i!=N-1 && j!=N-1) {
						core.add(new Coord (i, j));
					}
				}
			}
			
			coreCnt=Integer.MIN_VALUE;
			powerCnt=Integer.MAX_VALUE;
			
			dfs (0,0,0);
			sb.append("#").append(tc).append(" ").append(powerCnt).append("\n");
		}	
		System.out.println(sb.toString());
	}
	

	static void dfs (int tgtIdx, int cSum, int pSum) {

		if (tgtIdx==core.size()) {
			if (cSum>coreCnt) {
				coreCnt=cSum;
				powerCnt=pSum;
				return ;
			} else if (cSum==coreCnt) {
				powerCnt=Math.min(powerCnt, pSum);
				return ;
			} else return ;
		}
		

		for (int d=0; d<4; d++) {
			
			List<Coord> coord=new ArrayList<>();
			
			int y=core.get(tgtIdx).y;
			int x=core.get(tgtIdx).x;
			
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			coord.add(new Coord (ny, nx));
			
			int dist=0;
			while (isInRagne (ny,nx) && map[ny][nx]==0) {
				ny+=dy[d];
				nx+=dx[d];
				coord.add(new Coord (ny, nx));
				dist++;
			}	
			// 범위 내에서 끝났다는 말은 -> 다른 core을 만났다는 것
			if (isInRagne(ny,nx)) {
				dfs (tgtIdx+1, cSum, pSum);
				continue;
			}
			
			for (int i=0; i<coord.size()-1; i++) {				
				map[coord.get(i).y][coord.get(i).x]=2;
			}
			
			dfs (tgtIdx+1, cSum+1, pSum+dist);
			
			for (int i=0; i<coord.size()-1; i++) {				
				map[coord.get(i).y][coord.get(i).x]=0;
			}
			
		}
	}
	
	static boolean isInRagne (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}
	
	static class Coord {
		int y,x;
		
		Coord (int y, int x) {
			this.y=y;
			this.x=x;
		}
	}

}
