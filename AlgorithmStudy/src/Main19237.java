import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 19237번 - 어른 상어
 * 
 * 		https://www.acmicpc.net/problem/19237
 */

public class Main19237 {

	static int N, M, K;
	static List<shark> sharks;
	static int[][][] map;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	static class shark implements Comparable<shark> {

		int r;
		int c;
		int dir;
		int size;
		int[][] priority;

		public shark(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.priority = new int[5][5];
		}

		@Override
		public int compareTo(shark o) {
			if (o.size < this.size)
				return 1;
			return -1;
		}

	}

	public static void solve(int count) {

		if (count > 1000) {
			System.out.println(-1);
			return;
		}
		Queue<Integer> remove = new LinkedList<>();

		for (int i = 0; i < sharks.size(); i++) {
			shark now = sharks.get(i);
			int dir = now.dir;

			boolean flag = false;
			for (int j = 1; j < now.priority[dir].length; j++) {
				int nr = now.r + dr[now.priority[dir][j]];
				int nc = now.c + dc[now.priority[dir][j]];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc][0] == 0) {
					now.r = nr;
					now.c = nc;
					now.dir = now.priority[dir][j];

					flag = true;
					break;
				}
			}

			if (flag == false) {
				for (int j = 1; j < now.priority[dir].length; j++) {
					int nr = now.r + dr[now.priority[dir][j]];
					int nc = now.c + dc[now.priority[dir][j]];

					if (isRange(nr, nc) == false)
						continue;

					if (map[nr][nc][0] == now.size) {

						now.r = nr;
						now.c = nc;
						now.dir = now.priority[dir][j];

						break;
					}
				}

			}
		}

		for (int i = 0; i < sharks.size(); i++) {
			shark now = sharks.get(i);
			if (map[now.r][now.c][0] == 0 || map[now.r][now.c][0] == now.size) {
				map[now.r][now.c][0] = now.size;
				map[now.r][now.c][1] = K + 1;
			} else if (now.size < map[now.r][now.c][0]) {
				remove.offer(map[now.r][now.c][0]);
				map[now.r][now.c][0] = now.size;
				map[now.r][now.c][1] = K + 1;
			} else if (now.size > map[now.r][now.c][0]) {
				remove.offer(now.size);
			}
		}

		while (!remove.isEmpty()) {
			int now = remove.poll();
			for (int i = 0; i < sharks.size(); i++) {
				if (sharks.get(i).size == now) {
					sharks.remove(i);
					break;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j][1] > 0) {
					map[i][j][1]--;
					if(map[i][j][1] == 0)
						map[i][j][0] = 0;
				}
			}
		}

		if (sharks.size() == 1) {
			System.out.println(count);
			return;
		}
		
		solve(count + 1);

	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr >= N || nc >= N)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sharks = new ArrayList<>();
		map = new int[N][N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int size = Integer.parseInt(st.nextToken());
				if (size > 0) {
					sharks.add(new shark(i, j, size));
					map[i][j][0] = size;
					map[i][j][1] = K;
				}
			}
		}
		Collections.sort(sharks);
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			sharks.get(i).dir = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++) {
					sharks.get(i).priority[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		solve(1);
	}

}
