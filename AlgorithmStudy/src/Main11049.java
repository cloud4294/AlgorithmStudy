import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 11049번 - 행렬 곱셈 순서
 * 	
 * 		https://www.acmicpc.net/problem/11049
 */

public class Main11049 {

	static int N;
	static matrix[] matrixs;
	static int[][] dp;

	static class matrix {

		int A;
		int B;

		public matrix(int a, int b) {
			super();
			A = a;
			B = b;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		matrixs = new matrix[N + 1];
		dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 1; i <= N; i++) {
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			matrixs[i] = new matrix(A, B);
		}

		solve(1, N);

		System.out.println(dp[1][N]);
	}

	public static int solve(int A, int B) { // 다이나믹 프로그래밍

		if (A == B)
			return dp[A][B] = 0;
		if (dp[A][B] != Integer.MAX_VALUE)
			return dp[A][B];

		int min = Integer.MAX_VALUE;
		for (int i = A; i < B; i++) {
			min = Math.min(min, solve(A, i) + solve(i + 1, B) + (matrixs[A].A * matrixs[i].B * matrixs[B].B));
		}

		return dp[A][B] = min;
	}

}
