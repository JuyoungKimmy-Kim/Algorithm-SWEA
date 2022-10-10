package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class samsung2022_02_예술성 {

	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	static final int MAX=30*30;
	
	static int N, ans;
	static int[][] map;
	static boolean[][] visited;
	static List<Integer> [][] adj;
	static List<Area> area;
	static List<Info> info;
	
	static int[] artScore=new int[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		

		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		adj=new ArrayList[N][N];
		for (int i=0; i<N; i++)
			for (int j=0; j<N; j++)
				adj[i][j]=new ArrayList<>();
		
		area=new ArrayList<>();
		info=new ArrayList<>();
		
		visited=new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int t=0; t<4; t++) {
		
			area.clear();
			info.clear();
			for (int i=0; i<N; i++)
				Arrays.fill(visited[i], false);
			
			for (int i=0; i<N; i++)
				for (int j=0; j<N; j++)
					adj[i][j].clear();
			
			int gNo=0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!visited[i][j]) 
						bfs (i,j, map[i][j], ++gNo);
				}
			}
			
			
			
//			for (Area a : area)
//				System.out.println(a.toString());
//			
//			System.out.println();
//			
//			for (Info i : info)
//				System.out.println(i.toString());
			
			
			getScore(t);
			rotate();
			
//			print();
		}
		
		for (int i=0; i<4; i++)
			ans+=artScore[i];
		
		System.out.println(ans);
		

	}
	
	static void bfs (int i, int j, int number, int gNo) {
		
		int[] adjInfo =new int[MAX+1];
		
		int size=1;
		
		Queue<int[]> queue=new ArrayDeque<>();
		queue.add(new int[] {i, j});
		visited[i][j]=true;
		
		for (int k=0; k<adj[i][j].size(); k++) {
			int n=adj[i][j].get(k);
			adjInfo[n]++;
		}
		
		while (!queue.isEmpty()) {
			int y=queue.peek()[0];
			int x=queue.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (!isInRange(ny,nx) || visited[ny][nx]) continue;
				if (map[ny][nx]==number) {
					queue.add(new int[] {ny, nx});
					visited[ny][nx]=true;
					size++;
					
					for (int k=0; k<adj[ny][nx].size(); k++) {
						int n=adj[ny][nx].get(k);
						adjInfo[n]++;
					}
					
				} else {
					adj[ny][nx].add(gNo);
				}
			}
		}
		
		area.add(new Area(gNo, size, number));
		
		for (i=1; i<=MAX; i++) {
			if (adjInfo[i]==0) continue;
			
			info.add(new Info (gNo, i, adjInfo[i]));
		}	
	}
	
	static void rotate () {
		Queue <Integer> queue=new ArrayDeque<>();
		
		for (int i=0; i<N/2; i++) 
			queue.add(map[i][N/2]);
		
		for (int i=0; i<N/2; i++)
			queue.add(map[N/2][i]);
		
		for (int i=N-1; i>N/2; i--)
			queue.add(map[i][N/2]);
		
		for (int i=N-1; i>N/2; i--)
			queue.add(map[N/2][i]);
		
		
		
		for (int i=0; i<N/2; i++) {
			int n=queue.poll();
			map[N/2][i]=n;
		}
		
		for (int i=N-1; i>N/2; i--) {
			int n=queue.poll();
			map[i][N/2]=n;
		}
		
		for (int i=N-1; i>N/2; i--) {
			int n=queue.poll();
			map[N/2][i]=n;
		}
		
		for (int i=0; i<N/2; i++) {
			int n=queue.poll();
			map[i][N/2]=n;
		}
		
		
		squareRotate (0,0, N/2);
		squareRotate (0,N/2+1, N/2);
		squareRotate (N/2+1,0, N/2);
		squareRotate (N/2+1,N/2+1, N/2);
		
		
	}
	
	static void squareRotate (int y, int x, int L) {
		
		int [][]tmp=new int[L][L];
		
		for (int i=0; i<L; i++) 
			for (int j=0; j<L; j++)
				tmp[i][j]=map[y+L-1-j][x+i];
		
		for (int i=0; i<L; i++)
			for (int j=0; j<L; j++)
				map[y+i][x+j]=tmp[i][j];
	}
	
	static void getScore(int idx) {
		
		for (int i=0; i<info.size(); i++) {
			int g1=info.get(i).g1;
			int g2=info.get(i).g2;
			int length=info.get(i).length;
			
			int g1Size=area.get(g1-1).size;
			int g2Size=area.get(g2-1).size;
			
			int g1Number=area.get(g1-1).number;
			int g2Number=area.get(g2-1).number;
			
			artScore[idx]+=(g1Size+g2Size)*g1Number*g2Number*length;
		}
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0|| y>=N || x>=N) return false;
		return true;
	}
	
	static class Area {
		int groupNo;
		int size;
		int number;
		
		public Area(int groupNo, int size, int number) {
			super();
			this.groupNo = groupNo;
			this.size = size;
			this.number = number;
		}

		@Override
		public String toString() {
			return "Area [groupNo=" + groupNo + ", size=" + size + ", number=" + number + "]";
		}
		
		
	}
	
	static class Info {
		int g1,g2;
		int length;
		
		Info (int g1, int g2, int length) {
			this.g1=g1;
			this.g2=g2;
			this.length=length;
		}

		@Override
		public String toString() {
			return "Info [g1=" + g1 + ", g2=" + g2 + ", length=" + length + "]";
		}
		
		
	}

	static void print () {
		System.out.println("=================================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
	}
}
