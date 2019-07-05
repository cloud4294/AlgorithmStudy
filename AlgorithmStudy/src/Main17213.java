import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 17213번 - 과일 서리
 * 
 * 		https://www.acmicpc.net/problem/17213
 */


public class Main17213 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		System.out.println(solve(n, m));
	}

	public static int solve(int n, int m) {

		if(n == 1)
			return 1;
		else if (n == 2)
			return m - 1;
		else {
			int sum = 0;
			for (int i = 1; i <= m - n + 1; i++) {
				sum += solve(n - 1, m - i);
			}
			return sum;
		}

	}

}
