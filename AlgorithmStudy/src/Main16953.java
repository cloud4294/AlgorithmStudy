import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 16953번 - A -> B
 * 
 * 		https://www.acmicpc.net/problem/16953
 */

public class Main16953 {

	static int A;
	static int res;

	public static void solve(int B, int count) {// B를 A로 줄여 나감

		if (B == A) {// A와 같아지면 res를 갱신하고 종료
			res = count;
			return;
		} else if (B < A) 
			return;

		int next = 0;
		if (B % 10 == 1) { // 마지막 자리가 1일 경우 마지막 자리수를 없앰
			next = B / 10;
			solve(next, count + 1);
		} else if ((B % 10) % 2 == 1) { // 마지막 자리수가 1이 아닌 홀수일경우 주어진 연산으로 만들어질수 없으므로 종료
			return;
		} else { // 짝수인 경우 2로 나눔 
			next = B / 2;
			solve(next, count + 1);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		A = Integer.parseInt(input[0]);
		int B = Integer.parseInt(input[1]);
		res = Integer.MAX_VALUE;
		solve(B, 0);

		System.out.println(res == Integer.MAX_VALUE ? -1 : res + 1);

	}

}
