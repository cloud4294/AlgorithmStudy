import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * 		Baekjoon Online Judge 14499 - 주사위 굴리기
 * 
 * 		https://www.acmicpc.net/problem/14499
 */
public class Main14499 {

	static int N, M, x, y, K;
	static int[] dice;
	static int[][] map;
	static int[] my = { 1, -1, 0, 0 };
	static int[] mx = { 0, 0, -1, 1 };

	public static boolean isRange(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;
		return true;

	}

	public static void solve(int com) {

		x += mx[com - 1];
		y += my[com - 1];
		
		if(isRange(x, y) == false) {
			x -= mx[com - 1];
			y -= my[com - 1];
			return;
			
		}
		
		if(com == 1) {
			int temp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = temp;
			
		}else if(com == 2) {
			int temp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
		}else if(com == 3) {
			int temp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;		
		}else if(com == 4) {
			int temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
		}
		
		if(map[x][y] != 0) {
			dice[6] = map[x][y];
			map[x][y] = 0;
			
		}
		else {
			map[x][y] = dice[6];		
		}
		
		System.out.println(dice[1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		x = Integer.parseInt(input[2]);
		y = Integer.parseInt(input[3]);
		K = Integer.parseInt(input[4]);

		map = new int[N][M];
		dice = new int[7];

		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}

		input = br.readLine().split(" ");
		for (int i = 0; i < K; i++) {
			int com = Integer.parseInt(input[i]);

			solve(com);

		}

	}

}
