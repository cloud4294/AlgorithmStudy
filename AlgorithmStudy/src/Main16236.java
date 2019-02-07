import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 16236번 - 아기 상어
 * 
 * 		https://www.acmicpc.net/problem/16236
 */

public class Main16236 {

	static int N;
	static int[][] map;

	static int[] my = { -1, 0, 0, 1 };
	static int[] mx = { 0, -1, 1, 0 };
	static int max = 0;

	static class pos implements Comparable<pos> {
		int y;
		int x;
		int dis;

		public pos(int y, int x, int dis) {
			super();
			this.y = y;
			this.x = x;
			this.dis = dis;
		}

		@Override
		public int compareTo(pos o) { // 거리,y,x를 기준으로 정렬하기 위해 사용

			if (dis != o.dis)
				return Integer.compare(dis, o.dis);
			else {
				if (y != o.y)
					return Integer.compare(y, o.y);
				else
					return Integer.compare(x, o.x);
			}
		}
	}// 좌표,거리를 저장하는 클래스

	public static void solve(pos shark, int sharksize, int count, int time) {
		boolean[][] checked = new boolean[N + 2][N + 2];

		Queue<pos> queue = new LinkedList<>();
		queue.offer(shark);
		checked[shark.y][shark.x] = true;

		if (time > max)
			max = time;

		ArrayList<pos> list = new ArrayList<>(); // 현재위치에서 먹을수 있는 대상을 리스트에 저장
		while (!queue.isEmpty()) { // bfs시행
			pos now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = now.y + my[i];
				int nx = now.x + mx[i];

				if (map[ny][nx] == -1) // 다음지점이 벽이면 continue
					continue;

				if (checked[ny][nx] == false) { // 다음지점이 방문하지 않았으면
					if (map[ny][nx] == 0 || map[ny][nx] == sharksize) { // 다음지점이 0이거나 현재 상어의 크기와 같으면 큐에 넣음
						checked[ny][nx] = true;
						queue.offer(new pos(ny, nx, now.dis + 1));
					} else if (map[ny][nx] < sharksize) { // 다음지점이 상어의 크기 보다 작으면 리스트에 넣음
						list.add(new pos(ny, nx, time + now.dis + 1));
					}

				}

			}

		}
		if (list.size() > 0) {

			Collections.sort(list); // 먹을 수 있는 대상을 거리 순으로 정렬,거리가 같으면 y값을 기준으로 정렬, y값이 같으면 x값 기준으로 정렬
			pos next = list.get(0); // 정렬된 리스트의 첫번째 대상을 먹음
			map[next.y][next.x] = 0;
			if (count + 1 == sharksize) { // 상어의 크기 증가
				solve(new pos(next.y, next.x, 0), sharksize + 1, 0, next.dis);
			} else {
				solve(new pos(next.y, next.x, 0), sharksize, count + 1, next.dis);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2];
		StringTokenizer st;
		pos shark = null;

		for (int i = 0; i < N + 2; i++) {
			Arrays.fill(map[i], -1);
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new pos(i, j, 0);
					map[i][j] = 0;
				}
			}

		} // 입력값 초기화

		solve(shark, 2, 0, 0);

		System.out.println(max);
	}

}
