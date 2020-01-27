import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 		Baekjoon Online Judge 17828번 - 문자열 화폐
 * 
 * 		https://www.acmicpc.net/problem/17828
 */

public class Main17828 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]);
		long X = Integer.parseInt(input[1]);

		if (N == 1) {
			if (X >= 1 && X <= 26) {
				System.out.println((char) (X + 64));
				return;
			} else {
				System.out.println("!");
				return;
			}

		}

		long Z = (long) (X / 26);
		while (N - Z > X - (Z * 26))
			Z--;

		if (N > X || N * 26 < X) {
			bw.write("!");
			bw.flush();
			bw.close();
			return;

		}

		for (int i = 0; i < N - Z - 1; i++) {
			bw.write("A");
		}
		if (N - Z - 1 >= 0) {
			bw.write((char) (X - ((N - Z - 1) + (Z * 26)) + 64));
		}
		for (int i = 0; i < Z; i++) {
			bw.write("Z");
		}
		bw.flush();

		bw.close();
	}

}
