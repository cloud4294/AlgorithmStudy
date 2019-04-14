import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 		SW Expert Academy 1953.[모의 SW 역량테스트] -  탈주범 검거
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq
 */

public class SW1953 {

	static int N, M, L, R, C;
	static int[][] map;
	static int res;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point {
		int r;
		int c;
		int type;
		int time;

		public point(int r, int c, int type, int time) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
			this.time = time;
		}
	}

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
			return false;
		return true;
	}

	public static void solve() {

		boolean[][] visit = new boolean[N][M];

		Queue<point> queue = new LinkedList<>();

		queue.offer(new point(R, C, map[R][C], 0));
		visit[R][C] = true;

		while (!queue.isEmpty()) {
			point now = queue.poll();

			if (now.type == 1 || now.type == 2 || now.type == 4 || now.type == 7) {
				int nr = now.r + dr[0];
				int nc = now.c + dc[0];

				if (isRange(nr, nc) == true && now.time + 1 < L && visit[nr][nc] == false) {

					if (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6) {
						visit[nr][nc] = true;
						queue.offer(new point(nr, nc, map[nr][nc], now.time + 1));
					}
				}
			}

			if (now.type == 1 || now.type == 3 || now.type == 4 || now.type == 5) {
				int nr = now.r + dr[1];
				int nc = now.c + dc[1];
				if (isRange(nr, nc) == true && now.time + 1 < L && visit[nr][nc] == false) {
					if (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7) {
						visit[nr][nc] = true;
						queue.offer(new point(nr, nc, map[nr][nc], now.time + 1));
					}

				}
			}

			if (now.type == 1 || now.type == 2 || now.type == 5 || now.type == 6) {
				int nr = now.r + dr[2];
				int nc = now.c + dc[2];
				if (isRange(nr, nc) == true && now.time + 1 < L && visit[nr][nc] == false) {
					if (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7) {
						visit[nr][nc] = true;
						queue.offer(new point(nr, nc, map[nr][nc], now.time + 1));

					}
				}
			}

			if (now.type == 1 || now.type == 3 || now.type == 6 || now.type == 7) {
				int nr = now.r + dr[3];
				int nc = now.c + dc[3];
				if (isRange(nr, nc) == true && now.time + 1 < L && visit[nr][nc] == false) {
					if (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5) {

						visit[nr][nc] = true;
						queue.offer(new point(nr, nc, map[nr][nc], now.time + 1));
					}
				}
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j] == true)
					count++;
			}
		}

		res = count;

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int i = 1; i <= test_case; i++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			res = 0;
			map = new int[N][M];

			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					map[j][k] = sc.nextInt();
				}
			}

			solve();
			System.out.println("#" + i + " " + res);
		}

	}

}
