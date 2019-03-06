import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 		Baekjoon Online Judge 16954번 - 움직이는 미로 탈출
 * 
 * 		https://www.acmicpc.net/problem/16954
 */

public class Main16954 {

	static char[][] map;

	static int[] my = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
	static int[] mx = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[8][8];
		for (int i = 0; i < 8; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = line.charAt(j);
			}
		} // 입력값 초기화

		res = 0;
		solve(7, 0, 0);
		System.out.println(res);

	}

	public static void fall() {
		for (int i = 7; i > 0; i--) {
			for (int j = 0; j < 8; j++) {
				map[i][j] = map[i - 1][j];
			}
		}
		for (int i = 0; i < 8; i++) {
			map[0][i] = '.';
		}
	} // 떨어지는 벽 처리 

	public static boolean isRange(int y, int x) {

		if (y < 0 || y >= 8 || x < 0 || x >= 8)
			return false;
		return true;
	} // 범위 처리 

	public static void solve(int y, int x, int count) {

		if(res == 0) { // 	
			char[][] now = new char[8][8];
			
			if (count > 8) { //8초동안 벽에 맞지않았다면 벽이 더이상떨어지지않으므로 무조건 도달할수 있다. 
				res = 1;
				return;
			}
			
			for (int i = 0; i < 8; i++) {
				now[i] = Arrays.copyOf(map[i], 8);
			}
			
			fall();
			
			for (int i = 0; i < 9; i++) {
				int ny = y + my[i];
				int nx = x + mx[i];
				
				if (isRange(ny, nx) == false)
					continue;
				
				if (now[ny][nx] != '#' && map[ny][nx] != '#') { //현재 벽이있지 않는 곳과 다음에 벽이떨어지지 않는곳이면 
					solve(ny, nx, count + 1);
				}
				
			}
			
			for (int i = 0; i < 8; i++) {
				map[i] = Arrays.copyOf(now[i], 8);
			}
		}

	}

}
