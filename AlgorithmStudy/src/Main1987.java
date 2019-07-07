import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *		Baekjoon Online Judge 1987번 - 알파벳
 * 
 * 		https://www.acmicpc.net/problem/1987
 */

public class Main1987 {

	static int R, C;
	static String[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int max;

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return false;
		return true;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		R = Integer.parseInt(size[0]);
		C = Integer.parseInt(size[1]);
		map = new String[R][C];
		max = 0;
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.substring(j, j+1);
			}
		}
		String start = map[0][0];
		solve(0, 0, start);
		System.out.println(max);

	}

	public static void solve(int r, int c, String line) { // DFS 시행

		max = Math.max(max, line.length());
		
		for (int i = 0; i < 4; i++) {
			int nr = dr[i] + r;
			int nc = dc[i] + c;
			
			if(isRange(nr, nc) == false)
				continue;
			
			if(!line.contains(map[nr][nc])) {
				solve(nr,nc,line+map[nr][nc]);
			}
		}

	}

}
