import java.util.Scanner;

/*
 * 		SW Expert Academy 1952 - 수영장
 * 
 * 		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq
 */

public class Solution1952 {

	static int[] cost;
	static int[] use;
	static int[] dp;
	static int[] month;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		cost = new int[4];
		use = new int[13];
		month = new int[13];
		for (int i = 1; i <= t; i++) {
			int result = 0;
			dp = new int[13];
			for (int j = 0; j < 4; j++) {
				cost[j] = sc.nextInt();
			}

			for (int j = 1; j <= 12; j++) {
				use[j] = sc.nextInt();
			}

			for (int j = 1; j <= 12; j++) {

				month[j] = Math.min(use[j] * cost[0], cost[1]);

			}

			for (int j = 1; j <= 12; j++) {
				dp[j] = dp[j - 1] + month[j];
				if (j - 3 >= 0) {
					dp[j] = Math.min(dp[j], dp[j - 3] + cost[2]);
				}
			}
	
			System.out.println("#"+i+" "+Math.min(dp[12], cost[3]));
		}
	}

}
