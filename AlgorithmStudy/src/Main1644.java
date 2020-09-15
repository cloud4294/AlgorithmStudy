import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 1644번 - 소수의 연속합
 * 		
 * 		https://www.acmicpc.net/problem/1644
 */

public class Main1644 {

	static int max = 4000001;
	static List<Integer> num;
	static int[] prime;
	static int count;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		count = 0;
		num = new ArrayList<>();
		BuildPrime();

		solve(0, 0, num.get(0), N);
		
		System.out.println(count);
	}

	public static void solve(int left, int right, int sum, int N) { // 투포인터

		if(left > right)
			return;
		
		if (sum < N) {
			if (right + 1 < num.size())
				solve(left, right + 1, sum + num.get(right + 1), N);
		} else if (sum == N) {
			count++;
			if (right + 1 < num.size())
				solve(left, right + 1, sum + num.get(right + 1), N);
		} else if (sum > N)
			solve(left + 1, right, sum - num.get(left), N);
	}

	public static void BuildPrime() { // 에라스토테네스의 체

		prime = new int[max];

		for (int i = 2; i < max; i++) {
			if (prime[i] == 0) {
				num.add(i);
				for (int j = i + i; j < max; j += i) {
					prime[j] = 1;
				}
			}
		}
		

	}

}
