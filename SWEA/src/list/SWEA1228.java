package list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA1228 {
	
	static int N,M;
	static LinkedList<Integer> pw=new LinkedList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		for (int t=1; t<=10; t++) {
			
			pw.clear();
			
			N=Integer.parseInt(br.readLine());
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<N; i++) {
				int p=Integer.parseInt(st.nextToken());
				pw.add(p);
			}
			
			M=Integer.parseInt(br.readLine());
			st=new StringTokenizer (br.readLine());
			
			for (int i=0; i<M; i++) {
				
				String s=st.nextToken();
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				
				for (int j=0; j<y; j++) {
					int commend=Integer.parseInt(st.nextToken());			
					pw.add(x++, commend);
				}
			}
			
			StringBuilder sb=new StringBuilder ();
			sb.append("#").append(t).append(" ");
			for (int test=0; test<10; test++)
				sb.append(pw.get(test)).append(" ");
			System.out.println(sb.toString());
		}
	}

}
