import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17281 - ⚾
 * 
 * 		https://www.acmicpc.net/problem/17281
 */

public class Main17281 {

	static int N;
	static int[][] point;
	static List<Integer> order;
	static int result;
	static boolean[] base;

	public static void solve(int pos) {

		if (pos == 9) {

			int max = 0;
			int hit = 0;
			for (int i = 0; i < N; i++) {
				int outCount = 0;
				Arrays.fill(base, false);
				while (outCount < 3) {
					int now = order.get(hit);
					if (point[i][now] == 0) {
						outCount++;
					} else {
						for (int k = 2; k >= 0; k--) { 
							if (base[k] == true) { // base에 주자가 있으면 먼지 진루 시킴
								if (k + point[i][now] >= 3) {
									max++;
									base[k] = false;
								} else {
									base[k + point[i][now]] = true;
									base[k] = false;
								}

							}
						}
						if (point[i][now] != 4) // 현재 타자 진루 
							base[point[i][now] - 1] = true;
						else
							max++;

					}
					hit = (hit + 1) % 9;
				}

			}
			result = Integer.max(result, max); // 최대값 갱신
			return;
		} else if (pos == 3) { // 4번째 타자 지정
			order.add(0);
			solve(pos + 1);
			order.remove(Integer.valueOf(0));

		} else {
			for (int i = 1; i < 9; i++) { // 순열 조합 

				if (!order.contains(i)) {
					order.add(i);
					solve(pos + 1);
					order.remove(Integer.valueOf(i));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		point = new int[N][9];
		order = new ArrayList<>();
		base = new boolean[4];
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				point[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(0);
		System.out.println(result);
	}

}
