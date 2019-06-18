import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 14500 - 테트로미노
 * 
 * 		https://www.acmicpc.net/problem/14500
 */

public class Main14500 {

	static int[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, M;
	static int max;

	public static boolean isRange(int i, int j) {
		if (i < 0 || i >= N || j < 0 || j >= M)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);

		map = new int[N][M];
		visit = new boolean[N][M];
		max = 0;
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				visit[i][j] = true;
				count(i, j, 1, map[i][j]);
				visit[i][j] = false;
				count02(i,j);
			}
		}

		System.out.println(max);
	}

	

	public static void count02(int y, int x) { // DFS로 찾을수 없는 나머지 모양을 찾고 최대값 갱신

		for (int k = 0; k < 4; k++) {
			int sum = map[y][x];
			for (int k2 = 0; k2 < 4; k2++) {
				if (k == k2)
					continue;
				int ny = y + dr[k2];
				int nx = x + dc[k2];
				if (isRange(ny, nx) == true)
					sum += map[ny][nx];
				else {
					sum = 0;
					break;
				}
			}
			if (sum > max)
				max = sum;
		}

	}

	public static void count(int y, int x, int count, int point) { // DFS로 테트로미노 5가지중 4가지 모양을 찾고 최대값 갱신

		if (count == 4) {
			if (point > max)
				max = point;
			return;
		}

		for (int k = 0; k < 4; k++) {
			int ny = y + dr[k];
			int nx = x + dc[k];
			if (isRange(ny, nx) == true && visit[ny][nx] == false) {
				visit[ny][nx] = true;
				count(ny, nx, count + 1, point + map[ny][nx]);
				visit[ny][nx] = false;
			}

		}

	}

}
