import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 1937번 - 욕심쟁이 판다
 * 
 * 		https://www.acmicpc.net/problem/1937
 */

public class Main1937 {

	static int n;
	static int[][] map;
	static int[][] count;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		count = new int[n][n];
		int res = 0;
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				solve(i, j);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res = Math.max(res, count[i][j]);
			}
		}

		System.out.println(res);
	}

	public static int solve(int r, int c) { // 다이나믹 프로그래밍 

		if (count[r][c] != 0)
			return count[r][c];
		else {
			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] > map[r][c]) { // 메모이제이션
					count[r][c] = Math.max(count[r][c], solve(nr, nc) + 1);
					flag = true;
				}
			}
			if(flag == false)
				count[r][c] = 1;
			
			return count[r][c];
		}
	}

}
