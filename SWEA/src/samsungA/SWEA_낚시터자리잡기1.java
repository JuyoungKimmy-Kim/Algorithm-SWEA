package samsungA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_낚시터자리잡기1 {

	static int N, ans, allCustomerNum;
	static boolean[] visited, openedgate; // visited => 낚시터, openedgate => 이 게이트는 이미 들렸는지 확인용
	static int[][] customer;

	/*
	 * 문제 단순화 : 낚시터는 일차원 배열임 낚시터 게이트는 3개고 게이트는 위치를 지니고 있고 기다리는 낚시꾼들이 최소 한 명 이상 있음
	 * 낚시꾼들은 현재 게이트 기준으로 가장 가까운 위치에 차례로 앉음 낚시터가 부족한 경우는 없음 낚시꾼의 최소 이동 거리를 구해야함
	 * 
	 * 이 문제 핵심 : 낚시꾼은 다 차례로 앉는데 마지막 낚시꾼은 선택지가 갈릴 수 있다는 점 마지막 낚시꾼을 제외하고 나머지는 그냥 다
	 * 차례대로 앉히면 됨 마지막 낚시꾼이 어디를 택하느냐에 따라 어디에 앉았을 경우 어떻게 되는지 경우의 수를 따져봄 만약 마지막 낚시꾼이
	 * 선택지가 하나라면 경우의 수를 따지지 않고 진행함
	 * 
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;

			customer = new int[3][2]; // int[y][x] => y는 1,2,3번 게이트를 뜻함, x의 0은 게이트 위치, 1은 손님 수임
			allCustomerNum = 0; // 모든 고객수를 세어줄거임

			for (int i = 0; i < 3; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
				allCustomerNum += customer[i][1]; // 모든 고객 수를 변수에 담아둠 <- 기저조건으로 사용할 예정
			}
			// 입력 끝

			set(); // 함수 돌리기 전에 먼저 세팅해줌
			backTrack(0, 0, 0);
			set();
			backTrack(0, 0, 1);
			set();
			backTrack(0, 0, 2);

			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.print(sb);
	}

	static void set() {
		visited = new boolean[N + 1]; // 낚시터 초기화
		openedgate = new boolean[3]; // 게이트 변수 초기화
	}

	static void backTrack(int checkedCustom /* 자리 잡은 고객 수 */, int distance/* 현재 이동 거리 총합 */, int gate/* 현재 게이트 */) {
		Queue<Integer> que = new ArrayDeque<>(); // 큐 생성 => 뒤에 천천히 설명함

		if (allCustomerNum == checkedCustom) { // 기저조건 : 만약 자리 잡은 고객수가 총 고객수와 같다면
			ans = Math.min(distance, ans); // ans와 현재 이동거리 총합의 값을 비교해서 넣어줌
			return;
		}

		if (openedgate[gate])
			return; // 만약 이미 들린 게이트라면 그냥 나옴
		openedgate[gate] = true; // 이 게이트에 처음 들어왔다면 true로 만들어줌

		int cnt = customer[gate][1]; // 현재 고객 수를 넣어줌
		int dis = 0; // 이동거리 합을 잠시 담아줄 변수

		// 여기부는 마지막 낚시꾼이 남을 때 까지 단순하게 가장 거리가 작은 낚시터를 정해주는 부분
		while (cnt-- > 1) {
			int d = 300; // 거리 비교용 변수, Integer.MAX_VALUE 치기 싫어서 그냥 300 넣어줌
			int idx = 0; // 선택된 낚시터의 위치를 저장할 변수임

			for (int i = 1; i <= N; i++) { // 1부터 낚시터 크기 만큼 for문 돌려줌
				if (!visited[i]) { // 아직 낚시터에 아무도 없다면

					// 예를들어 지금 gate가 4에 있고 i가 4라고 치면 |4-4|+1하고 지금 거리 비교용 변수보다 작다면 수행함
					if (d > Math.abs(i - customer[gate][0]) + 1) {
						d = Math.abs(i - customer[gate][0]) + 1; // d값을 바꿔줌
						idx = i; // 현재 낚시터 위치를 넣어줌
					}
				}
			}
			// 위 for문에서 가장 거리가 적은 위치가 정해졌다면
			dis += d; // 그 거리를 dis에 더해줌
			que.add(idx); // 선택된 낚시터 위치를 que에 담아줌
			visited[idx] = true; // 선택된 낚시터에 이미 사람있다고 표시해줌
		}

		// 여기부터는 낚시터가 마지막 한 명 남았을 때 처리해야하는 부분

		int d = 300;
		ArrayList<int[]> idx = new ArrayList<>(); // 선택된 낚시터 위치와 이동 거리를 저장해 줄 List

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				if (d > Math.abs(i - customer[gate][0]) + 1) { // 가장 작은 거리를 발견하면
					d = Math.abs(i - customer[gate][0]) + 1; // d 값 수정
					idx.clear(); // 이때까지 모았던 거리들 다 삭제
					idx.add(new int[] { i, d }); // 낚시터 위치와 거리를 넣어줌
				} else if (d == Math.abs(i - customer[gate][0] + 1)) { // 만약 거리가 같은게 있다면
					idx.add(new int[] { i, d }); // 낚시터 위치와 거리를 넣어줌
				}
			}
		}

		dis += idx.get(0)[1]; // 이번 게이트에서 이동한 총 거리에다가 더해줌

		// 여기부터는 경우의 수에 따라 재귀를 호출하는 부분
		// 경우의 수는 낚시꾼의 위치와 어떤 게이트를 먼저 여느냐로 크게 두가지임

		if (idx.size() > 1) { // 만약 갈 수 있는 곳이 하나 이상이다.

			for (int i = 0; i < idx.size(); i++) { // 그렇다면 List에서 순서대로 꺼내서 경우의 수를 다 따져봄
				visited[idx.get(i)[0]] = true; // List에 선택된 낚시터 위치 하나 꺼내서 true해줌
				// backTrack(자리 잡은 고객수 + 이 게이트에 있던 고객수, 현재까지 거리 총합 + 이 게이트에서 선택된 거리, 다음으로 들려볼
				// 게이트 );
				backTrack(checkedCustom + customer[gate][1], distance + dis, (gate + 1) % 3); // 각각 게이트에 따라 다 따져봄
				backTrack(checkedCustom + customer[gate][1], distance + dis, (gate + 2) % 3);
				visited[idx.get(i)[0]] = false; // List 꺼낸걸로 따져봤으면 false로 바꿔줌
			}
		} else { // 만약 낚시꾼이 갈 수 있는 곳이 하나밖에 없다면
			visited[idx.get(0)[0]] = true;
			backTrack(checkedCustom + customer[gate][1], distance + dis, (gate + 1) % 3); // 게이트만 따져줌
			backTrack(checkedCustom + customer[gate][1], distance + dis, (gate + 2) % 3);
			visited[idx.get(0)[0]] = false;
		}

		// 큐 역할 -> 모든 자리 잡혔던 낚시터를 비워줘야 다음 경우의 수를 따질 수가 있음
		while (!que.isEmpty()) { // 큐가 빌 때까지 이번 함수에서 true처리한 부분들만 false로 바꿔줌
			visited[que.poll()] = false;
		}

		openedgate[gate] = false; // 이 게이트도 다시 선택 될 수 있으므로 게이트 사용하지 않은걸로 해
	}

}