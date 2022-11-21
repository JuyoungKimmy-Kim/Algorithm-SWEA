package a형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2112_보호필름 {

	static int T,D,W,K, ans;
	static int [][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			map=new int[D][W];
			for (int i=0; i<D; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			if (K==1) {
				System.out.println("#"+tc+" 0");
				continue;
			}
			
			ans=K;
			comb (0,0,true);
			System.out.println("#"+tc+" "+ans);
		}

	}
	
	static void comb (int tgtIdx, int cnt, boolean flag) {
		
		if (cnt>=ans) return ;
		if (flag && check()) {
			ans=cnt;
			return ;
		}
		
		if (tgtIdx==D) return;
		
		comb (tgtIdx+1, cnt, false);
		
		
		// tgt 선택하고 전부 0 or 전부 1		
		int[] tmp=new int[W];
		
		for (int i=0; i<W; i++) {
			tmp[i]=map[tgtIdx][i];
			map[tgtIdx][i]=1;
		}
		comb (tgtIdx+1, cnt+1, true);
		
		for (int i=0; i<W; i++) 
			map[tgtIdx][i]=0;
		comb(tgtIdx+1, cnt+1, true);
		
		for (int i=0; i<W; i++)
			map[tgtIdx][i]=tmp[i];
		
	}
	
	static boolean check () {
		for (int j=0; j<W; j++) {
			
			int prev=map[0][j];
			int count=1;
			
			for (int i=1; i<D; i++) {
				int now=map[i][j];
				
				if (prev==now) count++;
				else {
					prev=now;
					count=1;
				}
				
				if (count==K) break;
				if (i==D-1) return false;
			}
		}
		return true;
	}

}
