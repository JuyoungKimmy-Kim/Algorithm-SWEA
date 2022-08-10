package bfs.dfs;


/*
 * 
 * bfs
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1861_2 {
	
	static int[][] map;
	static int T,N,NO, COUNT;
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static Queue<Node> queue=new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			NO=0;
			COUNT=1;
			
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			
			for (int i=0; i<N; i++) {
				StringTokenizer st=new StringTokenizer (br.readLine());
				
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					//모든 방에서 출발해 보아야 함
					queue.add(new Node (i,j,map[i][j], 1));
					bfs();
				}
			}
			System.out.println("#"+tc+" "+NO+" "+COUNT);
		}
	}
	
	static void bfs () {
		
		while (!queue.isEmpty()) {
			Node node=queue.poll();
			
			if (node.cnt>COUNT) {
				COUNT =node.cnt;
				NO=node.no;
			}
			else if (node.cnt==COUNT) {
				NO=node.no<NO ? node.no : NO;
			}
			
			for (int d=0; d<4; d++) {
				int ny=node.y+dy[d];
				int nx=node.x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N || map[ny][nx]!=map[node.y][node.x]+1)
					continue;
				
				queue.offer(new Node (ny, nx, node.no, node.cnt+1));
				break; // 조건에 맞는 다음 방 번호는 1개 이므로 더 이상 delta를 따져볼 필요가 없음
			}
		}
	}
	
	static class Node {
		int y,x,no; //시작 방 번호
		int cnt; // 방문 횟수
		
		Node (int y, int x, int no, int cnt) {
			this.y=y;
			this.x=x;
			this.no=no;
			this.cnt=cnt;
		}
	}

}
