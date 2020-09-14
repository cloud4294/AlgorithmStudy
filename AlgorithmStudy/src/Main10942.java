import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 		Baekjoon Online Judge 10942번 - 팰린드롬?
 * 		
 * 		https://www.acmicpc.net/problem/10942
 */

public class Main10942 {

	static int N;
	static int[] num;
	static int[][] palinedrome;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer strbuff = new StringBuffer();
		N = Integer.parseInt(br.readLine());
		num = new int[N + 1];
		palinedrome = new int[N + 1][N + 1];

		String[] input = br.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(input[i - 1]);
		}

		int m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int s = Integer.parseInt(input[0]);
			int e = Integer.parseInt(input[1]);

			solve(s, e);

			if (palinedrome[s][e] == -1) // 출력시간 단축
				strbuff.append("0\n");
			else
				strbuff.append("1\n");
		}
		bw.write(strbuff.toString());
		bw.flush();
		bw.close();
	}

	public static int solve(int s, int e) { // dp

		if (s > e)
			return 1;

		if (palinedrome[s][e] != 0)
			return palinedrome[s][e];

		if (s == e)
			return palinedrome[s][e] = 1;

		if (num[s] != num[e]) {
			return palinedrome[s][e] = -1;
		} else
			return palinedrome[s][e] = solve(s + 1, e - 1);
	}

}
