import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17836번 - 공주님을 구해라!
 * 
 * 		https://www.acmicpc.net/problem/17836
 */

public class Main17836 {

	static int N, M, T, result;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static point gram;

	static class point {

		int r;
		int c;
		int t;

		public point(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		result = 0;
		gram = null;
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		BFS();

		if (gram != null) {
			result = Math.min(result, gram.t + (N - 1 - gram.r) + (M - 1 - gram.c));
		}

		if (result <= T)
			System.out.println(result);
		else
			System.out.println("Fail");
	}

	public static void BFS() {

		int[][] visit = new int[N][M];
		visit[0][0] = 1;
		Queue<point> queue = new LinkedList<>();
		queue.offer(new point(0, 0, 0));

		while (!queue.isEmpty()) {

			point now = queue.poll();

			if (map[now.r][now.c] == 2) {
				gram = new point(now.r, now.c, now.t);
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if ((map[nr][nc] == 0 || map[nr][nc] == 2) && visit[nr][nc] == 0) {
					visit[nr][nc] = visit[now.r][now.c] + 1;
					queue.offer(new point(nr, nc, now.t + 1));
				}
			}
		}

		if (visit[N - 1][M - 1] == 0)
			result = Integer.MAX_VALUE;
		else
			result = visit[N - 1][M - 1] - 1;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= M)
			return false;
		return true;
	}

}
