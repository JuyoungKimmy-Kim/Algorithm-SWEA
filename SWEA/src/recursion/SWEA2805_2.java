package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2805_2 {

	// �Է¹����鼭 �ٷ� ��� ó��
	// 2���� �迭 --> �迭 ����
	// �� ������ �Է� �޴� ���ڿ��� �ӽ� �迭�� ��� �� �迭���� startX endX ��ŭ ���鼭 ���Ѵ�.
	// ������ ũ�� N�� 1 �̻� 49 ������ Ȧ���̴�. (1 �� N �� 49) <= N �� �۾Ƽ� ū �̵��� ����.

	static int T, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			int sum = 0;
			int wide = 0; // center �κ��� �¿�� �־����� ����
			int half = N / 2; // ��� �ε��� ( Ȧ���̹Ƿ� 2�� ������ �� )
			int startX, endX; // ���� ��Ȯ ���� - ��

			for (int i = 0; i < N; i++) {

				startX = half - wide;
				endX = half + wide;

				char[] temp = br.readLine().toCharArray();
				for (int j = startX; j <= endX; j++) {
					sum += temp[j] - '0';
				}

				if (i < half) {
					wide++;
				} else {
					wide--;
				}
			}

			System.out.println("#" + t + " " + sum);
		}
	}

}
