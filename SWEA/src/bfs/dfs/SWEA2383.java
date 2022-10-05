package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2383 {

	static int T, N;
	static int[][] map;

	static List<Person> person;
	static List<Stair> stairs;
	// static List<Info> info;

	static PriorityQueue<Info> pq1, pq2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			person = new ArrayList<>();
			stairs = new ArrayList<>();

			pq1 = new PriorityQueue<>((Info i1, Info i2) -> i1.arriveTime - i2.arriveTime);
			pq2 = new PriorityQueue<>((Info i3, Info i4) -> i3.arriveTime - i4.arriveTime);

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 1) {
						person.add(new Person(i, j));
					} else if (map[i][j] > 1) {
						stairs.add(new Stair(i, j, map[i][j]));
					}
				}
			}

			timeCheck();
			int ret = simulation();
			sb.append("#").append(tc).append(" ").append(ret).append("\n");

		}
		System.out.println(sb.toString());
	}

	static int simulation() {
		int Time = 1;
		while (!pq1.isEmpty() || !pq2.isEmpty()) {

			// #1. 시간이 다 되어 빠져나갈 사람들 빠져나감
			for (int i = 0; i < 3; i++) {
				if (stairs.get(0).doneTime[i] == Time) {
					stairs.get(0).doneTime[i] = 0;
					stairs.get(0).capacity--;
				}
			}

			for (int i = 0; i < 3; i++) {
				if (stairs.get(1).doneTime[i] == Time) {
					stairs.get(1).doneTime[i] = 0;
					stairs.get(1).capacity--;
				}
			}

			Info i1 = null, i2 = null;

			while (!pq1.isEmpty()) {
				i1 = pq1.peek();
				if (person.get(i1.personNo).done) {
					pq1.poll();
					continue;
				}

				if (i1.arriveTime <= Time && stairs.get(0).capacity != 3) {
					pq1.poll();

					for (int i = 0; i < 3; i++) {
						if (stairs.get(0).doneTime[i] == 0) {
							stairs.get(0).doneTime[i] = Time + 1 + stairs.get(0).length;
							person.get(i1.personNo).done = true;
							stairs.get(0).capacity++;
							break;
						}
					}

				} else
					break;
			}

			while (!pq2.isEmpty()) {
				i2 = pq2.peek();
				if (person.get(i2.personNo).done) {
					pq2.poll();
					continue;
				}

				if (i2.arriveTime <= Time && stairs.get(1).capacity != 3) {
					pq2.poll();

					for (int i = 0; i < 3; i++) {
						if (stairs.get(1).doneTime[i] == 0) {
							stairs.get(1).doneTime[i] = Time + 1 + stairs.get(1).length;
							person.get(i2.personNo).done = true;
							stairs.get(1).capacity++;

							break;
						}
					}
				} else
					break;
			}

//			System.out.println("==========" + Time + "============");
//			System.out.println(stairs.get(0));
//			System.out.println(stairs.get(1));

			Time++;
		}

		for (int i = 0; i < 3; i++) {
			if (stairs.get(0).doneTime[i] != 0) {
				Time = Math.max(Time, stairs.get(0).doneTime[i]);
				stairs.get(0).doneTime[i] = 0;
			}
		}

		for (int i = 0; i < 3; i++) {
			if (stairs.get(1).doneTime[i] != 0) {
				Time = Math.max(Time, stairs.get(1).doneTime[i]);
				stairs.get(1).doneTime[i] = 0;
			}
		}

		return Time;
	}

	static void timeCheck() {
		for (int i = 0; i < person.size(); i++) {
			int py = person.get(i).y;
			int px = person.get(i).x;

			for (int j = 0; j < stairs.size(); j++) {
				int sy = stairs.get(j).y;
				int sx = stairs.get(j).x;

				int dist = Math.abs(py - sy) + Math.abs(px - sx);
				if (j == 0)
					pq1.add(new Info(i, j, dist));
				if (j == 1)
					pq2.add(new Info(i, j, dist));
			}
		}
	}

	static class Stair {
		int y, x, length;
		int capacity;
		int[] doneTime = new int[3];

		Stair(int y, int x, int length) {
			this.y = y;
			this.x = x;
			this.length = length;
		}

		@Override
		public String toString() {
			return "Stair [y=" + y + ", x=" + x + ", length=" + length + ", capacity=" + capacity + ", doneTime="
					+ Arrays.toString(doneTime) + "]";
		}

	}

	static class Person {
		boolean done;
		int y, x;

		Person(int y, int x) {
			this.y = y;
			this.x = x;
			this.done=false;
		}

	}

	static class Info {
		int personNo, stairNo;
		int arriveTime;

		Info(int personNo, int stairNo, int arriveTime) {
			this.personNo = personNo;
			this.stairNo = stairNo;
			this.arriveTime = arriveTime;
		}

		@Override
		public String toString() {
			return "Info [personNo=" + personNo + ", stairNo=" + stairNo + ", arriveTime=" + arriveTime + "]";
		}

	}

}
