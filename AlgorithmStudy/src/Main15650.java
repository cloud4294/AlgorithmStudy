import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 15650번 - N과 M(2)
 * 
 * 		https://www.acmicpc.net/problem/15650
 */

public class Main15650 {

	static int N,M;
	static int[] output;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		output = new int[M];
		
		solve(1,0);

	}

	public static void solve(int n,int count) {
		
		if(count == M ) {
			for (int i = 0; i < output.length; i++) {
				System.out.print(output[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for (int i = n; i <= N; i++) {
			output[count] = i;
			solve(i+1,count+1);
		}
		
	}

}
