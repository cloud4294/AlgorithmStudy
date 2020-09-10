import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 17090번 - 미로 탈출하기
 * 
 * 		https://www.acmicpc.net/problem/17090
 */

public class Main17090 {

	static int N, M;
	static char[][] map;
	static int[][] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");

		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);

		map = new char[N][M];
		visit = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(solve(i, j) == 1)
					count++;
			}
		}
		
		System.out.println(count);
	}

	public static int solve(int i, int j) {
		
		if(isRange(i, j) == false)
			return 1;

		if(visit[i][j] != 0)
			return visit[i][j];

		if (map[i][j] == 'U') {
			visit[i][j] = -1;
			return visit[i][j] = solve(i - 1, j);
		} else if (map[i][j] == 'R') {
			visit[i][j] = -1;
			return visit[i][j] = solve(i, j + 1);
		} else if (map[i][j] == 'D') {
			visit[i][j] = -1;
			return visit[i][j] = solve(i + 1, j);
		} else if (map[i][j] == 'L') {
			visit[i][j] = -1;
			return visit[i][j] = solve(i, j - 1);
		}
		
		return 0;
	}

	public static boolean isRange(int i, int j) {
		if (i < 0 || i >= N || j < 0 || j >= M)
			return false;
		return true;
	}

}
