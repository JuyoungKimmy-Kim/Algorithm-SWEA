package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * memoi[4]=7;
 * 
 * memoi[1]=3;
 * memoi[2]=5;
 * memoi[3]=6;
 * memoi[4]=7;
 */

/*

1
5
1 3 2 5 4

1
5
7 4 2 1 8 4 5 6 4 2
 */

public class SWEA3307 {

	
	static int T,N,len;
	static int[] input, memoi;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			input=new int[N];
			memoi=new int[N];
			
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int i=0; i<N; i++) 
				input[i]=Integer.parseInt(st.nextToken());
			
			len=0;
			for (int i=0; i<N; i++) {
				int pos= Arrays.binarySearch(memoi, 0,len,input[i]);
				if (pos>=0) continue;
				
				pos=Math.abs(pos)-1;
				memoi[pos]=input[i];
				if (pos== len) {
					len++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(len).append("\n");
		}
		System.out.println(sb.toString());
	}

}
