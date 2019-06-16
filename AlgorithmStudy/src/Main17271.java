import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 17271 - 리그 오브 레전설 (Small)
 * 
 * 		https://www.acmicpc.net/problem/17271
 */

public class Main17271 {

	static int[] result;
	static int M;
	static int div = 1000000007;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		M = sc.nextInt();
		result = new int[10001]; // N초가 남았을때 사용가능한 스킬의 경우의 수를 result배열에 저장
		solve(N);
		System.out.println(result[N]);

	}

	public static int solve(int n) { // 다이나믹 프로그래밍 

		if (n == 0) {
			return 1;

		} else if (n < 0)
			return 0;

		if (result[n] != 0)
			return result[n];
		else
			return result[n] = (solve(n - 1) + solve(n - M)) % div;

	}

}
