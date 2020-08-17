import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2169번 - 로봇 조종하기
 * 
 * 		https://www.acmicpc.net/problem/2169
 */

public class Main2169 {

	static int R, C;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R + 1][C + 1];
		dp = new int[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();

		System.out.println(dp[R][C]);

	}

	public static void solve() {
		
		
		for (int i = 1; i <= R; i++) {
			
			if(i == 1) {	
				dp[1][1] = map[1][1];
				for (int j = 2; j <= C; j++) {
					dp[i][j] = dp[i][j-1] + map[i][j];
				}
			}else {
				
				for (int j = 1; j <= C; j++) { 
					
					int ac = 0;
					int sum = dp[i-1][j] + map[i][j]; 
					
					for (int k = j-1; k >= 1; k--) { 
						ac += map[i][k];
						sum = Math.max(sum, dp[i-1][k]+ac + map[i][j]);
					}
					
					ac = 0;
					for (int k = j+1; k <= C; k++) { 
						ac += map[i][k];
						sum = Math.max(sum, dp[i-1][k]+ac + map[i][j]);
					}
					dp[i][j] = sum;
				}
				
			}	
		}	
	}
}
