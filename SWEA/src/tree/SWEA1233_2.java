package tree;

/*
 * 
 * 제일 밑에 있는 값이 숫자인지..
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1233_2 {
	
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
			
			ans= dfs (1)? 1 : 0; //맨 윛 (처음) 노드부터 시작
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	private static boolean dfs (int x) { // x : index
		//기저 조건
		if (x>N) return false; 
		// 이전 index에서 더 따져보려고 했는데 N을 초과
		// 이전이 연산자라는 의미 
		
		//현재 index의 노드가 숫자 <= 유효하려면 자식이 없어야 함
		if (Character.isDigit(node[x])) {
			if (x*2>N) return true;
			return false;
		} else {
			return (dfs(x*2) && dfs(x*2+1));
		}
	}

}
