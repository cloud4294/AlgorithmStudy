import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 19238번 - 스타트 택시
 * 
 * 		https://www.acmicpc.net/problem/19238
 */

public class Main19238 {

	static int N, M;
	static int[][] map;
	static HashMap<Integer, point> dest;
	static PriorityQueue<point> pq;
	static Queue<point> queue;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point implements Comparable<point> {
		int r;
		int c;
		int fuel;
		int count;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public point(int r, int c, int fuel, int count) {
			super();
			this.r = r;
			this.c = c;
			this.fuel = fuel;
			this.count = count;
		}

		@Override
		public int compareTo(point o) {
			if (o.count < this.count)
				return 1;
			else if (o.count == this.count) {
				if (o.r < this.r)
					return 1;
				else if (o.r == this.r) {
					if (o.c < this.c)
						return 1;
				}
			}
			return -1;
		}

	}

	public static void findStartPoint(point start, int count) {

		if (map[start.r][start.c] >= 2) {
			findEndPoint(start, count);
			return;
		}

		pq.clear();

		pq.offer(start);

		for (int i = 0; i <= N; i++) {
			Arrays.fill(visit[i], false);
		}

		visit[start.r][start.c] = true;

		point ready = null;

		while (!pq.isEmpty()) {

			point now = pq.poll();

			if (map[now.r][now.c] >= 2) {
				ready = new point(now.r, now.c, now.fuel, 0);
				pq.clear();
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] != 1 && visit[nr][nc] == false && now.fuel - 1 > 0) {
					visit[nr][nc] = true;
					pq.offer(new point(nr, nc, now.fuel - 1, now.count + 1));
				}
			}

		}

		if (ready != null) {
			findEndPoint(ready, count);
		} else {
			System.out.println(-1);
			return;
		}

	}

	public static void findEndPoint(point ready, int count) {

		int key = map[ready.r][ready.c];
		int endR = dest.get(key).r;
		int endC = dest.get(key).c;

		map[ready.r][ready.c] = 0;

		queue.clear();

		for (int i = 0; i <= N; i++) {
			Arrays.fill(visit[i], false);
		}

		visit[ready.r][ready.c] = true;
		queue.offer(ready);

		point start = null;

		while (!queue.isEmpty()) {
			point now = queue.poll();

			if (now.r == endR && now.c == endC) {
				start = new point(now.r, now.c, now.fuel + (now.count * 2), 0);
				queue.clear();
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] != 1 && visit[nr][nc] == false && now.fuel - 1 > 0) {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc, now.fuel - 1, now.count + 1));
				}

			}
		}

		if (start != null) {

			if (count == M) {
				System.out.println(start.fuel);
				return;
			}

			findStartPoint(start, count + 1);
		} else {
			System.out.println(-1);
			return;
		}

	}

	public static boolean isRange(int nr, int nc) {
		if (nr <= 0 || nc <= 0 || nr > N || nc > N)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visit = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());

		point start = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), fuel, 0);

		dest = new HashMap<>();
		for (int i = 2; i < M + 2; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = i;
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			dest.put(i, new point(r, c));
		}

		pq = new PriorityQueue<>();
		queue = new LinkedList<>();

		findStartPoint(start, 1);
	}

}
