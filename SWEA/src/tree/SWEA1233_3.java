package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1233_3 {
	
	static int N, ans;
	static char[] node;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		for (int tc=1; tc<=10; tc++) {
			N=Integer.parseInt(br.readLine());
			node=new char[N+1]; // 0 dummy
			
			for (int i=1; i<=N; i++) {
				node[i]=br.readLine().split(" ")[1].charAt(0);
			}
			
			int idx=N;
			ans=1; //default : 성공
			
			while (idx!=1) {
				
				//현재 idx Node의 유효성 검사
				//idx, idx-1 (맨 끝, 맨 끝 (왼쪽)) =>둘 다 숫자
				//위 두Node의 부모 => 연산자
				
				if (!Character.isDigit(node[idx]) || !Character.isDigit(node[idx-1]) ||
						Character.isDigit(node[idx/2])) {
					ans=0;
					break;
				}
				node [idx/2]='1'; //실제 수식을 처리 X => 단순하게 유효성 검사
				idx-=2;
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}

}
