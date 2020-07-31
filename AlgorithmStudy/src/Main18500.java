import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 18500번 - 미네랄2
 * 
 * 		https://www.acmicpc.net/problem/18500
 */

public class Main18500 {

	static int R, C;
	static char[][] map;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };

	static class point {
		int r;
		int c;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");

		R = Integer.parseInt(size[0]);
		C = Integer.parseInt(size[1]);
		map = new char[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			String line = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = line.charAt(j - 1);
			}
		}
		

		int N = Integer.parseInt(br.readLine());
		String[] com = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			solve(R + 1 - Integer.parseInt(com[i]), i % 2);
		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		

	}

	public static void solve(int row, int flag) { // 해당 지점 폭파 

		int col = -1;

		if (flag == 0) {
			for (int i = 1; i <= C; i++) {
				if (map[row][i] == 'x') {
					col = i;
					break;
				}
			}
		} else if (flag == 1) {
			for (int i = C; i > 0; i--) {
				if (map[row][i] == 'x') {
					col = i;
					break;
				}
			}

		}

		if (col != -1) {
			map[row][col] = '.';
			
			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] == 'x')
					findGround(nr, nc);
			}
		}
	}

	public static void findGround(int r, int c) { // 떠있는 클러스터 탐색

		boolean[][] visit = new boolean[R + 1][C + 1];
		Queue<point> queue = new LinkedList<>();

		visit[r][c] = true;
		point start = new point(r,c);
		queue.offer(start);
		List<point> list = new ArrayList<>();
		list.add(start);
		while (!queue.isEmpty()) {

			point now = queue.poll();

			if (now.r == R) {
				return;
			}

			for (int i = 0; i < 4; i++) {

				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] == 'x' && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					point next = new point(nr,nc);
					list.add(next);
					queue.offer(next);
				}

			}
		}
		dropBlock(list);
	}

	public static void dropBlock(List<point> list) { // 떠있는 클러스터 내림 
		
		List<point> bottom = new ArrayList<>();
		for (point p:list) {
			if(map[p.r+1][p.c] =='.') {
				bottom.add(p);
			}
		}
		int i = 1;
		while(true) {
			for (point p : bottom) {
				if(p.r+i > R || map[p.r+i][p.c] != '.')
					return;
			}
			
			for (point p : list) 
				map[p.r+(i-1)][p.c] = '.';
			for (point p : list) 
				map[p.r+i][p.c] = 'x';		
			i++;
		}
				
	}



	public static boolean isRange(int nr, int nc) { // 범위확인 
		if (nr <= 0 || nc <= 0 || nr > R || nc > C)
			return false;
		return true;
	}

}
