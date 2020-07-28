import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 18808번 - 스티커 붙이기 
 * 
 * 		https://www.acmicpc.net/problem/18808
 */

public class Main18808 {

	static int N, M;
	static int[][] notebook;
	static Queue<int[][]> stickers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		notebook = new int[N][M];
		stickers = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int[][] sticker = new int[r][c];

			for (int j = 0; j < r; j++) {
				st = new StringTokenizer(br.readLine());
				for (int l = 0; l < c; l++) {
					sticker[j][l] = Integer.parseInt(st.nextToken());
				}
			} // 붙일 스티커 초기화

			stickers.offer(sticker); 
		} 

		solve();
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(notebook[i][j] == 1)
					count++;
			}
		}
		
		System.out.println(count);

	}

	public static void solve() {

		while (!stickers.isEmpty()) {
			int[][] now = stickers.poll();

			for (int k = 0; k < 4; k++) {
				int nextR = -1;
				int nextC = -1;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (check(i, j, now) == true) { 
							nextR = i;
							nextC = j;
							break;
						}
					}
					if (nextR != -1 && nextC != -1)
						break;
				}

				if (nextR != -1 && nextC != -1) { // 스티커를 붙일수  있으면 
					for (int i = 0; i < now.length; i++) { // 스티커를 붙이고 다음으로
						for (int j = 0; j < now[0].length; j++) {
							if(notebook[nextR + i][nextC + j] == 0)
								notebook[nextR + i][nextC + j] = now[i][j];
						}
					}
					break;
				}

				now = turn(now);
			}
		}

	}

	public static int[][] turn(int[][] now) { // 스티커를 회전

		int[][] next = new int[now[0].length][now.length];

		for (int i = 0; i < now.length; i++) {
			for (int j = 0; j < now[0].length; j++) {
				next[j][(now.length - 1) - i] = now[i][j];
			}
		}

		return next;
	}

	public static boolean check(int i, int j, int[][] now) { // 노트북에 스티커를 붙일수 있는지 확인

		int nowR = 0;
		int nowC = 0;

		if (isRange(i + now.length - 1, j + now[0].length - 1) == false)
			return false;
		for (int k = i; k < i + now.length; k++) {
			nowC = 0;
			for (int l = j; l < j + now[0].length; l++) {
				if (now[nowR][nowC] == 1 && notebook[k][l] == 1)
					return false;

				nowC++;
			}
			nowR++;
		}

		return true;
	}

	public static boolean isRange(int nowR, int nowC) { // 범위 확인
		if (nowR < 0 || nowC < 0 || nowR >= N || nowC >= M)
			return false;
		return true;
	}

}
