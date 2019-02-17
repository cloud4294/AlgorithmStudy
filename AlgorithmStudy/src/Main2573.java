import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 		Baekjoon Online Judge 2573 - 빙산
 * 
 * 		https://www.acmicpc.net/problem/2573
 */

public class Main2573 {

	static int N, M;
	static int[][] map;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static int res;
	static boolean[][] visited;

	static class node {
		int y;
		int x;
		int count;

		public node(int y, int x, int count) {
			super();
			this.y = y;
			this.x = x;
			this.count = count;
		}
	}

	public static void solve() {

		int count = 1;
		while (true) {
			Queue<node> queue = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int mc = 0;
					if (map[i][j] > 0) {
						for (int j2 = 0; j2 < 4; j2++) {
							if (isRange(i + my[j2], j + mx[j2]) == false)
								continue;

							if (map[i + my[j2]][j + mx[j2]] == 0)
								mc++;
						}

						queue.offer(new node(i,j,mc));
					}
				}
			} // 녹을수 있는 대상을 큐에 저장 

			while(!queue.isEmpty()) {
				node now = queue.poll();
				if(map[now.y][now.x] - now.count < 0)
					map[now.y][now.x] = 0;
				else 
					map[now.y][now.x] -= now.count; 			
			} // map을 갱신 

			visited = new boolean[N][M];
			int cnt = 0;
			boolean check = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0 && visited[i][j] == false) {
						check = true;
						bfs(i, j);
						cnt++;
					}
				}
			} // bfs를 시행하여 덩어리 갯수를 계산 
			
			if (check == true) { // bfs를 시행한적이 있다면 
				if (cnt >= 2) { // 덩어리가 두개이상이면 녹인 횟수를 갱신하고 종료
					res = count;
					return;
				}
				count++; 
			} else { // bfs를 시행한적이없다면 종료
				res = 0; 
				return;
			}
		}
	}

	public static void bfs(int i, int j) { // 덩어리 갯수를 찾기위한 bfs 
		Queue<node> queue = new LinkedList<>();

		queue.offer(new node(i, j, 0));
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			node now = queue.poll();

			for (int k = 0; k < 4; k++) {
				int ny = now.y + my[k];
				int nx = now.x + mx[k];

				if (isRange(ny, nx) == false)
					continue;

				if (map[ny][nx] > 0 && visited[ny][nx] == false) {
					visited[ny][nx] = true;
					queue.offer(new node(ny, nx, 0));
				}
			}

		}

	}

	public static boolean isRange(int i, int j) { //범위 확인 메소드 

		if (i < 0 || i >= N || j < 0 || j >= M)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력값 초기화 

		res = 0;
		solve();

		System.out.println(res);
	}

}
