package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1233 {
	
	static int N;
	static char[] tree;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		for (int tc=1; tc<=10; tc++) {
			N=Integer.parseInt(br.readLine());
			tree=new char[N+1];
			
			for (int i=1; i<=N; i++) {
				StringTokenizer st=new StringTokenizer (br.readLine());
				st.nextToken();
				char c=st.nextToken().charAt(0);
				tree[i]=c;
			}
			
			boolean result = vaild();
			
			if (result) System.out.println("#"+tc+" 1");
			else System.out.println("#"+tc+" 0");		
		}
	}
	
	private static boolean vaild () {
		for (int i=1; i<=N; i++) {
			
			char c=tree[i];
			
			// leaf node -> 무조건 숫자
			if (i*2 >N || i*2+1 >N) {
				if (c<'0' || c>'9')
					return false;
				continue;
			}
			
			if (c>='0' && c<='9') {
				char left=tree[i*2];
				char right=tree[i*2+1];
				
				if ((left>='0' && left<='9') || (right>='0' && right<='9') )
					return false;
			}
		}
		return true;
	}

}
