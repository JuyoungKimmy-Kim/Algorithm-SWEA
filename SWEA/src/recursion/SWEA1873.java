package recursion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1873 {

	static int dy[]= {0,1,0,-1};
	static int dx[]= {1,0,-1,0};
	
	static int T,H,W,N;
	static char[][] map;
	static int y,x,d;
	
	public static void main(String[] args) throws IOException{
		
		System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			StringTokenizer st=new StringTokenizer (br.readLine(), " ");
			H=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			
			map=new char[H][W];
			
			for (int i=0; i<H; i++) {
				String line=br.readLine();
				for (int j=0; j<W; j++) {
					map[i][j]=line.charAt(j);

					switch (map[i][j]) {
					case '^' : y=i; x=j; d=3; break;
					case 'v' : y=i; x=j; d=1; break;
					case '<' : y=i; x=j; d=2; break;
					case '>' : y=i; x=j; d=0; break;
					default : break;
					}		
				}	
			}
			
			//print();
			
			N=Integer.parseInt(br.readLine());
			String line=br.readLine();
			
			for (int i=0; i<N; i++) {
				char op=line.charAt(i);
				
				if (op=='S') shoot();
				else move(op);
			}
			
			
			System.out.print("#"+t+" ");
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
			
			//print();
		}
	}
	
	static void move(char dir) {
		char c='0';
		switch (dir) {
		case 'U' : d=3; c='^'; break;
		case 'D' : d=1; c='v'; break;
		case 'L' : d=2; c='<'; break;
		case 'R' : d=0; c='>'; break;
		}
		
	
		int ny=y+dy[d];
		int nx=x+dx[d];
		
		if (isInRange(ny,nx))
			if (map[ny][nx]=='.') {
				map[y][x]='.';
				y=ny;
				x=nx;
		}

		map[y][x]=c;
		
		//print();
		
	}
	
	static void shoot () {
		int sy=y+dy[d];
		int sx=x+dx[d];
		
		//대포를 쏜 위치 다음 위치가 범위 밖이면 종료
		if (!isInRange(sy,sx)) return;
		
		// 다음 위치가 벽이거나 범위 밖일때까지 계속 전진
		
		while (true) {
			if (isInRange(sy, sx)) {
				if (map[sy][sx]=='.' || map[sy][sx]=='-') {
					sy+=dy[d];
					sx+=dx[d];
				}
				else break;
			}
			else break;
		}
		
		// 범위를 벗어나거나 강철로 만들어진 벽이면 아무 일도 일어나지 않음
		if (!isInRange(sy,sx) || map[sy][sx]=='#') return;
		// 벽돌로 만들어진 벽 -> 벽 파괴후 평지
		else if (map[sy][sx]=='*') {
			map[sy][sx]='.';
		}
		
		//print();
	}
	
	static boolean isInRange (int ny, int nx) {
		if (ny<0 || nx<0 || ny>=H || nx>=W)
			return false;
		return true;
	}
	
	
	static void print() {
		System.out.println("=====================================");
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.printf("%2c", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}

