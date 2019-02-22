import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 9205번 - 맥주 마시면서 걸어가기
 * 
 * 		https://www.acmicpc.net/problem/9205
 */

public class Main9205 {

	static class point {
		int y;
		int x;

		public point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			point[] map = new point[n + 2];
			boolean[] visit = new boolean[n + 2];
			for (int j = 0; j < n + 2; j++) {
				String[] input = br.readLine().split(" ");
				map[j] = new point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			}// 시작점, 편의점 위치,도착점  초기화

			visit[0] = true;
			Queue<point> queue = new LinkedList<>();
			queue.offer(map[0]);

			while (!queue.isEmpty()) {
				point now = queue.poll();

				for (int j = 1; j < n + 2; j++) {// 50미터에 한병씩 20병있고 편의점을 방문할때마다 20병 모두 채울수 있으므로 현재지점에서 1000미터 이내인 편의점의 위치를 큐에 넣어 탐색
					if (visit[j] == false && Math.abs(now.y - map[j].y) + Math.abs(now.x - map[j].x) <= 1000) {
						visit[j] = true;
						queue.offer(map[j]);
					}
				}
			}

			System.out.println(visit[n + 1] == true ? "happy" : "sad");

		}

	}

}
