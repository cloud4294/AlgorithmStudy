import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 16985번 - Maaaaaaaaaze
 * 
 * 		https://www.acmicpc.net/problem/16985
 */

public class Main16985 {

	static int[][][] map;
	static int[][] temp;
	static int[] dr = { -1, 0, 1, 0, 0, 0 };
	static int[] dc = { 0, 1, 0, -1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };
	static int result;
	static List<Integer> order;

	static class point {

		int r;
		int c;
		int h;
		int count;

		public point(int r, int c, int h, int count) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
			this.count = count;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new int[5][5][5];
		temp = new int[5][5];
		result = Integer.MAX_VALUE;
		order = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 5; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		makeOrder();

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);

	}

	public static void solve(int height) {

		if (height == 5) {
			BFS();
			return;
		}

		for (int i = 0; i < 4; i++) {
			turn(height);
			solve(height + 1);
		}

	}

	public static void makeOrder() {
		
		if(order.size() == 5) {
			int[][][] temp = new int[5][5][5];
			
			for (int h = 0; h < 5; h++) {
				int height = order.get(h);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						temp[h][i][j] = map[height][i][j];
					}
				}				
			}
			
			for (int h = 0; h < 5; h++) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						map[h][i][j] = temp[h][i][j];
					}
				}				
			}
			solve(0);
			
			for (int h = 0; h < 5; h++) {
				int height = order.get(h);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						map[height][i][j] = temp[h][i][j];
					}
				}				
			}
			
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			if(!order.contains(i)) {
				order.add(Integer.valueOf(i));
				makeOrder();
				order.remove(Integer.valueOf(i));
			}
		}
		
	}

	public static void BFS() {
		
		if(map[0][0][0] == 0)
			return;

		Queue<point> queue = new LinkedList<>();
		point start = new point(0, 0, 0, 0);
		queue.offer(start);

		boolean[][][] visit = new boolean[5][5][5];
		visit[0][0][0] = true;

		while (!queue.isEmpty()) {

			point now = queue.poll();

			if (now.r == 4 && now.c == 4 && now.h == 4) {
				result = Math.min(result, now.count);
				return;
			}

			for (int i = 0; i < 6; i++) {

				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				int nh = now.h + dh[i];

				if (isRange(nr, nc, nh) == false)
					continue;

				if (map[nh][nr][nc] == 1 && visit[nh][nr][nc] == false) {
					visit[nh][nr][nc] = true;
					queue.offer(new point(nr, nc, nh, now.count + 1));
				}
			}

		}

	}

	public static boolean isRange(int nr, int nc, int nh) {
		if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || nh < 0 || nh >= 5)
			return false;
		return true;
	}

	public static void turn(int height) {

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				temp[i][j] = map[height][4 - j][i];
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[height][i][j] = temp[i][j];
			}
		}

	}

}
