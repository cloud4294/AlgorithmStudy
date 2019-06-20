import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17211 - 좋은 날 싫은 날
 * 
 * 		https://www.acmicpc.net/problem/17211
 */

public class Main17211 {



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double[][] prob = new double[2][2];
		int N = Integer.parseInt(st.nextToken());
		int today = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		prob[0][0] = Double.parseDouble(st.nextToken());
		prob[0][1] = Double.parseDouble(st.nextToken());
		prob[1][0] = Double.parseDouble(st.nextToken());
		prob[1][1] = Double.parseDouble(st.nextToken());
		double[][] dp = new double[N+1][2];
		
		dp[0][today] = 1;
		
		for (int i = 1; i <= N; i++) { // 다이나믹 프로그래밍
			
			dp[i][0] = dp[i-1][0]*prob[0][0] + dp[i-1][1]*prob[1][0];
			dp[i][1] = dp[i-1][0]*prob[0][1] + dp[i-1][1]*prob[1][1];
			
			
		}
		System.out.println(Math.round(dp[N][0] * 1000));
		System.out.println(Math.round(dp[N][1] * 1000));
		

	}

	

}
