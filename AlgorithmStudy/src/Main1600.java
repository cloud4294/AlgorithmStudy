import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 1600번 - 말이 되고픈 원숭이
 * 
 * 		https://www.acmicpc.net/problem/1600
 */

public class Main1600 {

	static int[][] map;
	static boolean[][][] checked;
	static int k, w, h;

	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };

	static int[] my2 = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] mx2 = { -2, -1, 1, 2, 2, 1, -1, -2 };

	static class point {
		int y;
		int x;
		int length;
		int count;

		public point(int y, int x, int length, int count) {
			super();
			this.y = y;
			this.x = x;
			this.length = length;
			this.count = count;
		}
	}

	public static boolean valid(int y, int x) {
		if (y < 1 || y > h || x < 1 || x > w) {
			return true;
		}
		return false;
	}

	public static int BFS() {

		Queue<point> queue = new LinkedList<>();
		queue.offer(new point(1, 1, 0, 0));

		checked[1][1][0] = true;

		while (!queue.isEmpty()) {
			point now = queue.poll();
			if (now.y == h && now.x == w) {
				return now.length;
			} // 목적지에 도달했다면 현재까지의 길이를 리턴 

			for (int i = 0; i < 4; i++) {
				int ny = now.y + my[i];
				int nx = now.x + mx[i];

				if (valid(ny, nx) == false && map[ny][nx] == 0 && checked[ny][nx][now.count] == false) {

					checked[ny][nx][now.count] = true;

					queue.offer(new point(ny, nx, now.length + 1, now.count));
				}
			} // 4방향에 대한 BFS 시행 

			if (now.count < k) {
				for (int j = 0; j < 8; j++) {
					int ny = now.y + my2[j];
					int nx = now.x + mx2[j];
					if (valid(ny, nx) == false && map[ny][nx] == 0 && checked[ny][nx][now.count + 1] == false) {
						checked[ny][nx][now.count + 1] = true;
						queue.offer(new point(ny, nx, now.length + 1, now.count + 1));
					}
				}
			} // 말을 이용한 8방향에 대해 BFS 시행 
		}

		return -1; // 큐가 비어있을때까지 종료 되지 않았다면 -1 리턴 
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		k = sc.nextInt();
		w = sc.nextInt();
		h = sc.nextInt();

		map = new int[h+1][w+1];
		checked = new boolean[h+1][w+1][k+1]; // 좌표 방문, 말사용 횟수를 체크 

		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= w; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 초기 지형 정보 초기화 

		System.out.println(BFS());

	}

}
