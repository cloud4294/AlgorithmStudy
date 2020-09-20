import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 1194번 - 달이 차오른다, 가자.
 * 
 * 		https://www.acmicpc.net/problem/1194
 */


public class Main1194 {

	static int R, C;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point {

		int r;
		int c;
		int count;
		int key;

		public point(int r, int c, int count, int key) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
			this.key = key;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");

		R = Integer.parseInt(size[0]);
		C = Integer.parseInt(size[1]);

		map = new char[R][C];

		point start = null;

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				char now = input.charAt(j);
				if (now == '0') {
					start = new point(i, j, 0, 0);
					now = '.';
				}
				map[i][j] = now;
			}
		}

		System.out.println(solve(start));

	}

	public static int solve(point start) {  // BFS, 비트마스킹

		boolean[][][] visit = new boolean[1 << ('f' - 'a') + 1][R][C];

		Queue<point> queue = new LinkedList<>();

		queue.offer(start);
		visit[start.key][start.r][start.c] = true;

		while (!queue.isEmpty()) {

			point now = queue.poll();
	
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (visit[now.key][nr][nc] == false) {
					if (map[nr][nc] == '.') {
						visit[now.key][nr][nc] = true;
						queue.offer(new point(nr, nc, now.count + 1, now.key));
					} else if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
						int nextKey = now.key | 1 << ('f' - map[nr][nc]);
						visit[now.key][nr][nc] = true;
						visit[nextKey][nr][nc] = true;
						queue.offer(new point(nr, nc, now.count + 1, nextKey));
					} else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
						if ((now.key & 1 << ('f' - String.valueOf(map[nr][nc]).toLowerCase().charAt(0))) != 0) {
							visit[now.key][nr][nc] = true;
							queue.offer(new point(nr, nc, now.count + 1, now.key));
						}
					} else if (map[nr][nc] == '1') {
						return now.count + 1;
					}
				}

			}

		}
		return -1;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= R || nc < 0 || nc >= C)
			return false;
		return true;
	}

}
