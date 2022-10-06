package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2383_2 {

	static int T, N, pNum, ans;
	
	static int[][] map;
	static boolean[] selected;
	
	static List<Pos> person, stair;
	static List<Info> info;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			person = new ArrayList<>();
			stair = new ArrayList<>();
			info=new ArrayList<>();
			pNum=0; ans=Integer.MAX_VALUE;
		
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 1) {
						person.add(new Pos(i, j));
						pNum++;
					} else if (map[i][j] > 1)
						stair.add(new Pos(i, j));

				}
			}
			selected = new boolean[pNum];
			subSet(0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void subSet(int tgtIdx) {
		// #1. 매번 나누어진 팀에 대해 검사		
		ans=Math.min(ans, simulation());

		if (tgtIdx == pNum)
			return;


		// #2. subSet 만들기
		selected[tgtIdx] = true;
		subSet(tgtIdx + 1);
		selected[tgtIdx] = false;
		subSet(tgtIdx + 1);
	}

	static int simulation() {
		
		// #0. init
		info.clear();

		PriorityQueue<Info> pq1=new PriorityQueue<>( (Info i1, Info i2) -> i1.arriveTime-i2.arriveTime);
		PriorityQueue<Info> pq2=new PriorityQueue<>( (Info i3, Info i4) -> i3.arriveTime-i4.arriveTime);
		
		
		// #1. 사람과 계단 사이에 거리를 구해서 info 에 넣음
		// selected=true -> 1번 계단, selected=false -> 2번 계단
		
		for (int i = 0; i < pNum; i++) {
			if (selected[i]) {

				int py = person.get(i).y;
				int px = person.get(i).x;

				int sy = stair.get(0).y;
				int sx = stair.get(0).x;

				int dist = Math.abs(py - sy) + Math.abs(px - sx);
				
				info.add(new Info (i, 0, dist));
				pq1.add(info.get(i));

			} else {
				int py = person.get(i).y;
				int px = person.get(i).x;

				int sy = stair.get(1).y;
				int sx = stair.get(1).x;

				int dist = Math.abs(py - sy) + Math.abs(px - sx);
				
				info.add(new Info (i, 1, dist));
				pq2.add(info.get(i));
			}
		}
		
		// #2. 각 시간마다 계단에 내려갈 수 있는지 확인
		
		int capacity1=0, capacity2=0;						//현재 계단 1,2에 있는 사람 수
		int length1=map[stair.get(0).y][stair.get(0).x];	//계단 1을 내려가는 데 걸리는 시간
		int length2=map[stair.get(1).y][stair.get(1).x];	//계단 2를 내려가는 데 걸리는 시간
		
		int Time=1;
		while (!pq1.isEmpty() || !pq2.isEmpty()) {
			
			// # 2-1. 계단 내려가기를 완료한 사람이 있는지 확인
			for (int i=0; i<pNum; i++) {
				if (info.get(i).finishTime==Time) {
					info.get(i).finishTime=0;
					if (info.get(i).stairNo==0) capacity1--;
					else capacity2--;
				}
			}
			
			// #2-2. 1번 계단에서 현재 시간에 내려갈 수 있는 사람 확인 
			while (capacity1!=3 && !pq1.isEmpty()) {
				if (pq1.peek().arriveTime>Time) break;
				
				Info i=pq1.poll();
				capacity1++;
				
				// #중요! 도착 시간과 현재 시간이 같으면 1초 기다려야하고,
				//		 이미 도착해서 기다리고 있는 상태였다면 바로 내려감
				if (i.arriveTime==Time)
					i.finishTime=Time+1+length1;
				else i.finishTime=Time+length1;
			}
			
			
			// #2-3. 2번 계단에서 현재 시간에 내려갈 수 있는 사람 확인
			while (capacity2!=3 && !pq2.isEmpty()) {
				if (pq2.peek().arriveTime>Time) break;
				
				Info i=pq2.poll();
				capacity2++;
				
				if (i.arriveTime==Time)
					i.finishTime=Time+1+length2;
				else i.finishTime=Time+length2;
			}
			Time++;
		}
		
		// #3. 내려가기를 시작한 사람들이 없어서 while문 종료 -> 내려가고 있는 사람이 있을 수도 있음
		for (int i=0; i<pNum; i++) {
			if (info.get(i).finishTime!=0) {
				Time=Math.max(Time, info.get(i).finishTime);
			}
		}

		return Time;
	}
	
	static class Info {
		int personNo, stairNo;
		int arriveTime, finishTime;
		
		Info (int personNo, int stairNo, int arriveTime) {
			this.personNo=personNo;
			this.stairNo=stairNo;
			this.arriveTime=arriveTime;
		}
	}

	static class Pos {
		int y, x;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
