import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SW1949 {

	static class node {
		int r;
		int c;

		public node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	} // 좌표저장 클래스

	static int N, K;
	static int[][] map;
	static Queue<node> start;
	static int res;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;

	} // 범위 확인 메소드

	public static void solve(int r, int c, int length, boolean crush,boolean[][] visit) { // 방문 체크하며 dfs

		visit[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (isRange(nr, nc) == false || visit[nr][nc] == true)
				continue;
			
			if (map[nr][nc] < map[r][c]) { // 산을 부시지 않았을때
				solve(nr, nc, length + 1, crush,visit);
			} else if (map[nr][nc] >= map[r][c] && crush == false) {
				for (int j = 1; j <= K; j++) { // 산을 부셨을때
					if (map[nr][nc] - j < map[r][c]) {
						map[nr][nc] -= j;
						solve(nr, nc, length + 1, true,visit);
						map[nr][nc] += j;
					}
				}

			}

		}
		visit[r][c] = false;

		res = Math.max(length, res);
		return;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();

		for (int i = 0; i < t; i++) {

			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			res = 0;
			int max = 0;
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < N; j2++) {
					int now = sc.nextInt();
					max = Math.max(now, max);
					map[j][j2] = now;
				}
			}

			// 가장 높은 봉우리 찾아 큐에 넣음
			start = new LinkedList<>();
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < N; j2++) {
					if (map[j][j2] == max) {

						start.offer(new node(j, j2));
					}
				}
			}
			// 각 봉우리 별로 dfs 시행
			while (!start.isEmpty()) {
				node now = start.poll();
				solve(now.r, now.c, 1, false,new boolean[N][N]);
				
			}

			System.out.println("#" + (i + 1) + " " + res);

		}

	}

}
