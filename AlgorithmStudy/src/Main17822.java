import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17822 - 원판 돌리기
 * 
 * 		https://www.acmicpc.net/problem/17822
 */

public class Main17822 {

	static int N, M, T;

	static int[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void solve(int target, int dir, int dist) { // 원판회전

		List<Integer> list = new ArrayList<>();

		for (int i = target; i <= N; i += target) {
			list.clear();
			for (int j = 1; j <= M; j++) {
				list.add(map[i][j]);
			}
			if (dir == 0) {
				for (int j = 0; j < dist % M; j++) {
					list.add(0, list.remove(M - 1));
				}
			} else if (dir == 1) {
				for (int j = 0; j < dist % M; j++) {
					list.add(list.remove(0));
				}
			}

			for (int j = 1; j <= M; j++) {

				map[i][j] = list.get(j - 1);
			}
		}
		visit = new boolean[N + 1][M + 1];
		boolean flag = false;
		for (int i = 1; i <= N; i++) { 
			for (int j = 1; j <= M; j++) {
				if (map[i][j] != 0 && visit[i][j] == false)
					if (BFS(i, j) == true) // 인접한 수 제거
						flag = true;
			}

		}
		

		if (flag == false) { // 인접한 수가 없는 경우
			int sum = 0;
			int count = 0;

			for (int k = 1; k <= N; k++) {
				for (int m = 1; m <= M; m++) {
					if (map[k][m] != 0) {
						sum += map[k][m];
						count++;
					}
				}
			}
			double avg = (double) sum / count;
			for (int k = 1; k <= N; k++) {
				for (int m = 1; m <= M; m++) {
					if (map[k][m] != 0) {
						if (map[k][m] < avg)
							map[k][m]++;
						else if (map[k][m] > avg)
							map[k][m]--;
					}
				}
			}

			
		}
	}

	public static boolean BFS(int i, int j) {

		boolean flag = false;
		visit[i][j] = true;
		int[] start = { i, j };
		Queue<int[]> queue = new LinkedList<>();

		queue.offer(start);

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int k = 0; k < 4; k++) {
				int r = now[0] + dr[k];
				int c = now[1] + dc[k];
				if (c == M + 1)
					c = 1;
				else if (c == 0)
					c = M;

				if (isRange(r, c) == false)
					continue;

				if (map[r][c] == map[i][j] && visit[r][c] == false) {
					flag = true;
					visit[r][c] = true;
					map[r][c] = 0;
					int[] next = { r, c };
					queue.offer(next);
				}
			}

		}
		if (flag == true) {
			map[i][j] = 0;
		}

		return flag;
	}

	public static boolean isRange(int r, int c) {

		if (r <= 0 || r > N || c <= 0 || c > M)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			solve(target, dir, dist);
		}
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum += map[i][j];
			}
		}

		System.out.println(sum);

	}

}
