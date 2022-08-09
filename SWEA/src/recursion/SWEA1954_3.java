package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// �� <---> �� �ݺ�
// �� <---> �� �ݺ��Ǵ� �߰��� ���ϰ� �ٽ� �ݺ�
// �� <---> �� �� �� ����Ǹ� �� ���� ����Ǵ� ������ ���� �ϳ� �ش�

public class SWEA1954_3 {
	
	static int T,N;
	static int[][] snail;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			N=Integer.parseInt(br.readLine());
			snail=new int[N][N];
			
			//���� ��ǥ, ����
			int y=0;
			int x=-1; // 1�� ������ ���̹Ƿ� �̸� ����
			int d=1; // �����¿찡 ����X, 1:����, -1: ����
			
			int num=1;
			int count=N; // ���ۺ��� ��� �̾����� ���⿡ ���� ���ڸ� ����� ����, ���� �پ���
			int total=N*N; //while��
			
			while (num<=total) {
				//�� -> �� -> �� -> �� �ݺ�
				
				for (int i=0; i<count; i++) {
					x+=d;
					snail[y][x]=num++;
				}
				count--; // �� �� ��� �Ǵ� �� �� ����ϸ� 
				
				//�� -> �� -> �� -> ��
				
				for (int i=0; i<count; i++) {
					y+=d;
					snail[y][x]=num++;
				}
				
				d*=(-1);
				//���� ��ȯ
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