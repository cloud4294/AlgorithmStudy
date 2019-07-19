import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 6593 - 상범 빌딩
 * 
 * 		https://www.acmicpc.net/problem/6593
 */

public class Main6593 {

	static int L, R, C;
	static char[][][] map;
	static boolean[][][] visit;
	static point start, end;
	static int[] dr = { -1, 0, 1, 0, 0, 0 };
	static int[] dc = { 0, 1, 0, -1, 0, 0 };
	static int[] dl = { 0, 0, 0, 0, 1, -1 };

	static class point {
		int l;
		int r;
		int c;
		int count;

		public point(int l, int r, int c, int count) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}

	public static boolean isRange(int l, int r, int c) {
		if (l < 0 || l >= L || r < 0 || r >= R || c < 0 || c >= C)
			return false;
		return true;

	}

	public static void BFS() {
		
		Queue<point> queue = new LinkedList<>();
		queue.add(start);
		visit[start.l][start.r][start.c] = true;
		
		while(!queue.isEmpty()) {
			point now = queue.poll();
			if(now.l == end.l && now.r == end.r && now.c == end.c){
				System.out.println("Escaped in "+now.count+" minute(s).");
				return;
			}
			
			
			for (int i = 0; i < 6; i++) {
				int nl = now.l +dl[i];
				int nr = now.r +dr[i];
				int nc = now.c +dc[i];
				
				if(isRange(nl, nr, nc) == false)
					continue;
		
				
				if(map[nl][nr][nc] == '.' && visit[nl][nr][nc] == false) {
					visit[nl][nr][nc] = true;
					
					queue.offer(new point(nl,nr,nc,now.count+1));
				}
					
				
			}
			
			
		}
		System.out.println("Trapped!");
		
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String[] size = br.readLine().split(" ");
			L = Integer.parseInt(size[0]);
			R = Integer.parseInt(size[1]);
			C = Integer.parseInt(size[2]);

			if (L == 0 && R == 0 && C == 0)
				return;

			map = new char[L][R][C];
			visit = new boolean[L][R][C];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String line = br.readLine();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = line.charAt(k);
						if (map[i][j][k] == 'S') {
							start = new point(i, j, k, 0);
							map[i][j][k] = '.';
						}
						if (map[i][j][k] == 'E') {
							end = new point(i, j, k, 0);
							map[i][j][k] = '.';							
						}
					}
				}
				br.readLine();
			}

			BFS();

		}

	}

}
