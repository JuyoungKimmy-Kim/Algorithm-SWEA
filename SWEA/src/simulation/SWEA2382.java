package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA2382 {
	
	static final int dy []= {0,-1,1,0,0};
	static final int dx []= {0,0,0,-1,1};
	
	static int T, N,M,K;
	static List<Integer> [][] map;
	static List<Micro> micro;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			map=new ArrayList[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					map[i][j]=new ArrayList<>();
				}
			}
			
			micro=new ArrayList<>();
			
			for (int i=0; i<K; i++) {
				st=new StringTokenizer (br.readLine());
				int y=Integer.parseInt(st.nextToken());
				int x=Integer.parseInt(st.nextToken());
				int cnt=Integer.parseInt(st.nextToken());
				int dir=Integer.parseInt(st.nextToken());
				
				micro.add(new Micro (y,x,cnt,dir));
			}
			
			for (int i=0; i<M; i++) {
				move();
				remove();
			}
			int result=count();
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void move () {
		for (int i=0; i<K; i++) {
			if (!micro.get(i).alive) continue;
			
			int y=micro.get(i).y;
			int x=micro.get(i).x;
			int cnt=micro.get(i).cnt;
			int dir=micro.get(i).dir;
			
			y+=dy[dir]; x+=dx[dir];
			if (y==0 || x==0 || y==N-1 || x==N-1) {
				dir=changeDir(dir);
				cnt/=2;
				
				if (cnt==0) {
					micro.get(i).alive=false;
					continue;
				}
			}
			
			micro.get(i).y=y;
			micro.get(i).x=x;
			micro.get(i).cnt=cnt;
			micro.get(i).dir=dir;
			
			map[y][x].add(i);
		}
	}
	
	static void remove() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j].size()<=1) {
					map[i][j].clear();
					continue;
				}
				
				Collections.sort(map[i][j], (c1, c2)-> micro.get(c2).cnt-micro.get(c1).cnt);
				int total=0;
				for (int k=1; k<map[i][j].size(); k++) {
					int idx=map[i][j].get(k);
					total+=micro.get(idx).cnt;
					micro.get(idx).alive=false;
				}
				
				int biggest=map[i][j].get(0);
				micro.get(biggest).cnt+=total;
				
				map[i][j].clear();
			}
		}
	}
	
	static int changeDir (int dir) {
		switch (dir) {
		case 1: return 2;
		case 2: return 1;
		case 3: return 4;
		case 4: return 3;
		}
		return 0;
	}

	static int count () {
		int num=0;
		
		for (int i=0; i<K; i++) {
			if (!micro.get(i).alive) continue;
			num+=micro.get(i).cnt;
		}
		
		return num;
	}
	
	static class Micro {
		
		boolean alive;
		int y,x;
		int cnt, dir;
		
		Micro (int y, int x, int cnt, int dir) {
			this.alive=true;
			this.y=y;
			this.x=x;
			this.cnt=cnt;
			this.dir=dir;
		}
	}
}
