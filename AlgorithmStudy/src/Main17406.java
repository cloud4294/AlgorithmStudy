import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17406번 - 배열 돌리기 4
 * 
 * 		https://www.acmicpc.net/problem/17406
 */

public class Main17406 {

	static int N, M, K, res;
	static int[][] map;
	static com[] commands;
	static List<Integer> select;

	static class com {
		int r;
		int c;
		int s;

		public com(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	public static void solve(int count) {

		if (count == K) {

			int[][] result = new int[N + 1][M + 1];

			for (int i = 0; i < N + 1; i++) {
				result[i] = Arrays.copyOf(map[i], M + 1);
			}

			for (int i = 0; i < K; i++) { // 배열회전
				com now = commands[select.get(i)];
				int r = now.r;
				int c = now.c;
				int s = now.s;
				while (true) {
					if (r - s == now.r && c - s == now.c)
						break;
					int start = result[r - s][c - s];
					for (int j = r - s; j < r + s; j++) {
						result[j][c - s] = result[j + 1][c - s];
					}
					for (int j = c - s; j < c + s; j++) {
						result[r + s][j] = result[r + s][j + 1];
					}
					for (int j = r + s; j > r - s; j--) {
						result[j][c + s] = result[j - 1][c + s];
					}
					for (int j = c + s; j > c - s + 1; j--) {
						result[r - s][j] = result[r - s][j - 1];
					}
					result[r - s][c - s + 1] = start;
					s--;
				}

			}
			for (int i = 1; i <= N; i++) { // 최소값 갱신
				int sum = 0;
				for (int j = 1; j <= M; j++) {
					sum += result[i][j];
				}
				
				res = Math.min(res, sum);
			}

			return;
		}

		for (int i = 0; i < K; i++) { // 명령어 순서 조합
			if (!select.contains(i)) {
				select.add(i);
				solve(count + 1);
				select.remove(Integer.valueOf(i));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		commands = new com[K];
		select = new ArrayList<>();
		res = Integer.MAX_VALUE;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			commands[i] = new com(r, c, s);
		}

		solve(0);

		System.out.println(res);
	}

}
