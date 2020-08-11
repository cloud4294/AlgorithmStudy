import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 		Baekjoon Online Judge 16929번 - Two Dots
 * 
 * 		https://www.acmicpc.net/problem/16929
 */

public class Main16929 {

	static int N, M;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point {
		int r;
		int c;
		int count;

		public point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");

		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);

		map = new char[N][M];
		

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(findCycle(i, j) == true) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
	}

	public static boolean findCycle(int r, int c) {
		
		boolean[][] visit = new boolean[N][M];
		Stack<point> stack = new Stack<>();

		stack.push(new point(r, c, 1));

		while (!stack.isEmpty()) {

			point now = stack.pop();
			visit[now.r][now.c] = true;
			
			for (int i = 0; i < 4; i++) {
		
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
				if (isRange(nr, nc) == false)
					continue;
				
				if(now.count >= 4 && (nr == r && nc == c))
					return true;
				
				
				if(map[nr][nc] == map[now.r][now.c] && visit[nr][nc] == false) {
					stack.push(new point(nr,nc,now.count+1));
				}
			}
		}
		return false;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= M)
			return false;
		return true;
	}

}
