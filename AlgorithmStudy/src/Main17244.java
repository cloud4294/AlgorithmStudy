import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 17244번 - 아맞다우산
 * 
 * 		https://www.acmicpc.net/problem/17244
 */

public class Main17244 {

	static int N, M, items, result;
	static char[][] map;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point {

		int r;
		int c;
		int time;

		public point(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");
		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);
		items = 0;
		result = Integer.MAX_VALUE;
		map = new char[M][N];

		point start = null;

		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {

				map[i][j] = line.charAt(j);

				if (map[i][j] == 'S') {
					start = new point(i, j, 0);
				} else if (map[i][j] == 'X') {
					items++;
				}
			}
		}
		solve(start, 0);

		System.out.println(result);
	}

	public static void solve(point start, int count) {

		Queue<point> queue = new LinkedList<>();
		boolean[][] visit = new boolean[M][N];

		queue.offer(start);
		visit[start.r][start.c] = true;

		while (!queue.isEmpty()) {

			point now = queue.poll();

			if (map[now.r][now.c] == 'E' && count == items) {
				result = Math.min(result, now.time);
				return;
			}

			if (map[now.r][now.c] == 'X') {
				map[now.r][now.c] = '.';
				solve(now, count + 1);
				map[now.r][now.c] = 'X';
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;
				
				if (visit[nr][nc] == false && map[nr][nc] != '#') {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc, now.time + 1));
				}
			}
		}

	}

	private static boolean isRange(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr >= M || nc >= N)
			return false;
		return true;
	}

}
