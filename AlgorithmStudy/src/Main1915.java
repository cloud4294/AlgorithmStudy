import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * 		Baekjoon Online Judge 1915번 - 가장 큰 정사각형
 * 
 * 		https://www.acmicpc.net/problem/1915
 */

public class Main1915 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		int n = Integer.parseInt(size[0]);
		int m = Integer.parseInt(size[1]);
		int[][] dp = new int[n][m];

		int max = 0;
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				dp[i][j] = Integer.parseInt(line.substring(j, j + 1));
			}
		} // 입력값 초기화 

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (dp[i][j] == 0)
					continue;
				else {
					dp[i][j] += Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])); // 현재값을 세방향중 가장 작은 값 + 1로 갱신

				}
			}

		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(dp[i][j] >max)
					max = dp[i][j];
			}

		} // 최대길이를 찾음
		

		System.out.println(max * max);

	}

}
