import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 9944번 - NxM 보드 완주하기
 * 
 * 		https://www.acmicpc.net/problem/9944
 */

public class Main9944 {

	static int N, M, result;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int count = 0;
		String input = "";
		while ((input = br.readLine()) != null) {
			count++;
			String[] size = input.split(" ");

			N = Integer.parseInt(size[0]);
			M = Integer.parseInt(size[1]); // 맵의 사이즈
			result = Integer.MAX_VALUE;
			map = new char[N][M];

			int blockCount = 0; // 벽의 갯수 

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '*')
						blockCount++;
				}
			} // 맵 생성

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '.') { // 빈공간일때 시작점 
						for (int k = 0; k < 4; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];
							if (isRange(nr, nc) == true) {
								map[i][j] = '*';
								
								if (N * M - blockCount - 1 == 0) // 공을 두고 이동할 수 없는 경우 
									result = 0;
								else
									solve(i, j, k, N * M - blockCount - 1, 1); 
								
								map[i][j] = '.';
							}

						}
					}
				}
			}

			System.out.println("Case " + count + ": " + (result == Integer.MAX_VALUE ? -1 : result));
		}

	}

	public static void solve(int r, int c, int dir, int remainCount, int moveCount) {  
		// 현재 위치, 방향, 남은 빈칸 갯수, 이동횟수
		
		if (remainCount == 0) { // 남은 빈칸이 없을 경우 탐색 종료
			result = Math.min(result, moveCount);
			return;
		}

		int nr = r + dr[dir];
		int nc = c + dc[dir]; // 다음칸 위치

		if (isRange(nr, nc) == false || map[nr][nc] == '*') { // 다음칸에 갈 수 없을 경우

			for (int i = 0; i < 4; i++) {
				if (i == dir)
					continue;
				nr = r + dr[i];
				nc = c + dc[i]; // 방향을 바꾼 다음칸 위치

				if (isRange(nr, nc) == true && map[nr][nc] == '.') { // 다음 칸에 갈 수 있을 경우
					map[nr][nc] = '*';
					solve(nr, nc, i, remainCount - 1, moveCount + 1);
					map[nr][nc] = '.';
				}
			}

		} else { // 다음칸에 갈 수 있을 경우
			map[nr][nc] = '*';
			solve(nr, nc, dir, remainCount - 1, moveCount);
			map[nr][nc] = '.';
		}

	}

	public static boolean isRange(int r, int c) { // 범위 확인
		if (r < 0 || r >= N || c < 0 || c >= M)
			return false;
		return true;
	}

}
