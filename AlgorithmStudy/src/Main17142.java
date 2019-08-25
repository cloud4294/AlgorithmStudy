import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17142번 - 연구소(3)
 * 
 * 		https://www.acmicpc.net/problem/17142
 */

public class Main17142 {

	static int N, M;
	static List<point> virus;
	static int[] count;
	static int virusSize;
	static int[][] map;
	static int[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int result, zero;

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

	public static void solve(int i, int now) {  // DFS

		if (now == M) {
			BFS();
			return;
		}

		for (int j = i; j < virusSize; j++) {
			count[now] = j;
			solve(j + 1, now + 1);
		}

	}

	public static void BFS() { // BFS

		int[][] visit = new int[N][N];
		Queue<point> queue = new LinkedList<>();
		int time = 1;

		int infect = 0;
		for (int i = 0; i < M; i++) {
			point now = virus.get(count[i]);
			visit[now.r][now.c] = 1;
			queue.offer(now);
		}

		while (!queue.isEmpty()) {
			point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] != 1 && visit[nr][nc] == 0) {
					if (visit[now.r][now.c] + 1 >= result) // 갱신되지 않으므로 종료
						return;

					visit[nr][nc] = visit[now.r][now.c] + 1;

					if (map[nr][nc] != 2) // 비활성 바이러스 위치 제외 
						time = Math.max(time, visit[nr][nc]); 

					if (map[nr][nc] == 0) // 빈공간 갯수 
						infect++;

					queue.offer(new point(nr, nc));
				}

			}

		}

		if (infect == zero) // 감염된 공간 갯수와 빈공간 갯수가 일치하면
			result = Math.min(result, time);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		virus = new ArrayList<>();
		count = new int[M];
		map = new int[N][N];
		visit = new int[N][N];
		result = Integer.MAX_VALUE;
		zero = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new point(i, j));
				} else if (map[i][j] == 0)
					zero++;
			}

		}

		if (zero == 0) {
			System.out.println(0);
			return;
		}

		virusSize = virus.size();

		solve(0, 0);

		System.out.println(result == Integer.MAX_VALUE ? -1 : result - 1);
	}

}
