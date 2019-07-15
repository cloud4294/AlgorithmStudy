import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 2294번 - 동전 2
 * 
 * 		https://www.acmicpc.net/problem/2294
 */

public class Main2294 {

	static int[] dp;
	static List<Integer> values;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		values = new ArrayList<>();
		dp = new int[100001];
		for (int i = 0; i < n; i++) {
			int now = sc.nextInt();
			values.add(now);
			dp[now] = 1;
		}
		
		solve(k);
		
		
		if(dp[k] == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(dp[k]);
	}

	public static int solve(int k) { // 다이나믹 프로그래밍
		
		if(dp[k] != 0)
			return dp[k];
		
		int next = Integer.MAX_VALUE;
		for (int i = 0; i < values.size(); i++) {
			int now = values.get(i);
			if(k - now >= 0) {
				int check = solve(k-now);
				if(check != Integer.MAX_VALUE)
					next = Math.min(next, check+1);
			}
		}
		
		return dp[k] = next;
	}

}
