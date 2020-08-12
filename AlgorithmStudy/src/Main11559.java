import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 11559번 - Puyo Puyo
 * 
 * 		https://www.acmicpc.net/problem/11559
 */

public class Main11559 {

	static char[][] map;
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

		map = new char[12][6];

		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		System.out.println(solve(0));

	}

	public static int solve(int count) {

		boolean[][] visit = new boolean[12][6];
		Queue<point> queue = new LinkedList<>();
		Queue<point> remove = new LinkedList<>();
		boolean flag = false;

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (map[i][j] != '.' && visit[i][j] == false) {
					remove.clear();
					queue.clear();
					visit[i][j] = true;
					point start = new point(i, j);
					queue.offer(start);

					while (!queue.isEmpty()) { // BFS 탐색 
						point now = queue.poll();
						remove.offer(now);

						for (int k = 0; k < 4; k++) {
							int nr = now.r + dr[k];
							int nc = now.c + dc[k];

							if (isRange(nr, nc) == false)
								continue;

							if (map[nr][nc] == map[now.r][now.c] && visit[nr][nc] == false) {
								visit[nr][nc] = true;
								queue.offer(new point(nr, nc));
							}
						}
					}

					if (remove.size() >= 4) { // BFS 탐색한 블럭이 4개 이상이면 제거 
						flag = true;
						while (!remove.isEmpty()) {
							point now = remove.poll();
							map[now.r][now.c] = '.';
						}
					}
				}
			}
		}

		if (flag == true) {
			dropBlock();
			return solve(count+1);
		}
		return count;
	}

	public static void dropBlock() { // 블럭 내리기

		for (int c = 0; c < 6; c++) {
			char[] temp = new char[12];
			int index = 11;
			for (int r = 11; r >= 0; r--) {
				if (map[r][c] != '.') {
					temp[index] = map[r][c];
					index--;
				}
			}
			for (int i = index; i >= 0; i--) {
				temp[i] = '.';
			}

			for (int r = 0; r < 12; r++) {

				map[r][c] = temp[r];
			}

		}
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= 12 || nc < 0 || nc >= 6)
			return false;
		return true;
	}

}
