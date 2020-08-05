import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 2589번 - 보물섬
 * 
 * 		https://www.acmicpc.net/problem/2589
 */

public class Main2589 {

	static int R, C, result;
	static char[][] map;
	static int[][] group;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point {

		int r;
		int c;
		int count;

		public point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");
		R = Integer.parseInt(size[0]);
		C = Integer.parseInt(size[1]);
		map = new char[R][C];
		group = new int[R][C];
		result = 0;
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'L')
					solve(i, j);
			}
		}
		System.out.println(result);
	}	

	public static void solve(int i, int j) {
		boolean[][] visit = new boolean[R][C];

		point start = new point(i, j, 0);
		visit[i][j] = true;

		Queue<point> queue = new LinkedList<>();
		queue.offer(start);
		int length = 0;
		while (!queue.isEmpty()) {
			point now = queue.poll();

			length = Math.max(length, now.count);

			for (int k = 0; k < 4; k++) {
				int nr = now.r + dr[k];
				int nc = now.c + dc[k];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] == 'L' && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc, now.count + 1));
				}
			}
		}
		result = Math.max(result, length);
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= R || nc < 0 || nc >= C)
			return false;
		return true;
	}

}
