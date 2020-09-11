import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 		Baekjoon Online Judge 1445번 - 일요일 아침의 데이트
 * 
 * 		https://www.acmicpc.net/problem/1445
 */

public class Main1445 {

	static int N, M;
	static int[][] map;
	static point start;
	static point end;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point implements Comparable<point> {

		int r;
		int c;
		int count1;
		int count2;

		public point(int r, int c, int count1, int count2) {
			super();
			this.r = r;
			this.c = c;
			this.count1 = count1;
			this.count2 = count2;
		}

		@Override
		public int compareTo(point o) {
			if (o.count1 < this.count1)
				return 1;
			else if (o.count1 == this.count1)
				if (o.count2 < this.count2)
					return 1;
			return -1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");

		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);

		map = new int[N][M];

		start = null;
		end = null;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char now = line.charAt(j);

				if (now == 'g')
					map[i][j] = 2;
				else if (now == 'S')
					start = new point(i, j, 0, 0);
				else if (now == 'F')
					end = new point(i, j, 0, 0);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (map[i][j] == 2) {
					for (int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];

						if (isRange(nr, nc) == false)
							continue;

						if (nr == start.r && nc == start.c)
							continue;

						if (nr == end.r && nc == end.c)
							continue;

						if (map[nr][nc] != 2)
							map[nr][nc] = 1;
					}
				}
			}
		}

		point result = solve();
		System.out.println(result.count1 +" "+ result.count2);

	}

	public static point solve() { // 우선 순위 큐 사용, 우선순위가 높은 곳 부터 탐색

		point result = null;

		boolean[][] visit = new boolean[N][M];

		visit[start.r][start.c] = true;
		PriorityQueue<point> pq = new PriorityQueue<>();

		pq.offer(start);

		while (!pq.isEmpty()) {

			point now = pq.poll();

			if (now.r == end.r && now.c == end.c) {
				result = now;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (visit[nr][nc] == false) {
					visit[nr][nc] = true;
					if (map[nr][nc] == 0)
						pq.offer(new point(nr, nc, now.count1, now.count2));
					else if (map[nr][nc] == 1)
						pq.offer(new point(nr, nc, now.count1, now.count2 + 1));
					else if (map[nr][nc] == 2)
						pq.offer(new point(nr, nc, now.count1 + 1, now.count2));
				}

			}
		}

		return result;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= M)
			return false;
		return true;
	}

}
