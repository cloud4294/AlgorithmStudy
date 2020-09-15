import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1806번 - 부분합
 * 
 * 		https://www.acmicpc.net/problem/1806
 */

public class Main1806 {

	static int N, S;
	static int[] num;
	static long result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		result = Long.MAX_VALUE;
		
		num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		solve(0, 0, num[0], 1);
		
		System.out.println(result == Long.MAX_VALUE ? 0 : result);
	}

	public static void solve(int left, int right, long sum, int length) { // 투포인터

		if (left > right)
			return;

		if (sum < S) {
			if (right + 1 < N)
				solve(left, right + 1, sum + num[right + 1], length + 1);
		} else {
			result = Math.min(result, length);
			solve(left + 1, right, sum - num[left], length - 1);
		}
	}

}
