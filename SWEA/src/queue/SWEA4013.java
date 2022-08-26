package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA4013 {

	static int T, K;
	
	static int[][] qui;
	static List<Integer> list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {

			K=Integer.parseInt(br.readLine());
			
			qui=new int[4][8];

			
			for (int i=0; i<4; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<8; j++) {
					int x=Integer.parseInt(st.nextToken());
					qui[i][j]=x;
				}
			}
			
			for (int i=0; i<K; i++) {
				st=new StringTokenizer (br.readLine());
				int no=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				
				rotateCheck (no-1, d);				
			}
			
			int ans=0;
			if (qui[0][0]==1) ans+=1;
			if (qui[1][0]==1) ans+=2;
			if (qui[2][0]==1) ans+=4;
			if (qui[3][0]==1) ans+=8;
			
			System.out.println("#"+tc+" "+ans);
		}

	}
	
	// N==0, S==1
	// 왼쪽 2 <-> 오른쪽 6 
	static void rotateCheck (int no, int d) {
		
		List<int[]> willRotate =new ArrayList<> ();
		willRotate.add(new int[] {no,d});
		
		int left=qui[no][6];
		int right=qui[no][2];
		
		int dir=d;
		
		for (int i=no-1; i>=0; i--) {
			dir*=-1;
			int lRight=qui[i][2];
			if (lRight!=left) {
				willRotate.add(new int[] {i, dir});
				left=qui[i][6];
			}
			else break;
		}
		
		dir=d;
		for (int i=no+1; i<4; i++) {
			dir*=-1;
			int rLeft=qui[i][6];
			if (rLeft!=right ) {
				willRotate.add(new int[] {i, dir});
				right=qui[i][2];
			}
			else break;
		}
		
		rotate (willRotate);

		
	}
	
	//1: 시계 방향
	//-1: 반시계 방향
	static void rotate (List<int[]> r) {
		for (int i=0; i<r.size(); i++) {
			int no=r.get(i)[0];
			int d=r.get(i)[1];
			
			
			if (d==1) {
				//꼬리에 있는게 젤 앞으로
				int temp=qui[no][7];
				for (int t=7; t>=1; t--) 
					qui[no][t]=qui[no][t-1];
				qui[no][0]=temp;
				
			} else if (d==-1) {
				//앞에 있는 게 제일 뒤로
				int temp=qui[no][0];
				for (int t=0; t<=6; t++)
					qui[no][t]=qui[no][t+1];
				qui[no][7]=temp;
			}
			
			//print();
		}
	}

	static void print () {
		System.out.println("====================");
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<8; j++) {
				System.out.print(qui[i][j]==0 ? "N " : "S ");
			}
			System.out.println();
		}
	}
}
