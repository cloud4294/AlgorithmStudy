import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 3109번 - 빵집
 * 
 * 		https://www.acmicpc.net/problem/3109
 */

public class Main3109 {

	static int R, C;
	static char[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");

		R = Integer.parseInt(size[0]);
		C = Integer.parseInt(size[1]);
		map = new char[R][C];
		visit = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		
		System.out.println(solve());
		
	}

	public static int solve() {

		int result = 0;
		for (int i = 0; i < R; i++) {

			if(buildPipe(i, 0) == true)
				result++;
			
		}
		
		return result;
	}

	public static boolean buildPipe(int row, int col) { // 백트래킹

		if (col == C - 1) 
			return true;

		for (int i = row - 1; i <= row + 1; i++) { // 최대한 위에서부터 파이프를 설치할 수 있도록 그리디 탐색 

			if (isRange(i, col + 1) == false || map[i][col + 1] == 'x')
				continue;
			else if (visit[i][col + 1] == false) { 
				if (buildPipe(i, col + 1) == true) { // 파이프가 끝까지 도달했을경우 경로를 기록
					return visit[i][col + 1] = true; 
				}else // 도달하지 않았을경우 재방문하지 않도록 벽으로 변경
					map[i][col+1] = 'x';
			}

		}

		return false;
	}

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return false;
		return true;
	}

}
