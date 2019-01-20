import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 13460 - 구슬 탈출 2
 * 
 * 		https://www.acmicpc.net/problem/13460
 */

public class Main13460 {

	static int N, M;
	static char[][] map;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static boolean[][][][] checked;
	static int dy,dx;
	static int ans = -1;

	static class ball {
		int by;
		int bx;
		int ry;
		int rx;
		int count;

		public ball(int ry, int rx, int by, int bx, int count) {
			super();
			this.by = by;
			this.bx = bx;
			this.ry = ry;
			this.rx = rx;
			this.count = count;
		}

	} // 빨간 구슬과 파란구슬의 위치, 현재까지 이동횟수를 저장할 클래스 

	public static void solve(int ry, int rx, int by, int bx) {
		ball start = new ball(ry, rx, by, bx, 0);

		Queue<ball> queue = new LinkedList<>();
		queue.offer(start);
		checked[ry][rx][by][bx] = true;

		while (!queue.isEmpty()) {
			ball now = queue.poll();
			if (now.count > 10 ) // 이동횟수가 10번 이상 넘어가면 종료 
	            break;
	        if (now.rx == dx && now.ry == dy) {
	            ans = now.count;
	            break;
	        } // 빨간 구슬이 목적지에 도달하면 결과를 ans에 저장하고 종료

			for (int i = 0; i < 4; i++) {

				int[] nb = move(now.by, now.bx, i); //파란구슬의 각방향별 다음위치
				int[] nr = move(now.ry, now.rx, i); //빨간구슬의 각방향별 다음위치

				if (nb[0] == dy && nb[1] == dx) //파란 구슬이 목적지에 도달했다면 그경로는 사용할수 없으므로 다음 경로 탐색 
		                continue;
				
				if (nb[0] == nr[0] && nb[1] == nr[1]) {
					if (i == 0) {
						if (now.ry < now.by)
							nb[0]++;
						else
							nr[0]++;
					} else if (i == 1) {
						if (now.rx < now.bx)
							nr[1]--;
						else
							nb[1]--;
					} else if (i == 2) {
						if (now.ry < now.by)
							nr[0]--;
						else
							nb[0]--;
					} else if (i == 3) {
						if (now.rx < now.bx)
							nb[1]++;
						else
							nr[1]++;
					}

				} // 파란구슬과 빨간 구슬이 겹치는 경우 이전위치를 비교하여 겹치지 않도록 한다 

				if (checked[nr[0]][nr[1]][nb[0]][nb[1]] == false) {
					checked[nr[0]][nr[1]][nb[0]][nb[1]] = true;
					queue.offer(new ball(nr[0],nr[1],nb[0],nb[1],now.count+1));
				} // 다음위치가 방문한 적이 없다면 큐에삽입 

			}
		}

	}

	public static int[] move(int y, int x, int d) {
		int[] next = new int[2];
		while (true) {
			x += mx[d];
			y += my[d];
			if (map[y][x] == '#') {
				x -= mx[d];
				y -= my[d];
				break;
			} else if (map[y][x] == 'O')
				break;
		}

		next[0] = y;
		next[1] = x;

		return next;
	} // 한칸 씩 탐색하여 다음위치가 벽이면 한칸 전위치를 리턴, 목적지이면 현재위치를 리턴 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		int ry = 0, rx = 0, by = 0, bx = 0;
		map = new char[N][M];
		checked = new boolean[N][M][N][M];
		for (int i = 0; i < N; i++) {
			String Line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Line.charAt(j);
				if (map[i][j] == 'B') {
					by = i;
					bx = j;
				}
				if (map[i][j] == 'R') {
					ry = i;
					rx = j;
				}
				
				if (map[i][j] == 'O') {
					dy = i;
					dx = j;
				}

			}
		} // 지형 정보 초기화 

		solve(ry, rx, by, bx);
		
		System.out.println(ans);

	}

}
