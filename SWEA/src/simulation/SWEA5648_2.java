package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Atom {
	boolean alive;
	int y,x,d,k;
	
	public Atom (int y, int x, int d, int k) {
		this.y=y;
		this.x=x;
		this.d=d;
		this.k=k;
		this.alive=true;
	}
}

class Map {
	int y,x;
	List<Integer> no;
	
	Map (int y, int x, int n) {
		this.y=y; 
		this.x=x;
		no=new ArrayList<>();
		no.add(n);
	}
}

public class SWEA5648_2 {
							//상 하 좌 우
	static final int dy[]= {1,-1,0,0};
	static final int dx[]= {0,0,-1,1};
	
	static final int MAX=2001;
	
	static int T, N, ans, remain;
	static List<Atom> atom;
	static List<Map> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			N=Integer.parseInt(br.readLine());
			atom=new ArrayList<>();
			map=new ArrayList<>();
			
			ans=0;
			remain=N;
			
			for (int i=0; i<N; i++) {
				StringTokenizer st=new StringTokenizer (br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				int K=Integer.parseInt(st.nextToken());
	
				atom.add(new Atom (y,x, d, K));
				updateMap (i, y,x);
			}
			
			int cnt=0;
			while (true) {
				
				if (cnt==MAX) break;

				crush ();
				
				for (int i=0; i<map.size(); i++) {
					map.get(i).no.clear();
				}
				map.clear();
				
				move();	
				
				if (remain==0) 
					break;

				cnt++;
			
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	
	static void move () {

		for (int i=0; i<N; i++) {
			
			if (!atom.get(i).alive) continue;
			
			int y=atom.get(i).y;
			int x=atom.get(i).x;
			int d=atom.get(i).d;
			
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			atom.get(i).y=ny;
			atom.get(i).x=nx;

			updateMap (i, ny, nx);
		}
	}
	
	static void updateMap (int idx, int y, int x) {
		for (int j=0; j<map.size(); j++) {
			int my=map.get(j).y;
			int mx=map.get(j).x;
			
			if (my==y && mx==x) {
				map.get(j).no.add(idx);
				return ;
			}
		}
		map.add(new Map (y, x, idx));
	}
	
	static void crush () {
		for (int i=0, size=map.size(); i<size; i++) {
			if (map.get(i).no.size()==1) {
				int idx=map.get(i).no.get(0);
				int ny=atom.get(idx).y+dy[atom.get(idx).d];
				int nx=atom.get(idx).x+dx[atom.get(idx).d];
				
				halfCrush (idx, ny, nx);
				map.get(i).no.clear();
			}
			
			else if (map.get(i).no.size()>=2) {
				
				for (int j=0, nSize=map.get(i).no.size(); j<nSize; j++) {
					int idx=map.get(i).no.get(j);
					ans+=atom.get(idx).k;
					atom.get(idx).alive=false;
					remain--;
				}
				map.get(i).no.clear();
			}
		}
	
	}
	
	static void halfCrush (int idx, int ny, int nx) {
		for (int i=0, size=map.size(); i<size; i++) {
			if (map.get(i).y==ny && map.get(i).x==nx) {
				int d1=atom.get(idx).d;
				int d2=atom.get(i).d;
				
				if (map.get(i).no.size()==1 && canCrush (d1, d2)) {
					int nIdx=map.get(i).no.get(0);
					
					ans+=(atom.get(idx).k+atom.get(nIdx).k);
					
					atom.get(nIdx).alive=false;
					atom.get(idx).alive=false;
					remain-=2;
					
					map.get(i).no.clear();
					
					return ;
				}
			}
		}
	}
	
	static boolean canCrush (int d1, int d2) {
		if (d1==0 && d2==1) return true;
		if (d1==1 && d2==0) return true;
		if (d1==2 && d2==3) return true;
		if (d1==3 && d2==2) return true;
		return false;
	}
}
