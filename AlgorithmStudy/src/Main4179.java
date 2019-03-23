import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 4179번 - 불!
 * 
 * 		https://www.acmicpc.net/problem/4179
 */

public class Main4179 {

	static int R, C;
	static char[][] map;
	static Queue<node> fire;
	static int[][] firemap;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };

	static class node {
		int y;
		int x;
		int count;

		public node(int y, int x, int count) {
			super();
			this.y = y;
			this.x = x;
			this.count = count;
		}
	}// 좌표, 시간을 저장할 클래스 

	public static boolean isRange(int y, int x) {
		if (y < 0 || y >= R || x < 0 || x >= C)
			return false;
		return true;
	}

	public static void buildFireMap() { 

		while (!fire.isEmpty()) { // 불이 이동가능한 지점과 해당지점에 도달했을때 시간을 배열에 저장
			node now = fire.poll();
			for (int i = 0; i < 4; i++) {
				int ny = now.y + my[i];
				int nx = now.x + mx[i];

				if (isRange(ny, nx) == false || map[ny][nx] == '#')
					continue;

				if (map[ny][nx] == '.' && firemap[ny][nx] == Integer.MAX_VALUE) {
					firemap[ny][nx] = now.count + 1;
					fire.offer(new node(ny, nx, now.count + 1));
				}

			}
		}

	}

	private static void solve(node start) {

		Queue<node> queue = new LinkedList<>();
		queue.offer(start);
		boolean[][] visit = new boolean[R][C];
		visit[start.y][start.x] = true;

		while (!queue.isEmpty()) { // BFS 시행
			node now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int ny = now.y + my[i];
				int nx = now.x + mx[i];
				if (map[now.y][now.x] == '.' && isRange(ny, nx) == false) { // 가장자리에 도달하면 종료
					System.out.println(now.count + 1);
					return;
				}

				if (map[ny][nx] == '.' && now.count + 1 < firemap[ny][nx] && visit[ny][nx] == false) { // 다음위치에 이동할 시간에 불이 번지지 않고 방문한 적이없다면 큐에 넣음
					visit[ny][nx] = true;
					queue.offer(new node(ny, nx, now.count + 1));
				}

			}

		}
		System.out.println("IMPOSSIBLE");

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		firemap = new int[R][C];

		fire = new LinkedList<>();

		node start = null;
		for (int i = 0; i < R; i++) {
			String line = sc.next();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'J') {
					start = new node(i, j, 0);
					map[i][j] = '.';
				} else if (map[i][j] == 'F') {
					firemap[i][j] = 0;
					fire.offer(new node(i, j, 0));
				} else if(map[i][j] == '.') {
					firemap[i][j] = Integer.MAX_VALUE;
				}
			}
		} // 입력값 초기화 

		buildFireMap(); // 불이 이동하는 시간을 저장을 배열을 구함
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
			}
		}
		solve(start);
	}

}
