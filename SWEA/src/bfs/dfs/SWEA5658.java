package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA5658 {

	static int T,N,K;
	static TreeSet <Integer> set;
	static ArrayDeque<Integer> deque;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			set=new TreeSet<>(Collections.reverseOrder());
			deque=new ArrayDeque<>();
			
			String line=br.readLine();
			for (int i=0; i<line.length(); i++) {
				if (line.charAt(i)>='0' && line.charAt(i)<='9') 
					deque.add(line.charAt(i)-'0');
				else 
					deque.add(line.charAt(i)-'A'+10);
			}
			
			
			for (int i=0; i<N/4; i++) {
				// back -> front
				int back=deque.pollLast();
				deque.addFirst(back);
				
				addTree();
			}
			
			int idx=0;
			for (int n : set) {
				if (idx==K-1) {
					System.out.println("#"+tc+" "+n);
					break;
				}
				idx++;
			}
		}
	}
	
	static void addTree () {
		
		int idx=0;
		int num=0;
		
		
		for (int n : deque) {
			num=num*16+n;
			idx++;
			
			if (idx%(N/4)==0) {
				set.add(num);
				num=0;
			}
		}
	}
}
