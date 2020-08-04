import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 2629번 - 양팔저울
 * 
 * 		https://www.acmicpc.net/problem/2629
 */

public class Main2629 {

	static int n;
	static int max = 30;
	static int[] weight;
	static boolean[][] flag = new boolean[max + 1][max * 500 + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		weight = new int[max + 1];

		String[] input = br.readLine().split(" ");

		for (int i = 0; i < n; i++) {
			weight[i] = Integer.parseInt(input[i]);
		}

		solve(0, 0);
		
		int k = Integer.parseInt(br.readLine());
		input = br.readLine().split(" ");

		for (int i = 0; i < k; i++) {
			int now = Integer.parseInt(input[i]);

			if (now > max * 500)
				System.out.print("N ");
			else if (flag[n][now] == true)
				System.out.print("Y ");
			else
				System.out.print("N ");
		}

	}

	public static void solve(int index, int now) {

		if (index > n)
			return;

		if (flag[index][now] == true)
			return;

		flag[index][now] = true;

		solve(index + 1, now + weight[index]);
		solve(index + 1, now);
		solve(index + 1, Math.abs(now - weight[index]));

	}

}
