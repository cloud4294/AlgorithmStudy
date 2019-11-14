import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17779 - 게리맨더링 2
 * 
 * 		https://www.acmicpc.net/problem/17779
 */

public class Main17779 {

	static int N;
	static int[][] map;
	static int result;
	static boolean[][] visit;

	public static void solve(int x, int y, int d1, int d2) {

		int[] sum = { 0, 0, 0, 0, 0 };

		for (int i = 0; i <= N; i++) {
			Arrays.fill(visit[i], false);
		}

		if (x + d1 + d2 <= N && y - d1 >= 1 && y + d2 <= N) {

			int left = y;
			int right = y;
			boolean flag1 = false;
			boolean flag2 = false;

			for (int i = x; i <= x + d1 + d2; i++) {
				for (int j = left; j <= right; j++) {
					sum[4] += map[i][j];
					visit[i][j] = true;
				}

				if (flag1 == false)
					left--;
				else
					left++;

				if (flag2 == false)
					right++;
				else
					right--;

				if (left <= y - d1) {
					flag1 = true;
				}

				if (right >= y + d2) {
					flag2 = true;
				}

			}

			for (int i = 1; i < x + d1; i++) {
				for (int j = 1; j <= y; j++) {
					if (visit[i][j] == false) {
						sum[0] += map[i][j];
					}
				}
			}

			for (int i = 1; i <= x + d2; i++) {
				for (int j = y + 1; j <= N; j++) {
					if (visit[i][j] == false) {
						sum[1] += map[i][j];
					}
				}
			}

			for (int i = x + d1; i <= N; i++) {
				for (int j = 1; j < y - d1 + d2; j++) {
					if (visit[i][j] == false) {
						sum[2] += map[i][j];
					}

				}
			}

			for (int i = x + d2 + 1; i <= N; i++) {
				for (int j = y - d1 + d2; j <= N; j++) {
					if (visit[i][j] == false) {
						sum[3] += map[i][j];
					}
				}
			}

			int max = sum[0];
			int min = sum[0];
			for (int i = 1; i < 5; i++) {
				max = Math.max(max, sum[i]);
				min = Math.min(min, sum[i]);
			}

			result = Math.min(result, max - min);

			if (x + d1 + d2 + 1 <= N && y + d2 + 1 <= N) {
				solve(x, y, d1, d2 + 1);
			}

			if (x + d1 + d2 + 1 <= N && y - d1 - 1 >= 1)
				solve(x, y, d1 + 1, d2);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		result = Integer.MAX_VALUE;
		visit = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				solve(i, j, 1, 1);
			}
		}

		System.out.println(result);

	}

}
