import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2099번 - The game of death
 * 
 * 		https://www.acmicpc.net/problem/2099
 */


public class Main2099 {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][Integer.parseInt(st.nextToken()) - 1] = 1;
			map[i][Integer.parseInt(st.nextToken()) - 1] = 1;
		}

		map = pow(map, K);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (map[a - 1][b - 1] == 0)
				bw.write("life\n");
			else
				bw.write("death\n");

		}
		bw.flush();
		bw.close();
	}

	public static int[][] pow(int[][] map, int k) { // 행렬의 거듭제곱

		if (k == 1)
			return map;

		if (k == 2) {
			return countPow(map, map);
		}

		int[][] temp = pow(map, k / 2);
		if (k % 2 == 0) {
			return countPow(temp, temp);
		} else {
			return countPow(countPow(temp, temp), map);
		}
	}

	public static int[][] countPow(int[][] map1, int[][] map2) { // 행렬 곱셈 

		int[][] next = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					next[i][j] += map1[i][k] * map2[k][j];
				}
				if (next[i][j] > 0)
					next[i][j] = 1;
			}
		}

		return next;
	}

}
