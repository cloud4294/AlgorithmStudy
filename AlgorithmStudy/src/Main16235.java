import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 16235 - 나무 재테크
 * 
 * 		https://www.acmicpc.net/problem/16235
 */

public class Main16235 {

	static int N, M, K;
	static int[][] A;
	static int[][] health;
	static PriorityQueue<tree>[][] pq;
	static Queue<tree>[][] live, dead;
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class tree implements Comparable<tree> {

		int age;

		public tree(int age) {
			super();

			this.age = age;
		}

		@Override
		public int compareTo(tree arg0) {
			if (arg0.age < age)
				return 1;
			return -1;
		}

	}

	public static boolean isRange(int r, int c) {
		if (r <= 0 || r > N || c <= 0 || c > N)
			return false;
		return true;

	}

	public static void solve(int year) {
		if (year >= K) {
			int result = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					result += pq[i][j].size();
				}
			}
			System.out.println(result);
			return;
		}

		for (int i = 1; i <= N; i++) { // 봄
			for (int j = 1; j <= N; j++) {
				while (!pq[i][j].isEmpty()) {
					tree now = pq[i][j].poll();

					if (health[i][j] >= now.age) {
						health[i][j] -= now.age;
						now.age++;
						live[i][j].offer(now);
					} else {
						dead[i][j].offer(now);
					}

				}

			}
		}
		for (int i = 1; i <= N; i++) { // 여름
			for (int j = 1; j <= N; j++) {
				while (!dead[i][j].isEmpty()) {
					tree now = dead[i][j].poll();
					health[i][j] += now.age / 2;
				}

			}
		}

		for (int i = 1; i <= N; i++) { // 가을
			for (int j = 1; j <= N; j++) {
				while (!live[i][j].isEmpty()) {
					tree now = live[i][j].poll();

					pq[i][j].offer(now);
					if (now.age % 5 == 0) {
						for (int k = 0; k < 8; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];

							if (isRange(nr, nc) == false)
								continue;

							pq[nr][nc].offer(new tree(1));
						}
					}
				}

			}
		}

		for (int i = 1; i <= N; i++) { // 겨울
			for (int j = 1; j <= N; j++) {
				health[i][j] += A[i][j];
			}
		}

		solve(year + 1);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][N + 1];

		pq = new PriorityQueue[N + 1][N + 1];
		live = new LinkedList[N + 1][N + 1];
		dead = new LinkedList[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				pq[i][j] = new PriorityQueue<>();
				live[i][j] = new LinkedList<>();
				dead[i][j] = new LinkedList<>();
			}
		}

		health = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				health[i][j] = 5;
			}
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			pq[x][y].offer(new tree(z));
		}

		solve(0);

	}

}
