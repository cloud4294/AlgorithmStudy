import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 2580번 - 스도쿠
 * 
 * 		https://www.acmicpc.net/problem/2580
 * 
 */

public class Main2580 {

	static List<point> list;
	static boolean flag;

	static class point {
		int y;
		int x;

		public point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public static void solve(int[][] map, int length) {

		if(flag == false) { // 스도쿠가 완성 되지 않았다면 
			
			if (length == list.size()) { // 스도쿠의 빈칸을 채운 갯수가 리스트의 갯수와 같다면 전체 출력 
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						System.out.print(map[i][j] +" ");
					}
					System.out.println();
				}
				flag = true; // 스도쿠 완성
				return;
			} else {
				point now = list.get(length); // 현재 위치 
				int[] check = new int[10];
				int y = now.y;
				int x = now.x;
				int bx = 0;  // 3*3 블럭으로나누었을때 블럭의 좌성단 좌표 
				int by = 0;
				
				for (int i = 0; i < 9; i++) {
					if (check[map[y][i]] == 0) {
						check[map[y][i]]++;
					}
				} // 가로줄 체크 
				for (int i = 0; i < 9; i++) {
					if (check[map[i][x]] == 0) {
						check[map[i][x]]++;
					}
				} // 세로줄 체크 
				if (y >= 6)
					by = 6;
				else if (y >= 3)
					by = 3;
				
				if (x >= 6)
					bx = 6;
				else if (x >= 3)
					bx = 3;
				// 블럭 좌상단 위치 확인 
				
				for (int i = by; i < by + 3; i++) {
					for (int j = bx; j < bx + 3; j++) {
						if (check[map[i][j]] == 0) {
							check[map[i][j]]++;
						}
					}
				} // 블럭 체크 
				for (int i = 1; i <= 9; i++) {
					if (check[i] == 0) {
						int[][] nextmap = new int[9][9];
						for (int j = 0; j < 9; j++) {
							nextmap[j] = Arrays.copyOf(map[j], 9);
						}
						nextmap[y][x] = i;
						if(flag == false)
							solve(nextmap, length + 1) ;
						
					}
				} // 1 부터 9까지 체크되지 않은 수를 배열에 채운뒤 다음 위치를 채우기위한 재귀 호출 시행
				
			}
		}
		
	
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[9][9];
		list = new ArrayList<>();
		flag = false;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 0) {
					list.add(new point(i, j));
				}
			}
		} // 스도쿠 정보를 초기화하며 0인 부분의 위치를 리스트에 넣음 
		solve(map, 0);

	}

}
