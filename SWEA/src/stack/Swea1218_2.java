package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Swea1218_2 {
	
	static int N, ans;
	//static Stack<Character> stack=new Stack<>();
	static Deque<Character> stack=new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		for (int t=1; t<=10; t++) {
			
			stack.clear();
			ans=0;
			
			N=Integer.parseInt(br.readLine());
			String str=br.readLine();
			
			for (int i = 0; i < N; i++) {
				char token=str.charAt(i);
				
				//시작 문자 
				if (token=='<' || token=='{' || token=='(' || token=='[')
					stack.push(token);
				
				
				//닫는 문자
				else {
					if (stack.isEmpty()) {
						stack.push(token); 
						break;
					}
					char prev=stack.peek();
					if (token=='>' && prev!='<') break;
					else if (token=='}' && prev!='{') break;
					else if (token==')' && prev!='(') break;
					else if (token==']' && prev!='[') break;
					else stack.pop(); //쌍이 맞는다
				}
			}
			//최종적인 유효성 판단은 stack empty 해야 함
			if (stack.isEmpty()) ans=1;
			System.out.println("#"+t+" "+ans);
		}
	}

}
