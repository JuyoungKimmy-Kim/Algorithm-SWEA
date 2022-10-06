package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA2112 {

	static int T, D,W,K, ans;
	static int [][] map;
	static boolean[] selected;
	static List<Integer> tgt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			ans=Integer.MAX_VALUE;
			
			st=new StringTokenizer (br.readLine());
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			map=new int[D][W];
			selected=new boolean[D];
			
			for (int i=0; i<D; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			subSet (0,0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void subSet (int tgtIdx, int cnt) {
		
		if (cnt>=ans) return ;

		
		int[][] tmp=new int[D][W];
		copy(tmp, map);

		tgt=new ArrayList<>();
		for (int i=0; i<D; i++) {
			if (selected[i]) tgt.add(i);
		}
		
		if (check(tmp, 0, cnt)) {
			ans=cnt;
			return ;
		}

		if (tgtIdx==D) return ;
		
		selected[tgtIdx]=true;
		subSet (tgtIdx+1, cnt+1);
		
		selected[tgtIdx]=false;
		subSet (tgtIdx+1, cnt);
		
	}
	
	static boolean check (int [][] tmp, int tgtIdx, int total) {
		
		//print(tmp);
		
		if (tgtIdx==total) {
			for (int j=0; j<W; j++) {
				
				int prev=tmp[0][j];
				int cnt=1;
				
				for (int i=1; i<D; i++) {
					int now=tmp[i][j];
					if (prev==now) {
						cnt++;
						if (cnt==K) break;
					}
					else {
						prev=now;
						cnt=1;
					}
					if (i==D-1 && cnt<K) 
						return false;
				}
			}
			return true;
		}

		
		for (int j=0; j<W; j++) 
			tmp[tgt.get(tgtIdx)][j]=0;
		
		boolean ret=check (tmp, tgtIdx+1, total);
		if (ret) return true;
		
		for (int j=0; j<W; j++)
			tmp[tgt.get(tgtIdx)][j]=1;
		
		ret= check (tmp, tgtIdx+1, total);
		if (ret) return true;
		
		return false;
	}

	
	static void copy(int [][] a, int[][] b) {
		for (int i=0; i<D; i++) {
			for (int j=0; j<W; j++) {
				a[i][j]=b[i][j];
			}
		}
	}
	
	static void print (int[][] a) {
		System.out.println("============================");
		for (int i=0; i<D; i++) {
			for (int j=0; j<W; j++) {
				System.out.printf("%2d", a[i][j]);
			}
			System.out.println();
		}
	}

}
