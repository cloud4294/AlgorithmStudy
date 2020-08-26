import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 1113번 - 수영장 만들기
 * 
 * 		https://www.acmicpc.net/problem/1113
 */

public class Main1113 {

	static int N, M;
	static int[][] map;
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

		String[] size = br.readLine().split(" ");

		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);
		map = new int[N + 2][M + 2];
		int result = 0;
		
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j - 1)));
			}
		}

		for (int i = 1; i <= 9; i++) { // 물을 채워나감 
			BFS(0, 0, i); // 테두리와 연결되는 물의양은 세지않음

			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= M; k++) { // 테두리와 연결되지 않은 물의양만 카운트
					if (map[j][k] < i)
						result += BFS(j, k, i);
				}
			}
			
		}
		
		System.out.println(result);
	}

	public static int BFS(int r, int c, int h) {

		int count = 0;
		boolean[][] visit = new boolean[N+2][M+2];
		visit[r][c] = true;
		
		Queue<point> queue = new LinkedList<>();
		queue.offer(new point(r, c));

		while (!queue.isEmpty()) {

			point now = queue.poll();
			if(map[now.r][now.c] < h) {
				count++;
				map[now.r][now.c]++;
			}
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] < h && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc));
				}
			}
		}
		
		return count;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N+2 || nc < 0 || nc >= M+2)
			return false;
		return true;
	}

}
