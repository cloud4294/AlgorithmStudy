import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 15684 - 사다리 조작
 * 
 * 		https://www.acmicpc.net/problem/15684
 */

public class Main15684 {

	static boolean[][] bridge;
	static int N, M, H;
	static int result;

	public static int check(int now, int h) { // 사다리타기 결과 확인 
		for (int i = h; i <= H; i++) {
			if (bridge[i][now] == true)
				return check(now + 1, i + 1);
			else if (now - 1 >= 0 && bridge[i][now - 1] == true)
				return check(now - 1, i + 1);
		}

		return now;
	}

	public static void solve(int count, int add) {

		if (count >= 3) { // 3개 이상 놓은 경우 
			boolean flag = true;
			for (int j2 = 1; j2 <= N; j2++) {
				if (check(j2, 1) != j2) {
					flag = false;
					break;
				}
			}
			if (flag == true)
				result = Math.min(result, add);
			return;
		}
		if (add >= result)
			return;

		for (int i = 1; i <= H; i++) { // 사다리 연결 
			for (int j = 1; j < N; j++) {
				if (bridge[i][j] == true)
					continue;
				if (j + 1 <= N && bridge[i][j + 1] == true)
					continue;
				if (j - 1 >= 0 && bridge[i][j - 1] == true)
					continue;

				bridge[i][j] = true;
				solve(count + 1, add + 1);
				bridge[i][j] = false;

			}
		}
		solve(count + 1, add); // 연결 x 
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		bridge = new boolean[H + 1][N + 1];
		result = 4;

		if (M == 0) {
			System.out.println(0);
			return;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bridge[a][b] = true;
		}
		solve(0, 0);

		System.out.println(result == 4 ? -1 : result);
	}

}
