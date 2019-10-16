import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 		SW Expert Academy 2112 - [모의 SW 역량테스트] 보호 필름
 * 
 * 		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu&categoryId=AV5V1SYKAaUDFAWu&categoryType=CODE
 */

public class Solution2112 {

	static int D, W, K;
	static int[][] map;
	static int result;

	public static void solve(int depth, int count) { // DFS

		if (depth == D) {
			boolean flag = true;
			for (int i = 0; i < W; i++) {
				int check = 1;
				for (int j = 1; j < D; j++) {
					if (map[j][i] == map[j - 1][i])
						check++;
					else
						check = 1;

					if (check >= K) {
						break;
					}
				}

				if (check < K) {
					flag = false;
					break;
				}
			}

			if (flag == true)

				result = Math.min(result, count);

			return;
		}

		solve(depth + 1, count);

		int[] temp = new int[W];

		temp = Arrays.copyOf(map[depth], W);
		for (int i = 0; i < W; i++) {
			map[depth][i] = 0;
		}
		solve(depth + 1, count + 1);

		map[depth] = Arrays.copyOf(temp, W);
		for (int i = 0; i < W; i++) {
			map[depth][i] = 1;
		}
		solve(depth + 1, count + 1);
		map[depth] = Arrays.copyOf(temp, W);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			result = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			solve(0, 0);

			System.out.println("#"+t+" "+result);
		}

	}

}
