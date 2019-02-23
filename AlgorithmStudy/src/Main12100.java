import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 		Baekjoon Online Judge 12100번 - 2048(Easy)
 * 
 * 		https://www.acmicpc.net/problem/12100
 */

public class Main12100 {

	static int N;
	static int[][] board;
	static int res;

	public static void solve(int count) {

		if (count == 5) {
			for (int j2 = 0; j2 < N; j2++) {
				for (int j = 0; j < N; j++) {
					if (board[j2][j] > res)
						res = board[j2][j];
				}
		
			}
			return;
		}
		// 탐색 횟수가 5가 되면 최대값을 갱신

		for (int i = 0; i < 4; i++) { 
			int[][] now = new int[N][N];
			for (int j = 0; j < N; j++) {
				now[j] = Arrays.copyOf(board[j], N);
			}
			move(i);
			solve(count + 1);
			for (int j = 0; j < N; j++) {
				board[j] = Arrays.copyOf(now[j], N);
			}
		} 
		// 4방향 모두 탐색후 탐색횟수를 증가시키고 재탐색
	}

	public static void move(int dir) {

		boolean[][] visit = new boolean[N][N]; // 블럭을 합쳤는지 확인 하는 배열

		if (dir == 0) {
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int next = -1;
					for (int j2 = i - 1; j2 >= 0; j2--) {

						if (board[j2][j] == 0) { //다음 위치가 0이면 이동가능하므로 next에 저장
							next = j2;
						} else if (board[j2][j] == board[i][j] && visit[j2][j] == false) {// 현재위치와 값이 같고 합치지않은 블럭이면 블럭을합침
							visit[j2][j] = true;
							board[j2][j] *= 2;
							board[i][j] = 0;
							break;
						} else // 0이나 현재 블럭과 같은 값이아니면 탐색 종료
							break;
					}

					if (next != -1) { // 이동가능한 위치가있으면 이동
						board[next][j] = board[i][j]; 
						board[i][j] = 0; 
					}
				}
			}

		} else if (dir == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 2; j >= 0; j--) {
					int next = -1;
					for (int j2 = j + 1; j2 <= N - 1; j2++) {
						if (board[i][j2] == 0) {
							next = j2;
						} else if (board[i][j2] == board[i][j] && visit[i][j2] == false) {
							visit[i][j2] = true;
							board[i][j2] *= 2;
							board[i][j] = 0;
							break;
						} else
							break;
					}

					if (next != -1) {
						board[i][next] = board[i][j];
						board[i][j] = 0;
					}
				}
			}

		} else if (dir == 2) {
			for (int i = N - 2; i >= 0; i--) {
				for (int j = 0; j < N; j++) {
					int next = -1;
					for (int j2 = i + 1; j2 <= N - 1; j2++) {
						if (board[j2][j] == 0) {
							next = j2;
						} else if (board[j2][j] == board[i][j] && visit[j2][j] == false) {
							visit[j2][j] = true;
							board[j2][j] *= 2;
							board[i][j] = 0;
							break;
						} else
							break;
					}

					if (next != -1) {
						board[next][j] = board[i][j];
						board[i][j] = 0;
					}
				}
			}

		} else if (dir == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					int next = -1;
					for (int j2 = j - 1; j2 >= 0; j2--) {
						if (board[i][j2] == 0) {
							next = j2;
						} else if (board[i][j2] == board[i][j] && visit[i][j2] == false) {
							visit[i][j2] = true;
							board[i][j2] *= 2;
							board[i][j] = 0;
							break;
						} else
							break;
					}

					if (next != -1) {
						board[i][next] = board[i][j];
						board[i][j] = 0;
					}
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		board = new int[N][N];
		res = 0;
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		} // 입력값 초기화

		solve(0);

		System.out.println(res);
	}

}
