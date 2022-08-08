import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9229 {

	static int TC;
	static int N,M;
	static int[] chips;
	static int ret=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		TC=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=TC; tc++) {
			ret=Integer.MAX_VALUE;
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			chips=new int[N];
			
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<N; i++) {
				chips[i]=Integer.parseInt(st.nextToken());
			}
			
			select (0,0,0);
			
			if (ret==Integer.MAX_VALUE) ret=-1;
			System.out.println("#"+tc+" "+ret);
			
		}

	}
	
	private static void select (int cnt, int start, int sum) {
		if (cnt==2) {
			if (ret==Integer.MAX_VALUE || sum>ret) ret=sum;
			return ;
		}
		
		for (int i=start; i<N; i++) {
			if (chips[i]>=M || sum+chips[i]>M) continue;
			
			select (cnt+1, i+1, sum+chips[i]);
		}
	}

}
