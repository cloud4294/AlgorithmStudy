import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 15651번 - N과 M(3)
 * 
 * 		https://www.acmicpc.net/problem/15651
 */

public class Main15651 {

	static int N, M;
	static int[] output;
	
	static BufferedWriter bw;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		N = sc.nextInt();
		M = sc.nextInt();

		output = new int[N+1];
		solve(0);
		
		bw.close();
	}

	public static void solve(int n) throws IOException {
		
		if(n == M) {
			for (int i = 0; i < M; i++) {
				bw.write(output[i]+" ");
			}
			bw.write("\n");
			bw.flush();
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			output[n] = i;
			solve(n+1);
		}
	}

}
