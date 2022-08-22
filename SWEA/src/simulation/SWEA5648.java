//package simulation;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//class Atom {
//	boolean alive;
//	int no;
//	int y,x,d,k;
//	
//	public Atom (int y, int x, int d, int k) {
//		this.y=y;
//		this.x=x;
//		this.d=d;
//		this.k=k;
//		this.alive=true;
//	}
//}
//
//public class SWEA5648 {
//							//상 하 좌 우
//	static final int dy[]= {-1,1,0,0};
//	static final int dx[]= {0,0,-1,1};
//	
//	static final int MAX=2001;
//	
//	static int T, N, ans, remain;
//	static List<Atom> atom;
//
//	static List<Integer>map[][];
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
//		T=Integer.parseInt(br.readLine());
//		
//		for (int tc=1; tc<=T; tc++) {
//			
//			N=Integer.parseInt(br.readLine());
//			atom=new ArrayList<>();
//			map=new ArrayList [MAX][MAX];
//			for (int i=0; i<MAX; i++) {
//				for (int j=0; j<MAX; j++) {
//					map[i][j]=new ArrayList<>();
//				}
//			}
//			
//			ans=0;
//			remain=N;
//			
//			for (int i=0; i<N; i++) {
//				StringTokenizer st=new StringTokenizer (br.readLine());
//				int x=Integer.parseInt(st.nextToken());
//				int y=Integer.parseInt(st.nextToken());
//				int d=Integer.parseInt(st.nextToken());
//				int K=Integer.parseInt(st.nextToken());
//				
//				y=1000-y;
//				x+=1000;
//				
//				atom.add(new Atom (y,x, d, K));
//				map[y][x].add(i);
//			}
//			
//			int cnt=0;
//			while (true) {
//				
//				if (cnt==MAX) break;
//				//print();
//				
//				move();
//				
//				//print();
//				
//				crush();
//				
//				//print();
//				
//				
//				if (remain==0) 
//					break;
//				
//				cnt++;
//			}
//			
//			System.out.println("#"+tc+" "+ans);
//		}
//	}
//	
//	
//	static void move () {
//		
//		for (int i=0; i<MAX; i++)
//			for (int j=0; j<MAX; j++)
//				map[i][j].clear();
//		
//		for (int i=0; i<N; i++) {
//			
//			if (!atom.get(i).alive) continue;
//			
//			int y=atom.get(i).y;
//			int x=atom.get(i).x;
//			int d=atom.get(i).d;
//			
//			int ny=y+dy[d];
//			int nx=x+dx[d];
//			
//			if (ny<0 || nx<0 || ny>=MAX || nx>=MAX) {
//				atom.get(i).alive=false;
//				remain--;
//				continue;
//			}
//			
//			atom.get(i).y=ny;
//			atom.get(i).x=nx;
//			map[ny][nx].add(i);
//		}
//	}
//	
//	static void crush () {
//		for (int i=0; i<MAX; i++) {
//			for (int j=0; j<MAX; j++) {
//				if (map[i][j].size()==0) continue;
//				
//				if (map[i][j].size()==1) {
//					
//					int idx=map[i][j].get(0);
//					int d=atom.get(idx).d;
//					int ny=i+dy[d];
//					int nx=j+dx[d];
//					
//					if (ny<0 || nx<0|| ny>=MAX || nx>=MAX) continue;
//					if (map[ny][nx].size()==1) {
//						int nIdx=map[ny][nx].get(0);
//						int nd=atom.get(nIdx).d;
//						
//						if (canCrush (d, nd)) {
//							ans+=(atom.get(idx).k+atom.get(nIdx).k);
//							atom.get(idx).alive=false;
//							atom.get(nIdx).alive=false;
//							map[i][j].clear();
//							map[ny][nx].clear();
//							remain-=2;
//						}
//					}
//				}
//				else if (map[i][j].size()>=2) {
//					for (int k=0; k<map[i][j].size(); k++) {
//						int idx=map[i][j].get(k);
//						ans+=atom.get(idx).k;
//						atom.get(idx).alive=false;
//						remain--;
//					}
//					map[i][j].clear();
//				}
//			}
//		}
//	}
//	
//	static boolean canCrush (int d1, int d2) {
//		if (d1==0 && d2==1) return true;
//		if (d1==1 && d2==0) return true;
//		if (d1==2 && d2==3) return true;
//		if (d1==3 && d2==2) return true;
//		return false;
//	}
//	
//	static void print () {
//		
//		System.out.println("=======================================");
//		for (int i=999; i<1001; i++) {
//			for (int j=999; j<1001; j++) {
//				System.out.print(map[i][j].size()+" ");
//			}
//			System.out.println();
//		}
//	}
//}
