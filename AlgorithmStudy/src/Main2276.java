import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2276번 - 물 채우기
 * 
 * 		https://www.acmicpc.net/problem/2276
 */

public class Main2276 {

	static int M, N, Result;
	static int[][] map;
	static List<Integer> height;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static PriorityQueue<point> pq;
	static boolean[][] visit;

	static class point implements Comparable<point> {

		int r;
		int c;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(point o) {
			if (map[o.r][o.c] < map[this.r][this.c])
				return 1;
			return -1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		height = new ArrayList<>();
		Result = 0;

		pq = new PriorityQueue<>();
		visit = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0 || j == 0 || i == M - 1 || j == N - 1) // 가장자리 부분을 높이 별로 pq에 넣음 
					pq.offer(new point(i, j));
			}
		}

		while (!pq.isEmpty()) {
			point now = pq.poll();
			DFS(now.r, now.c, map[now.r][now.c]); // 가장 낮은 높이부터 물을 채울수 있는 영역을 탐색
		}

		System.out.println(Result);

	}

	public static void DFS(int r, int c, int h) {

		visit[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (isRange(nr, nc) == false)
				continue;

			if (map[nr][nc] <= h) {
				if (visit[nr][nc] == false) { // 물을 채울 수 있는 경우 
					Result = Result + (h - map[nr][nc]);
					DFS(nr, nc, h);
				}
			} else // 물을 채울 수 없는 경우 새로운 가장자리로 추가
				pq.offer(new point(nr, nc));

		}
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= M || nc < 0 || nc >= N)
			return false;
		return true;
	}

}
