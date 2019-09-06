import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 5427 - 불
 * 		
 * 		https://www.acmicpc.net/problem/5427
 */

public class Main5427 {

	static int w, h;
	static char[][] map;
	static int[][] fireMap;
	static Queue<point> queue;
	static point start;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int result;

	static class point {
		int r;
		int c;
		int count = 0;

		public point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

	}

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= h || c < 0 || c >= w)
			return false;
		return true;
	}

	public static void solve() {

		while (!queue.isEmpty()) { // 불 BFS
			point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] == '.' && fireMap[nr][nc] == 0) {
					fireMap[nr][nc] = now.count + 1;
					queue.offer(new point(nr, nc, now.count + 1));
				}
			}
		}

		queue.offer(start);
		boolean[][] visit = new boolean[h][w];
		visit[start.r][start.c] = true;
		while (!queue.isEmpty()) { // 탈출 BFS
			point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false) {
					result = now.count;
					break;

				}

				if ((now.count + 1 < fireMap[nr][nc] || fireMap[nr][nc] == 0) && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc, now.count + 1));
				}
			}
			if (result > 0)
				break;

		}
		queue.clear();

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		queue = new LinkedList<>();
		for (int i = 0; i < t; i++) {
			String[] size = br.readLine().split(" ");
			w = Integer.parseInt(size[0]);
			h = Integer.parseInt(size[1]);
			map = new char[h][w];
			fireMap = new int[h][w];
			result = -1;
			start = null;
			for (int j = 0; j < h; j++) {
				String line = br.readLine();
				for (int k = 0; k < w; k++) {
					map[j][k] = line.charAt(k);
					if (map[j][k] == '#')
						fireMap[j][k] = -1;
					else if (map[j][k] == '*') {
						fireMap[j][k] = 1;
						queue.offer(new point(j, k, 1));
					} else if (map[j][k] == '@') {
						start = new point(j, k, 1);
						map[j][k] = '.';
					}
				}
			}

			solve();
			System.out.println(result == -1 ? "IMPOSSIBLE" : result);

		}

	}

}
