import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 9328번 - 열쇠
 * 
 * 		https://www.acmicpc.net/problem/9328
 */

public class Main9328 {

	static int R, C, Result;
	static char[][] map;
	static boolean[] key;
	static boolean[][] visit;
	static List<point>[] door;
	static Queue<point> queue;
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

		int test_case = Integer.parseInt(br.readLine());

		for (int t = 0; t < test_case; t++) {
			String[] size = br.readLine().split(" ");

			R = Integer.parseInt(size[0]);
			C = Integer.parseInt(size[1]);
			Result = 0;

			map = new char[R][C];
			visit = new boolean[R][C];
			door = new ArrayList[27];
			queue = new LinkedList<>();

			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			key = new boolean[27];

			for (int i = 0; i < 27; i++) {
				door[i] = new ArrayList<>();
			}
			String keySet = br.readLine();
			if (!keySet.equals("0")) {
				for (int i = 0; i < keySet.length(); i++) {
					key[keySet.charAt(i) - 'a'] = true;
				}
			}

			for (int i = 0; i < R; i++) {

				if (map[i][0] != '*' && visit[i][0] == false) {
					if (map[i][0] == '$') {
						Result++;
						move(i, 0);
					} else if (map[i][0] >= 'A' && map[i][0] <= 'Z') {
						if (key[String.valueOf(map[i][0]).toLowerCase().charAt(0) - 'a'] == true) {
							map[i][0] = '.';
							move(i, 0);
						} else
							door[map[i][0] - 'A'].add(new point(i, 0));
					} else if (map[i][0] >= 'a' && map[i][0] <= 'z') {
						key[map[i][0] - 'a'] = true;
						for (int j = 0; j < door[map[i][0] - 'a'].size(); j++) {
							queue.offer(door[map[i][0] - 'a'].get(j));
						}
						map[i][0] = '.';
						move(i, 0);
					} else
						move(i, 0);
				}
				if (map[i][C - 1] != '*' && visit[i][C - 1] == false) {
					if (map[i][C - 1] == '$') {
						Result++;
						move(i, C - 1);

					} else if (map[i][C - 1] >= 'A' && map[i][C - 1] <= 'Z') {
						if (key[String.valueOf(map[i][C - 1]).toLowerCase().charAt(0) - 'a'] == true) {
							map[i][C - 1] = '.';
							move(i, C - 1);
						} else
							door[map[i][C - 1] - 'A'].add(new point(i, C - 1));
					} else if (map[i][C - 1] >= 'a' && map[i][C - 1] <= 'z') {
						key[map[i][C - 1] - 'a'] = true;
						for (int j = 0; j < door[map[i][C - 1] - 'a'].size(); j++) {
							queue.offer(door[map[i][C - 1] - 'a'].get(j));
						}
						map[i][C - 1] = '.';
						move(i, C - 1);
					} else
						move(i, C - 1);
				}
			}

			for (int i = 0; i < C; i++) {
				if (map[0][i] != '*' && visit[0][i] == false) {
					if (map[0][i] == '$') {
						Result++;
						move(0, i);
					} else if (map[0][i] >= 'A' && map[0][i] <= 'Z') {
						if (key[String.valueOf(map[0][i]).toLowerCase().charAt(0) - 'a'] == true) {
							map[0][i] = '.';
							move(0, i);
						} else
							door[map[0][i] - 'A'].add(new point(0, i));
					} else if (map[0][i] >= 'a' && map[0][i] <= 'z') {
						key[map[0][i] - 'a'] = true;
						for (int j = 0; j < door[map[0][i] - 'a'].size(); j++) {
							queue.offer(door[map[0][i] - 'a'].get(j));
						}
						map[0][i] = '.';
						move(0, i);
					} else
						move(0, i);
				}

				if (map[R - 1][i] != '*' && visit[R - 1][i] == false) {
					if (map[R - 1][i] == '$') {

						Result++;
						move(R - 1, i);
					} else if (map[R - 1][i] >= 'A' && map[R - 1][i] <= 'Z') {
						if (key[String.valueOf(map[R - 1][i]).toLowerCase().charAt(0) - 'a'] == true) {
							map[R - 1][i] = '.';
							move(R - 1, i);
						} else
							door[map[R - 1][i] - 'A'].add(new point(R - 1, i));
					} else if (map[R - 1][i] >= 'a' && map[R - 1][i] <= 'z') {
						key[map[R - 1][i] - 'a'] = true;
						for (int j = 0; j < door[map[R - 1][i] - 'a'].size(); j++) {
							queue.offer(door[map[R - 1][i] - 'a'].get(j));
						}
						map[R - 1][i] = '.';
						move(R - 1, i);
					} else
						move(R - 1, i);
				}
			}
			System.out.println(Result);
		}

	}

	public static void move(int r, int c) {

		visit[r][c] = true;
		point start = new point(r, c);
		queue.offer(start);

		while (!queue.isEmpty()) {

			point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] == '.' && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc));
				} else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {
					visit[nr][nc] = true;
					if (key[String.valueOf(map[nr][nc]).toLowerCase().charAt(0) - 'a'] == true) {
						map[nr][nc] = '.';
						queue.offer(new point(nr, nc));
					} else {
						door[map[nr][nc] - 'A'].add(new point(nr, nc));
					}
				} else if (map[nr][nc] >= 'a' && map[nr][nc] <= 'z' && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc));
					if (key[map[nr][nc] - 'a'] == false) {
						key[map[nr][nc] - 'a'] = true;
						for (int j = 0; j < door[map[nr][nc] - 'a'].size(); j++) {
							queue.offer(door[map[nr][nc] - 'a'].get(j));
						}
					}
					map[nr][nc] = '.';

				} else if (map[nr][nc] == '$' && visit[nr][nc] == false) {
					Result++;
					map[nr][nc] = '.';
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc));
				}
			}

		}

		return;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= R || nc < 0 || nc >= C)
			return false;
		return true;
	}

}
