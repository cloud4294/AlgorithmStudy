import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 		Baekjoon Online Judge 2665번 - 미로만들기
 * 
 * 		https://www.acmicpc.net/problem/2665
 */

public class Main2665 {

	static int N;
	static int[][] map;
	static int[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point implements Comparable<point> {

		int r;
		int c;
		int count;

		public point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

		@Override
		public int compareTo(point o) {
			if (o.count < this.count)
				return 1;
			return -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new int[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
				visit[i][j] = Integer.MAX_VALUE;
			}
		}

		BFS();

		System.out.println(visit[N-1][N-1]);

	}

	public static void BFS() {

		PriorityQueue<point> pq = new PriorityQueue<>();

		pq.offer(new point(0, 0, 0));

		visit[0][0] = 0;

		while (!pq.isEmpty()) {

			point now = pq.poll();
	
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if ( now.count < visit[nr][nc]) {
					if (map[nr][nc] == 1) {
						visit[nr][nc] = now.count;
						pq.offer(new point(nr, nc, now.count));
					} else if (map[nr][nc] == 0) {
						visit[nr][nc] = now.count + 1;
						pq.offer(new point(nr, nc, now.count + 1));
					}
				}
			}
		}

	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			return false;
		return true;
	}

}
