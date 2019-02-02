import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2589 {

	static int N, M;
	static char[][] map;
	static boolean[][] checked;
	static int res;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };

	static class dot {
		int y;
		int x;
		int length;

		public dot(int y, int x, int length) {
			super();
			this.y = y;
			this.x = x;
			this.length = length;
		}
	} // 좌표와 길이를 저장할 클래스 

	public static void bfs(int i, int j) {
		checked = new boolean[N + 2][M + 2];
		Queue<dot> queue = new LinkedList<>();

		dot start = new dot(i, j, 0);
		queue.offer(start);
		int l = 0;

		while (!queue.isEmpty()) { // bfs시행
			dot now = queue.poll();

			if (now.length >= l) {
				l = now.length;
			}

			for (int k = 0; k < 4; k++) {
				int ny = now.y + my[k];
				int nx = now.x + mx[k];

				if (map[ny][nx] != 'L')
					continue;

				if (checked[ny][nx] == false) {
					checked[ny][nx] = true;
					queue.offer(new dot(ny, nx, now.length + 1));
				}
			}
		}

		if (l > res) { // bfs종료시 길이가 현재 길이보다 길다면 갱신
			res = l;
		}

	} 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rc = br.readLine().split(" ");
		N = Integer.parseInt(rc[0]);
		M = Integer.parseInt(rc[1]);

		map = new char[N + 2][M + 2];

		res = 0;

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j - 1);
			}
		} // 입력값 초기화

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 'L')
					bfs(i, j);
			}
		} // L값을 가진 모든 지점에 대해 bfs시행
		System.out.println(res);

	}

}
