import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2174번 - 로봇 시뮬레이션
 * 
 * 		https://www.acmicpc.net/problem/2174
 */

public class Main2174 {

	static int A, B, N, M;

	static int[][] map;
	static robot[] robots;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class robot {
		int r;
		int c;
		int dir;

		public robot(int c, int r, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	public static boolean isRange(int r, int c) {
		if (r < 1 || r > B || c < 1 || c > A)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		map = new int[B+1][A+1];

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		robots = new robot[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			int dir = 0;
			if (d.equals("E")) {
				dir = 1;
			} else if (d.equals("W")) {
				dir = 3;
			} else if (d.equals("S")) {
				dir = 2;
			} else if (d.equals("N")) {
				dir = 0;
			}
			robots[i] = new robot(c, B-r+1, dir);
			map[B-r+1][c] = i;
		}

		for (int i = 0; i < M; i++) {
			
			
			
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			String com = st.nextToken();
			int count = Integer.parseInt(st.nextToken());

			robot now = robots[target];
			for (int j = 0; j < count; j++) {
				
				if (com.equals("F")) {
					int nr = now.r + dr[now.dir];
					int nc = now.c + dc[now.dir];

					if (isRange(nr, nc) == false) {
						System.out.println("Robot " + target + " crashes into the wall");
						return;
					}
					
					if (map[nr][nc] != 0) {
						System.out.println("Robot " + target + " crashes into robot " + map[nr][nc]);
						return;
					}
					map[now.r][now.c] = 0;
					now.r = nr;
					now.c = nc;
					map[nr][nc] = target;

				} else if (com.equals("L")) {
					now.dir = (now.dir + 3) % 4;
				} else if (com.equals("R")) {
					now.dir = (now.dir + 1) % 4;
				}

			}

			
		}
		System.out.println("OK");

	}

}
