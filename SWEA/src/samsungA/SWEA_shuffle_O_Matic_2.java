package samsungA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_shuffle_O_Matic_2 { // 3번째 셔플 문제

	static int n, m, ans;
	static ArrayList<Integer> asc, desc;

	/*
	 * 문제 정리 : 카드 갯수 N은 4이상 12 이하이며 짝수임 카드 번호는 1~N까지 숫자임 문제에서 제시하는 섞는 방법을 통해서 이 카드들이
	 * 전부 오름차순 혹은 내림차순으로 정렬되는지 확인하면 됨
	 * 
	 * 핵심 : 모든 경우의 수 다 따져보면 됨 조금 생각해야하는 부분은 섞는 방법에 대한 구현부분임
	 * 
	 * 
	 * 지금 왼손과 오른손에 각각 8개의 카드를 순서대로 손에 들고 있다고 생각해보자 카드의 순서는 왼손에 1 2 3 4 이고 오른 손에 5 6
	 * 7 8 이다.
	 * 
	 * 다이얼 숫자 x가 0이면 왼손, 오른손 카드들을 다 순서대로 내려놓으면 됨 여담) x가 0이면 바뀌는게 없음 => 그럼 0은 신경 안써도
	 * 됨 0도 포함하면 실행 시간이 증가할 것 같음 => 근데 깜빡하고 포함했는데 통과는 함. 두번 제출하고 싶지 않아서 시도는 안해봤는데 아마
	 * 0을 신경 안쓰면 더 빨라질 것 같아요
	 * 
	 * x가 1이면? 1 2 3 까지만 내려 놓음 그다음엔 순서대로 오른손의 5를 내려놓음 다음엔 왼손의 4를 내려놓음 다음엔 오른손의 6을
	 * 내려놓음 왼손이 비었으면 오른손 카드인 7을 대신 내려놓음 또 오른손 카드를 8을 내려놓음
	 * 
	 * x가 2라면 ? 1, 2까지만 먼저 내려놓음 다음엔 순서대로 오른손 왼손 오른손 왼손을 반복하고 왼손이 비면 오른손 카드만 내주면 됨
	 * 
	 * x가 3이라면? 1만 내려놓음 나머진 반복
	 * 
	 * 여기서 알 수 있는게 왼손에 지금 4장의 카드가 있으니 4-x만큼 왼손 카드를 먼저 버리는 거임
	 * 
	 * 
	 * x가 n/2이상이면 오른쪽을 먼저 내는 차례이고 왼손이랑 순서가 거꾸로임 n/2라면 오른손에서 한장만 버리고 왼손 오른손을 반복함
	 * 
	 * n/2+1라면? 오른손 카드 두개를 내주고 왼손 오른손 왼손 오른손을 반복해줌
	 * 
	 * n/2+2라면? 오른손 카드 3장을 먼저 내줌 왼손 오른손 반복
	 * 
	 * 위와 같이 작동하게 구현해주면 다음은 그렇게 만들어진 것으로 모든 경우의 수를 돌아봄 혹시 다섯번 섞었는데도 정렬이 한번도 안되면
	 * 실패한거임
	 * 
	 */

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			ArrayList<Integer> arr = new ArrayList<>();
			asc = new ArrayList<>();
			desc = new ArrayList<>();
			ans = Integer.MAX_VALUE;

			while (st.hasMoreTokens()) {
				arr.add(Integer.parseInt(st.nextToken()));
			}

			for (int i = 1; i <= n; i++) {
				asc.add(i);
			}

			for (int i = n; i > 0; i--) {
				desc.add(i);
			}

			backTrack(0, arr, 0);
			if (ans == Integer.MAX_VALUE)
				ans = -1;
			sb.append("#" + tc + " " + ans + "\n");
		}

		System.out.print(sb);

	}

	static void backTrack(int depth, ArrayList<Integer> src, int prev) { // depth는 섞은 횟수 src는 현재 카드 배열
		
		
		for (int n : src)
			System.out.print(n+" ");
		System.out.println();
		
		if (src.equals(asc) || src.equals(desc)) { // 카드가 오름차 혹은 내림차로 정렬 됐는지 확인
			ans = Math.min(ans, depth); // 되었다면 ans값 Math.min으로 갱신
			return;
		}

		if (depth == 5 || depth > ans) { // 만약 5번 섞었는데도 정렬이 안되었으면 끝
			return;
		}

		ArrayList<Integer> tmp = new ArrayList<>(); // 순서대로 버린 카드 줍줍하는 tmp

		for (int k = 0; k < n; k++) { // 여기 부분 0에서 시작이 아닌 1에서 시작하면 더 빨리질 것 같음. 원래 1로 하려다 습관적으로 0을 기입해버림
			
			if (k==prev) continue;
			
			int left = 0, right = n / 2, flag = 0; // flag는 짝수 번때 판별용
			tmp.clear();
			if (k < n / 2) { // 만약 다이얼 수가 n/2보다 작으면 왼쪽 먼저 내는 차례
				for (int i = 0; i < n / 2 - k; i++) { // n/2-다이얼 수 k만큼 먼저 카드를 버림
					tmp.add(src.get(left++));
				}

				for (int i = n / 2 - k; i < n; i++) { // 여기서 오른손 먼저 왼손 먼저를 해줌 그걸 판단하는 flag
					if (flag++ % 2 == 0) {
						tmp.add(src.get(right++));
					} else {
						if (left < n / 2)
							tmp.add(src.get(left++));
						else
							tmp.add(src.get(right++));
					}
				}
				backTrack(depth + 1, tmp, k);
			} else { // 오른쪽이 먼저내야하는 차례일 때 결국 왼손이랑 비슷함
				for (int i = 0; i < (k + 1) - n / 2; i++) {
					tmp.add(src.get(right++));
				}

				for (int i = k - n / 2 + 1; i < n; i++) {
					if (flag++ % 2 == 0) {
						tmp.add(src.get(left++));
					} else {
						if (right < n)
							tmp.add(src.get(right++));
						else
							tmp.add(src.get(left++));
					}
				}
				backTrack(depth + 1, tmp, k);
			}
		}
	}

}