package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2805_2 {

	// 입력받으면서 바로 계산 처리
	// 2차원 배열 --> 배열 없이
	// 행 단위로 입력 받는 문자열을 임시 배열에 담고 그 배열에서 startX endX 만큼 돌면서 더한다.
	// 농장의 크기 N은 1 이상 49 이하의 홀수이다. (1 ≤ N ≤ 49) <= N 이 작아서 큰 이득은 없다.

	static int T, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			int sum = 0;
			int wide = 0; // center 로부터 좌우로 멀어지는 정도
			int half = N / 2; // 가운데 인덱스 ( 홀수이므로 2로 나누면 됨 )
			int startX, endX; // 가로 수확 시작 - 끝

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
