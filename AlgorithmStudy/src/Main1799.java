import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1799번 - 비숍
 * 
 * 		https://www.acmicpc.net/problem/1799
 */

public class Main1799 {

	static int N;
	static int[][] map;
	static int[][] visit;
	static List<point>[] bishop;
	static int[] dr = { -1, -1, 1, 1 };
	static int[] dc = { -1, 1, -1, 1 };
	static int result;

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

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		map = new int[N][N];
		visit = new int[N][N];

		bishop = new ArrayList[2];
		bishop[0] = new ArrayList<>();
		bishop[1] = new ArrayList<>();

		result = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					if ((i + j) % 2 == 0)
						bishop[0].add(new point(i, j));
					else
						bishop[1].add(new point(i, j));
				}
			}
		}
		int sum = 0;
		solve(0, 0, 0);
		sum = result;
		result = 0;
		solve(1, 0, 0);

		System.out.println(sum + result);
	}

	public static void solve(int color, int index, int count) {

		if (index == bishop[color].size()) {

			result = Math.max(result, count);
			return;
		}

		point now = bishop[color].get(index);

		if (visit[now.r][now.c] == 0) {

			marking(now.r, now.c, 1);
			solve(color, index + 1, count + 1);
			marking(now.r, now.c, -1);
		}
		solve(color, index + 1, count);

	}

	public static void marking(int r, int c, int flag) {

		visit[r][c] += flag;

		for (int i = 0; i < 4; i++) {
			int nr = r;
			int nc = c;

			while (true) {
				nr += dr[i];
				nc += dc[i];

				if (isRange(nr, nc) == false)
					break;

				visit[nr][nc] += flag;
			}
		}
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			return false;
		return true;
	}

}
