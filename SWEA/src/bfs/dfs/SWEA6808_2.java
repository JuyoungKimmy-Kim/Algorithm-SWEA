package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA6808_2 {
	
	static final int MAX=10;
	
	static int T, win, lose, N=9;
	static int[] input =new int[19];
	static int[] guCard=new int[9]; 	// 테케에서 고정
	static int[] inCard=new int[9];		// guCard에 없는 번호를 입력 <= 순열을 만들기 위해 선택할 수 있는 src
	static int[] tgt=new int[9];
	
	static boolean[] select=new boolean[N];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			win=0; lose=0;
			Arrays.fill(input, 0);
			
			st=new StringTokenizer (br.readLine());
			// 규영이 카드
			int num=0;
			for (int i=0; i<N; i++) {
				num=Integer.parseInt(st.nextToken());
				guCard[i]=num;
				input[num]=1;							// 인영이 카드 설정
			}
			num=0;
			for (int i=1; i<=18; i++) {
				if (input[i]==0) inCard[num++]=i;
			}
			
			perm(0);
			System.out.println("#"+tc+" "+win+" "+lose);
		}
	}
	
	
	static void perm (int tgtIdx) {
		// 기저 조건 ==> 규영이의 카드로부터 임의의 카드를 순열로 완성한 경우
		if (tgtIdx==N) {
			//complete code
			check();
			return ;
		}
		
		for (int i=0; i<N; i++) {
			if (select[i]) continue;
			
			tgt[tgtIdx]=inCard[i];
			select[i]=true;
			perm(tgtIdx+1);				
			//현재  i를 선택해서 모든 경우의 순열을 따져 본 후에 return 되어서 돌아오면 다음 i로
			
			select[i]=false;
		}
	}
	
	static void check () {
		
		int guSum=0;
		int inSum=0;
		
		for (int i=0; i<N; i++) {
			if (guCard[i] > tgt[i])
				guSum+=guCard[i]+tgt[i];
			else inSum+=guCard[i]+tgt[i];			
		}
		if (guSum > inSum) win++;
		else if (guSum< inSum) lose++;
	}

}
