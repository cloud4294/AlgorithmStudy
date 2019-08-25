import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17144번 - 미세먼지 안녕!
 * 
 * 		https://www.acmicpc.net/problem/17144
 */

public class Main17144 {

	static int R, C, T;
	static int[][] room;
	static point up, down;
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

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return false;
		return true;
	}

	public static void spread() { // 바이러스 확산
		int[][] next = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) {
					int count = 0;
					for (int j2 = 0; j2 < 4; j2++) {
						int nr = i + dr[j2];
						int nc = j + dc[j2];

						if (isRange(nr, nc) == false || (nr == up.r && nc == up.c) || (nr == down.r && nc == down.c))
							continue;

						next[nr][nc] += room[i][j] / 5;
						count++;
					}

					next[i][j] += room[i][j] - ((room[i][j] / 5) * count);
				}
			}
		}

		for (int i = 0; i < R; i++) {
			room[i] = Arrays.copyOf(next[i], C);
		}

	}

	public static void circulation() { // 공기 청정기 바람 이동

		int r = up.r;
		int c = up.c;

		int dir = 0;

		while (true) { // 위 

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr == up.r && nc == up.c)
				break;

			if (isRange(nr, nc) == false || (nr == up.r + 1 && nc == C - 1)) {
				dir = (dir + 1) % 4;
				continue;
			}

			if (!(r == up.r && c == up.c) && !(r == down.r && c == down.c)) {
				room[r][c] = room[nr][nc];
				room[nr][nc] = 0;
			}

			r = nr;
			c = nc;

		}

		r = down.r;
		c = down.c;

		dir = 2;

		while (true) { // 아래

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr == down.r && nc == down.c)
				break;

			if (isRange(nr, nc) == false || (nr == down.r - 1 && nc == C - 1)) {
				dir = (dir + 3) % 4;
				continue;
			}

			if (!(r == up.r && c == up.c) && !(r == down.r && c == down.c)) {
				room[r][c] = room[nr][nc];
				room[nr][nc] = 0;
			}

			r = nr;
			c = nc;

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		int result = 0;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] == -1) {
					if (up == null)
						up = new point(i, j);
					else
						down = new point(i, j);
				}
			}
		}

		for (int i = 0; i < T; i++) {
			spread();
			circulation();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += room[i][j];
			}
		}

		System.out.println(result);
	}

}
