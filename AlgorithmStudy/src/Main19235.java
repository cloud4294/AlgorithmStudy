import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 19235번 - 모노미노도미노
 * 
 * 		https://www.acmicpc.net/problem/19235
 */

public class Main19235 {

	static int[][][] map;
	static int[] dr = { -1, 0, 1, 0, 0 };
	static int[] dc = { 0, 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int result = 0;
		int sum = 0;

		map = new int[2][6][4];
		for (int i = 1; i <= N; i++) {
			String input[] = br.readLine().split(" ");
			int t = Integer.parseInt(input[0]);
			int x = Integer.parseInt(input[1]);
			int y = Integer.parseInt(input[2]);

			result += moveBlock(t, x, y, i);

			// printMap();
		}

		System.out.println(result);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 4; k++) {
					if (map[i][j][k] != 0)
						sum++;
				}
			}
		}
		System.out.println(sum);
	}

	public static int moveBlock(int t, int x, int y, int blockNum) {

		if (t == 1) {
			// move green
			int nextRow = -1;
			for (int i = 0; i < 6; i++) {
				if (map[0][i][y] == 0)
					nextRow = i;
				else
					break;
			}
			map[0][nextRow][y] = blockNum;

			// move blue
			int nextCol = -1;
			for (int i = 0; i < 6; i++) {
				if (map[1][i][3 - x] == 0)
					nextCol = i;
				else
					break;
			}
			map[1][nextCol][3 - x] = blockNum;

		} else if (t == 2) {
			// move green
			int nextRow = -1;
			for (int i = 0; i < 6; i++) {
				if (map[0][i][y] == 0 && map[0][i][y + 1] == 0)
					nextRow = i;
				else
					break;
			}
			map[0][nextRow][y] = blockNum;
			map[0][nextRow][y + 1] = blockNum;

			// move blue
			int nextCol = -1;
			for (int i = 1; i < 6; i++) {
				if (map[1][i][3 - x] == 0 && map[1][i - 1][3 - x] == 0)
					nextCol = i;
				else
					break;
			}
			map[1][nextCol][3 - x] = blockNum;
			map[1][nextCol - 1][3 - x] = blockNum;

		} else if (t == 3) {
			// move green
			int nextRow = -1;
			for (int i = 1; i < 6; i++) {
				if (map[0][i][y] == 0 && map[0][i - 1][y] == 0)
					nextRow = i;
				else
					break;
			}
			map[0][nextRow][y] = blockNum;
			map[0][nextRow - 1][y] = blockNum;

			// move blue
			int nextCol = -1;
			for (int i = 0; i < 6; i++) {
				if (map[1][i][3 - x] == 0 && map[1][i][2 - x] == 0)
					nextCol = i;
				else
					break;
			}
			map[1][nextCol][3 - x] = blockNum;
			map[1][nextCol][2 - x] = blockNum;
		

		}

		return RemoveBlock();

	}

	public static int RemoveBlock() {

		int removeCount = 0;

		for (int i = 0; i < 2; i++) {
			for (int j = 5; j >= 0; j--) {
				boolean flag = true;
				for (int k = 0; k < 4; k++) {
					if (map[i][j][k] == 0)
						flag = false;
				}

				if (flag == true) {
					for (int k = 0; k < 4; k++) {
						map[i][j][k] = 0;
					}
					removeCount++;
				}

			}

			for (int j = 5; j >= 0; j--) {
				for (int k = 0; k < 4; k++) {
					if (map[i][j][k] != 0) {
						int blockNum = map[i][j][k];
						for (int l = 0; l < 5; l++) {
							int nr = j + dr[l];
							int nc = k + dc[l];

							if (isRange(nr, nc) == false)
								continue;

							if (map[i][j][k] == map[i][nr][nc]) {
								if (l == 0 || l == 2) {
								
									int nextRow = Math.max(j, nr);

									for (int m = nextRow + 1; m < 6; m++) {
										if (isRange(m, k) == true && map[i][m][k] == 0)
											nextRow = m;
										else
											break;
									}

									map[i][j][k] = 0;
									map[i][nr][nc] = 0;
									map[i][nextRow][k] = blockNum;
									map[i][nextRow - 1][k] = blockNum;

								} else if (l == 1 || l == 3) {
									
									int nextRow = j;
									for (int m = j + 1; m < 6; m++) {
										if (isRange(m, k) == true && map[i][m][k] == 0 && map[i][m][nc] == 0)
											nextRow = m;
										else
											break;
									}

									map[i][j][k] = 0;
									map[i][nr][nc] = 0;
									map[i][nextRow][k] = blockNum;
									map[i][nextRow][nc] = blockNum;

								} else if (l == 4) {
									
									int nextRow = j;
									for (int m = j + 1; m < 6; m++) {
										if (isRange(m, k) == true && map[i][m][k] == 0)
											nextRow = m;
										else
											break;
									}

									map[i][j][k] = 0;
									map[i][nextRow][k] = blockNum;
								}

								break;
							}

						}

					}
				}
			}

		}


		for (int i = 0; i < 2; i++) {
			int rowCount = 0;
			for (int j = 1; j >= 0; j--) {
				for (int k = 0; k < 4; k++) {
					if (map[i][j][k] != 0) {
						rowCount++;
						break;
					}
				}
			}

			for (int j = 0; j < rowCount; j++) {
				for (int l = 5; l > 0; l--) {
					for (int c = 0; c < 4; c++) {
						map[i][l][c] = map[i][l - 1][c];
					}
				}
			}

			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 4; k++) {
					map[i][j][k] = 0;
				}
			}
		}

		if (removeCount == 0)
			return 0;
		
		return removeCount + RemoveBlock();
	}

	public static boolean isRange(int nr, int nc) {

		if (nr < 0 || nr >= 6 || nc < 0 || nc >= 4)
			return false;
		return true;
	}

	public static void printMap() {
		for (int j = 0; j < 4; j++) {
			for (int j2 = 0; j2 < 10; j2++) {
				if (j2 >= 0 && j2 <= 3)
					System.out.print("0 ");
				else
					System.out.print(map[1][j2 - 4][3 - j] + " ");
			}
			System.out.println();
		}
		for (int j = 0; j < 6; j++) {
			for (int j2 = 0; j2 < 4; j2++) {
				System.out.print(map[0][j][j2] + " ");
			}
			System.out.println();
		}
		System.out.println("=========");
	}

}
