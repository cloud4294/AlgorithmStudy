import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 7562 - 나이트의 이동
 * 
 * 		https://www.acmicpc.net/problem/7562
 */

public class Main7562 {

	static int L;
	static int dy[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int dx[] = { -2, -1, 1, 2, 2, 1, -1, -2 };

	static class point {
		int y;
		int x;
		int count;

		public point(int y, int x, int count) {
			super();
			this.y = y;
			this.x = x;
			this.count = count;
		}
	} // 현재 좌표, 이동횟수

	public static boolean isRange(int y, int x) {
		if (y < 0 || y >= L || x < 0 || x >= L)
			return false;
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			L = sc.nextInt();
			point start = new point(sc.nextInt(), sc.nextInt(), 0);
			point end = new point(sc.nextInt(), sc.nextInt(), 0);
			solve(start, end);
		}

	}

	public static void solve(point start, point end) { // BFS 시행

		boolean[][] visit = new boolean[L][L];

		visit[start.y][start.x] = true;

		Queue<point> queue = new LinkedList<>();

		queue.offer(start);

		while (!queue.isEmpty()) {

			point now = queue.poll();

			if (now.y == end.y && now.x == end.x) {
				System.out.println(now.count);
				break;
			}

			for (int i = 0; i < 8; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];

				if (isRange(ny, nx) == false)
					continue;

				if (visit[ny][nx] == false) {
					visit[ny][nx] = true;
					queue.offer(new point(ny, nx, now.count + 1));
				}

			}

		}

	}

}
