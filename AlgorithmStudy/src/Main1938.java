import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 1938번 - 통나무 옮기기
 * 
 * 		https://www.acmicpc.net/problem/1938
 */


public class Main1938 {

	static char[][] map;
	static boolean[][][] checked;
	static point start;
	static point end;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static int result = 0;
	static char w = '\u0000';

	static class point {
		int y;
		int x;
		int dir;
		int count;

		public point(int y, int x, int dir, int count) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.count = count;
		}
	} // 좌표, 방향 , 현재지점까지 이동 횟수를 저장 

	public static void solve() {

		Queue<point> queue = new LinkedList<>();
		queue.offer(start);

		checked[start.y][start.x][start.dir] = true; 

		while (!queue.isEmpty()) { // bfs를 시행
			point now = queue.poll();

			if (now.y == end.y && now.x == end.x && now.dir == end.dir) {

				result = now.count;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = now.y + my[i];
				int nx = now.x + mx[i];

				if (map[ny][nx] == w) // 현재지점이 벽이면 다음루프로
					continue;

				if (now.dir == 0 && checked[ny][nx][now.dir] == false) { // 통나무가 세로 방향이고 현재지점을 방문하지 않았다면
					if (map[ny + 1][nx] == w || map[ny - 1][nx] == w) // 통나무 위나 아래로 벽이라면 다음 루프로
						continue;
					else if (map[ny][nx] != '1' && map[ny + 1][nx] != '1' && map[ny - 1][nx] != '1') { // 다음지점에 통나무가 '1'에 걸리지 않는다면 방문체크후 큐에넣음
						checked[ny][nx][now.dir] = true;
						queue.offer(new point(ny, nx, now.dir, now.count + 1));
					}

				} else if (now.dir == 1 && checked[ny][nx][now.dir] == false) { // 통나무가 가로 방향이고 현재지점을 방문하지 않았다면

					if (map[ny][nx + 1] == w || map[ny][nx - 1] == w)
						continue;
					else if (map[ny][nx] != '1' && map[ny][nx + 1] != '1' && map[ny][nx - 1] != '1') {
						checked[ny][nx][now.dir] = true;
						queue.offer(new point(ny, nx, now.dir, now.count + 1));
					}

				}

			}

			int y = now.y;
			int x = now.x;
			int dir = (now.dir + 1) % 2;
			if (checked[y][x][dir] == false) { // 통나무를 회전한뒤 현재지점을 방문하지 않았다면
				if (map[y + 1][x + 1] == w || map[y + 1][x] == w || map[y + 1][x - 1] == w || map[y][x + 1] == w
						|| map[y][x - 1] == w || map[y - 1][x + 1] == w || map[y - 1][x] == w || map[y - 1][x - 1] == w
						|| map[y][x] == w) { // 통나무회전시 벽에 걸리면 다음루프로 
					checked[y][x][dir] = true;
				} else if (map[y + 1][x + 1] != '1' && map[y + 1][x] != '1' && map[y + 1][x - 1] != '1'
						&& map[y][x + 1] != '1' && map[y][x - 1] != '1' && map[y - 1][x + 1] != '1'
						&& map[y - 1][x] != '1' && map[y - 1][x - 1] != '1' && map[y][x] != '1') { // 통나무 회전시 '1'에 걸리지않는다면 방문체크후 큐에 넣음
					checked[y][x][dir] = true;
					queue.offer(new point(y, x, dir, now.count + 1));
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N + 2][N + 2];
		checked = new boolean[N + 2][N + 2][2]; // 좌표,방향을 방문체크
		
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = line.charAt(j - 1);
				if (map[i][j] == 'B' && start == null) {// 현재지점에서 위쪽이나 왼쪽에 'B'가 있으면 현재지점이 중심이 되고 방향이 정해짐 
					if (map[i - 1][j] == 'B') { 
						start = new point(i, j, 0, 0);
					} else if (map[i][j - 1] == 'B') {
						start = new point(i, j, 1, 0);
					}
				}

				if (map[i][j] == 'E' && end == null) {
					if (map[i - 1][j] == 'E') {
						end = new point(i, j, 0, 0);
					} else if (map[i][j - 1] == 'E') {
						end = new point(i, j, 1, 0);
					}
				}
			}
		} // 입력값 초기화 

		solve();

		System.out.println(result);
	}

}
