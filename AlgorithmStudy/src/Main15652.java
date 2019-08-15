import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 15651번 - N과 M(4)
 * 
 * 		https://www.acmicpc.net/problem/15652
 */

public class Main15652 {

	static int N, M;
	static int[] output;
	
	static BufferedWriter bw;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		N = sc.nextInt();
		M = sc.nextInt();

		output = new int[N+1];
		solve(1,0);
		
		bw.close();
	}

	public static void solve(int n,int count) throws IOException {
		
		if(count == M ) {
			for (int i = 0; i < M; i++) {
				bw.write(output[i]+" ");
			}
			bw.write("\n");
			bw.flush();
			return;
		}
		
		for (int i = n; i <= N; i++) {
			output[count] = i;
			solve(i,count+1);
		}
	}

}
