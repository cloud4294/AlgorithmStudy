
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 16955번 - 오목, 이길 수 있을까?
 * 
 * 		https://www.acmicpc.net/problem/16955
 */

public class Main16955 {

	static char[][] map;
	static int[] my = { 0, 1, 1, 1 };
	static int[] mx = { 1, 1, 0, -1 };
	static int res;

	public static void solve() {

		for (int i = 0; i < 10; i++) { // 비어있는 모든 칸에 구사과의 돌을 놓고 확인 
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == '.' && res == 0) {
					map[i][j] = 'X';
					check();
					map[i][j] = '.';
				}
			}
		}
	} 

	public static boolean isRange(int y, int x) { // 범위 확인 메소드 
		if (y < 0 || y >= 10 || x < 0 || x >= 10)
			return false;
		return true;

	}

	public static void check() { 

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 'X') { // 첫번째 돌
					for (int j2 = 0; j2 < 4; j2++) { // 오목이 만들어질수 있는 모양이 4가지이므로 4방향 검사
						if(find(i,j,j2,0) == true) // 승리시 종료 
							return;
					}

				}
			}
		}

	}

	public static boolean find(int y,int x,int dir,int count) {
		
		if(count == 4) { // 돌의 갯수가 5개가 되면 승리 
			res = 1;
			return true;
		}

		int ny = y + my[dir];
		int nx = x + mx[dir];
			
		if(isRange(ny,nx) == false)
			return false;
		
		if(map[ny][nx] == 'X') // 다음 돌이 있으면 
			return find(ny,nx,dir,count+1);
		else
			return false;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[10][10];
		res = 0;
		for (int i = 0; i < 10; i++) {
			String line = br.readLine();
			for (int j = 0; j < 10; j++) {
				map[i][j] = line.charAt(j);
			}
		} // 입력값 초기화 

		solve();

		System.out.println(res);

	}

}
