import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 		Baekjoon Online Judge 11404번 - 플로이드
 * 
 * 		https://www.acmicpc.net/problem/11404
 */

public class Main11404 {

	static int N, M;
	static int[][] map;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int src = Integer.parseInt(input[0]);
			int dst = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);
			if (cost < map[src][dst])
				map[src][dst] = cost;
		}

		for (int k = 1; k <= N; k++) {	// 플로이드 워셜
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][k] != INF && map[k][j] != INF) {
						map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print((map[i][j] == INF ? 0 : map[i][j]) + " ");
			}
			System.out.println();
		}
	}

}
