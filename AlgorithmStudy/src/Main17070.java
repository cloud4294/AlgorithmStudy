import java.util.Scanner;


/*
 * 		Baekjoon Online Judge 17070번 - 파이프 옮기기 1
 * 
 * 		https://www.acmicpc.net/problem/17070
 */

public class Main17070 {

	static int N;
	static int[][] map;
	static int res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		res = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		} // 입력값 초기화

		solve(0, 1, 0);

		System.out.println(res);

	}

	public static void solve(int y, int x, int dir) {

		if (y == N - 1 && x == N - 1) {
			res++;
			return;
		} // 목적지에 도달햇을 경우 

		if (dir != 2 && x + 1 < N && map[y][x + 1] == 0) {
			solve(y, x + 1, 0);
		} // 세로 방향이 아닌경우 가로 방향 탐색 

		if (dir != 0 && y + 1 < N && map[y + 1][x] == 0) {
			solve(y + 1, x, 2);
		} // 가로 방향이 아닌경우 세로 방향 탐색 

		if (y + 1 < N && x + 1 < N && map[y + 1][x] == 0 && map[y][x + 1] == 0 && map[y + 1][x + 1] == 0)// 대각선 방향이 가능한지 탐색
			solve(y + 1, x + 1, 1);
			
	}

}
