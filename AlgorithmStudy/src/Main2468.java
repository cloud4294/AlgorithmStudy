import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 2468번 - 안전 영역 
 * 
 * 		https://www.acmicpc.net/problem/2468
 */

public class Main2468 {

	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int count;

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
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;
	}

	public static void solve(int r, int c,int h) {
		Queue<point> queue = new LinkedList<>();
		visit[r][c] = true;
		queue.offer(new point(r, c));
		while (!queue.isEmpty()) {
			point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;
				if (map[nr][nc] > h && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc));
				}

			}

		}
		count++;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		count = 0;
		int result = 0;

		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		for (int h = 0; h <= 100; h++) { // 비의 높이 
			visit = new boolean[N][N];
			count = 0;
			for (int i = 0; i < N; i++) { // bfs 시행
				for (int j = 0; j < N; j++) {
					if (visit[i][j] == false && map[i][j] > h)
						solve(i, j,h);
				}
			}
			result = Math.max(result, count);
		}

		System.out.println(result);
	}

}
