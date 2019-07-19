import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 10164 - 격자상의 경로
 * 
 * 		https://www.acmicpc.net/problem/10164
 */

public class Main10164 {

	static int N, M, K;
	static int[][] map;
	static int[][] dp;
	static int startY,startX;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		dp = new int[N][M];
		int targetY = 0;
		int targetX = 0;
		int count = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = count++;
				if(map[i][j] == K) {
					targetY = i;
					targetX = j;
				}
			}
		}
		dp[0][0] = 1;
		
		if(K == 0) { // 중간지점이 없을때
			startX = 0;
			startY = 0;
			solve(N-1,M-1);
		}
		else { // 중간지점이 있을때 
			startX = 0;
			startY = 0;
			solve(targetY,targetX); //시작지점부터 중간지점까지
			startX = targetX;
			startY = targetY;
			solve(N-1,M-1); // 중간지점부터 목적지까지
		}
			

		System.out.println(dp[N-1][M-1]);
		
	}

	public static int solve(int i,int j) { // 다이나믹프로그래밍
		
		if(i == startY && j == startX)
			return dp[i][j];
		else if(i <= startY)
			return dp[i][j] = solve(i,j-1);
		else if(j <= startX)
			return dp[i][j] = solve(i-1,j);
		else 
			return dp[i][j] = solve(i,j-1) + solve(i-1,j);
	}

	

}
