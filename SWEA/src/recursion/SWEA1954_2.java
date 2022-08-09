package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// �ð� �������� �̵�
// delta

public class SWEA1954_2 {
	
	static int T,N;
	static int[][] snail;
	
	public static int[] dy = { 0, 1, 0, -1 };
	public static int[] dx = { 1, 0, -1 ,0};
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			N=Integer.parseInt(br.readLine());
			snail=new int[N][N];
			
			//���� ��ǥ, ����
			int y=0;
			int x=0;
			int d=0; //�� index	
			
			int num=1;
			//N*N��ŭ �ݺ��ϸ鼭 ���ڸ� ����
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					snail[y][x]=num++; //���� ����
					
					// �̵�
					// ���� �������� �̵��ϴٰ� �� �̻� ������ ���� �������� ��ȯ
					
					if (d==0) { // ��
						if (x+dx[d]>=N || snail[y][x+dx[d]]!=0) //���������� ���ٰ� ���̳� �ٸ� ��ǥ ������
							d=1; //  �Ϸ��� ����
					} else if (d==1) { //��
						if (y+dy[d]>=N || snail[y+dy[d]][x]!=0) // �Ʒ��� �������ٰ� ���̳� �ٸ� ��ǥ ������
							d=2; // ���� ����
					} else if (d==2) { //��
						if (x+dx[d]<0 || snail[y][x+dx[d]]!=0) //  �������� ���ٰ� ���̳� �ٸ� ��ǥ�� ������
							d=3; 
					} else if (d==3) { //��
						if (y+dy[d]<0 || snail[y+dy[d]][x]!=0) // ���ΰ��ٰ� ���̳� �ٸ� ��ǥ�� ������
							d=0;
					}
					
					x+=dx[d]; y+=dy[d];
				}
			}
			
			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(snail[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
	

}