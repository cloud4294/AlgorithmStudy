import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 7579번 - 앱
 * 
 * 		https://www.acmicpc.net/problem/7579
 */

public class Main7579 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] mem = new int[N];
		int[] cost = new int[N];
		
		int costMax = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			costMax += cost[i];
		}
		
		int[] dp = new int[costMax+1];
		
		for (int i = 0; i < N; i++) {  // 다이나믹 프로그래밍
			for (int j = costMax; j >= cost[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j-cost[i]] + mem[i]);
			}
		
		}
		
		for (int i = 0; i < costMax; i++) {
			if(dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
		
	}


}
