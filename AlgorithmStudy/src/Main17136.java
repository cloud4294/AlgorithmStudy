import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17136 - 색종이 붙이기
 * 
 * 		https://www.acmicpc.net/problem/17136
 */

public class Main17136 {

	static int[][] map;
	static int[] count;
	static int result;

	public static void solve(int cnt, int paper) {

		if (cnt > result) // 현재 최소값보다 더 많은 색종이가 사용된 경우 제외
			return;

		if (paper == 0) { // 칸을 모두 채운경우 최소값 갱신
			result = Math.min(result, cnt);
			return;
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (map[i][j] == 1) {
					for (int k = 5; k > 0; k--) { // 큰 색종이 부터 탐색
						if (count[k] >= 5)
							continue;

						if (i + k <= 10 && j + k <= 10) {
							boolean check = true;
							for (int l = i; l < i + k; l++) {
								for (int m = j; m < j + k; m++) {
									if (map[l][m] == 0) {
										check = false;
										break;
									}
								}
								if (check == false)
									break;
							}

							if (check == true) { // k크기의 색종이가 사용가능한 경우
								for (int l = i; l < i + k; l++) {
									for (int m = j; m < j + k; m++) {
										map[l][m] = 0;

									}
								}
								count[k]++;

								solve(cnt + 1, paper - (k * k)); 

								for (int l = i; l < i + k; l++) {
									for (int m = j; m < j + k; m++) {
										map[l][m] = 1;

									}
								}
								count[k]--;

							}
						}

					}

					return; // 채워야하는 칸을 채우지 못한 경우이므로 탐색 종료
				}

			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new int[10][10];
		count = new int[6];
		result = Integer.MAX_VALUE;
		int paper = 0;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					paper++; // 채워야하는 칸의 갯수 
			}
		}

		solve(0, paper);

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);

	}

}
