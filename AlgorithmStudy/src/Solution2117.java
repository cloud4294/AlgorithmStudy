import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		SW Expert Academy 2117 - [모의 SW 역량테스트] 홈 방범 서비스
 * 
 * 		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu&categoryId=AV5V61LqAf8DFAWu&categoryType=CODE
 */

public class Solution2117 {

	static int N, M;
	static int[][] map;
	static int result;

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;
	}

	public static void solve(int i, int j, int k) {

		if (k > N+1)
			return;
		int cost = (k * k) + ((k - 1) * (k - 1));
		int count = 0;
		for (int r = i - k; r <= i + k; r++) {
			for (int c = j - k; c <= j + k; c++) {
				if (isRange(r, c) == false)
					continue;
				if (Math.abs(r - i) + Math.abs(c - j) < k && map[r][c] == 1) {
					count++;
				}
			}
		}

		if (cost <= count * M) {
			result = Math.max(result, count);
		}
		solve(i, j, k + 1);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			result = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					solve(i, j, 1);
				}
			}

			System.out.println("#"+t+" "+result);
		}

	}

}
