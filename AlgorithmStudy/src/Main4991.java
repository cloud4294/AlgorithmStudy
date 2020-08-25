import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 4991번 - 로봇 청소기
 * 
 * 		https://www.acmicpc.net/problem/4991
 */

public class Main4991 {

	static int R, C ,result;
	static int[][] map;
	static int[][] dist;
	static List<point> pollution;
	static List<Integer> order;
	static point start;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class point {

		int r;
		int c;
		int length;

		public point(int r, int c, int length) {
			super();
			this.r = r;
			this.c = c;
			this.length = length;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String[] size = br.readLine().split(" ");

			C = Integer.parseInt(size[0]);
			R = Integer.parseInt(size[1]);
			
			if(C == 0 && R == 0)
				return;
			result = Integer.MAX_VALUE;
			map = new int[R][C];
			pollution = new ArrayList<>();
			order = new ArrayList<>();
			start = null;
			int count = 2;
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					char now = line.charAt(j);
					if (now == '*') {
						map[i][j] = count++;
						pollution.add(new point(i, j, 0));
					} else if (now == 'o') {
						map[i][j] = 1;
						start = new point(i, j, 0);
					} else if (now == '.')
						map[i][j] = 0;
					else if (now == 'x')
						map[i][j] = -1;
				}
			}

			dist = new int[pollution.size() + 2][pollution.size() + 2];

			BFS(start);
			
			for (int i = 0; i < pollution.size(); i++) {
				BFS(pollution.get(i));
			}
			
			DFS();
			
			if(result == Integer.MAX_VALUE)
				System.out.println(-1);
			else 
				System.out.println(result);
		}
	}

	public static void DFS() {
		
		
		if(order.size() == pollution.size()) {
			int sum = 0;
			int src = 1;
			for (int i = 0; i < order.size(); i++) {
				if(dist[src][order.get(i)+2] == 0)
					return;
				sum += dist[src][order.get(i)+2];
				src = order.get(i) + 2;
			}
			
			result = Math.min(result, sum);
			
			return;
		}
		for (int i = 0; i < pollution.size(); i++) {
			if(!order.contains(i)) {
				order.add(Integer.valueOf(i));
				DFS();
				order.remove(Integer.valueOf(i));
			}
		}
	}

	public static void BFS(point start) {

		boolean[][] visit = new boolean[R][C];
		visit[start.r][start.c] = true;

		Queue<point> queue = new LinkedList<>();
		queue.offer(start);

		while (!queue.isEmpty()) {

			point now = queue.poll();

			if (map[now.r][now.c] > 0) {

				dist[map[start.r][start.c]][map[now.r][now.c]] = now.length;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;

				if (map[nr][nc] >= 0 && visit[nr][nc] == false) {
					visit[nr][nc] = true;
					queue.offer(new point(nr, nc, now.length + 1));
				}
			}
		}

	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= R || nc < 0 || nc >= C)
			return false;
		return true;
	}

}
