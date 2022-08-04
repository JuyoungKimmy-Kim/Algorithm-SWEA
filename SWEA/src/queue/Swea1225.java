package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea1225 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		for (int tc=1; tc<=10; tc++) {
			String T=br.readLine();
			
			Queue<Integer> q=new LinkedList<>();
			
			st=new StringTokenizer (br.readLine(), " ");
			for (int i=0; i<8; i++) {
				int num=Integer.parseInt(st.nextToken());
				q.add(num);
			}
			
			int value=1;
			
			while (value!=0 ) {
				for (int i=1; i<=5; i++) {
					value=q.poll();
					value-=i;
					if (value<=0)
						value=0;
					q.offer(value);
					if (value==0) break;
				}
			}
			
			System.out.print("#"+tc+" ");
			for (int i=0; i<8; i++)
				System.out.print(q.poll()+" ");
			System.out.println();
		}

	}

}
