import java.util.Arrays;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 1562번 - 계단 수
 * 
 * 		https://www.acmicpc.net/problem/1562
 */

public class Main1562 {

	static int N;
	static int mod = 1000000000;
	static int[][][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int result = 0;

		dp = new int[N + 1][10][1 << 10];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		for (int i = 1; i <= 9; i++) {
			result = (result + solve(1, i, 1 << i)) % mod;
		}

		System.out.println(result);
	}

	public static int solve(int index, int now, int visit) { // 비트마스킹, 다이나믹 프로그래밍

		if (index == N)
			if (visit == (1 << 10) - 1)
				return 1;
			else
				return 0;

		if (dp[index][now][visit] != -1)
			return dp[index][now][visit];

		int result = 0;
		
		if (now - 1 >= 0) {
			result = (result + solve(index + 1, now - 1, visit | (1 << now - 1))) % mod;
		}

		if (now + 1 <= 9) {
			result = (result + solve(index + 1, now + 1, visit | (1 << now + 1))) % mod;
		}

		return dp[index][now][visit] = result;
	}

}
