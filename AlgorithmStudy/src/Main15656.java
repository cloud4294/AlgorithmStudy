import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 		Baekjoon Online Judge 15656번 - N과 M(7)
 * 
 * 		https://www.acmicpc.net/problem/15656
 */

public class Main15656 {

	static int N, M;
	static int[] num;
	static List<Integer> output;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] size = br.readLine().split(" ");
		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);

		num = new int[N];
		output = new ArrayList<>();
		String[] input = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(input[i]);
		}
		Arrays.sort(num);

		solve(0);
		bw.close();
	}

	public static void solve(int count) throws IOException {

		if (count == M) {
			for (int j = 0; j < M; j++) {
				bw.write(output.get(j) + " ");

			}
			bw.write("\n");
			bw.flush();
			return;
		}

		for (int j = 0; j < N; j++) {
			
			output.add(num[j]);
			solve(count + 1);
			output.remove(output.size()-1);

		}
	}

}
