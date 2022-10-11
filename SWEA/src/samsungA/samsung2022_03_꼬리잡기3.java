package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class samsung2022_03_꼬리잡기3 {

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };

	static int N, M, K, V, ans;
	static int[][] map, pmap;
	static boolean[][] visited;

	static List<Pos> pos = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		pmap = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					pos.add(new Pos());
					dfs(i, j, V++);
				}
			}
		}

		for (int k = 0; k < K; k++) {

			for (int i = 0; i < V; i++)
				move(i);

//			throwBall(k);
//			System.out.println(k+" 시간 : 움직임 후");
//			print(map);

			if (k==0) throwBall (0,0);
			else throwBall (k/N, k%N);
//			System.out.println(k+" 시간 : 공던진 후");
//			print(map);
		}
		System.out.println(ans);


	}

	static void throwBall(int round, int i) {
		int y = 0, x = 0;

		if (round==0) {
			y = i;
			x = 0;

			while (x < N && (map[y][x] == 0 || map[y][x]==4))
				x++;
			if (x == N)
				return;

		} else if (round==1) {
			y = N - 1;
			x = i;

			while (y >= 0 && (map[y][x] == 0 || map[y][x]==4))
				y--;
			if (y == -1)
				return;

		} else if (round==2) {
			y = N - i-1;
			x = N - 1;

			while (x >= 0 && (map[y][x] == 0 || map[y][x]==4))
				x--;
			if (x == -1)
				return;

		} else if (round==3) {
			round -= (N * 3);
			y = 0;
			x = N - i-1;

			while (y < N && (map[y][x] == 0 || map[y][x]==4))
				y++;
			if (y == N)
				return;
		}

		int n = pmap[y][x];
		getScore(n-1, y, x);
	}

	
    
  /*
    public static void throwBall(int turn) {
        int t =turn % (4*N+1);
    
        if(t <= N) {
        	t-=1;
            for(int i = 0; i < N; i++) {
                if(1 <= map[t][i] && map[t][i] <= 3) {
                	getScore(pmap[t][i], t, i);
                    return;
                }
            }
        }
        else if(t <= 2 * N) {
            // n+1 ~ 2n 라운드의 경우 아래에서 윗쪽 방향으로 공을 던집니다.
            t -= N;
            t-=1;
            for(int i = 0; i < N; i++) {
                if(1 <= map[N -1 -i][t] && map[N - 1 - i][t] <= 3) {
                	getScore(pmap[N-1-i][t], N-1-i, t);
                    return;
                }
            }
        }
        else if(t <= 3 * N) {
            // 2n+1 ~ 3n 라운드의 경우 오른쪽에서 왼쪽 방향으로 공을 던집니다.
            t -= (2 * N);
            t-=1;
            for(int i = 0; i < N; i++) {
                if(1 <= map[N - 1 - t][N - 1 - i] && map[N - 1 - t][N - 1 - i] <= 3) {
                	getScore(pmap[N - 1 - t][N - 1 - i], N - 1 - t, N - 1 - i);
                    return;
                }
            }
        }
        else {
            // 3n+1 ~ 4n 라운드의 경우 위에서 아랫쪽 방향으로 공을 던집니다.
            t -= (3 * N);
            t-=1;
            for(int i = 0; i < N; i++) {
                if(1 <= map[i][N - 1 - t] && map[i][N - 1 - t] <= 3) {
                	getScore(pmap[i][N - 1 - t], i, N - 1 - t);
                    return;
                }
            }
        }
    }
	
	*/
	
	// idx번 째 팀의 y,x에 있는 사람을 맞춤
	static void getScore(int n, int y, int x) {
		
		for (int i=0; i<pos.get(n).size; i++) {
			int ty=pos.get(n).coord.get(i)[0];
			int tx=pos.get(n).coord.get(i)[1];
			
			if (y==ty && x==tx) {
				ans+= ((i+1)*(i+1));
				break;
			}
		}
		
		List<int[]> tmp=new ArrayList<>();
		
		for (int i=pos.get(n).tail; i>=0; i--) {
			y=pos.get(n).coord.get(i)[0];
			x=pos.get(n).coord.get(i)[1];
			tmp.add(new int [] {y, x});
		}
		
		for (int i=pos.get(n).size-1; i>pos.get(n).tail; i--) {
			y=pos.get(n).coord.get(i)[0];
			x=pos.get(n).coord.get(i)[1];
			tmp.add(new int [] {y, x});
		}
		
		pos.get(n).coord.clear();
		pos.get(n).coord=tmp;
		
		for (int i=0; i<pos.get(n).size; i++) {
			y=pos.get(n).coord.get(i)[0];
			x=pos.get(n).coord.get(i)[1];
			if (i==0) {
				map [y][x]=1;
			} else if (i<pos.get(n).tail) {
				map[y][x]=2;
			} else if (i==pos.get(n).tail) {
				map[y][x]=3;
			} else {
				map[y][x]=4;
			}
		}
	}

	static void move(int n) {
		int size = pos.get(n).size;
		int ey = pos.get(n).coord.get(size - 1)[0];
		int ex = pos.get(n).coord.get(size - 1)[1];

		for (int i = size - 1; i >= 1; i--) {
			pos.get(n).coord.get(i)[0] = pos.get(n).coord.get(i - 1)[0];
			pos.get(n).coord.get(i)[1] = pos.get(n).coord.get(i - 1)[1];
		}

		pos.get(n).coord.get(0)[0] = ey;
		pos.get(n).coord.get(0)[1] = ex;

		for (int i=0; i<size; i++) {
			int y=pos.get(n).coord.get(i)[0];
			int x=pos.get(n).coord.get(i)[1];
			if (i==0) {
				map [y][x]=1;
			} else if (i<pos.get(n).tail) {
				map[y][x]=2;
			} else if (i==pos.get(n).tail) {
				map[y][x]=3;
			} else {
				map[y][x]=4;
			}
		}		
	}

	static void dfs(int y, int x, int V) {
		visited[y][x] = true;
		pos.get(V).coord.add(new int[] { y, x });

		if (map[y][x] == 3) {
			pos.get(V).tail = pos.get(V).size;
		}

		pos.get(V).size++;
		pmap[y][x] = V + 1;

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (!isInRange(ny, nx) || visited[ny][nx] || map[ny][nx] == 0)
				continue;

			if (pos.get(V).coord.size() == 1 && map[ny][nx] != 2)
				continue;

			visited[ny][nx] = true;
			dfs(ny, nx, V);
		}
	}

	static boolean isInRange(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= N)
			return false;
		return true;
	}

	static class Pos {
		List<int[]> coord;
		int size, tail;

		Pos() {
			coord = new ArrayList<>();
		}
	}

	static void print(int[][] map) {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
	}
}
