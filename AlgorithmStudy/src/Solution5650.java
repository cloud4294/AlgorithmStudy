import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		SW Expert Academy 5650 - [모의 SW 역량테스트] 핀볼 게임
 * 
 * 		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRF8s6ezEDFAUo&categoryId=AWXRF8s6ezEDFAUo&categoryType=CODE
 */

public class Solution5650 {

	static int N;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int Sr, Sc, result;

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;
	}

	public static void solve(int i, int j, int k) {

		int nowR = i;
		int nowC = j;
		int point = 0;
		while (true) {
			nowR += dr[k];
			nowC += dc[k];
			if (nowR == Sr && nowC == Sc) {
				result = Math.max(result, point);
				return;
			}

			if (isRange(nowR, nowC) == false) {
				k = (k + 2) % 4;
				point++;
			} else if (map[nowR][nowC] == -1) {
				result = Math.max(result, point);
				return;
			} else if (map[nowR][nowC] > 0 && map[nowR][nowC] <= 5) {
				if (map[nowR][nowC] == 1) {
					if (k == 0) {
						k = (k + 2) % 4;
						point++;
					} else if (k == 1) {
						k = (k + 2) % 4;
						point++;
					} else if (k == 2) {
						k = (k + 3) % 4;
						point++;
					} else if (k == 3) {
						k = (k + 1) % 4;
						point++;
					}
				} else if (map[nowR][nowC] == 2) {
					if (k == 0) {
						k = (k + 1) % 4;
						point++;
					} else if (k == 1) {
						k = (k + 2) % 4;
						point++;
					} else if (k == 2) {
						k = (k + 2) % 4;
						point++;
					} else if (k == 3) {
						k = (k + 3) % 4;
						point++;
					}
				} else if (map[nowR][nowC] == 3) {
					if (k == 0) {
						k = (k + 3) % 4;
						point++;
					} else if (k == 1) {
						k = (k + 1) % 4;
						point++;
					} else if (k == 2) {
						k = (k + 2) % 4;
						point++;
					} else if (k == 3) {
						k = (k + 2) % 4;
						point++;
					}
				} else if (map[nowR][nowC] == 4) {
					if (k == 0) {
						k = (k + 2) % 4;
						point++;
					} else if (k == 1) {
						k = (k + 3) % 4;
						point++;
					} else if (k == 2) {
						k = (k + 1) % 4;
						point++;
					} else if (k == 3) {
						k = (k + 2) % 4;
						point++;
					}
				} else if (map[nowR][nowC] == 5) {
					k = (k + 2) % 4;
					point++;
				}
			} else if (map[nowR][nowC] > 5 && map[nowR][nowC] <= 10) {
				boolean flag = false;
				for (int l = 0; l < N; l++) {
					for (int l2 = 0; l2 < N; l2++) {
						if (map[l][l2] == map[nowR][nowC] && ((nowR == l && nowC == l2) == false)) {
							nowR = l;
							nowC = l2;
							flag = true;
							break;
						}
					}
					if(flag == true)
						break;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			result = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						Sr = i;
						Sc = j;
						for (int k = 0; k < 4; k++) {
							solve(i, j, k);
						}
					}
				}
			}

			System.out.println("#" + t + " " + result);
		}

	}

}
