package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea1225 {

	static Queue<Integer> queue=new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		while (true) {
			String tc=br.readLine();
			if (tc==null || tc.length()==0) break; // 없거나, 공백문자
			
			//initialize
			queue.clear();
			StringTokenizer st=new StringTokenizer (br.readLine());
			
			for (int i = 0; i < 8; i++) 
				queue.offer(Integer.parseInt(st.nextToken()));
			
			make();
			
			System.out.print("#"+tc+" ");
			for (int num : queue)
				System.out.print(num+" ");
			System.out.println();
		}
	}
	
	private static void make () {
		int num=0;
		while (true) {

			// 1 cycle => 1,2,3,4,5 순차적으로 감소
			for (int i=1; i<=5; i++) {
				num=queue.poll()-i;
				
				if (num<=0) {
					queue.offer(0);
					return ;
				}
				
				queue.offer(num);
			}
		}
	}

}
