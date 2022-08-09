package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA6808_3 {
	
	static final int MAX=10;
	
	static int T, win, lose, N=9;
	static int[] input =new int[19];
	static int[] guCard=new int[9]; 	// ���ɿ��� ����
	static int[] inCard=new int[9];		// guCard�� ���� ��ȣ�� �Է� <= ������ ����� ���� ������ �� �ִ� src
	
	static boolean[] select=new boolean[N];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			win=0; lose=0;
			Arrays.fill(input, 0);
			
			st=new StringTokenizer (br.readLine());
			// �Կ��� ī��
			int num=0;
			for (int i=0; i<N; i++) {
				num=Integer.parseInt(st.nextToken());
				guCard[i]=num;
				input[num]=1;							// �ο��� ī�� ����
			}
			num=0;
			for (int i=1; i<=18; i++) {
				if (input[i]==0) inCard[num++]=i;
			}
			
			perm(0,0,0);
			System.out.println("#"+tc+" "+win+" "+lose);
		}
	}
	
	
	static void perm (int guIdx, int guSum, int inSum) {
		// ���� ���� ==> �Կ����� ī��κ��� ������ ī�带 ������ �ϼ��� ���
		if (guIdx==N) {
			//complete code
			if (guSum>inSum) win++;
			else if (guSum<inSum) lose++;
			return ;
		}
		
		for (int index=0; index<N; index++) { //�ο����� ī�� index 
			if (select[index]) continue;

			select[index]=true;
			// �Ķ���� tgtIdx <= �Կ����� ī��
			if (guCard[guIdx] > inCard[index]) 
				perm (guIdx+1, guSum + guCard[guIdx] + inCard[index], inSum);
			else 
				perm (guIdx+1, guSum, inSum + guCard[guIdx] + inCard[index]);
			
			select[index]=false;
		}
	}
	

}
