package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA6808_4 {
	
	static final int MAX=10;
	
	static int T, win, lose, N=9;
	static int[] input =new int[19];
	static int[] guCard=new int[9]; 	
	static int[] inCard=new int[9];		
	
	static boolean[] select=new boolean[N];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			win=0; lose=0;
			Arrays.fill(input, 0);
			
			st=new StringTokenizer (br.readLine());

			int num=0;
			for (int i=0; i<N; i++) {
				num=Integer.parseInt(st.nextToken());
				guCard[i]=num;
				input[num]=1;						
			}
			num=0;
			for (int i=1; i<=18; i++) {
				if (input[i]==0) inCard[num++]=i;
			}
			

			
			while (true) {
	
				check ();
				if (!np()) break;
			}
			
		
			System.out.println("#"+tc+" "+win+" "+lose);
		}
	}
	
	static boolean np() {
		
		int[] src=inCard;
		int i=src.length-1;
		
		while (i>0 && src[i-1] >=src[i]) --i;
		
		if (i==0) return false; //descending ���� ū ��
		
		int j=src.length-1;
		
		while (src[i-1] >= src[j]) --j;
		swap (src, i-1, j);
		
		int k=src.length-1;
		
		while (i<k) {
			swap (src, i++, k--);
		}
		return true;
	}

	static void swap (int[] array, int i, int j) {
		int tmp=array[i];
		array[i]=array[j];
		array[j]=tmp;
	}
	
	static void check () {
		
		int guSum=0;
		int inSum=0;
		
		for (int i=0; i<N; i++) {
			if (guCard[i] > inCard[i])
				guSum+=guCard[i]+inCard[i];
			else inSum+=guCard[i]+inCard[i];			
		}
		if (guSum > inSum) win++;
		else if (guSum< inSum) lose++;
	}

}
