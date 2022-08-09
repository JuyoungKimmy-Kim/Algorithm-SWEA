package list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA1228_2 {
	
	static int N,M;
	static LinkedList<String> list=new LinkedList<>();
	static StringBuilder sb=new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		for (int t=1; t<=10; t++) {
			
			list.clear();
			
			N=Integer.parseInt(br.readLine());
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<N; i++) {
				String p=st.nextToken();
				list.add(p);
			}
			
			M=Integer.parseInt(br.readLine());
			st=new StringTokenizer (br.readLine());
			
			for (int i=0; i<M; i++) {
				
				st.nextToken(); // I 명령어
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				
				int count=x+y; //x위치에 y개 만큼 문자열 추가
				for (int j=x; j<count; j++) {			
					list.add(j, st.nextToken());
				}
			}
			
			StringBuilder sb=new StringBuilder ();
			sb.append("#").append(t).append(" ");
			for (int test=0; test<10; test++)
				sb.append(list.get(test)).append(" ");
			System.out.println(sb.toString());
		}
	}

}
