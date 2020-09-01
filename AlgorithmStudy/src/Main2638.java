import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2638번 - 치즈
 * 
 * 		https://www.acmicpc.net/problem/2638
 */

public class Main2638 {

	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point {

		int r;
		int c;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 2][M + 2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(BFS(0, 0, 0));

	}

	public static int BFS(int r, int c, int count) {

		int[][] visit = new int[N + 2][M + 2];
		visit[r][c] = 1;

		Queue<point> queue = new LinkedList<>();
		queue.offer(new point(r, c));

		Queue<point> remove = new LinkedList<>();

		while (!queue.isEmpty()) {
			point now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] == 0 && visit[nr][nc] == 0) {
					visit[nr][nc] = 1;
					queue.offer(new point(nr, nc));
				}

				if (map[nr][nc] == 1) {
					if (visit[nr][nc] < 2)
						visit[nr][nc]++;
					if (visit[nr][nc] == 2) {
						visit[nr][nc]++;
						remove.offer(new point(nr, nc));
					}
				}
			}

		}

		if (remove.size() == 0) {

			return count;
		}

		while (!remove.isEmpty()) {
			point rm = remove.poll();
			map[rm.r][rm.c] = 0;
		}

		return BFS(0, 0, count + 1);
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N + 2 || nc < 0 || nc >= M + 2)
			return false;
		return true;
	}

}

