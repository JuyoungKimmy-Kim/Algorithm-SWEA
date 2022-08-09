package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 시계 방향으로 이동
// delta

public class SWEA1954_2 {
	
	static int T,N;
	static int[][] snail;
	
	public static int[] dy = { 0, 1, 0, -1 };
	public static int[] dx = { 1, 0, -1 ,0};
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			N=Integer.parseInt(br.readLine());
			snail=new int[N][N];
			
			//시작 좌표, 방향
			int y=0;
			int x=0;
			int d=0; //우 index	
			
			int num=1;
			//N*N만큼 반복하면서 숫자를 저장
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					snail[y][x]=num++; //숫자 저장
					
					// 이동
					// 현재 방향으로 이동하다가 더 이상 못가면 다음 방향으로 전환
					
					if (d==0) { // 우
						if (x+dx[d]>=N || snail[y][x+dx[d]]!=0) //오른쪽으로 가다가 벽이나 다른 좌표 만나면
							d=1; //  하래로 변경
					} else if (d==1) { //하
						if (y+dy[d]>=N || snail[y+dy[d]][x]!=0) // 아래로 내려가다가 벽이나 다른 좌표 만나면
							d=2; // 위로 변경
					} else if (d==2) { //좌
						if (x+dx[d]<0 || snail[y][x+dx[d]]!=0) //  왼쪽으로 가다가 벽이나 다른 좌표를 만나면
							d=3; 
					} else if (d==3) { //상
						if (y+dy[d]<0 || snail[y+dy[d]][x]!=0) // 위로가다가 벽이나 다른 좌표를 만나면
							d=0;
					}
					
					x+=dx[d]; y+=dy[d];
				}
			}
			
			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(snail[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
	

}