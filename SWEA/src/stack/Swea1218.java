package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Swea1218 {
	
	static char[] open_bracket = {'{','[','(', '<'};
	static char[] close_bracket = {'}', ']', ')', '>'};

	static int N;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		for (int t=1; t<=10; t++) {
			N=Integer.parseInt(br.readLine());
			String line=br.readLine();
			
			boolean flag=true;
			Stack <Character> stack=new Stack<>();
			
			for (int i=0; i<N; i++) {
				char ch=line.charAt(i);
				
				//여는 괄호일 경우
				if (isOpenBracket(ch))
					stack.add(ch);
				
				//닫는 괄호일 경우
				else {
					if (stack.isEmpty()) {
						flag=false;
						break;
					}
					
					else {
						char top=stack.peek();
						if ((ch=='}' && top=='{') || (ch==']' && top=='[') || (ch==')' && top=='(') ||
								(ch=='>' && top=='<')) {
							stack.pop();
						}
						else {
							flag=false;
							break;
						}
					}
				}
				
			}
			
			if (!stack.isEmpty()) flag=false;
			
			if (flag) 
				System.out.println("#"+t+" 1");
			else
				System.out.println("#"+t+" 0");
			
		}
	}
	
	private static boolean isOpenBracket(char ch) {
		for (int i=0; i<4; i++) {
			if (open_bracket[i]==ch)
				return true;
		}
		return false;
	}

}
