import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 16956번 - 늑대와 양
 * 
 * 		https://www.acmicpc.net/problem/16956
 */


public class Main16956 {

	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static int R, C;

	public static boolean isRange(int y, int x) {
		if (y < 0 || y >= R || x < 0 || x >= C)
			return false;
		return true;

	} // 범위 확인 메소드

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		char[][] map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		} // 입력값 초기화

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'W') {
					for (int j2 = 0; j2 < 4; j2++) {
						int di = i + my[j2];
						int dj = j + mx[j2];
						
						if(isRange(di, dj) == false)
							continue;
						
						if(map[di][dj] == '.')
							map[di][dj] ='D';
						else if(map[di][dj] == 'S') {
							System.out.println(0);
							return;
						}
						
					}
				}
			}
		} // 늑대들을 울타리안에 가둬둠, 늑대와 양이 인접해있다면 울타리에 가둘수 없으므로 종료
		System.out.println(1);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
