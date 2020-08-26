import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 		Baekjoon Online Judge 9376번 - 탈옥
 * 
 * 		https://www.acmicpc.net/problem/9376	
 */

public class Main9376 {

	static int R, C;
	static char[][] map;
	static int[][] door;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point implements Comparable<point> {

		int r;
		int c;
		int count;

		public point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

		@Override
		public int compareTo(point o) {
			if (o.count < this.count)
				return 1;
			return -1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());
		for (int t = 0; t < test_case; t++) {
			String[] size = br.readLine().split(" ");
			R = Integer.parseInt(size[0]) + 2;
			C = Integer.parseInt(size[1]) + 2;
			map = new char[R][C];
			door = new int[R][C];

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] = '.';
				}
			}

			point start1 = null;
			point start2 = null;

			for (int i = 1; i < R - 1; i++) {
				String line = br.readLine();
				for (int j = 1; j < C - 1; j++) {
					map[i][j] = line.charAt(j - 1);
					if (map[i][j] == '$') {
						if (start1 == null)
							start1 = new point(i, j, 0);
						else
							start2 = new point(i, j, 0);
					}
				}
			}

			BFS(0, 0); // 외부에서 통과하는 문의 갯수
			BFS(start1.r, start1.c); // 시작지점1 에서 통과하는 문의 갯수
			BFS(start2.r, start2.c); // 시작지점2 에서 통과하는 문의 갯수

			int result = Integer.MAX_VALUE;

			for (int i = 0; i < R; i++) { // 시작지점1, 시작지점2, 외부에서 시작하여 만나는 한점에서 통과한 문의 갯수가 최소가 되는 지점을 구함
				for (int j = 0; j < C; j++) {
					
					if(map[i][j] != '*') {
						if (map[i][j] == '#') // 문에서 만나는 경우 해당 지점의 문의 중복제거 
							result = Math.min(result, door[i][j] - 2); 
						else
							result = Math.min(result, door[i][j]);						
					}
				}

			}
			System.out.println(result);
		}
	}

	public static void BFS(int r, int c) { 

		PriorityQueue<point> pq = new PriorityQueue<>();

		boolean[][] visit = new boolean[R][C];
		visit[r][c] = true; 
		pq.offer(new point(r, c, 0));

		while (!pq.isEmpty()) {

			point now = pq.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] != '*' && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					if (map[nr][nc] == '#') {
						door[nr][nc] += now.count + 1;
						pq.offer(new point(nr, nc, now.count + 1));
					} else {
						door[nr][nc] += now.count;
						pq.offer(new point(nr, nc, now.count));
					}
				}
			}

		}
	}

	public static boolean isRange(int nr, int nc) {

		if (nr < 0 || nr >= R || nc < 0 || nc >= C)
			return false;
		return true;
	}

}
