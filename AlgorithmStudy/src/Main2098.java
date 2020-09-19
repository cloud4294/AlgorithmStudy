import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2098번 - 외판원 순회
 * 
 * 		https://www.acmicpc.net/problem/2098
 */

public class Main2098 {

	static int N;
	static int[][] W;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		dp = new int[N][(1 << N) - 1];
		StringTokenizer st = null;

		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(tsp(0, 1));
		
	}

	public static int tsp(int current, int visited) { // 비트마스킹 , 다이나믹프로그래밍

		if (visited == (1 << N) - 1) {
			if (W[current][0] == 0)
				return Integer.MAX_VALUE;
			return W[current][0];
		}

		if (dp[current][visited] != Integer.MAX_VALUE)
			return dp[current][visited];

		for (int i = 0; i < N; i++) {

			if (W[current][i] == 0 || (visited & (1 << i)) != 0)
				continue;

			int next = visited | (1 << i);
			int value = tsp(i, next);

			if (value != Integer.MAX_VALUE)
				dp[current][visited] = Math.min(dp[current][visited], value + W[current][i]);
		}

		return dp[current][visited];
	}

}
