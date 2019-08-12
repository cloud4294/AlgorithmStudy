import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 		SW Expert Academy 7829 - 보물왕 태혁
 * 
 * 		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWtInr3auH0DFASy&categoryId=AWtInr3auH0DFASy&categoryType=CODE
 * 
 */
public class Solution7829 {

	static int N;
	static int[] prime;
	static int pw;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			pw = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			prime = new int[N];

			String[] number = br.readLine().split(" ");
			int min = Integer.MAX_VALUE;
			int max = 0;

			for (int j = 0; j < N; j++) {
				prime[j] = Integer.parseInt(number[j]);
				min = Math.min(prime[j], min);
				max = Math.max(prime[j], max);

			}

			System.out.println("#"+i+" "+(min * max));
		}
	}

}
