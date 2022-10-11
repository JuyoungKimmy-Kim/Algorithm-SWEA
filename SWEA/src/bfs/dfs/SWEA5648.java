package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 처음에 map을 사용하지 않고 좌표로만 구현 -> 시간 초과가 계속 났음
 * 1.5초에 충돌하는 경우는 halfcrush 로 구현 -> 상당히 복잡했음
 * 
 * ==> Map을 2배로 늘려서 표현
 * 좌표는 (-1000,-1000) 에서 (1000, 1000) 까지 주어지고, 좌표를 2배로 늘려줌
 * map의 최대 좌표는 4000인데 그 이유는 최대 좌표 (2000,2000) 에서 어느 방향으로 (2000, 2000) 만큼 갔는데도 
 * 다른 원자와 충돌하지 않았다는 것은 평생 충돌하지 않을 것이라는 얘기
 * 
 * 또 특이하게 상은 y가 증가하는 방향, 하는 y가 감소하는 방향.. -> 이상한 방향을 사용해서 헷갈렸음
 */
public class SWEA5648 {

	static final int dy [] = {1,-1,0,0};
	static final int dx [] = {0,0,-1,1};
	
	static final int MAX=4001;
	
	static int T,N, ans, total;
	static List<Atom> atom;
	static int [][] map=new int[MAX][MAX];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			ans=0;
			N=Integer.parseInt(br.readLine());
			atom=new ArrayList<>();

			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				int k=Integer.parseInt(st.nextToken());
				
				atom.add(new Atom ((y+1000)*2, (x+1000)*2, d, k));	
			}
			total=N;
			
			while (total > 0) {
				move();				
				crush();
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void crush () {
		for (int i=0; i<N; i++) {
			if (atom.get(i).alive==false) continue;
			int y=atom.get(i).y;
			int x=atom.get(i).x;
			
			if (map[y][x]==1) {
				map[y][x]=0;
				continue;
			}
			
			if (map[y][x]>1) {
				
				atom.get(i).alive=false;
				ans+=atom.get(i).k;
				
				for (int j=i+1; j<N; j++) {
					if (atom.get(j).alive==false) continue;
					if (atom.get(j).y==y && atom.get(j).x==x) {
						atom.get(j).alive=false;
						ans+=atom.get(j).k;
					}
				}
				
				total-=map[y][x];
				map[y][x]=0;
			}
		}
	}
	
	static void move () {
		for (int i=0; i<N; i++) {
			if (atom.get(i).alive==false) continue;
			
			int y=atom.get(i).y;
			int x=atom.get(i).x;
			int d=atom.get(i).d;
			
			y+=dy[d]; 
			x+=dx[d];
			
			if (y<0 || x<0 || y>=MAX || x>=MAX) {
				total--;
				atom.get(i).alive=false;
				continue;
			}
			
			atom.get(i).y=y;
			atom.get(i).x=x;
			
			map[y][x]++;
		}
	}

	
	static class Atom {
		boolean alive;
		int y,x,d,k;
		Atom (int y, int x, int d, int k) {
			this.y=y;
			this.x=x;
			this.d=d;
			this.k=k;
			
			this.alive=true;
		}
	}

}
