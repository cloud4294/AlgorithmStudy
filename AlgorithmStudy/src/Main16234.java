
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 16234 - 인구 이동
 * 
 * 		https://www.acmicpc.net/problem/16234
 */

public class Main16234 {

	static int N, L, R;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static int result;

	static class point {
		int y;
		int x;

		public point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public static void solve(int[][] map, int level) {
		int[][] checked = new int[N + 2][N + 2]; // 단계를 넘어갈때마다 방문 체크 초기화

		Queue<point> queue = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		int area = 0;
		boolean flag = false;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (checked[i][j] == 0) { // 방문하지않았으면 
					int sum = 0;
					int count = 0;
					area++;
					queue.offer(new point(i, j));
					checked[i][j] = area;

					while (!queue.isEmpty()) {
						point now = queue.poll();
						sum += map[now.y][now.x];
						count++;
						for (int k = 0; k < 4; k++) {
							int ny = now.y + my[k];
							int nx = now.x + mx[k];
							if (map[ny][nx] == -1)
								continue;

							int diff = map[now.y][now.x] > map[ny][nx] ? map[now.y][now.x] - map[ny][nx]
									: map[ny][nx] - map[now.y][now.x];

							if (diff >= L && diff <= R && checked[ny][nx] == 0) { // 인접한 지역의 차가 L과 R사이이고 방문하지않았으면
								checked[ny][nx] = area; // 방문배열에 지역번호를 갱신
								queue.offer(new point(ny, nx)); //큐에 넣음
								flag = true;
							}

						}

					} // bfs시행

					list.add(sum / count); //bfs로 탐색된 지역에 갱신될 값을 리스트에 저장
				}
			}

		}

		if (flag == false) {
			result = level;
			return;
		} // 인구이동이 일어나지 않았다면 종료

		int[][] nextmap = new int[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				if (map[i][j] == -1)
					nextmap[i][j] = -1;
				else {
					nextmap[i][j] = list.get(checked[i][j] - 1); // 리스트의 지역번호에 해당하는 값으로 다음지역의 배열을 채움

				}
			}

		} // 다음지역의 배열 생성

		solve(nextmap, level + 1); // 단계를 증가하여 반복수행

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 2][N + 2];

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				map[i][j] = -1;
			}
		} 

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 데이터 초기화

		solve(map, 0);

		System.out.println(result);
	}

}
