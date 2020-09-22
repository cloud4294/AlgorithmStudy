import java.util.Arrays;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 1328번 - 고층 빌딩
 * 
 * 		https://www.acmicpc.net/problem/1328
 */

public class Main1328 {

	static int N, L, R;
	static long[][][] dp;
	static int MOD = 1000000007;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int L = sc.nextInt();
		int R = sc.nextInt();

		dp = new long[N + 1][L + 1][R + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= L; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		System.out.println(solve(N, L, R) % MOD);
	}

	public static long solve(int n, int l, int r) { // 다이나믹 프로그래밍 

		if ((l == 1 && r == n) || (r == 1 && l == n)) {
			return dp[n][l][r] = 1;
		}

		if (n == 0 || l == 0 || r == 0)
			return 0;

		if (dp[n][l][r] != -1)
			return dp[n][l][r];

		return dp[n][l][r] = (solve(n - 1, l - 1, r) + solve(n - 1, l, r - 1) + (solve(n - 1, l, r) * (n - 2))) % MOD;

	}

}
