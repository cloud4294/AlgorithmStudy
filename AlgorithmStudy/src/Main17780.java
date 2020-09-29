import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 		Baekjoon Online Judge 17780 - 새로운 게임
 * 
 * 		https://www.acmicpc.net/problem/17780
 */
public class Main17780 {

	static int N, K, result;
	static int[][] map;
	static horse[] horses;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Deque<Integer>[][] pos;
	static Queue<Integer> queue;

	static class horse {

		int r;
		int c;
		int index;
		int dir;

		public horse(int r, int c, int index, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.index = index;
			this.dir = dir;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		horses = new horse[K];
		pos = new ArrayDeque[N][N];
		queue = new LinkedList<>();
		result = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				pos[i][j] = new ArrayDeque<Integer>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			if (dir == 1)
				dir = 1;
			else if (dir == 2)
				dir = 3;
			else if (dir == 3)
				dir = 0;
			else if (dir == 4)
				dir = 2;

			horses[i] = new horse(r - 1, c - 1, i, dir);
			pos[r - 1][c - 1].add(i);
		}

		solve(1);

		System.out.println(result);

	}

	public static void solve(int turn) {

		if (turn >= 1000) {
			result = -1;
			return;
		}

		for (int i = 0; i < K; i++) {
			horse now = horses[i];

			if (!pos[now.r][now.c].isEmpty() && pos[now.r][now.c].peek() == i) {
				int nr = now.r + dr[now.dir];
				int nc = now.c + dc[now.dir];
				if (isRange(nr, nc) == false || map[nr][nc] == 2) { // 범위를 벗어나거나 파란블럭인 경우
					int ndir = (now.dir + 2) % 4;
					nr = now.r + dr[ndir];
					nc = now.c + dc[ndir];

					if (isRange(nr, nc) == false || map[nr][nc] == 2) { // 다시 범위를 벗어나거나 파란블럭인 경우
						horses[i].dir = ndir;
					} else if (map[nr][nc] == 0) {
						horses[i].dir = ndir;
						move(now.r,now.c,nr,nc,true);
					} else if (map[nr][nc] == 1) {
						horses[i].dir = ndir;
						move(now.r,now.c,nr,nc,false);
					}

				} else if (map[nr][nc] == 0) { // 흰블럭 이동
					move(now.r,now.c,nr,nc,true);
				} else if (map[nr][nc] == 1) { // 빨간블럭 이동
					move(now.r,now.c,nr,nc,false);
				}

				if (isRange(nr, nc) == true) {
					if (pos[nr][nc].size() >= 4) {
						result = turn;
						return;
					}
				}

			}
		}

		solve(turn + 1);
	}
	
	public static void move(int r,int c,int nr,int nc, boolean flag) { // 이동 
		
		if(flag == true) { // 그대로 이동하는 경우
			while (!pos[r][c].isEmpty()) {
				int next = pos[r][c].poll();
				queue.add(next);
				pos[nr][nc].add(next);
			}
			while (!queue.isEmpty()) {
				int next = queue.poll();
				horses[next].r = nr;
				horses[next].c = nc;
			}
		}else { // 순서가 뒤집혀서 이동하는 경우
			while (!pos[r][c].isEmpty()) {
				int next = pos[r][c].removeLast();
				queue.add(next);
				pos[nr][nc].add(next);
			}
			while (!queue.isEmpty()) {
				int next = queue.poll();
				horses[next].r = nr;
				horses[next].c = nc;
			}
		}
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			return false;
		return true;
	}

}
