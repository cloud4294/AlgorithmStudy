import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 2931번 - 가스관
 * 
 * 		https://www.acmicpc.net/problem/2931
 */

public class Main2931 {

	static int R, C;
	static char[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point {

		int r;
		int c;
		int dir;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public point(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");

		R = Integer.parseInt(size[0]);
		C = Integer.parseInt(size[1]);

		map = new char[R][C];
		visit = new boolean[R][C];

		point M = null;
		point Z = null;

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'M') {
					M = new point(i, j);
					visit[i][j] = true;
				} else if (map[i][j] == 'Z') {
					Z = new point(i, j);
					visit[i][j] = true;
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			int nr = M.r + dr[i];
			int nc = M.c + dc[i];
			if (isRange(nr, nc) == false)
				continue;
			if (map[nr][nc] != '.') {
				M.dir = i;
			}
		}

		for (int i = 0; i < 4; i++) {
			int nr = Z.r + dr[i];
			int nc = Z.c + dc[i];
			if (isRange(nr, nc) == false)
				continue;
			if (map[nr][nc] != '.') {
				Z.dir = i;
			}
		}

		M = findPoint(M);
		Z = findPoint(Z);
		findPipe(new point(M.r + dr[M.dir], M.c + dc[M.dir], M.dir),
				new point(Z.r + dr[Z.dir], Z.c + dc[Z.dir], Z.dir));
	}

	public static void findPipe(point M, point Z) {
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != '.' && visit[i][j] == false) {
					System.out.println((M.r+1) + " "+ (M.c+1) +" +");
					return;
				}
			}
		}
		
		if((M.dir == 0 && Z.dir == 3) || (M.dir == 3 && Z.dir == 0)) {
			System.out.println((M.r+1) + " "+ (M.c+1) +" 1");
		}else if((M.dir == 2 && Z.dir == 3) || (M.dir == 3 && Z.dir == 2)) {
			System.out.println((M.r+1) + " "+ (M.c+1) +" 2");
		}else if((M.dir == 2 && Z.dir == 1) || (M.dir == 1 && Z.dir == 2)) {
			System.out.println((M.r+1) + " "+ (M.c+1) +" 3");
		}else if((M.dir == 0 && Z.dir == 1) || (M.dir == 1 && Z.dir == 0)) {
			System.out.println((M.r+1) + " "+ (M.c+1) +" 4");
		}else if((M.dir == 0 && Z.dir == 2) || (M.dir == 2 && Z.dir == 0)) {
			System.out.println((M.r+1) + " "+ (M.c+1) +" |");
		}else if((M.dir == 1 && Z.dir == 3) || (M.dir == 3 && Z.dir == 1)) {
			System.out.println((M.r+1) + " "+ (M.c+1) +" -");
		}
		
	}

	public static point findPoint(point p) {

		int nr = p.r + dr[p.dir];
		int nc = p.c + dc[p.dir];

		visit[nr][nc] = true;

		if (map[nr][nc] == '-' || map[nr][nc] == '|' || map[nr][nc] == '+') {
			return findPoint(new point(nr, nc, p.dir));
		} else if (map[nr][nc] == '1') {
			if (p.dir == 0) {
				return findPoint(new point(nr, nc, 1));
			} else if (p.dir == 3) {
				return findPoint(new point(nr, nc, 2));
			}
		} else if (map[nr][nc] == '2') {
			if (p.dir == 2) {
				return findPoint(new point(nr, nc, 1));
			} else if (p.dir == 3) {
				return findPoint(new point(nr, nc, 0));
			}
		} else if (map[nr][nc] == '3') {
			if (p.dir == 2) {
				return findPoint(new point(nr, nc, 3));
			} else if (p.dir == 1) {
				return findPoint(new point(nr, nc, 0));
			}
		} else if (map[nr][nc] == '4') {
			if (p.dir == 0) {
				return findPoint(new point(nr, nc, 3));
			} else if (p.dir == 1) {
				return findPoint(new point(nr, nc, 2));
			}
		}

		return p;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr >= R || nc >= C)
			return false;
		return true;
	}

}
