import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 		Baekjoon Online Judge 2449번 - 전구
 * 
 * 		https://www.acmicpc.net/problem/2449
 */

public class Main2449 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] num = new int[n];

		st = new StringTokenizer(br.readLine());

		int index = 0;
		for (int i = 0; i < n; i++) {

			int now = Integer.parseInt(st.nextToken());

			if (index == 0)
				num[index++] = now;
			else if (num[index - 1] != now) {
				num[index++] = now;
			}

		}

		solve(0, index, num);
	}

	public static void solve(int src, int dst, int[] num) { // 다이나믹 프로그래밍

		int[][] dp = new int[dst][dst];

		for (int i = 0; i < dst; i++) {
			for (int j = 0; j < dst; j++) {
				if (i != j)
					dp[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int l = 1; l < dst; l++) {
			for (int i = 0; i < dst; i++) {
				int j = i + l;
				if (j >= dst)
					continue;

				for (int k = i; k < j; k++) {
					if(dp[i][k] == Integer.MAX_VALUE || dp[k+1][j] == Integer.MAX_VALUE)
						continue;
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (num[i] == num[k + 1] ? 0 : 1));
				}

			}
		}

		System.out.println(dp[src][dst - 1]);
	}

}
